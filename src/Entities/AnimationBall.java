package Entities;

import Game.Model;
// I created this class to ovverride the move method for animation purposes
public class AnimationBall extends Ball{
    public AnimationBall(int x, int y, Model model) {
        super(x, y, model);
    }

    @Override
    public void move(){
        this.x += speed[0] * scalarSpeed;
        this.y += speed[1] * scalarSpeed;
        shape.update(this);
    }
}
