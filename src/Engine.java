import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Engine
{
    private final static LinkedList<Cell> cells = new LinkedList<>();
    private final static LinkedList<Bullet> bulletsP1 = new LinkedList<>();
    private final static LinkedList<Bullet> bulletsP2 = new LinkedList<>();
    private static Tank tankP1, tankP2;
    private int cellSize = 80;
    private final int startCellSize = cellSize;
    private static double velocity = 200;
    private final static double startVelocity = velocity;
    //private final int ticknumber;
    private boolean armagedonNotExist = true;
    private final int mapWidth, mapWidthMin, mapHeight, mapHeightMin;
    //private final int tick = 0;
    private final Random random = new Random();
    private static  Fizyka physics;
    private static int bulletsLimit = 0;


    public Engine(int width, int heigt, int mapwidth, int mapheight)
    {
        this.mapWidth = width/2 + mapwidth/2;
        this.mapHeight = heigt/2 + mapheight/2;
        this.mapHeightMin = heigt/2 - mapheight/2;
        this.mapWidthMin = width/2 - mapwidth/2;
        physics = new Fizyka(mapWidthMin, mapWidth, mapHeightMin, mapHeight);
        tankP1 = new Tank(1, mapWidthMin, (mapHeight - mapWidthMin) / 2);
        tankP2 = new Tank(2, mapWidth, (mapHeight - mapWidthMin) / 2);
        //ticknumber = 0;
        createCell( width/2, heigt/2, false);
    }

    public void setBulletsLimit(int limit)
    {
        bulletsLimit = limit;
    }

    public void tick()
    {
        updateBullets();
    }

    private static void fireBullet(int x, int y, int ownerID, int limit)
    {
        if(ownerID == 1)
        {
            if(limit > bulletsP1.size())
                bulletsP1.add(new Bullet(x, y, tankP1.getOwner(), x- tankP1.getX(), y - tankP1.getY(), velocity));
        }
        else if(ownerID == 2)
        {
            if(limit > bulletsP2.size())
                bulletsP2.add(new Bullet(x, y, tankP2.getOwner(), x- tankP2.getX(), y - tankP2.getY(), velocity));
        }
    }


    private void updateBullets()
    {
        for(int i = 0 ; i< bulletsP1.size(); i++)
        {
            if(!physics.move(bulletsP1.get(i).getVectorX(), bulletsP1.get(i).getVectorY(), bulletsP1.get(i)))
                bulletsP1.remove(i);
            else {
                int tmp = physics.bulletCollision(bulletsP1.get(i), cells);
                if (tmp != -1)
                {
                    bulletsP1.remove(i);
                    int p = cells.get(tmp).decreaseValue();
                    if(p > 0)
                    {
                        cells.remove(tmp);
                        tankP1.addPoints(p);
                    }
                    else tankP1.addPoints(-p);
                }
            }
        }
        for(int i = 0 ; i< bulletsP2.size(); i++)
        {
            if(!physics.move(bulletsP2.get(i).getVectorX(), bulletsP2.get(i).getVectorY(), bulletsP2.get(i)))
                bulletsP2.remove(i);
            else {
                int tmp = physics.bulletCollision(bulletsP2.get(i), cells);
                if (tmp != -1) {
                    bulletsP2.remove(i);
                    int p = cells.get(tmp).decreaseValue();
                    if(p > 0)
                    {
                        cells.remove(tmp);
                        tankP2.addPoints(p);
                    }
                    else tankP2.addPoints(-p);
                }
            }
        }
    }

    public void score()
    {
        System.out.println("P1 = " + tankP1.getScore() + "   P2 =" + tankP2.getScore());
    }

    public void increaseSpeed(double percent)
    {
        if(increaseBulletSpeed(percent))
        {
            for(Bullet b : bulletsP1)
            {
                b.changeSpeed(velocity);
            }
            for(Bullet b : bulletsP2)
            {
                b.changeSpeed(velocity);
            }
        }
    }

    private static boolean increaseBulletSpeed(double procent)
    {
        velocity *= ((100 - procent)/100 +1);
        if(velocity > startVelocity * 3)
        {
            velocity = startVelocity * 3;
            return false;
        }
        return true;
    }

    public void changeSize(double percent)
    {
        if(decreaseCellSize(percent))
        for(Cell c : cells)
        {
            c.changeSize(cellSize, cellSize);
        }
    }

    private boolean decreaseCellSize(double procent)
    {
        cellSize *= (100 - procent)/100;
        if(cellSize < startCellSize /2)
        {
            cellSize = startCellSize /2;
            return false;
        }
        return true;
    }

    public void mapCellTick()
    {
        for (Cell cell : cells)
            cell.increaseValue();
    }

    public void mapParentsTick()
    {
        System.out.println(cells.size());
        int limit = cells.size();
        for (int i = 0; i<limit; i++)
            if (System.currentTimeMillis() - cells.get(i).getLastBirth() / 1000000000 > 5)
            {
                cells.get(i).setLastBirth(System.currentTimeMillis());
                childBorn(cells.get(i).getX(), cells.get(i).getY(), cells.get(i).getCurrentValue() - 1);
            }
    }

    public void newRandomCell()
    {
        createCell(random.nextInt(mapWidth - mapWidthMin) + mapWidthMin, random.nextInt(mapHeight - mapHeightMin) + mapHeightMin, false);
    }

    private boolean createCell(int x, int y, boolean dziecko)
    {

        Rectangle nowy = new Rectangle();
        nowy.setBounds(  x - cellSize / 2, y - cellSize / 2, cellSize, cellSize);
        if(x > mapWidthMin + cellSize/2 && x < mapWidth - cellSize / 2)
        {
            if (y > mapHeightMin + cellSize / 2 && y < mapHeight - cellSize / 2)
            {
                if ( !Fizyka.collision(cells, nowy))
                {
                    Cell cell = new Cell(x, y, cellSize, armagedonNotExist, dziecko);
                    cells.add(cell);
                    if(cell.getArmagedon())
                        armagedonNotExist = false;
                    return true;
                }
            }
        }
        return false;
    }

    private void childBorn(int x, int y, int ilosc)
    {
        for(int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
            {
                if(ilosc == 0) return;
                if(createCell(x + (i - 1) * cellSize, y + (j - 1) * cellSize, true))
                    ilosc--;
            }
    }

    public void render (Graphics g)
    {
        g.setColor(new Color(0xFF04DD));
        g.drawLine(mapWidthMin, mapHeightMin, mapWidthMin, mapHeight);
        g.drawLine(mapWidthMin, mapHeightMin, mapWidth,  mapHeightMin);
        g.drawLine(mapWidth, mapHeight, mapWidthMin, mapHeight);
        g.drawLine(mapWidth, mapHeight, mapWidth,  mapHeightMin);
        for (int i = 0; i < cells.size(); i++)
        {
            Cell cell = cells.get(i);
            cell.render(g);
        }
        tankP1.render(g);
        tankP2.render(g);
        for (int i = 0; i < bulletsP1.size(); i++)
        {
            Bullet bullet = bulletsP1.get(i);
            bullet.render(g);
        }
        for (int i = 0; i < bulletsP2.size(); i++)
        {
            Bullet bullet = bulletsP2.get(i);
            bullet.render(g);
        }
    }

    public static void sterowanie(int option)
    {
        switch(option)
        {
            case 1: physics.move(0,-3, tankP1); tankP1.changeImage(); break;
            case 2: physics.move(0,3, tankP1); tankP1.changeImage(); break;
            case 3: physics.move(0,-3, tankP2); tankP2.changeImage(); break;
            case 4: physics.move(0,3, tankP2); tankP2.changeImage(); break;
            case 5: tankP1.changeAngle(2); break;
            case 6: tankP1.changeAngle(-2);break;
            case 7: tankP2.changeAngle(2);break;
            case 8: tankP2.changeAngle(-2);break;
            case 9: fireBullet(tankP1.getCannonX(), tankP1.getCannonY(), tankP1.getOwner(), bulletsLimit); break;
            case 10: fireBullet(tankP2.getCannonX(), tankP2.getCannonY(), tankP2.getOwner(), bulletsLimit); break;
        }
    }
}
