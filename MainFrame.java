import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//This is the starting class for the game. It contains all game panels and methods that switch between them
public class MainFrame extends JFrame{
   
   private ClassSelectionPanel mainPanel;
   private ClassDescriptionPanel magePanel, warriorPanel, archerPanel;
   private PlayLocationPanel gamePanel;
    
   //static final variables to easily change frame dimensions
   public static final int SCREEN_WIDTH = 750;
   public static final int SCREEN_HEIGHT = 500;
   
   //Constructor initialises this frame and checks for previous save data
   public MainFrame(){
    super("Adventure Game");
    setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
    setLayout(null);
    setResizable(false);
    setVisible(true);
   
    boolean didLoadCharacter = CharacterManager.getSaveData();
    
    //If previous data is found, ask the user if they would like to continue
    //If not, delete that data and start game as normal
    if(didLoadCharacter){
        int selectedOption = JOptionPane.showConfirmDialog(this, "Load from file", "Previous Save Data Found!", JOptionPane.YES_NO_OPTION);
        System.out.println(selectedOption);
        
        if(selectedOption == 0){//'Yes' is selected - the page returns to the playLocationPanel
            gamePanel = new PlayLocationPanel(this, "Hope Hills", "D:\\Document Folders\\AdventureGame\\assets\\bgimage.jpg");
            add(gamePanel);
            repaint();
        }else{
           CharacterManager.deleteSaveData();
           System.out.println("SHOWING CLASS SELECTION PANEL");
           
           mainPanel = new ClassSelectionPanel(this);
           add(mainPanel);
        }
    }else{ //File is not found - game begins as usual
        mainPanel = new ClassSelectionPanel(this);
        add(mainPanel);
    }
    

    //Save game progress whenever the window is closed
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        CharacterManager.saveProgress();
        System.exit(0);
      }
    });
    setVisible(true);
    
   }
   
   //initialises and shows the mage description panel
   public void showMageDescription(){
        magePanel = new ClassDescriptionPanel(this,
                                                 "Mage", 
                                                 "D:\\Document Folders\\AdventureGame\\assets\\mageimagesmall.png", 
                                                  "Meet the enigmatic Mage, a master of arcane arts and mystical spells. With a deep connection to the ethereal realms, the Mage harnesses the power of elements to shape the destiny of our quest. Armed with wisdom and ancient knowledge, this spellcaster brings magic to life, casting spells that can both heal and destroy. Beware, for the Mage's powers are as unpredictable as they are awe-inspiring.");
        add(magePanel);
        repaint();
        mainPanel.setVisible(false);
   }
   
   //initialises and shows the warrior description panel
   public void showWarriorDescription(){
       warriorPanel = new ClassDescriptionPanel(this, 
                                                 "Warrior", 
                                                 "D:\\Document Folders\\AdventureGame\\assets\\warriorhatsmall.png", 
                                                  "In the heart of battle, the indomitable Warrior stands as a bulwark of courage and strength. Clad in armor that bears the scars of countless conflicts, this steadfast protector wields a mighty sword with unparalleled skill. Fearless and unyielding, the Warrior charges into the fray, defending the group with unwavering loyalty. When the going gets tough, trust in the Warrior to lead the way.");
       add(warriorPanel);
       repaint();
       mainPanel.setVisible(false);
   }
   
   //initialises and shows the archer description panel
   public void showArcherDescription(){
       archerPanel = new ClassDescriptionPanel(this, 
                                                 "Archer", 
                                                 "D:\\Document Folders\\AdventureGame\\assets\\archerhatsmall.png", 
                                                  "Meet the elusive Archer, a nimble and silent sentinel of the shadows. With a keen eye and a steady hand, this marksman excels at long-range combat. Master of the bow and arrows, the Archer strikes with precision, picking off foes from afar before they even know what hit them. In the wilderness and urban jungles alike, the Archer's swift and deadly accuracy makes them an invaluable asset to the party.");

       add(archerPanel);
       repaint();
       mainPanel.setVisible(false);
   }
   
   //Makes any currently visible panel invisible, and then makes the class selection panel visible.
   public void backToHome(){
        if(magePanel != null && magePanel.isVisible())
          magePanel.setVisible(false);
        if(warriorPanel != null && warriorPanel.isVisible())
           warriorPanel.setVisible(false);
        if(archerPanel != null && archerPanel.isVisible())
            archerPanel.setVisible(false);
        if(gamePanel != null && gamePanel.isVisible())
            gamePanel.setVisible(false);
           
        mainPanel.setVisible(true);
        repaint();
   }
   
   //Makes any currently visible panel invisible, and then makes the class selection panel visible.
   //Will also clear the text in the nameinput text field.
   public void resetGame(){
        if(magePanel != null && magePanel.isVisible())
            magePanel.setVisible(false);
        if(warriorPanel != null && warriorPanel.isVisible())
            warriorPanel.setVisible(false);
        if(archerPanel != null && archerPanel.isVisible())
            archerPanel.setVisible(false);
        if(gamePanel != null && gamePanel.isVisible())
            gamePanel.setVisible(false);
           
        mainPanel.setVisible(true);
        mainPanel.clearNameInput();
        repaint();
   }
   
   //Creates a new character object for the user, and displays a new PlayLocationPanel
  public void startGame(String selectedClass){
       if(magePanel != null && magePanel.isVisible()){
           magePanel.setVisible(false);
       }else if(warriorPanel != null && warriorPanel.isVisible()){
           warriorPanel.setVisible(false);
       }else{
           archerPanel.setVisible(false);
       }
      
       CharacterManager.createCharacter(selectedClass, mainPanel.getCharacterName());
       
       gamePanel = new PlayLocationPanel(this, "Hope Hills", "D:\\Document Folders\\AdventureGame\\assets\\bgimage.jpg");
       add(gamePanel);
       repaint();
       
       
       
   }
   
  public static void main (String[]args){
       new MainFrame();
   }
}


