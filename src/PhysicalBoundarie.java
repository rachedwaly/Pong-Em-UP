import java.awt.*;

public class PhysicalBoundarie extends Rectangle {
    public boolean isOrientation() {
        return orientation;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    private boolean orientation;
    public PhysicalBoundarie(int x,int y, int width,int height,boolean o){
        super(x,y,width,height);
        orientation=o;
    }

}
