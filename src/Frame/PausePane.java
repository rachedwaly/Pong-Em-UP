package Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class PausePane extends JPanel {


    private PongEmUp pongemup;

    public PausePane(PongEmUp pongemup){
        this.pongemup=pongemup;
        setOpaque(true);
        setVisible(false);


        addMouseListener(new MouseAdapter(){}); //adding new mouse listener specific only to this
        //jpanel

        setBorder(new EmptyBorder(50, 10, 10, 10));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        JLabel label=new JLabel("<html><h1><strong><i>Pong'em UP " +
                "</i></strong></h1><hr></html>");
        label.setForeground(new Color(150));
        add(label, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JPanel menu = new JPanel(new GridBagLayout());
        menu.setOpaque(false);

        JButton options=new JButton("Options");
        JButton goBackToMainMenu=new JButton("Go Back To Main Menu");
        JButton goBackToDesktop=new JButton("Go To Desktop");
        JButton resumeButton=new JButton("Resume");
        JButton retryButton=new JButton("Retry");

        resumeButton.addActionListener(e -> {
            pongemup.resumeGame();
            discardPanel();
        });

        options.addActionListener(e -> {
            pongemup.goToOptions();
            discardPanel();
        });

        goBackToDesktop.addActionListener(e -> pongemup.quit());

        goBackToMainMenu.addActionListener(e -> {
            pongemup.goBackToMainMenu();
            discardPanel();
        });

        goBackToMainMenu.addActionListener(e -> {
            pongemup.restartGame();
            discardPanel();
        });

        menu.add(resumeButton,gbc);
        menu.add(options,gbc);
        menu.add(goBackToMainMenu,gbc);
        menu.add(goBackToDesktop,gbc);

        gbc.weighty=1;
        add(menu,gbc);

    }

    private void discardPanel() {
        setOpaque(false);
        setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0,0,getWidth(),getHeight());
    }



}
