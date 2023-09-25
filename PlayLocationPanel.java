import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//Describes a JPanel in which the character can 'Explore' a region for a battle or chest event to appear
public class PlayLocationPanel extends JPanel implements ActionListener{
    JLabel gameLocation, playerHealth, healthTitle, characterName, exploreInfo, enemyType, enemyHealth, combatLogInfo, combatLogTitle, strengthLabel, dexterityLabel, intellectLabel, levelLabel;
    ImagePanel gameBackground, classImage, exploreImage, enemyImage;
    JPanel gameInfo, healthContainer, abilitiesContainer,actionsPanel, actionsContainer, explorePanel, abilitiesPanel, combatLogContainer;
    JButton travelButton, a2, a3, exploreAction1, exploreAction2, backToExploreButton;
    //Keep track of the latest explore event encounterd
    ExploreResult currentExploreResult;
    //Keep a refernece to the MainFrame for accessing its methods
    MainFrame parent;
    
    public PlayLocationPanel(MainFrame parentFrame, String location, String background){
        parent = parentFrame;
         
        setLayout(null);
        setSize(MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
      
        gameLocation = new JLabel (location, SwingConstants.CENTER);
        gameLocation.setSize(150, 50);
        gameLocation.setFont(gameLocation.getFont().deriveFont(24.0f));
        gameLocation.setLocation(600, 0);
        gameLocation.setForeground(Color.decode("#406159"));
        add(gameLocation);
        
        gameBackground = new ImagePanel(background);
        gameBackground.setSize(750, 510);
        gameBackground.setLocation(0,0); 
        
        gameInfo = new JPanel (new GridLayout(0, 2, 0, 25));
        gameInfo.setSize(150, 275);
        gameInfo.setLocation(590,80);
        //gameInfo.setBackground(Color.decode("#101010"));
        gameInfo.setForeground(Color.decode("#FEFEFE"));
        gameInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(gameInfo);

        enemyImage = new ImagePanel("D:\\Document Folders\\AdventureGame\\assets\\smallgoblin.png", true);
        enemyImage.setVisible(false);
        enemyImage.setSize(120, 75);
        enemyImage.setLocation((gameInfo.getX()/2) - (enemyImage.getWidth()/2), 220);
        add(enemyImage);
        
        enemyType = new JLabel ("", SwingConstants.CENTER);
        enemyType.setSize(585, 20);
        enemyType.setLocation(0,enemyImage.getY() - enemyType.getHeight());
        enemyType.setFont(gameLocation.getFont().deriveFont(15.0f));
        enemyType.setVisible(false);
        add(enemyType);
        
        enemyHealth = new JLabel("100/100", SwingConstants.CENTER);
        enemyHealth.setSize(585, 50);
        enemyHealth.setLocation(0, enemyImage.getY() + enemyImage.getHeight());
        enemyHealth.setFont(gameLocation.getFont().deriveFont(15.0f));
        enemyHealth.setVisible(false);
        add(enemyHealth);
        
        classImage = new ImagePanel (CharacterManager.getCurrentCharacter().getImage(), true);
        
        characterName = new JLabel (CharacterManager.getCurrentCharacter().getName(), SwingConstants.CENTER);
        //characterName.setSize(100,50);
        characterName.setLocation(0, 0);
        gameInfo.add(characterName);
        gameInfo.add(classImage);
        levelLabel = new JLabel ("Level " + CharacterManager.getCurrentCharacter().getLevel(), SwingConstants.CENTER);
        gameInfo.add(levelLabel);
      
        gameInfo.add(new JLabel (CharacterManager.getCurrentCharacter().getClassName(), SwingConstants.CENTER));
        gameInfo.add(new JLabel ("Strength", SwingConstants.CENTER));
        strengthLabel = new JLabel (String.valueOf(CharacterManager.getCurrentCharacter().getStrength()), SwingConstants.CENTER);
        gameInfo.add(strengthLabel);
        gameInfo.add(new JLabel ("Intellect", SwingConstants.CENTER));
        intellectLabel = new JLabel (String.valueOf(CharacterManager.getCurrentCharacter().getIntellect()), SwingConstants.CENTER);
        gameInfo.add(intellectLabel);
        gameInfo.add(new JLabel ("Dexterity", SwingConstants.CENTER));
        dexterityLabel = new JLabel (String.valueOf(CharacterManager.getCurrentCharacter().getDexterity()), SwingConstants.CENTER);
        gameInfo.add(dexterityLabel);
       
        explorePanel = new JPanel(new GridLayout(0,1,0,25));
        explorePanel.setSize(535, 350);
        explorePanel.setLocation((gameInfo.getX()/2) - (explorePanel.getWidth()/2), 50);
        explorePanel.setBorder(new EmptyBorder(10, 30, 10, 30));
        explorePanel.setVisible(false);
        
        JLabel exploreTitle = new JLabel("<html><center>You Explore "+location+"</center></html>", SwingConstants.CENTER);
        exploreTitle.setFont(gameLocation.getFont().deriveFont(18.0f));
        exploreTitle.setForeground(Color.decode("#406159"));
        explorePanel.add(exploreTitle);
        exploreInfo = new JLabel("<html><center></center></html>", SwingConstants.CENTER);
        explorePanel.add(exploreInfo);
        
        exploreImage = new ImagePanel (CharacterManager.getCurrentCharacter().getImage(), true);
        explorePanel.add(exploreImage);
        
        exploreAction1 = new JButton("Start Battle");
        exploreAction1.setActionCommand("battle");
        exploreAction1.setBackground(Color.decode("#406159"));
        exploreAction1.setForeground(Color.WHITE);
        exploreAction1.addActionListener(this);
        explorePanel.add(exploreAction1);
        
        exploreAction2 = new JButton("Run Away");
        exploreAction2.setActionCommand("back");
        exploreAction2.setBackground(Color.decode("#406159"));
        exploreAction2.setForeground(Color.WHITE);
        exploreAction2.addActionListener(this);
        explorePanel.add(exploreAction2);
        
        add(explorePanel);
        
        actionsContainer = new JPanel(new GridLayout(0, 1, 0, 25));
        actionsContainer.setSize(535, 100);
        actionsContainer.setLocation((gameInfo.getX()/2) - (actionsContainer.getWidth()/2), 355);//325
        actionsContainer.setBorder(new EmptyBorder(10, 30, 10, 30));
        add(actionsContainer);
        
        healthContainer = new JPanel (new GridLayout(0, 1, 0, 5));
        healthContainer.setSize(gameInfo.getWidth(),actionsContainer.getHeight());
        healthContainer.setLocation(gameInfo.getX(),actionsContainer.getY());
        healthContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
        healthContainer.setForeground(Color.decode("#406159"));
        add(healthContainer);
        
        healthTitle = new JLabel ("<html><center>Your Health</center></html>", SwingConstants.CENTER);
        healthTitle.setFont(gameLocation.getFont().deriveFont(18.0f));
        healthTitle.setForeground(Color.decode("#406159"));
        healthContainer.add(healthTitle);
        
        playerHealth = new JLabel ("<html><center>" + CharacterManager.getCurrentCharacter().getCurrentHp() + "/" + CharacterManager.getCurrentCharacter().getMaxHp() +"</center></html>", SwingConstants.CENTER);
        playerHealth.setFont(gameLocation.getFont().deriveFont(23.0f));
        healthContainer.add(playerHealth);
        
        JLabel likeToDoLabel = new JLabel("<html><center>Select An Action</center></html>", SwingConstants.CENTER);
        likeToDoLabel.setSize(585, 30);
        likeToDoLabel.setFont(gameLocation.getFont().deriveFont(18.0f));
        likeToDoLabel.setForeground(Color.decode("#406159"));
        actionsContainer.add(likeToDoLabel);
        
        actionsPanel = new JPanel (new GridLayout(0, 3, 5, 25));
        actionsPanel.setOpaque(false);
        actionsPanel.setSize(500, 50);
        actionsContainer.add(actionsPanel);
        
        combatLogContainer = new JPanel(new GridLayout(0, 1, 0, 10));
        combatLogContainer.setSize(535, 100);
        combatLogContainer.setLocation((gameInfo.getX()/2) - (combatLogContainer.getWidth()/2), 50);
        combatLogContainer.setBorder(new EmptyBorder(10, 30, 10, 30));
        combatLogContainer.setVisible(false);
        
        combatLogTitle = new JLabel("<html><center>Combat Log</center></html>", SwingConstants.CENTER);
        combatLogTitle.setSize(585, 30);
        combatLogTitle.setFont(gameLocation.getFont().deriveFont(18.0f));
        combatLogTitle.setForeground(Color.decode("#406159"));
        combatLogContainer.add(combatLogTitle);
        
        combatLogInfo = new JLabel("<html><center>Battle has began! Waiting for you to make a move...</center></html>", SwingConstants.CENTER);
        combatLogInfo.setSize(585, 30);
        combatLogInfo.setFont(gameLocation.getFont().deriveFont(15.0f));
        combatLogContainer.add(combatLogInfo);
        add(combatLogContainer);
        
        abilitiesContainer = new JPanel(new GridLayout(0, 1, 0, 25));
      
        abilitiesContainer.setSize(535, 100);
        abilitiesContainer.setLocation((gameInfo.getX()/2) - (abilitiesContainer.getWidth()/2), 355);
        abilitiesContainer.setBorder(new EmptyBorder(10, 30, 10, 30));
        JLabel selectAbilityLabel = new JLabel("<html><center>Select An Ability</center></html>", SwingConstants.CENTER);
        selectAbilityLabel.setSize(585, 30);
        selectAbilityLabel.setFont(gameLocation.getFont().deriveFont(18.0f));
        selectAbilityLabel.setForeground(Color.decode("#406159"));
        abilitiesContainer.add(selectAbilityLabel);
        abilitiesContainer.setVisible(false);
        ArrayList<Ability> currentCharacterAbilities = CharacterManager.getCurrentCharacter().getAbilities();
        abilitiesPanel = new JPanel (new GridLayout(0,currentCharacterAbilities.size(), 5, 25));
        abilitiesPanel.setOpaque(false);
        abilitiesPanel.setSize(500, 50);        
        
        addAbilityButtons();
        abilitiesContainer.add(abilitiesPanel);
        add(abilitiesContainer);
        
        //abilitiesContainer.add(new JLabel(""));
        //abilities.add(likeToDoLabel);
        
        backToExploreButton = new JButton("Go Back");
        backToExploreButton.setSize(100,30);
        backToExploreButton.setLocation((gameInfo.getX() /2) - (backToExploreButton.getWidth()/2),abilitiesContainer.getY());
        backToExploreButton.setActionCommand("backtoexplore");
        backToExploreButton.setBackground(Color.decode("#406159"));
        backToExploreButton.setForeground(Color.WHITE);
        backToExploreButton.addActionListener(this);
        add(backToExploreButton);
        backToExploreButton.setVisible(false);
        
        travelButton = new JButton("Explore");
        travelButton.setActionCommand("explorebuttoncommand");
        travelButton.setBackground(Color.decode("#406159"));
        travelButton.setForeground(Color.WHITE);
        travelButton.addActionListener(this);
        actionsPanel.add(travelButton);
        
        a2 = new JButton("Health Potion");
        a2.setActionCommand("ability2buttoncommand");
        a2.setBackground(Color.decode("#406159"));
        a2.setForeground(Color.WHITE);
        a2.addActionListener(this);
        actionsPanel.add(a2);
        
        a3 = new JButton("");
        a3.setActionCommand("ability3buttoncommand");
        a3.setBackground(Color.decode("#406159"));
        a3.setForeground(Color.WHITE);
        a3.addActionListener(this);
        actionsPanel.add(a3);
        
        add(gameBackground);
    }
    
    //Adds this character's abilities as buttons.
    //They will be enabled if the character is a high enouhg level to use them.
    public void addAbilityButtons(){
        ArrayList<Ability> currentCharacterAbilities = CharacterManager.getCurrentCharacter().getAbilities();
        //Loop through all this user's abilities
        for(int i = 0; i < currentCharacterAbilities.size(); i++){
            Ability a = currentCharacterAbilities.get(i);
            JButton abilityButton = new JButton(a.getName());
            if(CharacterManager.getCurrentCharacter().getLevel() < a.getLevelRequirement()){
                abilityButton.setEnabled(false);
            }
            //Sets the action command for the button to be its index position
            //Eg the first ability will have action command "0" and so on.
            abilityButton.setActionCommand(String.valueOf(i));
            abilityButton.setBackground(Color.decode("#406159"));
            abilityButton.setForeground(Color.WHITE);
            abilityButton.addActionListener(this);
            abilitiesPanel.add(abilityButton);
        }
    }
    //Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e){
       if(e.getActionCommand().equals("explorebuttoncommand")){
           //Generates a random explore result, and  changes the UI accordingly.
           currentExploreResult = generateExploreResult();
           exploreInfo.setText("<html><center>" + currentExploreResult.getInfo() + "</center></html>");
           
           exploreImage.setImage(currentExploreResult.getImagePath());
           exploreImage.repaint();
           exploreAction1.setText(currentExploreResult.getConfirmCommand());
           exploreAction1.setActionCommand(currentExploreResult.getConfirmCommand());
           exploreAction2.setText(currentExploreResult.getCancelCommand());
           explorePanel.setVisible(true);
           actionsContainer.setVisible(false);
       }else if(e.getActionCommand().equals("ability2buttoncommand")){
           //Variable for max hp is created for ease of use 
           int maxHealth = CharacterManager.getCurrentCharacter().getMaxHp();
           //If the player health is below half of the max health, the button can be clicked which restores current
           //hp to the max hp value
           if(CharacterManager.getCurrentCharacter().getCurrentHp() < CharacterManager.getCurrentCharacter().getMaxHp()/2){
               CharacterManager.getCurrentCharacter().setCurrentHp(maxHealth); //current hp set to max hp
               playerHealth.setText("<html><center>" + CharacterManager.getCurrentCharacter().getCurrentHp() + "/" + CharacterManager.getCurrentCharacter().getMaxHp() +"</center></html>");
               JOptionPane.showMessageDialog(this, "Your health has been restored.");
           }else{
                JOptionPane.showMessageDialog(null, "Your health is too high to use a health potion.");
           }
       }
       else if(e.getActionCommand().equals("ability3buttoncommand")){
           //This button is not implemented
       }
       else if(e.getActionCommand().equals("Start Battle")){
           explorePanel.setVisible(false);
           enemyImage.setVisible(true);
           abilitiesContainer.setVisible(true);
           BattleEvent event = (BattleEvent) currentExploreResult;
   
           enemyImage.setImage(event.getEnemy().getImage());
           enemyImage.repaint();
          
           enemyType.setText("Level " + event.getEnemy().getLevel() + " " + event.getEnemy().getName());
           enemyType.setVisible(true);
           enemyHealth.setText("HP " + event.getEnemy().getCurrentHp() +"/" + event.getEnemy().getMaxHp());
           enemyHealth.setVisible(true);
           
           combatLogTitle.setText("Combat Log");
           combatLogInfo.setText("Battle Has Began! Waiting for you to make a move...");
           combatLogContainer.setVisible(true);
           
           
       }else if(e.getActionCommand().equals("Open Chest")){
           exploreAction1.setText("OPENING CHEST!");
       }else if(e.getActionCommand().equals("back")){
           explorePanel.setVisible(false);
           actionsContainer.setVisible(true);
       }else if (e.getActionCommand().equals("backtoexplore")){
           enemyHealth.setVisible(false);
           enemyType.setVisible(false);
           enemyImage.setVisible(false);
           combatLogContainer.setVisible(false);
           backToExploreButton.setVisible(false);
           
           
           actionsContainer.setVisible(true);
       }
       
       
       //Loop through all user abilities to check if one of them was clicked
       //Each abilitiy button will simply have its index positon as the action command
       //Eg the first ability will have action command "0" and so on
       ArrayList<Ability> allAbilities = CharacterManager.getCurrentCharacter().getAbilities();
       for(int i = 0; i < allAbilities.size(); i ++){
            if(e.getActionCommand().equals(String.valueOf(i))){
                //Found the clicked ability.
                Ability clickedAbility = allAbilities.get(i);
                //Cast the current event to a BattleEvent to access the methods in BattleEvent Class
                BattleEvent event = (BattleEvent) currentExploreResult;
                //Attack the enemy with this an ability and update the UI accordingly
                int yourDamageDone = event.getEnemy().takeDamage(CharacterManager.getCurrentCharacter(), clickedAbility);
                enemyHealth.setText("HP " + event.getEnemy().getCurrentHp() +"/" + event.getEnemy().getMaxHp());
                combatLogInfo.setText("<html><center>You use " + clickedAbility.getName() + ", " + event.getEnemy().getName() + " takes " + yourDamageDone + " damage.");
                
                if(event.getEnemy().getCurrentHp() == 0){
                   //Check if the enemy died, if so, update the UI and gain experience
                   combatLogInfo.setText(combatLogInfo.getText() + "<br> You have defeated " + event.getEnemy().getName() + ".");
                   abilitiesContainer.setVisible(false);
                   backToExploreButton.setVisible(true);
                   combatLogTitle.setText("You Have Defeated The Level " + event.getEnemy().getLevel() + " " + event.getEnemy().getName() +"!");
                   combatLogInfo.setText("<html><center>You Gain " + event.getEnemy().getExperience() + " Experience Points.");
                   //Check to see if we level up, if so update the UI
                   boolean didLevelUp = CharacterManager.getCurrentCharacter().gainExperience(event.getEnemy());
                   if(didLevelUp){
                     combatLogInfo.setText(combatLogInfo.getText() + "<br> You Are Now Level " + CharacterManager.getCurrentCharacter().getLevel() + "!");
                     levelLabel.setText("Level " + CharacterManager.getCurrentCharacter().getLevel());
                     strengthLabel.setText(String.valueOf(CharacterManager.getCurrentCharacter().getStrength()));
                     dexterityLabel.setText(String.valueOf(CharacterManager.getCurrentCharacter().getDexterity()));
                     intellectLabel.setText(String.valueOf(CharacterManager.getCurrentCharacter().getIntellect()));
                     playerHealth.setText("<html><center>" + CharacterManager.getCurrentCharacter().getCurrentHp() + "/" + CharacterManager.getCurrentCharacter().getMaxHp() + "</center></html>" );
                     abilitiesPanel.removeAll();
                     addAbilityButtons();
                    }
                }else{
                    //If the enemy didnt die, it attacks back and the UI is updated accordingly
                    Ability enemyChosenAbility = event.getEnemy().getRandomAbility();
                    int enemyDamageDone = CharacterManager.getCurrentCharacter().takeDamage(event.getEnemy(), enemyChosenAbility);
                    combatLogInfo.setText(combatLogInfo.getText() +"<br>" + event.getEnemy().getName() + " uses " + enemyChosenAbility.getName() + ", You " + "take " +enemyDamageDone + " damage.");
                    playerHealth.setText("<html><center>" + CharacterManager.getCurrentCharacter().getCurrentHp() + "/" + CharacterManager.getCurrentCharacter().getMaxHp() +"</center></html>");
                    //Check to see if the player died, if so show a message dialog, delete the save file and reset the game.
                    if(CharacterManager.getCurrentCharacter().getCurrentHp() == 0){
                        CharacterManager.deleteSaveData();
                        JOptionPane.showMessageDialog(this, "You have been defeated by the " + event.getEnemy().getName(), "Game Over!", JOptionPane.OK_OPTION);
                        parent.resetGame();
                    }
                    
                }
                
                combatLogInfo.setText(combatLogInfo.getText() + "</center></html>");
               
           }
       }
     
    }
    
    //Will generatea battle event with a Level 1 Goblin 75% of the time
    //And a chest event the rest of the time
    public ExploreResult generateExploreResult(){
        ExploreResult exploreResult;
        if(Math.random() > 0.25){
            exploreResult = new BattleEvent(new Goblin(1));
        }else{
            exploreResult = new ChestEvent();
        }
      
        return exploreResult;
    }
    
}
