import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener
{
    private boolean A = false;
    private boolean W = false;
    private boolean S = false;
    private boolean D = false;
    private boolean UP = false;
    private boolean DOWN = false;
    private boolean LEFT = false;
    private boolean RIGHT = false;

    public Movement(Canvas c)
    {
        c.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                Engine.sterowanie(9);
            case KeyEvent.VK_ENTER:
                Engine.sterowanie(10);
        }
    }


    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                W = true;
                break;
            case KeyEvent.VK_S:
                S = true;
                break;
            case KeyEvent.VK_UP:
                UP = true;
                break;
            case KeyEvent.VK_DOWN:
                DOWN = true;
                break;
            case KeyEvent.VK_A:
                A = true;
                break;
            case KeyEvent.VK_D:
                D = true;
                break;
            case KeyEvent.VK_LEFT:
                LEFT = true;
                break;
            case KeyEvent.VK_RIGHT:
                RIGHT = true;
                break;
            case KeyEvent.VK_SPACE:
                Engine.sterowanie(9);
                break;
            case KeyEvent.VK_ENTER:
                Engine.sterowanie(10);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                W = false;
                break;
            case KeyEvent.VK_S:
                S = false;
                break;
            case KeyEvent.VK_UP:
                UP = false;
                break;
            case KeyEvent.VK_DOWN:
                DOWN = false;
                break;
            case KeyEvent.VK_A:
                A = false;
                break;
            case KeyEvent.VK_D:
                D = false;
                break;
            case KeyEvent.VK_LEFT:
                LEFT = false;
                break;
            case KeyEvent.VK_RIGHT:
                RIGHT = false;
                break;
        }
    }

    public void movement()
    {
        if(W) Engine.sterowanie(1);
        if(S) Engine.sterowanie(2);
        if(UP) Engine.sterowanie(3);
        if(DOWN) Engine.sterowanie(4);
        if(A) Engine.sterowanie(5);
        if(D) Engine.sterowanie(6);
        if(LEFT) Engine.sterowanie(7);
        if(RIGHT) Engine.sterowanie(8);
    }
}
