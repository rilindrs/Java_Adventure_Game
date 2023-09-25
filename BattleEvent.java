
public class BattleEvent extends ExploreResult
{
   private Enemy enemy;
   //Referencing the superclass and instatiating the variables from the constructor of this class
   public BattleEvent(String i, String ip, String confirm, String cancel){
       super(i, ip, confirm, cancel);
   }
   //Instantiaing the four variables with values for the creation of the Explore Result
   public BattleEvent(Enemy e){
       super("Whilst travelling the rocky turrain, you come across a Level " + e.getLevel() + " " + e.getName() + ", what would you like to do?",e.getSmallImage(),"Start Battle", "Run Away");
       enemy = e;
    }
   
   public void setEnemy(Enemy e){
       enemy = e;
    }
   
   public Enemy getEnemy(){
       return enemy;
    }
}
