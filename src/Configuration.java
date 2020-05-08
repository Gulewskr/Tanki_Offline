import java.io.*;

public class Configuration {
    private final String path;
    private int max_bullet_count;
    private double new_object_spawn_time;
    private double child_spawn_time;
    private double strength_increase_time;
    private int bonus_drop_rate;
    private int map_width;
    private int map_height;
    private int game_time;
    private int max_score;
    private double increase_speed_decrease_size_time;
    private int bullet_speed_increase;
    private int cell_size_decrease;

    public int getMax_bullet_count() {
        return max_bullet_count;
    }

    public double getNew_object_spawn_time() {
        return new_object_spawn_time;
    }

    public double getChild_spawn_time() {
        return child_spawn_time;
    }

    public double getStrength_increase_time() {
        return strength_increase_time;
    }

    public int getBonus_drop_rate() {
        return bonus_drop_rate;
    }

    public int getMap_width() {
        return map_width;
    }

    public int getMap_height() {
        return map_height;
    }

    public int getGame_time() {
        return game_time;
    }

    public int getMax_score() {
        return max_score;
    }

    public double getIncrease_speed_decrease_size_time() {
        return increase_speed_decrease_size_time;
    }

    public int getBullet_speed_increase() {
        return bullet_speed_increase;
    }

    public int getCell_size_decrease() {
        return cell_size_decrease;
    }

    public Configuration(String path)
    {
        this.path = path;
        setup();
    }

    private void setup(){
        File file = new File(path);
        int lineCount=1;
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while( (line = br.readLine()) != null){
                if(line.length() > 17 && line.substring(0, 16).equals("max_bullet_count")) {
                    this.max_bullet_count = Integer.parseInt(line.substring(17));
                }
                else if(line.length() > 22 && line.substring(0, 21).equals("new_object_spawn_time")){
                    this.new_object_spawn_time = Double.parseDouble(line.substring(22));
                }
                else if(line.length() > 17 && line.substring(0, 16).equals("child_spawn_time")) {
                    this.child_spawn_time = Double.parseDouble(line.substring(17));
                }
                else if(line.length() > 23 && line.substring(0, 22).equals("strength_increase_time")){
                    this.strength_increase_time = Double.parseDouble(line.substring(23));
                }
                else if(line.length() > 16 && line.substring(0, 15).equals("bonus_drop_rate")){
                    this.bonus_drop_rate = Integer.parseInt(line.substring(16));
                }
                else if(line.length() > 10 && line.substring(0, 9).equals("map_width")){
                    this.map_width = Integer.parseInt(line.substring(10));
                }
                else if(line.length() > 11 && line.substring(0, 10).equals("map_height")){
                    this.map_height = Integer.parseInt(line.substring(11));
                }
                else if(line.length() > 10 && line.substring(0, 9).equals("game_time")){
                    this.game_time = Integer.parseInt(line.substring(10));
                }
                else if(line.length() > 10 && line.substring(0, 9).equals("max_score")){
                    this.max_score = Integer.parseInt(line.substring(10));
                }
                else if(line.length() > 34 && line.substring(0, 33).equals("increase_speed_decrease_size_time")){
                    this.increase_speed_decrease_size_time = Double.parseDouble(line.substring(34));
                }
                else if(line.length() > 22 && line.substring(0, 21).equals("bullet_speed_increase")){
                    this.bullet_speed_increase = Integer.parseInt(line.substring(22));
                }
                else if(line.length() > 19 && line.substring(0, 18).equals("cell_size_decrease")){
                    this.cell_size_decrease = Integer.parseInt(line.substring(19));
                }
                else
                    System.out.println("Błędny parametr w linii " + lineCount);
                lineCount++;
            }
            if(this.max_bullet_count < 1) {
                System.out.println("Nieprawidłowy parametr max_bullet_count, ustawiono 2");
                this.max_bullet_count = 2;
            }
            if(this.new_object_spawn_time <= 0){
                System.out.println("Nieprawidłowy parametr new_object_spawn_time, ustawiono na 5");
                this.new_object_spawn_time = 5;
            }
            if(this.child_spawn_time <= 0){
                System.out.println("Nieprawidłowy parametr child_spawn_time, ustawiono na 5");
                this.child_spawn_time = 5;
            }
            if(this.strength_increase_time <= 0){
                System.out.println("Nieprawidłowy parametr strength_increase_time, ustawiono na 5");
                this.strength_increase_time = 5;
            }
            if(this.bonus_drop_rate < 1 || this.bonus_drop_rate > 99) {
                System.out.println("Nieprawidłowy parametr bonus_drop_rate, ustawiono na 20");
                this.bonus_drop_rate = 20;
            }
            if(this.map_width < 100 || this.map_width > 1100){
                System.out.println("Nieprawidłowy parametr map_width, ustawiono na 900");
                this.map_width = 900;
            }
            if(this.map_height < 100 || this.map_height > 800){
                System.out.println("Nieprawidłowa parametr map_height, ustawiono na 500");
                this.map_height = 500;
            }
            if(this.game_time < 1){
                System.out.println("Nieprawidłowy parametr game_time, ustawiono na 60");
                this.game_time = 60;
            }
            if(this.max_score < 1){
                System.out.println("Nieprawidłowy parametr max_score, ustawiono na 30");
                this.max_score = 30;
            }
            if(this.increase_speed_decrease_size_time <= 0) {
                System.out.println("Nieprawidłowy parametr increase_speed_decrease_size_time, ustawiono na 10");
                this.increase_speed_decrease_size_time = 10;
            }
            if(this.bullet_speed_increase < 1 || this.bullet_speed_increase > 200){
                System.out.println("Nieprawidłowy parametr bullet_speed_increase, ustawiono na 30");
                this.bullet_speed_increase = 30;
            }
            if(this.cell_size_decrease < 1 || this.cell_size_decrease > 50){
                System.out.println("Nieprawidłowy parametr bullet_speed_increase, ustawiono na 20");
                this.cell_size_decrease = 20;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Nie znaleziono pliku " + this.path);
        } catch (IOException e) {
            System.out.println("Nie można czytać z pliku " + this.path);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Błąd wczytywania danych z pliku " + this.path);
        }
    }

}