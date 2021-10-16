package Entities;

import Game.Model;

public class HorizontalWall extends Wall{
    public HorizontalWall(int x, int y, int width, int height, Model model){
        super(x,y,width,height, model);
    }

    /*@Override
    public ArrayList<PhysicalBoundarie> getPhysicalBoundaries() {
        PhysicalBoundarie c=new PhysicalBoundarie((int)x,(int)y,width,height,true);
        ArrayList <PhysicalBoundarie> list=new ArrayList<>();
        list.add(c);
        return list;
    }*/
}
