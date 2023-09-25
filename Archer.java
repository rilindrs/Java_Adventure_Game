public class Archer extends Character
{
    
    private static final int BASE_STRENGTH = 5;
    private static final int BASE_DEX = 12;
    private static final int BASE_INTELLECT = 7;
    
    public Archer(String name){
        super(name, "D:\\Document Folders\\AdventureGame\\assets\\archertiny.png");
    }
    
    public void setStats(){
        setIntellect(BASE_INTELLECT + (getLevel() * 2));
        setDexterity(BASE_DEX + (getLevel() * 4));
        setStrength(BASE_STRENGTH + (getLevel() * 1));
    }
    
    public void setAbilities(){
        getAbilities().clear();
        getAbilities().add(new Ability("Arrow", 1, 5, "dex"));
        getAbilities().add(new Ability("Rapid Fire", 2, 10, "dex"));
        getAbilities().add(new Ability("Poison Arrow", 4, 15, "dex"));
        getAbilities().add(new Ability("Rain of Arrows", 7, 25, "dex"));
    }
    
    public String getClassName(){
        return "Archer";
    }
}