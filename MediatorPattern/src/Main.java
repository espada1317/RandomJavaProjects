public class Main {
    public static void main(String[] args)
    {
        ChatRoomInterface chatroom = new ChatRoom();

        User user1 = new ChatUser(chatroom,"1", "Alex");
        User user2 = new ChatUser(chatroom,"2", "Brian");
        User user3 = new ChatUser(chatroom,"3", "Charles");
        User user4 = new ChatUser(chatroom,"4", "David");
        User administrator = new ChatAdministrator(chatroom, "5", "God");

        chatroom.addUser(user1);
        chatroom.addUser(user2);
        chatroom.addUser(user3);
        chatroom.addUser(user4);
        chatroom.addUser(administrator);

        user1.send("Hello brian", "2");
        user2.send("Hey buddy", "1");
        administrator.send("hello friend","1");
        administrator.send("bolishoi brat peredaiot privet!","5");
    }
}
