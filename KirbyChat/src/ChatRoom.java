
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Nathan Dias <nathanxyzdias@gmail.com>
 */
public class ChatRoom implements Runnable{
    String name,passwordHash;
    List<User> myParticipants=new ArrayList<>();
    List<ChatMessage> messages = new ArrayList<>();
    
    private boolean wannaRun=true;
    //KirbyServer master;
    
    public ChatRoom(String name,String passwordHash,String... messages){
        this.name=name;
        this.passwordHash=passwordHash;
        if(messages.length!=0){
            for(String s:messages)
                this.messages.add(new ChatMessage(s));
        }
        //this.master=master;
        
    }
    
    @Override
    public void run(){
        while(wannaRun){
            
        }
    }
    
    public void stop(){
        
        wannaRun=false;
    }
    

}
