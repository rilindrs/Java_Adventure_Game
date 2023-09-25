import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClassSelectionPanel extends JPanel implements ActionListener
{
   private JTextField nameInput;
   private JButton mageButton, warriorButton, archerButton;
   private JLabel selectClassTitle, selectNameTitle;
   
   MainFrame parent; //Reference to the parent class to enable access to methods in the class
   
   //Creating and initialising the design features of the class selection panel
  public ClassSelectionPanel(MainFrame parentFrame){
        parent = parentFrame;
        
        setLayout(new GridLayout(0, 1, 0, 25));
        setSize(300, 300);
        setLocation( (MainFrame.SCREEN_WIDTH/2) - (getWidth()/2) , 50);
        
        selectNameTitle = new JLabel("Enter Character Name Below");
        selectNameTitle.setHorizontalAlignment(JLabel.CENTER);
        add(selectNameTitle);
        
        nameInput = new JTextField();
        nameInput.setToolTipText("Enter Character Name");
        add(nameInput);
        
        selectClassTitle = new JLabel("Select a Class");
        selectClassTitle.setHorizontalAlignment(JLabel.CENTER);
        add(selectClassTitle);
        
        mageButton = new JButton("Mage");
        mageButton.setBackground(Color.decode("#FF5722"));
        mageButton.setForeground(Color.WHITE);
        mageButton.addActionListener(this);
        mageButton.setActionCommand("magebuttoncommand");
        add(mageButton);
        
        warriorButton = new JButton("Warrior");
        warriorButton.setBackground(Color.decode("#607D8B"));
        warriorButton.setForeground(Color.WHITE);
        warriorButton.addActionListener(this);
        warriorButton.setActionCommand("warriorbuttoncommand");
        add(warriorButton);
        
        archerButton =new JButton ("Archer");
        archerButton.setBackground(Color.decode("#009688"));
        archerButton.setForeground(Color.WHITE);
        archerButton.addActionListener(this);
        archerButton.setActionCommand("archerbuttoncommand");
        add(archerButton);
  }
  
  //Reference to the name of the player 
  public String getCharacterName(){
      return nameInput.getText();
  }
  
  //Method which removes the original value of the player name and resets the value
  public void clearNameInput(){
      nameInput.setText("");
  }
  
  //Actions that are carried out after the command is selected
  @Override
  public void actionPerformed(ActionEvent e){
       //Ensure the user has input a name
       if(nameInput.getText().trim().isEmpty()){
           JOptionPane.showMessageDialog(this, "Please enter a character name first");
           return;
       }
       //Parent class referenced to access the methdos that contain information about each class
       if(e.getActionCommand().equals("magebuttoncommand")){
          parent.showMageDescription();  
       } else if (e.getActionCommand().equals("warriorbuttoncommand")){
          parent.showWarriorDescription();  
       } else if (e.getActionCommand().equals("archerbuttoncommand")){
          parent.showArcherDescription();  
       }
  }
}
