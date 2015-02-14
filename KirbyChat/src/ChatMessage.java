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