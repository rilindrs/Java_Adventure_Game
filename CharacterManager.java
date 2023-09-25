import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//This class has convenience methods for accessing the current character of the user.
//It is also in charge of save file management.
public class CharacterManager
{
   //A static reference to the current character so that it can be accessed throughout the whole application
   private static Character currentCharacter;
   //The filepath to save and load data from.
   private static final String FILENAME = "characterdata.txt";
   
   //public getter for the static Character object
   public static Character getCurrentCharacter(){
       return currentCharacter;
   }
   
   //Instantiates the current Character to type Mage, Archer or Warrior, sets its name using the constructer.
   public static void createCharacter(String selectedClass, String selectedName){
        switch(selectedClass){
          case "Mage":
            currentCharacter = new Mage(selectedName);
            break;
          case "Warrior":
            currentCharacter = new Warrior(selectedName);
            break;
          case "Archer":
            currentCharacter = new Archer(selectedName);
            break;
       }
       
       //The character is initially level 0, so level it up to 1.
       currentCharacter.levelUp();
       
       System.out.println("NEW CHARACTER CREATED WITH NAME : " + currentCharacter.getName());
   }
   
   //Saves a comma seperated String of values that represenets the current player character.
   public static void saveProgress(){
     
       if(currentCharacter != null){
          
           System.out.println("TRYING TO SAVE PROGRESS");
           
           try{
               PrintWriter out = new PrintWriter(FILENAME);
               //The toString method for the Character class will return the comma seperated values we need
               //To generate the character from the file later on.
               out.print(currentCharacter.toString());
               out.close();
           }catch(Exception e){
               System.out.println("PROBLEM WRITING TO FILE");
               
           }
       }
   }
   
   //Tries to load save data if it exists. Returns true if data was succesfully loaded.
   //Also Instantiates and sets the Character using the contents of the file.
   public static boolean getSaveData(){
       try{
          
           String allFileData = new String(Files.readAllBytes(Paths.get(FILENAME, "")));
           String[] parts = allFileData.split(",");
           createCharacter(parts[1], parts[0]);
           //The method call to setLevel will automatically set this Characters stats and abilities.
           getCurrentCharacter().setLevel(Integer.parseInt(parts[3]));
           //Restore the state of the HP when we saved.
           getCurrentCharacter().setCurrentHp(Integer.parseInt(parts[2]));
           return true;
       }catch(Exception e){
           System.out.println("PROBLEM READING FROM FILE");
       }
       //Returns false if something goes wrong.
       return false;
   }
   
   //Removes the file and sets the current Character to null.
   public static void deleteSaveData(){
       try{
           File characterFile = new File(FILENAME);
           characterFile.delete();
           currentCharacter = null;
       }catch(Exception e){
           System.out.println("PROLBLEM DELETING FILE");
       }
   }
}
