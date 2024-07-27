import java.util.ArrayList;
import java.util.List;

public class Account implements java.io.Serializable {

    String username;
    int authToken;
    List<Message> messageBox;

    public Account(String username, int authToken){
        this.username=username;
        this.authToken=authToken;
        this.messageBox = new ArrayList<>();
    }
    String getUsername() {return this.username;}
    int getAuthToken() {return this.authToken;}
    List<Message> getMessageBox() {return this.messageBox;}

}
