package AltLib;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * Loads images in a separate static class
 */
public class ImageLoader {
    public static HashMap<String, Image> allImages;
    public static BufferedImage healthImage;
    public static BufferedImage[] bgImage;
    public static BufferedImage cloudImage;
    public static BufferedImage[] explosionAnimation;
    public static BufferedImage sentryImage;
    public static BufferedImage[] stickImage;
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
            healthImage = ImageIO.read(ImageLoader.class.getResource("/health.png"));
            bgImage = new BufferedImage[2];
            bgImage[0] = ImageIO.read((ImageLoader.class.getResource("/background lvl1.jpg")));
            bgImage[1] = ImageIO.read(ImageLoader.class.getResource("/background lvl2.jpg"));
            cloudImage = ImageIO.read(ImageLoader.class.getResource("/cloud.png"));
            explosionAnimation = new BufferedImage[9];
            explosionAnimation[0] = ImageIO.read(ImageLoader.class.getResource("/explosion_animation/1death.png"));
            explosionAnimation[1] = ImageIO.read(ImageLoader.class.getResource("/explosion_animation/2death.png"));
            explosionAnimation[2] = ImageIO.read(ImageLoader.class.getResource("/explosion_animation/3death.png"));
            explosionAnimation[3] = ImageIO.read(ImageLoader.class.getResource("/explosion_animation/4death.png"));
            explosionAnimation[4] = ImageIO.read(ImageLoader.class.getResource("/explosion_animation/5death.png"));
            explosionAnimation[5] = ImageIO.read(ImageLoader.class.getResource("/explosion_animation/6death.png"));
            explosionAnimation[6] = ImageIO.read(ImageLoader.class.getResource("/explosion_animation/7death.png"));
            explosionAnimation[7] = ImageIO.read(ImageLoader.class.getResource("/explosion_animation/8death.png"));
            explosionAnimation[8] = ImageIO.read(ImageLoader.class.getResource("/explosion_animation/9death.png"));
            sentryImage = ImageIO.read(ImageLoader.class.getResource("/sentry.png"));
            sentryDamaged = ImageIO.read(ImageLoader.class.getResource("/sentryDamaged.png"));
            spinnerImage = ImageIO.read(ImageLoader.class.getResource("/ufo.png"));
            spinnerDamaged = ImageIO.read(ImageLoader.class.getResource("/ufoDamaged.png"));
            planeImage = ImageIO.read(ImageLoader.class.getResource("/plane.png"));
            gameoverImage = ImageIO.read(ImageLoader.class.getResource("/gameover.png"));
            shieldBonusImage = ImageIO.read(ImageLoader.class.getResource("/shield.png"));
            shieldStickImage = ImageIO.read(ImageLoader.class.getResource("/shieldStick.png"));
            muscleBonusImage = ImageIO.read(ImageLoader.class.getResource("/muscle.png"));
            stickImage=new BufferedImage[4];
            stickImage[0]=ImageIO.read(ImageLoader.class.getResource("/Stick/texture0.png"));
            stickImage[1]=ImageIO.read(ImageLoader.class.getResource("/Stick/texture1.png"));
            stickImage[2]=ImageIO.read(ImageLoader.class.getResource("/Stick/texture2.png"));
            stickImage[3]=ImageIO.read(ImageLoader.class.getResource("/Stick/texture3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
