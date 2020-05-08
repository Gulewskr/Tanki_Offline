import java.awt.*;

public class GameObject
{
    private int x;
    private int y;
    private int width;
    private int height;

    public GameObject(int x, int y, int width, int height)
    {
               this.x = x;
               this.y = y;
               this.width = width;
               this.height = height;
    }

    public void changeSize(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void changePosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Rectangle getRectangle()
    {
        return new Rectangle(x - width/2, y - height/2 ,width, height);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return  y;
    }

    public int getWidth()
    {
        return  width;
    }

    public int getHeight()
    {
        return  height;
    }
}
