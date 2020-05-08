import java.awt.*;
import java.util.Random;

public class Cell extends GameObject
{
    private int currentValue;
    private int maxValue;
    private int spadek = 0;
    private boolean isArmagedon = false;
    private boolean isAlive = true;
    private long lastbirth;

    public Cell(int x, int y, int size, boolean armegadonChance, boolean isChildren)
    {
        super(x, y, size, size);
        Random random = new Random();
        if(isChildren)
            currentValue = 1;
        else
            this.currentValue = random.nextInt(7) + 1;
        this.maxValue = currentValue;
        this.lastbirth = System.nanoTime();
        if(armegadonChance)
            if(random.nextInt(100) > 85)
                isArmagedon = true;
    }

    public int getCurrentValue()
    {
        return currentValue;
    }

    public void increaseValue()
    {
        if(currentValue < 8 && isAlive)
            currentValue++;
        if(maxValue < currentValue)
            maxValue = currentValue;
    }

    public int decreaseValue()
    {
        if(isAlive)
        {
            if (currentValue > 1)
            {
                currentValue--;
                return 0;
            } else {
                if(killCell())
                    return -maxValue;
                else
                    return maxValue;
            }
        } else
            return spadek;
    }

    private boolean killCell()
    {
        isAlive = false;
        return losujdobytek();
    }

    private boolean losujdobytek()
    {
        Random random = new Random();
        if(random.nextInt(100) > 50)
        {
            losujWartoscDobytku(random.nextInt(100));
            return true;
        }
        else
        return false;
    }

    private void losujWartoscDobytku(int x)
    {
        if(x >= 95)
        {
            spadek = 100;
            return;
        }
        if(x >= 85)
        {
            spadek = 50;
            return;
        }
        if(x >= 70)
        {
            spadek = 30;
            return;
        }
        if(x >= 50)
        {
            spadek = 20;
            return;
        }
        if(x >= 0)
        {
            spadek = 10;
        }
    }

    public long getLastBirth()
    {
        return lastbirth;
    }

    public void setLastBirth(long lastBirth)
    {
        this.lastbirth = lastBirth;
    }

    private Color cellColour()
    {
        Color color;
        switch (maxValue) {
            case 1:
                color = new Color(0x94CB8A);
                break;
            case 2:
                color = new Color(0x71A16D);
                break;
            case 3:
                color = new Color(0x61AC4D);
                break;
            case 4:
                color = new Color(0x4AAF39);
                break;
            case 5:
                color = new Color(0x33A822);
                break;
            case 6:
                color = new Color(0x21760F);
                break;
            case 7:
                color = new Color(0x125807);
                break;
            case 8:
                color = new Color(0x092B03);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + maxValue);
        }
        return color;
    }

    public void render(Graphics g)
    {
        if(isAlive)
        {
            g.setColor(cellColour());
            g.fillRect(getRectangle().x, getRectangle().y, getRectangle().width, getRectangle().height);
            g.setFont(new Font("Arial Black", Font.ITALIC, getHeight() / 3));
            g.setColor(Color.black);
            g.drawString(String.valueOf(currentValue), getX() - getWidth() / 7, getY() + getHeight() / 8);
            //getX() - getWidth() / 7, getY() - getHeight() / 8);
        }
        else
        {
            g.setColor(new Color(0x14149C));
            g.fillRect(getRectangle().x, getRectangle().y, getRectangle().width, getRectangle().height);
            g.setFont(new Font("Arial Black", Font.ITALIC, getHeight() / 3));
            g.setColor(Color.black);
            g.drawString(String.valueOf(currentValue), getX() - getWidth() / 7, getY() - getHeight() / 8);
        }
    }

    public boolean getArmagedon()
    {
        return isArmagedon;
    }

    public boolean getAlive()
    {
        return isAlive;
    }

    public int getMaxValue()
    {
        return maxValue;
    }

    public  int getSpadek()
    {
        return spadek;
    }
}
