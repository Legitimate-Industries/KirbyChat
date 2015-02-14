
import java.io.File;
import java.net.DatagramSocket;
import java.sql.Timestamp;
import java.util.*;

/**
 *
 * @author Nathan Dias <nathanxyzdias@gmail.com>
 */
public class KirbyServer implements Runnable{
    
    int serverlisten,clientlisten;
    DatagramSocket listenFromClient,sendToClient;
    //List<User> users = new ArrayList<>();
    Map<String,User> map = new HashMap<>();
    Map<String,ChatRoom> map2 = new HashMap<>();
    
    private boolean wannaRun=true;
    
    public KirbyServer(int sl,int cl)throws Exception{
        serverlisten=sl;
        clientlisten=cl;
        listenFromClient = new DatagramSocket(sl);
        sendToClient=new DatagramSocket();
        
    }
    
    public KirbyServer(File f,File g) throws Exception{
        Scanner yo = new Scanner(f);
        serverlisten=Integer.parseInt(yo.nextLine());
        clientlisten=Integer.parseInt(yo.nextLine());
        
        while(yo.hasNextLine()){
            User toAdd = new User(yo.nextLine(),yo.nextLine(),Integer.parseInt(yo.nextLine()));
            map.put(toAdd.username,toAdd);
            //users.add(new User(yo.nextLine(),yo.nextLine(),Integer.parseInt(yo.nextLine())));
            //map.put(users.get(users.size()-1).username,users.get(users.size()-1));
        }
        
        
    }
    
    
    
    @Override
    public void run(){
        while(wannaRun){
            
            
            
        }
        
        
    }
    
    public void stop(){
        wannaRun=false;
        
        
    }
    
    
    public static Timestamp stamp(){
        return new Timestamp(new Date().getTime());
    }
    
    public static String date(){
        Timestamp stamp = stamp();
        String s = stamp.toLocaleString();
        return s;
    }
    
    
    public static void main(String args[])throws Exception{
        
        
    }
    
    
    
    
    
}
class User{//<editor-fold>
    String username,passwordHash;
    int accesslevel;
    transient String ip;
    boolean online=false;
    
    public static int USER=0,ADMIN=1;
    
    public static String toHash(String toHash){
        String s;
        char[] arr = new char[10];
        for(int x=0;x<toHash.length();x++){
            arr[x%10]=(char) (toHash.charAt(x)+(char)5);
            for(int y=9;y>=0;y--){
                arr[y]+=(int)(Math.sin(arr[x%10])*arr[x%10]);
                arr[y]%=26;
            }
            arr[x%10]%=26;
            
        }
        for(int a=0;a<arr.length;a++){
            arr[a]+='A';
        }
        s=new String(arr);
        return s;
    }
    
    
    public User(String username,String passwordHash,int accesslevel){
        this.username=username;
        this.passwordHash=passwordHash;
        this.accesslevel=accesslevel;
    }
    
    public boolean login(String ip,String passwordHash){
        if(online||!passwordHash.equals(this.passwordHash))
            return false;
        this.ip=ip;
        return online=true;
    }
    
    public boolean logoff(){
        if(!online)
            return false;
        ip="";
        return !(online=false);
    }
}//</editor-fold>

class ChatRoom implements Runnable{
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

class ChatMessage{
    String timestamp;
    String sender;
    String message;
    public ChatMessage(String ts,String sender,String msg){
        timestamp=ts;
        this.sender=sender;
        message=msg;
    }
    
    public ChatMessage(String source){
        String[] split = source.split("::");
        timestamp=split[0];
        sender=split[1];
        for(int x=2;x<split.length;x++)
            message+=split[x];
    }
    
    public String encrypted(String hash){
        String s = "";
        String y = toString();
        for(int x=0;x<y.length();x++){
            s+=y.charAt(x)+hash.charAt(x%hash.length());
        }
        return s;
    }
    
    public static String decrypted(String hash,String key){
        String s = "";
        for(int x=0;x<hash.length();x++){
            s+=hash.charAt(x)-key.charAt(x%key.length());
        }
        return s;
    }
    
    @Override
    public String toString(){
        return timestamp+"::"+sender+"::"+message;
    }
}