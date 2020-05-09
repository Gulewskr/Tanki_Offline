import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class Tank extends GameObject{
    private int angle;
    private int score = 0;
    private int owner;
    private final int cannonHeight = 40;
    private final int cannonWidth = 20;
    private Image tankImage1;
    private Image tankImage2;
    private Image tankImage ;
    private Image cannonImage;

    public Tank(int owner, int x ,int y)
    {
        super( x, y, 40 , 60);
        this.owner = owner;
        if(owner == 1)
        {
            changePosition(x - getWidth()/2, y);
            angle = -90;
        }
        else if(owner == 2)
        {
            changePosition(x + getWidth()/2, y);
            angle = 90;
        }
        else{
            System.out.println("Uknown owner in Tank object (must be 1 or 2)");
        }
        try {
            tankImage2 = ImageIO.read(new File("src/images/tank2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            tankImage1 = ImageIO.read(new File("src/images/tank1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            cannonImage = ImageIO.read(new File("src/images/cannon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tankImage = tankImage1;
    }

    public void changeAngle(int kat)
    {
        if(owner == 1)
        {
            if(angle + kat < -45 && angle + kat > -135)
                angle += kat;
            else if(angle + kat > -45)
                angle = -45;
            else if(angle + kat < -135)
                angle = -135;
        }
        if(owner == 2)
        {
            if (angle + kat > 45 && angle + kat < 135)
                angle += kat;
            else if (angle + kat < 45)
                angle = 45;
            else if (angle + kat > 135)
                angle = 135;
        }
    }

    public void render(Graphics g)
    {
        //czołg
        g.setColor(new Color(52305));
        g.drawImage(tankImage,getRectangle().x, getRectangle().y, getRectangle().width, getRectangle().height, null);
        //działo
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        AffineTransform at = AffineTransform.getTranslateInstance(getX(), getY());
        g2d.setTransform(at);
        g2d.rotate(Math.toRadians(angle));
        g2d.setColor(Color.blue);
        g2d.drawImage(cannonImage,-cannonWidth/2, -10, cannonWidth, cannonHeight, null);
        g2d.setTransform(old);
    }

    public int getOwner() {
        return owner;
    }

    public int getCannonX()
    {
        return  (int)(getX() - (cannonHeight * Math.sin(Math.toRadians(angle))));
    }

    public int getCannonY()
    {
        return  (int)(getY() - (-1 * cannonHeight * Math.cos(Math.toRadians(angle))));
    }

    public void addPoints(int points)
    {
        score += points;
    }

    public void changeImage()
    {
        if(tankImage == tankImage1)
            tankImage = tankImage2;
        else tankImage = tankImage1;
    }

    public int getScore()
    {
        return score;
    }

    //Nieużywane
    /*
    public int getAngle() {
        return angle;
     }
     */
}