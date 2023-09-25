import java.util.*;
public class Mage extends Character
{
    
    private static final int BASE_STRENGTH = 5;
    private static final int BASE_DEX = 7;
    private static final int BASE_INTELLECT = 12;
    
    public Mage(String name){
        super(name, "D:\\Document Folders\\AdventureGame\\assets\\magetiny.png");
    }
    
    public void setStats(){
        setIntellect(BASE_INTELLECT + (getLevel() * 4));
        setDexterity(BASE_DEX + (getLevel() * 2));
        setStrength(BASE_STRENGTH + (getLevel() * 1));
    }
    
    public void setAbilities(){
        getAbilities().clear();
        getAbilities().add(new Ability("Fireball", 1, 5, "int"));
        getAbilities().add(new Ability("Icicle", 2, 10, "int"));
        getAbilities().add(new Ability("Mind Melt", 4, 15, "int"));
        getAbilities().add(new Ability("Meteor", 7, 25, "int"));
    }
    
     public String getClassName(){
        return "Mage";
    }
}
