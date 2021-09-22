public class Enemy extends Entity{
    public int iX,iY; //pos initiale de l'objet
    public int fX,fY; //pos finale de l'objet

    public Enemy(){

        super();
        iX = fX = x;
        iY = fY = y;

    }


    public void move(){
        x = (int)lerp(iX,fX, 0.5f);
        y = (int)lerp(iY,fY, 0.5f);
    }

    public static float lerp(int a, int b, float prop){
        return prop * a + (1 - prop) * b;
    }

}
