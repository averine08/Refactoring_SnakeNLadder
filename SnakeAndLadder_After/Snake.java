import java.util.ArrayList;

public class Snake {
    private Possition head = new Possition();
    private Possition tail = new Possition();

    public Snake(){
        createSnakes();
    }

    public Snake(int xH, int yH, int locH, int xT, int yT, int locT){        
        setHead(xH, yH, locH);
        setTail(xT, yT, locT);
    }

    public void setHead(int x, int y, int loc){
        this.head.setX(x);
        this.head.setY(y);
        this.head.setPos(loc);
    }
    public void setTail(int x, int y, int loc){
        this.tail.setX(x);
        this.tail.setY(y);
        this.tail.setPos(loc);
    }
    public Possition getHead(){
        return this.head;
    }
    public Possition getTail(){
        return this.tail;
    }

    // from public to private -> deficient encapsulation
    private ArrayList<Snake> newSnakes= new ArrayList<Snake>();

    // from public to private -> deficient encapsulation
    private void createSnakes(){
        newSnakes.add(new Snake(923, 395, 30, 761, 503, 7));
        newSnakes.add(new Snake(761, 287, 47, 707, 449, 15));
        newSnakes.add(new Snake(653, 233, 56, 491, 449, 19));
        newSnakes.add(new Snake(815, 125, 73, 923, 233, 51));
        newSnakes.add(new Snake(491, 71, 82, 491, 287, 42));
        newSnakes.add(new Snake(869, 17, 92, 707, 125, 75));
        newSnakes.add(new Snake(545, 17, 98, 707, 233, 55));
    }

    //giving data of arraylist to another class -> leaky encapsualtion
    // public ArrayList<Snake> getSnakeList(){
        // createSnakes();
    //     return newSnakes;
    // }
    public Snake getSnake(int i){
        return newSnakes.get(i);
    }
}
