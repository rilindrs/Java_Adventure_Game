import java.util.*;
public abstract class Enemy extends Character
{
    String smallImagePath;
    
    public Enemy(String nname, String iimage, String simage){
        super(nname,iimage);
        smallImagePath = simage;
    }
    
    public String getSmallImage(){
        return smallImagePath;
    }
    //This method creates a random object and returns a random ability that a subclass of enemy will contain
    public Ability getRandomAbility(){
        Random rand = new Random();
        return getAbilities().get(rand.nextInt(getAbilities().size()));
    }
    
    public void setEnemyExperience(){
        setExperience(getLevel() * getBaseExperience());
    }
    
    public abstract int getBaseExperience();
}
