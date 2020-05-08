import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable
{
    public static Configuration conf;
    static
    {
        conf = new Configuration("src/configuration");
    }
    private final int width = 1200;
    private final int height = 900;
    private  boolean running;
    private final UpdateGame gra;
    private int sekunda = 0;
    private int tick = 0;
    private final Movement movement = new Movement(this);

    public Game()
    {
        Window window = new Window(width, height, this, "Tanki Offline");
        gra = new UpdateGame(width, height, conf.getMap_width(), conf.getMap_height());
        gra.setBulletsLimit(conf.getMax_bullet_count());
    }

    private void start(){
        running = true;
    }

    public void run()
    {
        long lastTime = System.nanoTime();
        int fps = 60;
        double nsConvert = 1000000000.0 / fps;
        double deltaT = 0;

        while(running)
        {
            long now = System.nanoTime();
            deltaT += (now - lastTime) / nsConvert;
            lastTime = now;

            while(deltaT >= 1)
            {
                deltaT--;
                tick ++;
                movement.movement();
                gra.tick();
                if(tick%fps == 0)
                {
                    sekunda++;
                    tick = 0;
                    if(sekunda % conf.getIncrease_speed_decrease_size_time() == 0)
                    {
                        gra.changeSize(conf.getCell_size_decrease());
                        gra.increaseSpeed(conf.getBullet_speed_increase());
                    }
                    if(sekunda % conf.getChild_spawn_time() == 0)
                        gra.mapParentsTick();
                    if(sekunda % conf.getStrength_increase_time() == 0)
                        gra.mapCellTick();
                    if(sekunda % conf.getNew_object_spawn_time() == 0)
                        gra.newRandomCell();
                }
            }
            if(running)
                render();
        }

        stop();
    }

    private void stop()
    {

    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        super.paint(g);
        g.setColor(new Color(0x8B5D5D));
        g.fillRect(0, 0, width, height);
        gra.render(g);
        g.dispose();
        bs.show();
    }

    public static void main(String [] args)
    {
        Game game = new Game();
        game.start();
        Thread gameThread = new Thread(game);
        gameThread.start();
    }
}
