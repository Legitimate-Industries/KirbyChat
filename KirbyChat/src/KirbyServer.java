
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
        yo = new Scanner(g);
        while(yo.hasNextLine()){
            
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
        System.out.println(User.toHash("kirby"));
        System.out.println(User.toHash("kirbx"));
        System.out.println(User.toHash("sirby"));
        System.out.println(User.toHash("neesama"));
        System.out.println(User.toHash("asdsdfw jfqi ofiqo"));
        
    }
    
    
    
    
    
}
