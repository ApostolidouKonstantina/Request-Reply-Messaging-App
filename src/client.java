import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class client {

    //java client <ip> <port number> 1 <username>
    //java client <ip> <port number> 2 <authToken>
    //java client <ip> <port number> 3 <authToken> <recipient> <message_body>
    //java client <ip> <port number> 4 <authToken>
    //java client <ip> <port number> 5 <authToken> <message_id>
    //java client <ip> <port number> 6 <authToken> <message_id>

    public static void main(String[] args)  {
        try {
            String ip = args[0];
            int port_number = Integer.parseInt(args[1]);
            int action = Integer.parseInt(args[2]);
            // connect to the RMI registry
            Registry rmiRegistry = LocateRegistry.getRegistry(port_number);
            // get reference for remote object
            MessagingInt stub = (MessagingInt) rmiRegistry.lookup("messaging");

            if (action==1) {
                String username = args[3];
                System.out.println(stub.CreateAccount(username));
            }
            else if(action==2)  {
                int authToken = Integer.parseInt(args[3]);
                List<String> res = stub.ShowAccounts(authToken);
                for (String r: res)
                    System.out.println(r);
            }
            else if(action==3) {
                int authToken = Integer.parseInt(args[3]);
                String recipient = args[4];
                String message_body = args[5];
                System.out.println(stub.SendMessage(authToken,recipient,message_body));
            }
            else if(action==4){
                int authToken = Integer.parseInt(args[3]);
                List<String> res = stub.ShowInbox(authToken);
                for (String r: res)
                    System.out.println(r);
            }
            else if(action==5){
                int authToken = Integer.parseInt(args[3]);
                int message_id = Integer.parseInt(args[4]);
                System.out.println(stub.ReadMessage(authToken, message_id));
            }
            else if(action==6) {
                int authToken = Integer.parseInt(args[3]);
                int message_id = Integer.parseInt(args[4]);
                System.out.println(stub.DeleteMessage(authToken, message_id));
            }
        } catch (Exception e) { System.out.println(e); }
    }
}
