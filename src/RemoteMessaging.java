import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoteMessaging extends UnicastRemoteObject implements MessagingInt  {

    public RemoteMessaging() throws RemoteException { super(); }

    List<Account> Accounts = new ArrayList<>() {};
    List<Message> Messages = new ArrayList<>() {};

    //1
    public String CreateAccount(String username) throws RemoteException{
        //checking if username is valid
        Pattern p = Pattern.compile("[!'@#$%&*()_+=|<>?{}\\[\\]~/]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(username);
        boolean b = m.find();
        if (b)  return "Invalid Username";
        //checking if username already exists
        for (Account ac : Accounts) {
            if (username.equals(ac.getUsername()))
                return "Sorry, the user already exists";
        }
        //getting a token and adding the new account
        int token;
        if (Accounts.size()==0) token=1024;
        else token=Accounts.size()+1024;
        Account a = new Account(username, token);
        Accounts.add(a);
        return Integer.toString(token);
    }

    //2
    public List<String> ShowAccounts(int authToken) throws RemoteException{
        List<String> Result = new ArrayList<>();
        //finding if authToken exists
        int acc = TokenExists(authToken);
        if (acc == -1) {
            Result.add("Invalid Auth Token");
            return Result;
        }
        //making a list of "n. <username_1>"
        String s;
        for (int i=0; i<Accounts.size(); i++){
            s = i+1+". "+Accounts.get(i).getUsername();
            Result.add(s);
        }
        return Result;
    }

    //3
    public String SendMessage(int authToken, String recipient, String message_body) throws RemoteException{
        //finding if authToken exists
        int sender = TokenExists(authToken);
        if (sender == -1) return "Invalid Auth Token";
        //searching for the sender
        for (Account a : Accounts){
            if (recipient.equals(a.getUsername())){
                int message_id;
                if (Messages.size()==0)
                    message_id = 1;
                else
                    message_id=(Messages.get(Messages.size() - 1).getMessage_id())+1;
                Message m = new Message(false, message_id, Accounts.get(sender).getUsername(), recipient, message_body);
                a.getMessageBox().add(m);
                Messages.add(m);
                return "OK";
            }
        }
        return "User does not exist";
    }

    //4
    public List<String> ShowInbox(int authToken) throws RemoteException{
        List<String> Result = new ArrayList<>();
        //finding if authToken exists
        int acc = TokenExists(authToken);
        if (acc == -1) {
            Result.add("Invalid Auth Token");
            return Result;
        }
        //making a list of "<message_id_1>. from:<username_x> *?"
        String s;
        List<Message> current_mes = Accounts.get(acc).getMessageBox();
        for (Message m : current_mes) {
            s = m.getMessage_id() + ". from: " + m.getSender();
            if (!(m.getIsRead()))
                s = s + "*";
            Result.add(s);
        }
        return Result;
    }

    //5
    public String ReadMessage(int authToken, int message_id) throws RemoteException{
        //finding if authToken exists
        int sender = TokenExists(authToken);
        if (sender == -1) return "Invalid Auth Token";
        //find message id
        for (Message m: Accounts.get(sender).messageBox ){
            if (message_id==m.message_id){
                m.setIsRead();
                return ("("+m.getSender()+")"+m.getBody());
            }
        }
        return "Message ID does not exist";
    }

    //6
    public String DeleteMessage(int authToken, int message_id) throws RemoteException{
        int user = TokenExists(authToken);
        if (user == -1) return "Invalid Auth Token";
        //find message id
        if (Accounts.get(user).messageBox.size()>0)
            for (Message m: Accounts.get(user).messageBox ) {
                if (message_id == m.message_id) {
                    Accounts.get(user).messageBox.remove(m);
                    Messages.remove(m);
                    return "OK";
                }
            }
        return "Message does not exist";
    }

    //checking AuthToken's existence
    public int TokenExists(int authToken) throws RemoteException{
        for (int i=0; i<Accounts.size(); i++) {
            if (authToken == Accounts.get(i).getAuthToken()) {
                return i;
            }
        }
        return -1;
    }

}