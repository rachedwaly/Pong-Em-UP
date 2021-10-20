package Frame.OptionsPane;

import Frame.OptionsPane.Buttons.Button;
import Frame.OptionsPane.Buttons.NextButton;
import Frame.OptionsPane.Buttons.PreviousButton;
import Game.Handler;

import javax.swing.*;

import static java.lang.Math.abs;

public class StickDrawingArea extends JPanel{
    private JButton nextButton=new Button();
    private JButton previousButton=new Button();
    private int index= Handler.stickPhoto;
    private StickPreview stickPreview=new StickPreview(index);

    public StickDrawingArea(){
        nextButton.setUI(new NextButton());
        previousButton.setUI(new PreviousButton());
        add(previousButton);
        add(stickPreview);
        add(nextButton);
        nextButton.addActionListener(e -> goToNext());
        previousButton.addActionListener(e->goToPrevious());
    }

    public void goToNext(){
        index+=1;
        stickPreview.setPhoto(index);
        repaint();
    }


    public void goToPrevious(){
        index-=1;
        stickPreview.setPhoto(index);
        repaint();

    }

    public int getIndex() {
        return index;
    }
}
