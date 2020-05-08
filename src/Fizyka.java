import java.awt.Rectangle;
import java.util.LinkedList;

public class Fizyka
{
    private final int mapWmin;
    private final int mapWmax;
    private final int mapHmin;
    private final int mapHmax;

    public Fizyka(int mapWmin, int mapWmax, int mapHmin, int mapHmax)
    {
        this.mapWmin = mapWmin;
        this.mapWmax = mapWmax;
        this.mapHmin = mapHmin;
        this.mapHmax = mapHmax;
    }

    static public boolean collision(LinkedList<Cell> cells, Rectangle pole)
    {
        for (Cell cell : cells) {
            if (pole.intersects(cell.getRectangle())) {
                return true;
            }
        }
        return false;
    }

    static public int bulletCollision(Bullet bullet, LinkedList<Cell> cells)
    {
        for(int i = 0; i < cells.size(); i++)
        {
            if(bullet.getRectangle().intersects(cells.get(i).getRectangle()))
            {
                return i;
            }
        }
        return -1;
    }

    public boolean move(int vecX, int vecY, GameObject object)
    {
        int x = object.getX();
        int y = object.getY();
        boolean granica = false;

        if(x + vecX < mapWmin && x >= mapWmin)
        {
            x=mapWmin;
            granica = true;
        }
        else
        {
            if(x+vecX >mapWmax && x <= mapWmax) {
                x = mapWmax;
                granica = true;
            }
            else {
                x += vecX;
            }
        }

        if(y+vecY < mapHmin) {
            y = mapHmin;
            granica = true;
        }
        else
        {
            if(y+vecY >mapHmax) {
                y = mapHmax;
                granica = true;
            }
            else
                y+=vecY;
        }

        object.changePosition(x , y);
        return !granica;
    }
}
