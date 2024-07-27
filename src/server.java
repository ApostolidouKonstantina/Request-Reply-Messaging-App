import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class server {
    public static void main(String[] args) {
        try {
            RemoteMessaging stub = new RemoteMessaging();
            // create the RMI registry on port
            int port_number = Integer.parseInt(args[0]);
            Registry rmiRegistry = LocateRegistry.createRegistry(port_number);
            rmiRegistry.rebind("messaging", stub);
            System.out.println("Server start");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}