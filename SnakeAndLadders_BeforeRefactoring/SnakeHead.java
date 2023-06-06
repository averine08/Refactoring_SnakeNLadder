import java.util.ArrayList;

public class SnakeHead extends Snake{
    private ArrayList<SnakeHead> snakeHeads = new ArrayList<SnakeHead>();

    public SnakeHead(){
        super();
        createHead();
    }

    public SnakeHead(int x, int y, int pos){
        super(x, y, pos);
    }

    public void createHead(){
        snakeHeads.add(new SnakeHead(923, 395, 30));
        snakeHeads.add(new SnakeHead(761, 287, 47));
        snakeHeads.add(new SnakeHead(653, 233, 56));
        snakeHeads.add(new SnakeHead(815, 125, 73));
        snakeHeads.add(new SnakeHead(491, 71, 82));
        snakeHeads.add(new SnakeHead(869, 17, 92));
        snakeHeads.add(new SnakeHead(545, 17, 98));
    }

    public SnakeHead getHead(int i){
        return snakeHeads.get(i);
    }
}