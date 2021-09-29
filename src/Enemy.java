import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Entity{ //Eventuellement transformer en LineEnemy
    public int fX,fY; //pos "finale" de l'objet, ou sa loop de comportement commence
    protected boolean loopMode = false; //false : se deplace vers (fX,fY) || true : effectue sa loop de behavior
    public Projectile[] projectiles = new Projectile[Entity.PROJECTILEBUFFER];
    private int projectileIndex = 0;
    protected int innerTimer = 0;

    static final String SENTRY = "SENTRY";
    private Enemy(int x0, int y0, int fX, int fY){
        x = x0;
        y = y0;

        this.fX = fX;
        this.fY = fY;

        lookDirection = new int[]{0,1};

        if(fX - x != 0)
            speed[0] = (fX - x)/Math.abs(fX - x);
        else
            speed[0] = 0;
        if(fY - y != 0)
            speed[1] = (fY - y)/Math.abs(fY - y);
        else
            speed[1] = 0;


    }

    public Enemy(String name, int x0, int y0 , int fX, int fY){
        this(x0,y0,fX,fY);
        switch(name){
            case "SENTRY":
                width = 20;
                height = 20;
                speed[0] *= 2;
                speed[1] *= 2;
                for(int i = 0; i < projectiles.length; i++)
                    projectiles[i] = new Projectile(5,20,10,new int[]{5,5});
                color = Color.BLUE;
                this.name = name;
                break;
            case "JUGGERNAUT":
                break;
            case "SPINNER":
                break;

        }
    }


    public void update(){
        move();
        behaviorUpdate();
        innerTimer += Model.DELAY;
    }

    public void move(){ //eventually move to abstract


        x += speed[0];
        y += speed[1];

        if(!loopMode && Math.abs(fX - x) <= 2f && Math.abs(fY - y) <= 2f) { //home reached
            loopMode = true;
            innerTimer = 4000; //soft reset the timer to control the loop easily
        }

    }

    public void fire(){
        projectiles[projectileIndex].fire(this); // shoot vertically
        projectileIndex = (projectileIndex + 1) % PROJECTILEBUFFER;
    }


    public void behaviorUpdate(){
        if(loopMode){
            switch(name){
                case "SENTRY":

                    if(innerTimer % 2000 < 1000){
                        speed[0] = - 1;
                        speed[1] = 0;

                    }else{
                        speed[0] = 1;
                        speed[1] = 0;
                    }

                    if(innerTimer % 400 == 0)
                        fire();
                    break;
                case "JUGGERNAUT":
                    break;
                case "SPINNER":
                    break;

            }
        }

    }

    @Override
    public void drawEntity(Graphics g){
        g.setColor(this.color);
        g.fillRect(x,y,width,height);
    }

    @Override
    public ArrayList<PhysicalBoundarie> getPhysicalBoundaries() {
        PhysicalBoundarie c1=new PhysicalBoundarie(x,y,1,height,false); //left side
        PhysicalBoundarie c2=new PhysicalBoundarie(x+width-1,y,1,height,false); // right side
        PhysicalBoundarie c3=new PhysicalBoundarie(x,y,width,1,true); //top side
        PhysicalBoundarie c4=new PhysicalBoundarie(x,y+height-1,width,1,true); //bottom side
        ArrayList <PhysicalBoundarie> list=new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        return list;
    }

}
