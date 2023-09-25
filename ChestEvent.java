
public class ChestEvent extends ExploreResult
{
  public ChestEvent(String i, String ip, String confirm, String cancel){
       super(i, ip, confirm, cancel);
  }
  ////Instantiaing the four variables with values for the creation of the Explore Result
  public ChestEvent(){
    super("You see something glimmering in the distance, as you approach it, you realise its a chest full of treasure!", "D:\\Document Folders\\AdventureGame\\assets\\tinychest.png", "Open Chest", "Do Nothing");
  }
}
