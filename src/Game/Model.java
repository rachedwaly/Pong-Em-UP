package Game;


import Entities.*;
import Entities.Bonus.Bonus;
import Entities.Bonus.LengthBonus;
import Entities.Bonus.LifeBonus;
import Entities.Bonus.ShieldBonus;
import Frame.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/***
 * Main model class, glueing the game's implementation together
 * Credit : Mostly Rached
 */
public class Model implements ActionListener, KeyListener {


    public static final boolean DEBUGMODE = false;
    public static Random random  = new Random();
    public static int HEIGHT=600,WIDTH=300;
    public static final int DELAY = 8;
    public Stick stick;
    public Ball b;
    protected HashMap<String,Image> allImages=new HashMap<>();
    protected Playground view;
    protected Entity entityBuffer1,entityBuffer2;
    protected Timer timer;
    protected int currentLvl=1;
    protected PongEmUp pongEmUp;

    public static int stickPhoto=0;
    public static int ballColor=0;


    public boolean playing=true;

    //on sépare les backgroundobjects des drawables vu qu'ils doivent etre dessinés avant ces
    // derniers
    protected ArrayList<Entity> drawables=new ArrayList<>();
    protected ArrayList<BackgroundObject> backgroundObjects=new ArrayList<>();
    protected ArrayList<Entity> physicalObjects = new ArrayList<>();
    protected ArrayList<Bonus> bonuses= new ArrayList<>();

    protected Enemy[] enemyList;
    protected ArrayList<Enemy> enemyCurrentList = new ArrayList<>();
    private int waveNumber = 0;
    private int[] waveSizeList = new int[]{3,4,2,5,1};


    /**
     * @param pongEmUp the main frame of the game
     * @throws IOException
     */
    public Model(PongEmUp pongEmUp){
        this.pongEmUp=pongEmUp;
    }

    /**
     * This method generate enemies
     */
    protected void generateEnemies() {
        int size = 0;
        for(int sizeList : waveSizeList)
            size += sizeList;

        enemyList = new Enemy[size];
        enemyList[0] = new Enemy(Enemy.SENTRY,100,0,100,200,this);
        enemyList[1] = new Enemy(Enemy.SENTRY,250,0,250,200,this);
        enemyList[2] = new Enemy(Enemy.SPINNER,125,0,125,300,this);

        enemyList[3] = new Enemy(Enemy.SPINNER,100,-100,150,200,this);
        enemyList[4] = new Enemy(Enemy.SPINNER,100,0,100,300,this);
        enemyList[5] = new Enemy(Enemy.SENTRY,100,0,100,200,this);
        enemyList[6] = new Enemy(Enemy.SENTRY,250,0,250,200,this);

        enemyList[7] = new Enemy(Enemy.SPINNER,75,0,75,200,this);
        enemyList[8] = new Enemy(Enemy.SPINNER,200,0,200,200,this);

        enemyList[9] = new Enemy(Enemy.SENTRY,100,0,100,200,this);
        enemyList[10] = new Enemy(Enemy.SENTRY,100,-100,100,100,this);
        enemyList[11] = new Enemy(Enemy.SENTRY,175,-100,175,100,this);
        enemyList[12] = new Enemy(Enemy.SENTRY,250,-100,250,100,this);
        enemyList[13] = new Enemy(Enemy.SENTRY,250,0,250,200,this);

        enemyList[14] = Enemy.bossify(new Enemy(Enemy.SPINNER,100,0,100,200,this));

    }


    /**
     * @param e this method will be called each tick of the timer in order to update the model
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.update();
        view.update();
    }


    public void addPhysicalObject(Entity e){
        physicalObjects.add(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        stick.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
    stick.keyReleased(e);
    }

    /**
     * This method will sove the collision of all the entities inside the physicalObjects
     * arrayList, Also it will move the entire entities
     */
    protected void update() {
        updateEnemies();
        solveCollisions();

        for (int i = 0; i < physicalObjects.size(); i++) {
            physicalObjects.get(i).update();
        }

        for (int i=0;i<backgroundObjects.size();i++){
            backgroundObjects.get(i).update();
        }
    }

    protected void updateEnemies(){
        if(enemyCurrentList.size() == 0 && waveNumber < 5){
            int enemyIndex = 0;
            for(int i = 0; i < waveNumber; i++)
                enemyIndex += waveSizeList[i]; //get the real index
            for(int i = enemyIndex; i < enemyIndex + waveSizeList[waveNumber]; i++) //add the right number of enemies
                enemyCurrentList.add(enemyList[i]);
            waveNumber++;

            for(Enemy enemy : enemyCurrentList){
                addPhysicalObject(enemy);
                addDrawable(enemy);
                for(Projectile projectile : enemy.projectiles){
                    addPhysicalObject(projectile);
                    addDrawable(projectile);
                }
            }
        }
    }
    protected void addDrawable(Entity e){
        drawables.add(e);
    }

