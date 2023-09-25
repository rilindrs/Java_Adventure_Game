
import java.util.*;
//Class is made abstract so an instance of this class cannot be created. the subclasses can be instantiated
//Describes a Character object that can gain experience, level up, battle, takeDamage etc
public abstract class Character
{   
    //the experience requirements for each level, eg. level 4 requires 80 exp to reach
    private static final int[] EXPERIENCE_FOR_LEVELS = {0, 10, 35, 80, 120, 200, 350};
    
    private String name;
    private String image;
    private int level;
    private int strength;
    private int intellect;
    private int dexterity;
    private int experience;
    
    private int maxHp;
    private int currentHp;
    //Declaration of arrayList abilities which enables additional abilities to be added without having  to change the size of the array
    private ArrayList<Ability> abilities;
    
    public Character(String nname, String iimage){
        name = nname;
        image = iimage;
        abilities = new ArrayList<>();
    }
    
    //Increases the level by 1, and also sets the stats, abilities and maxHP based on that level
    public void levelUp(){
        level++;
        setStats();
        setAbilities();
        setMaxHp(getLevel() * 100);
        setCurrentHp(maxHp);
    }
    
    //Adds the amount of experience an enemy holds to this character's experience
    public boolean gainExperience(Enemy e){
        int levelBefore = getLevel();
        experience += e.getExperience();
        
        //Check to see if we are max level.
        if(getExperience() >= EXPERIENCE_FOR_LEVELS[EXPERIENCE_FOR_LEVELS.length-1]){
            setLevel(EXPERIENCE_FOR_LEVELS.length);
        }
        
        //Check to see if we have advanced to the next level
        for(int i = 0; i < EXPERIENCE_FOR_LEVELS.length; i++){
            if(getExperience() < EXPERIENCE_FOR_LEVELS[i]){
                setLevel(i);
                break;
            }
        }
        
        
        setStats();
        setAbilities();
        setMaxHp(getLevel() * 100);
        
        int levelAfter = getLevel();
        
        //Returns true if there was a level up or false otherwise
        if(levelAfter != levelBefore)
            return true;
        else return false;
        //setCurrentHp(maxHp);
    }
    
    //Calculates the amount of damage this Character takes based on this formula:
    //Total Damage Taken = ability base power + (effected stat base power (Class dependent) + (level of the
    //current Character * specific class multiplyer)
    //EG. An Enemy Goblin takes damage from a Level 3 Mage's Fireball
    //Total Damage Taken = 5 + (12 + (3 * 4)) = 29
    public int takeDamage(Character fromWho, Ability usedAbility){
        int damageTaken = usedAbility.getBasePower();
        switch(usedAbility.getUsedStat()){
            case "str":
                damageTaken += fromWho.getStrength();
            break;
            case  "dex":
                damageTaken += fromWho.getDexterity();
            break;
            case "int":
                damageTaken += fromWho.getIntellect();
            break;
        }
        currentHp -= damageTaken;
        //Does not allow for currentHp to be below 0
        if(currentHp < 0)
            currentHp = 0;
        
        //Return the damage dealt to player
        return damageTaken;
    }
    
    //Must be overriden by the subclass
    public abstract void setStats();
    //Must be overriden by the subclass
    public abstract void setAbilities();
    //Must be overriden by the subclass
    public abstract String getClassName();
    
    
    public int getLevel(){
        return level;
    }
    public void setLevel(int l){
        level = l;
        setStats();
        setAbilities();
        setMaxHp(getLevel() * 100);
    }
    
    public int getStrength(){
        return strength;
    }
    public void setStrength(int s){
        strength = s;
    }
    
    public int getIntellect(){
        return intellect;
    }
    public void setIntellect(int i){
        intellect = i;
    }
    
    public int getDexterity(){
        return dexterity;
    }
    public void setDexterity(int d){
        dexterity = d;
    }
    
    public ArrayList<Ability> getAbilities(){
        return abilities;
    }
    
    public int getExperience(){
        return experience;
    }
    
    public void setExperience(int e){
        experience = e;
    }
    
    public String getName(){
        return name;
    }
    
    public String getImage(){
        return image;
    }
    
    public int getMaxHp(){
        return maxHp;
    }
    
    public void setMaxHp(int h){
        maxHp = h;
    }
    
    public int getCurrentHp(){
        return currentHp;
    }
    
    public void setCurrentHp(int h){
        currentHp = h;
    }
    
    //Used in saving comma seperated values to the file
    @Override
    public String toString(){
        return getName() + "," + getClassName() + "," + getCurrentHp() + "," + getLevel();
    }
    
    // public String toStringLong(){
        // return "name: " +getName() + "\nlevel: " + getLevel() + "\nclass: " + getClassName() + "\ncurrentHp: " + getCurrentHp() + "\nmaxHp: " + getMaxHp() + "\nStr: " + getStrength() + "\nInt: " + getIntellect() +  "\nDex: " + getDexterity() + "\nAbilities: " + getAbilities();
    // }
    
}
