public class Message implements java.io.Serializable{

    boolean isRead;
    int message_id;
    String sender;
    String receiver;
    String body;

    public Message(boolean isRead, int message_id, String sender, String receiver, String body){
        this.isRead=isRead;
        this.message_id=message_id;
        this.sender=sender;
        this.receiver=receiver;
        this.body=body;
    }
    void setIsRead() {this.isRead= true;}
    boolean getIsRead() {return this.isRead;}
    String getSender() {return this.sender;}
    String getBody() {return this.body;}
    int getMessage_id() {return this.message_id;}

}