    /**
     * Looping on all the boundaries of the entities to check for collisions and call the
     * whenCollided method of entity
     * Credit : Kevin
     */
    public void solveCollisions(){
        for(int i = 0; i < physicalObjects.size() - 1; i++){
            entityBuffer1 = physicalObjects.get(i);
            for(int j = i + 1; j < physicalObjects.size(); j++){
                entityBuffer2 = physicalObjects.get(j);
                if(entityBuffer1.getShape().intersects(entityBuffer2.getShape())){//Order of collision
                    physicalObjects.get(i).whenCollided(entityBuffer2);
                    physicalObjects.get(j).whenCollided(entityBuffer1);
                }
            }
        }
    }
    public Playground getView() {
        return view;
    }

    public int getPlayerSpawnLeft(){
        return stick.getLives();
    }
    public int getPlayerScore(){
        return stick.getScore();
    }


    public void stopTimer(){
        timer.stop();
    }
    public void startTimer(){
        timer.start();
    }


    public ArrayList<Entity> getDrawables() {
        return drawables;
    }

    public int getCurrentLvl() {
        return currentLvl;
    }

    public void setCurrentLvl(int currentLvl) {
        this.currentLvl = currentLvl;
    }


    protected void setUpBackgroundObjects() {
        switch(getCurrentLvl()){
            case 1:{
                addBackGroundObject(new BackgroundObject("cloud",50,60,this,new float[]{0.5f,
                        0.0f}));
                addBackGroundObject(new BackgroundObject("cloud",200,80,this,new float[]{-0.5f,
                        0.0f}));
                addBackGroundObject(new BackgroundObject("cloud",150,105,this,new float[]{1f,
                        0.0f}));
                addBackGroundObject(new BackgroundObject("plane",100,200,this,new float[]{-0.5f,
                        0.0f}));
                break;
            }
        }

    }

    public ArrayList<BackgroundObject> getBackgroundObjects() {
        return backgroundObjects;
    }

    private void addBackGroundObject(BackgroundObject backgroundObject){
        backgroundObjects.add(backgroundObject);
    }


    /** this method is called whenever an enemy is dead, we remove it from all the data
     * structures and assign null to the object so that the garbage collector of java erase it
     * later on
     * @param enemy
     */
    public void removeEnemy(Enemy enemy){
        enemyCurrentList.remove(enemy);
        stick.setScore(stick.getScore()+10);
        physicalObjects.remove(enemy);
        drawables.remove(enemy);
        spawnBonus(enemy.getX(), enemy.getY());
        enemy = null;
    }

    /** Same thing as removeEnemy but for bonus
     * @param bonus
     */
    //this method is called when a bonus is deleted
    public void removeBonus(Bonus bonus){
        physicalObjects.remove(bonus);
        drawables.remove(bonus);
        bonuses.remove(bonus);
        bonus = null;
    }

    /** this method will generate randomly each time an enemy dies
     * @param x
     * @param y
     */
    private void spawnBonus(float x, float y) {
        int gen=random.nextInt(3);

        switch (gen) {
            case 0 -> {
                ShieldBonus shieldBonus = new ShieldBonus("shield", x, y, 1000, stick, this);
                addDrawable(shieldBonus);
                addPhysicalObject(shieldBonus);
                addBonus(shieldBonus);
                break;
            }
            case 1 -> {
                LengthBonus lengthBonus = new LengthBonus("lengthBonus", x, y, 3000, stick, 50, this);
                addDrawable(lengthBonus);
                addPhysicalObject(lengthBonus);
                addBonus(lengthBonus);
                break;
            }
            case 2 -> {
                LifeBonus lifeBonus = new LifeBonus("lifeBonus", x, y, 0, stick, this);
                addDrawable(lifeBonus);
                addPhysicalObject(lifeBonus);
                addBonus(lifeBonus);
                break;
            }

        }

    }

    /**
     * this method will be used to handle pauses of the game
     * @return boolean
     */
    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    /**
     * this method will stop entirely the game
     */
    public void stopTheGame(){
        setPlaying(false);
        timer.stop();
        pongEmUp.gameOver();
    }


    public ArrayList<Bonus> getBonuses(){
        return bonuses;
    }

    private void addBonus(Bonus bonus){
        bonuses.add(bonus);
    }










}
