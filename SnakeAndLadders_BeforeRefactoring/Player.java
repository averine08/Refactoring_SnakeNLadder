import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Possition{

    public Player(int x, int y, int loc){
        this.setX(x);
        this.setY(y);
        this.setPos(loc);
    }
    public void setPlayer(int x, int y, int loc){
        this.setX(x);
        this.setY(y);
        this.setPos(loc);
    }
    public Player getPossition(){
        return this;
    }

    public Image setPiece(String code){
        BufferedImage player = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
        Graphics2D piece = player.createGraphics();

        piece.setPaint(Color.decode(code));
        piece.fillRect(0, 0, 20, 20);
        piece.dispose();

        return player;
    }
}
