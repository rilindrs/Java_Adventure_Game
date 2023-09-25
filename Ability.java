
public class Ability
{
   private String name;
   private int levelRequirement;
   private int basePower;
   private String usedStat;
   //This constructor instantiates the ability vairables that all character in the game will have
   public Ability(String nname, int req, int bp, String us){
       name = nname;
       levelRequirement = req;
       basePower = bp;
       usedStat = us;
   }
   
   public String getName(){
       return name;
    }
   
   public int getLevelRequirement(){
       return levelRequirement;
    }
   
   public int getBasePower(){
       return basePower;
    }
   
   public String getUsedStat(){
       return usedStat;
    }
   
   public String toString(){
       return name;
    }
}
