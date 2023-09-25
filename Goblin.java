
public class Goblin extends Enemy
{   
    //Declaring and initialising the base stats for the goblin
    private static final int BASE_STRENGTH = 5;
    private static final int BASE_DEX = 3;
    private static final int BASE_INTELLECT = 1;
    private static final int BASE_EXP = 5;
    //Referencing the super class in order to pass in two images
    public Goblin(){
       super("Goblin","D:\\Document Folders\\AdventureGame\\assets\\smallgoblin.png", "D:\\Document Folders\\AdventureGame\\assets\\tinygoblin.png");
       levelUp();
       setEnemyExperience();
    }
    
    public Goblin(int level){
       super("Goblin","D:\\Document Folders\\AdventureGame\\assets\\smallgoblin.png", "D:\\Document Folders\\AdventureGame\\assets\\tinygoblin.png");
       
       setLevel(level);
       setStats();
       setAbilities();
       setMaxHp(getLevel() * 100);
       setCurrentHp(getMaxHp());
       setEnemyExperience();
       
    }
    
    public void setStats(){
        setIntellect(BASE_INTELLECT + (getLevel() * 2));
        setDexterity(BASE_DEX + (getLevel() * 2));
        setStrength(BASE_STRENGTH + (getLevel() * 3));
    }
    
    public void setAbilities(){
        getAbilities().clear();
        getAbilities().add(new Ability("Pummel", 1, 5, "str"));
        getAbilities().add(new Ability("Gobsmack", 1, 7, "str"));
    }
    
    public String getClassName(){
        return "Goblin";
    }
    
    public int getBaseExperience(){
        return BASE_EXP;
    }
}
