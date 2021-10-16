package AltLib;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageLoader {
    public static HashMap<String, Image> allImages;
    public static BufferedImage healthImage;
    public static BufferedImage[] bgImage;
    public static BufferedImage cloudImage;
    public static BufferedImage[] explosionAnimation;
    public static BufferedImage sentryImage;
    public static BufferedImage sentryDamaged;
    public static BufferedImage spinnerImage;
    public static BufferedImage spinnerDamaged;
    public static BufferedImage planeImage;
    public static BufferedImage gameoverImage;
    public static BufferedImage shieldBonusImage;
    public static BufferedImage shieldStickImage;
    public static BufferedImage muscleBonusImage;

    static {
        try {
            healthImage = ImageIO.read(new File("Resources/health.png"));
            bgImage = new BufferedImage[2];
            bgImage[0] = ImageIO.read(new File("Resources/background lvl1.jpg"));
            bgImage[1] = ImageIO.read(new File("Resources/background lvl2.jpg"));
            cloudImage = ImageIO.read(new File("Resources/cloud.png"));
            explosionAnimation = new BufferedImage[9];
            explosionAnimation[0] = ImageIO.read(new File("Resources/explosion_animation/1death.png"));
            explosionAnimation[1] = ImageIO.read(new File("Resources/explosion_animation/2death.png"));
            explosionAnimation[2] = ImageIO.read(new File("Resources/explosion_animation/3death.png"));
            explosionAnimation[3] = ImageIO.read(new File("Resources/explosion_animation/4death.png"));
            explosionAnimation[4] = ImageIO.read(new File("Resources/explosion_animation/5death.png"));
            explosionAnimation[5] = ImageIO.read(new File("Resources/explosion_animation/6death.png"));
            explosionAnimation[6] = ImageIO.read(new File("Resources/explosion_animation/7death.png"));
            explosionAnimation[7] = ImageIO.read(new File("Resources/explosion_animation/8death.png"));
            explosionAnimation[8] = ImageIO.read(new File("Resources/explosion_animation/9death.png"));
            sentryImage = ImageIO.read(new File("Resources/sentry.png"));
            sentryDamaged = ImageIO.read(new File("Resources/sentryDamaged.png"));
            spinnerImage = ImageIO.read(new File("Resources/ufo.png"));
            spinnerDamaged = ImageIO.read(new File("Resources/ufoDamaged.png"));
            planeImage = ImageIO.read(new File("Resources/plane.png"));
            gameoverImage = ImageIO.read(new File("Resources/gameover.png"));
            shieldBonusImage = ImageIO.read(new File("Resources/shield.png"));
            shieldStickImage = ImageIO.read(new File("Resources/shieldStick.png"));
            muscleBonusImage = ImageIO.read(new File("Resources/muscle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
