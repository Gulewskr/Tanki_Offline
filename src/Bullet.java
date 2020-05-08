import java.awt.*;

public class Bullet extends GameObject {
    private final int playerID;
    private final double angleVecX;
    private final double angleVecY;
    private int vectorX;
    private int vectorY;
    private final int speedScaling = 300;

    public Bullet(int x, int y, int playerID, double velx, double vely , double velocity )
    {
        super( x, y, 10, 10);
        this.playerID = playerID;
        this.angleVecX = velx;
        this.angleVecY = vely;
        this.vectorX = (int)(velx * velocity / speedScaling);
        this.vectorY = (int)(vely * velocity / speedScaling);
    }

    public void render(Graphics g)
    {
        g.setColor(Color.yellow);
        g.fillOval(getX() - getWidth()/2, getY() - getHeight()/2, getWidth(), getHeight());
    }

    public void changeSpeed(double velocity)
    {
        this.vectorX = (int)(angleVecX * velocity / speedScaling);
        this.vectorY = (int)(angleVecY * velocity / speedScaling);
    }


    public int getVectorX()
    {
        return vectorX;
    }

    public int getVectorY()
    {
        return vectorY;
    }

    public int getPlayerID()
    {
        return playerID;
    }
}
