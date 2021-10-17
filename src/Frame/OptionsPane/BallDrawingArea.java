package Frame.OptionsPane;

import Frame.OptionsPane.Buttons.Button;
import Frame.OptionsPane.Buttons.NextButton;
import Frame.OptionsPane.Buttons.PreviousButton;
import Game.Model;

import javax.swing.*;

import static java.lang.Math.abs;

public class BallDrawingArea extends JPanel{
    private JButton nextButton=new Button();
    private JButton previousButton=new Button();



    private int index= Model.ballColor;
    private BallPreview ballPreview=new BallPreview(index);


    public BallDrawingArea(){
        nextButton.setUI(new NextButton());
        previousButton.setUI(new PreviousButton());
        add(previousButton);
        add(ballPreview);
        add(nextButton);
        nextButton.addActionListener(e -> goToNext());
        previousButton.addActionListener(e->goToPrevious());
    }

    public void goToNext(){
        index+=1;
        ballPreview.setColor(index);
        repaint();
    }
    public void goToPrevious(){
        index-=1;
        ballPreview.setColor(index);
        repaint();

    }

    public int getIndex() {
        return index;
    }
}
