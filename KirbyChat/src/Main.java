
import java.io.File;
import java.util.Scanner;


/**
 *
 * @author Nathan Dias <nathanxyzdias@gmail.com>
 */
public class Main {
    
    public static void main(String[] args)throws Exception{
        KirbyServer k;
        Scanner yo = new Scanner(System.in);
        System.out.print("Old or new server: ");
      if(yo.nextLine().equalsIgnoreCase("old")){
          k=new KirbyServer(new File("userdata"),
                            new File("roomdata"));
      }  
      else{
          System.out.print("Server receive and send ports: ");
          k=new KirbyServer(yo.nextInt(),yo.nextInt());
      }
      Runtime.getRuntime().addShutdownHook(new Thread(()->{k.stop();}));
      new Thread(k).start();
    }
}
