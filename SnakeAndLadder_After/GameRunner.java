import javax.swing.*;
import java.awt.*;

public class GameRunner extends JFrame{
    GameRunner(){
        add(new Board(960, 540, 20));
        setResizable(false);
        pack();
        setTitle("Snake and Ladder");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(()->{
            JFrame game = new GameRunner();
            game.setVisible(true);
        });
    }
}

