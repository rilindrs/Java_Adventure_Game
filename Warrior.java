public class Warrior extends Character
{
    
    private static final int BASE_STRENGTH = 12;
    private static final int BASE_DEX = 7;
    private static final int BASE_INTELLECT = 5;
    
    public Warrior(String name){
        super(name, "D:\\Document Folders\\AdventureGame\\assets\\warriortiny.png");
    }
    
    public void setStats(){
        setIntellect(BASE_INTELLECT + (getLevel() * 1));
        setDexterity(BASE_DEX + (getLevel() * 2));
        setStrength(BASE_STRENGTH + (getLevel() * 4));
    }
    public void setAbilities(){
        getAbilities().clear();
        getAbilities().add(new Ability("Sword Swing", 1, 5, "str"));
        getAbilities().add(new Ability("Power Charge", 2, 10, "str"));
        getAbilities().add(new Ability("Whirlwind", 4, 15, "str"));
        getAbilities().add(new Ability("Blade Storm", 7, 25, "str"));
    }
    
    public String getClassName(){
        return "Warrior";
    }
}