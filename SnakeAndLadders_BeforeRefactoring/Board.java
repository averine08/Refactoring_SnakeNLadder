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
    private SnakeHead snakeHead = new SnakeHead();
    private SnakeTail snakeTail = new SnakeTail();
    private BottomLadder bottomLadder = new BottomLadder();
    private TopLadder topLadder = new TopLadder();

    private boolean inGame = true;

    private Timer timer;
    private Image playerPiece1;
    private Image playerPiece2;
    private Image boardImage;
    private Image turnLabel;
    private Image Dice1;
    private Image Dice2;

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

    public void runGame(){
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graph){
        super.paintComponent(graph);
        if(inGame){
            graph.drawImage(boardImage, 0, 0, this);
            graph.drawImage(playerPiece2, player2.getX(), player2.getY(), this);
            graph.drawImage(playerPiece1, player1.getX(), player1.getY(), this);
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
        int ladderIdx = checkBottomLadder(player.getPos());
            if(ladderIdx != -1){
                moveUpLadder(ladderIdx);
            }

        int snakeIdx = checkSnakeHead(player.getPos());
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

    public int checkBottomLadder(int pos){
        for(int i=0; i<6; i++){
            if(pos == bottomLadder.getBottomLadder(i).getPos()){
                return i;
            }
        }
        return -1;
    }

    public int checkSnakeHead(int pos){
        for(int i=0; i<7; i++){
            if(pos == snakeHead.getHead(i).getPos()){
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

    public void moveUpLadder(int ladderIdx){
        int x = topLadder.gettopLadder(ladderIdx).getX();
        int y = topLadder.gettopLadder(ladderIdx).getY();
        int loc = topLadder.gettopLadder(ladderIdx).getPos();
        if(checkPlayer1()){
            player1.setPlayer(x, y, loc);
        }else{
            player2.setPlayer(x, y, loc);
        }
        repaint();
    }

    public void moveDownSnake(int snakeIdx){
        int x = snakeTail.getTail(snakeIdx).getX();
        int y = snakeTail.getTail(snakeIdx).getY();
        int loc = snakeTail.getTail(snakeIdx).getPos();
        if(checkPlayer1()){
            player1.setPlayer(x, y, loc);
        }else{
            player2.setPlayer(x, y, loc);
        }
        repaint();
    }

    public void movePlayer(Player player){
        int x = player.getX();
        int y = player.getY();
        int loc = player.getPos();

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
