import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    BufferedImage image;
    int drawX, drawY;
    boolean shouldCenter;
    //Overloaded constructors that have different parameters. This constructor with a single parameter
    //only requires the file path of the image and begins to draw the image from the top left of the panel
    public ImagePanel(String filePath) {
        drawX = 0;
        drawY = 0;
        try {                
            image = ImageIO.read(new File(filePath));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        setOpaque(false); //Makes the background invisible
    }
    
    //This constructor takes additional two parameters that allows me to specify the precise position
    //of where the image will be drawn from
    public ImagePanel(String filePath, int x, int y) {
        drawX = x;
        drawY = y;
       try {                
          image = ImageIO.read(new File(filePath));
       } catch (IOException ex) {
            System.out.println(ex.getMessage());
       }
       setOpaque(false);
    }
    
    //This constructor takes an additioanl parameter that allows the image to be drawn from the center
    //of the panel
    public ImagePanel(String filePath, boolean center) {
        drawX = 0;
        drawY = 0;
        shouldCenter = center;
       try {                
          image = ImageIO.read(new File(filePath));
       } catch (IOException ex) {
            System.out.println(ex.getMessage());
       }
       setOpaque(false);
    }
    
    //This method is used when painting an image on the panel
    public void setImage(String filePath){
        try {                
            image = ImageIO.read(new File(filePath));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //The previous constructor will use this overriden method in order to center the image on the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(shouldCenter){
           g.drawImage(image, (int)(g.getClipBounds().getCenterX() - (image.getWidth()/2)), (int)(g.getClipBounds().getCenterY() - (image.getHeight()/2)), this); 
        }else{
           g.drawImage(image, drawX, drawY, this); 
        }         
    }

}