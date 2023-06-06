import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener{
    private final int Width;
    private final int Height;
    private final int delay = 500;
    private int moveHolder;
    private int diceHolder;
    private int turn = 1;
    private String endMessage;

    private Player player1 = new Player(383, 503, 0);
    private Player player2 = new Player(383, 503, 0);
    private Dice dice = new Dice();
    private Ladder ladder = new Ladder();
    private Snake snake = new Snake();

    private boolean inGame = true;

    private Timer timer;
    private Image playerPiece1;
    private Image playerPiece2;
    private Image boardImage;
    private Image turnLabel;
    private Image Dice1;
    private Image Dice2;

    // dihapus untuk leaky encapsulation
    // private ArrayList<Ladder> newLadders = ladder.getLadderList();
    // private ArrayList<Snake> newSnakes= snake.getSnakeList();

    public Board(int width, int height, int pixel){
        this.Width = width;
        this.Height = height;

        setGame();
    }

    public void setGame(){
        addKeyListener(new eventAdapter());
        setPreferredSize(new Dimension(Width, Height));
        setFocusable(true);
        playerPiece1 = player1.setPiece("#FFFFFF");
        playerPiece2 = player2.setPiece("#000000");
        Dice1 = dice.setDice("Assets/side_6.png");
        Dice2 = dice.setDice("Assets/side_6.png");
        turnLabel = setTurnLabel("Player 1");
        boardImage = setBoard();
    }

    public Image setBoard(){
        ImageIcon bgIcon = new ImageIcon("Assets/BoardFin.png");
        BufferedImage bg = new BufferedImage(960, 540, BufferedImage.TYPE_INT_ARGB);
        Graphics2D background = bg.createGraphics();
        background.drawImage(bgIcon.getImage(), 0, 0, null);
        background.dispose();

        return bg;
    }

    public Image setTurnLabel(String turnMessage){
        BufferedImage lbl = new BufferedImage(165, 70, BufferedImage.TYPE_INT_ARGB);
        Graphics2D label = lbl.createGraphics();
        Font font = new Font("Calibri", Font.BOLD, 28);
        FontMetrics metrix = getFontMetrics(font);

        label.setPaint(Color.orange);
        label.fillRoundRect(0, 0, 165, 70, 50, 50);
        label.setColor(Color.black);
        label.setFont(font);
        label.drawString(turnMessage, (165 - metrix.stringWidth(turnMessage))/2, 43);
        label.dispose();

        return lbl;
    }

    // move to dice class since it's dice's characteristic => broken modularization
    // public Image setDice(String path){
    //     ImageIcon dcIcon = new ImageIcon(path);
    //     BufferedImage diceIMG = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
    //     Graphics2D dice = diceIMG.createGraphics();
    //     dice.drawImage(dcIcon.getImage(), 0, 0, 90, 90, null);
    //     dice.dispose();

    //     return diceIMG;
    // }

    // move to player class since it's players's characteristic => broken modularization
    // public Image setPiece(String code){
    //     BufferedImage player = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
    //     Graphics2D piece = player.createGraphics();

    //     piece.setPaint(Color.decode(code));
    //     piece.fillRect(0, 0, 20, 20);
    //     piece.dispose();

    //     return player;
    // }

    public void runGame(){
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graph){
        super.paintComponent(graph);
        if(inGame){
            graph.drawImage(boardImage, 0, 0, this);
            graph.drawImage(playerPiece2, player2.getPlayer().getX(), player2.getPlayer().getY(), this);
            graph.drawImage(playerPiece1, player1.getPlayer().getX(), player1.getPlayer().getY(), this);
            graph.drawImage(Dice1, 102, 137, this);
            graph.drawImage(Dice2, 227, 137, this);
            graph.drawImage(turnLabel, 128, 348, this);
            Toolkit.getDefaultToolkit().sync();
        }else{
            endGame(graph);
        }
    }

    public void endGame(Graphics graph){
        Font text = new Font("Times New Roman", Font.BOLD, 40);
        FontMetrics metrics = getFontMetrics(text);

        graph.setColor(Color.BLACK);
        graph.setFont(text);
        graph.drawString(endMessage, (Width-metrics.stringWidth(endMessage))/2, Height/2);
    }

    public boolean checkPlayer1(){
        if(turn%2 == 1){
            return true;
        }
        return false;
    }

    public void checkLadderAndSnake(Player player){
        int ladderIdx = checkBottomLadder(player.getPlayer().getPos());
            if(ladderIdx != -1){
                moveUpLadder(ladderIdx);
            }

        int snakeIdx = checkSnakeHead(player.getPlayer().getPos());
            if(snakeIdx != -1){
                moveDownSnake(snakeIdx);
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame && moveHolder > 0){
            if(checkPlayer1()){
                movePlayer(player1);
            }else{
                movePlayer(player2);
            }
            moveHolder--;
        }else if(moveHolder <= 0 || !inGame){
            timer.stop();

            if(checkPlayer1()){
                checkLadderAndSnake(player1);
            }else{
                checkLadderAndSnake(player2);
            }

            if(diceHolder != 12){
                turn++;
                if(checkPlayer1()){
                    turnLabel = setTurnLabel("Player 1");
                }else{
                    turnLabel = setTurnLabel("Player 2");
                }
                repaint();
            }
        }
    }

    // diubah untuk leaky encapsulation
    public int checkBottomLadder(int pos){
        for(int i=0; i<6; i++){
            if(pos == ladder.getLadder(i).getBottom().getPos()){
                return i;
            }
        }
        return -1;
    }

    // diubah untuk leaky encapsulation
    public int checkSnakeHead(int pos){
        for(int i=0; i<7; i++){
            if(pos == snake.getSnake(i).getHead().getPos()){
                return i;
            }
        }
        return -1;
    }

    public int rollMyDice(){
        int diceA = dice.rollDice();
        int diceB = dice.rollDice();

        Dice1 = dice.setDice(dice.dicePath(diceA));
        Dice2 = dice.setDice(dice.dicePath(diceB));

        repaint();

        return diceA + diceB;
    }

    // diubah untuk leaky encapsulation
    public void moveUpLadder(int ladderIdx){
        int x = ladder.getLadder(ladderIdx).getTop().getX();
        int y = ladder.getLadder(ladderIdx).getTop().getY();
        int loc = ladder.getLadder(ladderIdx).getTop().getPos();
        if(checkPlayer1()){
            player1.setPlayer(x, y, loc);
        }else{
            player2.setPlayer(x, y, loc);
        }
        repaint();
    }

    // diubah untuk leaky encapsulation
    public void moveDownSnake(int snakeIdx){
        int x = snake.getSnake(snakeIdx).getTail().getX();
        int y = snake.getSnake(snakeIdx).getTail().getY();
        int loc = snake.getSnake(snakeIdx).getTail().getPos();
        if(checkPlayer1()){
            player1.setPlayer(x, y, loc);
        }else{
            player2.setPlayer(x, y, loc);
        }
        repaint();
    }

    public void movePlayer(Player player){
        int x = player.getPlayer().getX();
        int y = player.getPlayer().getY();
        int loc = player.getPlayer().getPos();

        if(loc == 100){
            moveHolder = 0;
            if(checkPlayer1()){
                endMessage = "Game Over, Player 1 WIN !!!";
            }else{
                endMessage = "Game Over, Player 2 WIN !!!";
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inGame = false;
            repaint();
        }

        int pos = (int)Math.floor(loc/10);

        if(pos%2 == 0 && loc%10 != 0){
            x += 54;
        }else if(pos%2 == 1 && loc%10 != 0){
            x -= 54;
        }else if(loc%10 == 0 && loc != 0){
            y -= 54;
        }else if(loc == 0){
            x += 54;
        }

        loc++;

        player.setPlayer(x, y, loc);
        repaint();
    }

    private class eventAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            if(moveHolder <= 0){
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_ENTER){
                    moveHolder = rollMyDice();
                    diceHolder = moveHolder;
                    runGame();
                }
            }else{
                return;
            }
        }
    }
}
