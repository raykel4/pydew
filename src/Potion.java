import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Potion extends JPanel implements ActionListener, KeyListener{
    private class Tile{
        int x;
        int y;
        Item plant;

        Tile(int x, int y){
            this.x = x;
            this.y = y;
        }

        Tile(int x, int y, Item plant){
            this.x = x;
            this.y = y;
            this.plant = plant;
        }

        public Item getItem(){
            return this.plant;
        }

        public void changeItem(){
            this.plant = getRandomPlant();
        }

    }

    public class Item {
        private String name;
        private int amount;
        private String desc;
        private Tile location;
    
        Item(){
            name = "plant";
            amount = 1;
            desc = "green";
        }
    
        Item(String name, int amount, String desc, Tile location){
           this.name = name;
           this.amount = amount;
           this.desc = desc; 
           this.location = location;
        }
    
        public int getAmount(){
            return this.amount;
        } 
    
        public void updateAmount(){
            this.amount++;
        }
    
        public String getName(){
            return this.name;
        }
    
        @Override
        public String toString(){
            return this.name + " is " + this.desc + " of amount: " + this.amount;
        }
    }
    



    int boardWidth;
    int boardHeight;
    int tileSize = 25;
    
    //tiles
    Tile player;
    Tile plant;
    Random random;

   //Inventory
    Item radish = new Item("Radish", 1, "a spicy little guy", plant);
    Item spinach = new Item("Spinach", 1, "healthy green", plant);
    Item chive = new Item("Chive", 1, "part of the onion family", plant);
    Item pepper = new Item("Pepper", 1, "rainbow", plant);
    Item broccoli = new Item("Broccoli", 1, "tiny tree", plant);
    ArrayList<Item> inventory = new ArrayList<Item>(3); 

    //plants
    ArrayList<Item> plants = new ArrayList<Item>();
    {
        plants.add(radish);
        plants.add(spinach);
        plants.add(chive);
        plants.add(pepper);
        plants.add(broccoli);
        plants.add(new Item("Carrot", 1, "a root vegetable", plant));
    }

    //game logic
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;

    public Item getRandomPlant(){
        int index = (int)(Math.random() * plants.size()); 
        return plants.get(index);
    }

    Potion(int boardWidth, int boardHeight){
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        //player tile
        player = new Tile(1,1);
        plant = new Tile(10, 10, getRandomPlant());
        random = new Random();
        placePlant();

        velocityX = 0;
        velocityY = 0;

        gameLoop = new Timer(100, this);
        gameLoop.start();

    }

    public void placePlant() {
        plant.x = random.nextInt(boardWidth/tileSize);
        plant.y = random.nextInt(boardHeight/tileSize);
        plant.changeItem();
    }

        public void harvest(){
        if (inventory.contains(plant.getItem())){
            plant.getItem().updateAmount();
        } else{
            inventory.add(plant.getItem());
        }
            placePlant();
        }

    public boolean collision(Tile tile1, Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fill3DRect(player.x*tileSize, player.y*tileSize, tileSize, tileSize, true);
        g.setColor(Color.green);
        g.fill3DRect(plant.x * tileSize, plant.y * tileSize, tileSize, tileSize, true);
        if (inventory.isEmpty()){
        } else {
            //g.drawString(plant.getItem().toString(), 30, 30);
            g.setColor(Color.white);
            for (int i = 0; i < inventory.size(); i++){
                g.drawString(inventory.get(i).toString(), 30, 30+(i*20));
            }
        }
    }

    public void move(){
        if (collision(player, plant)){
            harvest();
        }
        player.x += velocityX;
        player.y += velocityY;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub 
        if(e.getKeyCode() == KeyEvent.VK_UP //&& velocityY != 1
        ){
            velocityX = 0;
            velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN //&& velocityY != -1
        ){
            velocityX = 0;
            velocityY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT //&& velocityX != -1
        ){
            velocityX = 1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT //&& velocityX != 1
        ){
            velocityX = -1;
            velocityY = 0;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
        if (e.getKeyCode() == KeyEvent.VK_UP){
            velocityX = 0;
            velocityY = 0;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN
        ){
            velocityX = 0;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT 
        ){
            velocityX = 0;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT 
        ){
            velocityX = 0;
            velocityY = 0;
        }
    }


}