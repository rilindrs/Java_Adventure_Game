import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClassDescriptionPanel extends JPanel implements ActionListener
{
    JLabel classTitle;
    ImagePanel classImage;
    JTextArea classInfo;
    JButton startButton, backButton;
    //Reference to the parent class to enable access to methods in the class
    MainFrame parent; 
    
    
    //Creating and initialising the design features of the class description panel
    public ClassDescriptionPanel(MainFrame parentFrame, String title, String imageFilepath, String description){
        //Keeping a refernece to the MainFrame parent so that we can call its methods from this class.
        parent = parentFrame;
        
        setLayout(null);
        setSize(MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
        classTitle = new JLabel(title, SwingConstants.CENTER); 
        classTitle.setSize(500, 50);
        classTitle.setFont(classTitle.getFont().deriveFont(24.0f));
        classTitle.setLocation( (getWidth()/2) - (classTitle.getWidth()/2), 10);
        add(classTitle);
        
        classImage = new ImagePanel(imageFilepath, true);
        classImage.setSize(170, 135);
        classImage.setLocation( (getWidth()/2)-(classImage.getWidth()/2), getHeight()/6);
        add(classImage);
        
        classInfo = new JTextArea(description);
        
        classInfo.setSize(getWidth()/2, getHeight()/4);
        classInfo.setLocation( (getWidth()/2)-(classInfo.getWidth()/2),(int)(getHeight()*0.50));
        classInfo.setLineWrap(true);
        classInfo.setBorder(BorderFactory.createCompoundBorder(classInfo.getBorder(),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        add(classInfo);
        
        startButton = new JButton("START ADVENTURE");
        startButton.setSize(150, getHeight()/12);
        startButton.setLocation( (getWidth()/2)-(startButton.getWidth()/2), (int)(getHeight()*0.80) );
        startButton.setBackground(Color.decode("#ffeb3b"));
        startButton.addActionListener(this);
        startButton.setActionCommand("startadventurebutton");
        add(startButton);
        
        backButton = new JButton ("< Back");
        backButton.setSize(100, 20);
        backButton.setLocation(10, 10);
        add(backButton);
        backButton.addActionListener(this);
        backButton.setActionCommand("backbuttoncommand");
        
    }
    
    //Reference to method in parent class that enables the 'back to home' functionality
    //Reference to method in parent class 
    @Override
   public void actionPerformed(ActionEvent e){
       if(e.getActionCommand().equals("backbuttoncommand")){
           parent.backToHome();
       }else if(e.getActionCommand().equals("startadventurebutton")){
           parent.startGame(classTitle.getText());
       }
   }
}
