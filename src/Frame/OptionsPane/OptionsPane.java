package Frame.OptionsPane;

import Frame.*;
import Game.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OptionsPane extends JPanel {
    private PongEmUp pongEmUp;


    public OptionsPane(PongEmUp pongEmUp){
        this.pongEmUp=pongEmUp;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        JLabel titleLabel=new JLabel("<html><strong><i> Customization Menu " +
                "</i></strong><hr></html>");
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        titleLabel.setBorder(new EmptyBorder(0,0,10,0));
        add(titleLabel,gbc);



        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(100,0,0,0);
        JLabel stickLabel=new JLabel("<html><strong><i>Select the looks of your stick " +
                "</i></strong></html>",SwingConstants.CENTER);
        stickLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        add(stickLabel,gbc);
        StickDrawingArea stickDrawingArea=new StickDrawingArea();
        gbc.insets = new Insets(0,0,0,0);
        add(stickDrawingArea,gbc);
        stickDrawingArea.setBorder(new EmptyBorder(0,0,50,0));

        JLabel ballLabel=new JLabel("<html><strong><i>Select the color of your ball " +
                "</i></strong></html>",SwingConstants.CENTER);
        gbc.insets = new Insets(0,0,0,0);
        ballLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        add(ballLabel,gbc);
        BallDrawingArea ballDrawingArea=new BallDrawingArea();
        gbc.insets = new Insets(0,0,30,0);
        add(ballDrawingArea,gbc);



        gbc.fill=GridBagConstraints.NONE;
        JButton setButton=new JButton("Validate choice");
        setButton.setPreferredSize(new Dimension(150,40));
        gbc.insets = new Insets(10,0,0,0);
        add(setButton,gbc);
        setButton.addActionListener(e -> {
            Model.ballColor=ballDrawingArea.getIndex();
            Model.stickPhoto=stickDrawingArea.getIndex();
        });



        JButton backButton=new JButton("Return");
        backButton.setPreferredSize(new Dimension(150,40));


        add(backButton,gbc);

        backButton.addActionListener(e -> {
            pongEmUp.exitOptionsMenu();
        });






    }
}
