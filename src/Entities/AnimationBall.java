package Entities;

import Game.Model;

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
