1. Account: every account has a username, a token και a list of messages.
2. Message: every message contains a message_id, a sender, a receiver, the body of the text and a boolean variable that shows if the message was read or not.
3. MessagingInt is the common interface client/server file.
4. RemoteMessaging: Development of the servers Interface. \
a) Create Account: receives a username that the user has requested and checks if it contains not valid characters and if it already exists. If everything is valid, it saves a unique token with the new account. In the end, it returns the token or an error message.\
b) Show Accounts: checks the recieving token is valid and returns a list with every account's usernames or an error message if the token is invalid\
c) Send Message: checks the receiving token of the message receiver, creates a message_id and adds the message to the receiver's messages list and also the server's setting it to unread.\
d) Show Inbox: creates the user's inbox in (<message_id>. from:<username> *read?) format\
e) ReadMessage: returns the body of the text with the receiving message_id or an error message\
f) DeleteMessage: deletes the message with the receiving message_id\
6. server: development of the Server. Asks for Port number.
7. client: development of the Client. Asks for action and calls the appropriate function.
