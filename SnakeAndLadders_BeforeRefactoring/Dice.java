import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Dice {
    public String dicePath(int side){
        if(side == 1){
            return "Assets/side_1.png";
        }else if(side == 2){
            return "Assets/side_2.png";
        }else if(side == 3){
            return "Assets/side_3.png";
        }else if(side == 4){
            return "Assets/side_4.png";
        }else if(side == 5){
            return "Assets/side_5.png";
        }else if(side == 6){
            return "Assets/side_6.png";
        }
        return "/Assets/side_6_pip.png";
    }

    public Image setDice(String path){
        ImageIcon dcIcon = new ImageIcon(path);
        BufferedImage diceIMG = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
        Graphics2D dice = diceIMG.createGraphics();
        dice.drawImage(dcIcon.getImage(), 0, 0, 90, 90, null);
        dice.dispose();

        return diceIMG;
    }

    public int rollDice(){
        Random dice = new Random();
        int side = Math.abs(dice.nextInt()) % 6 + 1;
        return side;
    }
}