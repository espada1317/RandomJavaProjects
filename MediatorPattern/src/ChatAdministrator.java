public class ChatAdministrator extends User{

    public ChatAdministrator(ChatRoomInterface room, String id, String name) {
        super(room, id, name);
    }

    @Override
    public void send(String msg, String userId) {
        System.out.println(this.getName() + " \t\t\t:: Sending Message :\t " + msg);

        if(getId() == userId)
        {
            for(String key: ChatRoom.getUsersMap().keySet()) {
                if(key != userId)
                {
                    getMediator().sendMessage(msg, key);
                }
            }
        } else {
            getMediator().sendMessage(msg, userId);
        }
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.getName() + " \t\t\t:: Received Message :\t " + msg);
    }
}
