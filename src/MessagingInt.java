import java.rmi.*;
import java.util.List;

public interface MessagingInt extends Remote {
    //1
    String CreateAccount(String username) throws RemoteException;
    //2
    List<String> ShowAccounts(int authToken) throws RemoteException;
    //3
    String SendMessage(int authToken, String recipient, String message_body) throws RemoteException;
    //4
    List<String> ShowInbox(int authToken) throws RemoteException;
    //5
    String ReadMessage(int authToken, int message_id) throws RemoteException;
    //6
    String DeleteMessage(int authToken, int message_id) throws RemoteException;
}