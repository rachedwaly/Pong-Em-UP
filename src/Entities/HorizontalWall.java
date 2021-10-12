package Entities;

import java.util.ArrayList;

public class HorizontalWall extends Wall{
    public HorizontalWall(int x,int y,int width,int height){
        super(x,y,width,height);
        orientation=true;
    }

    @Override
    public ArrayList<PhysicalBoundarie> getPhysicalBoundaries() {
        PhysicalBoundarie c=new PhysicalBoundarie((int)x,(int)y,width,height,true);
        ArrayList <PhysicalBoundarie> list=new ArrayList<>();
        list.add(c);
        return list;
    }
}
