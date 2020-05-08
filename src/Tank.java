import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Tank extends GameObject{
    private int angle;
    private int score = 0;
    private int owner;
    private final int cannonHeight = 30;
    private final int cannonWidth = 20;

    public Tank(int owner, int x ,int y)
    {
        super( x, y, 40 , 80);
        this.owner = owner;
        if(owner == 1)
        {
            changePosition(x - 20, y);
            angle = -90;
        }
        else if(owner == 2)
        {
            changePosition(x + 20, y);
            angle = 90;
        }
        else{
            System.out.println("Uknown owner in Tank object (must be 1 or 2)");
        }
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
        g.fillRect(getRectangle().x, getRectangle().y, getRectangle().width, getRectangle().height);
        //działo
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        AffineTransform at = AffineTransform.getTranslateInstance(getX(), getY());
        g2d.setTransform(at);
        g2d.rotate(Math.toRadians(angle));
        g2d.setColor(Color.blue);
        g2d.fillRect(-cannonWidth/2, 0, cannonWidth, cannonHeight);
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

    //Nieużywane
    /*
    public int getAngle() {
        return angle;
    }

    public int getScore()
    {
        return score;
    }
     */
}