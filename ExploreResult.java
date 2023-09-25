
public abstract class ExploreResult
{ 
   private String info;
   private String imagePath;
   private String confirmCommand;
   private String cancelCommand;
   private int type;
   //Initialisation of instance variables
   public ExploreResult(String i, String ip, String confirm, String cancel){
       info = i;
       imagePath = ip;
       confirmCommand = confirm;
       cancelCommand = cancel;
   }
   //Getters and setters for each variable
   public String getInfo(){
       return info;
    }
   
   public void setInfo(String i){
       info = i;
    }
   
   public String getImagePath(){
       return imagePath;
    }
   
   public void setImagePath(String i){
       imagePath = i;
    }
   
   public String getConfirmCommand(){
       return confirmCommand;
    }
   
   public void setConfirmCommand(String i){
       confirmCommand = i;
    }
   
   public String getCancelCommand(){
       return cancelCommand;
    }
   
   public void setCancelCommand(String i){
       cancelCommand = i;
    }
}
