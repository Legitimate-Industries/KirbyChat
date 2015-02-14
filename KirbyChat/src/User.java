class User{
    String username,passwordHash;
    int accesslevel;
    transient String ip;
    boolean online=false;
    
    public static int USER=0,ADMIN=1;
    
    public static String toHash(String toHash){
        String s;
        char[] arr = new char[10];
        for(int x=0;x<arr.length;x++){
            arr[x]=(char)((Math.sin(x%2)*x)%26);
        }
        
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
}