import java.util.ArrayList;

public class SnakeTail extends Snake{
    private ArrayList<SnakeTail> snakeTails = new ArrayList<SnakeTail>();

    public SnakeTail(){
        super();
        createTail();
    }

    public SnakeTail(int x, int y, int pos){
        super(x, y, pos);
    }

    public void createTail(){
        snakeTails.add(new SnakeTail(761, 503, 7));
        snakeTails.add(new SnakeTail(707, 449, 15));
        snakeTails.add(new SnakeTail(491, 449, 19));
        snakeTails.add(new SnakeTail(923, 233, 51));
        snakeTails.add(new SnakeTail(491, 287, 42));
        snakeTails.add(new SnakeTail(707, 125, 75));
        snakeTails.add(new SnakeTail(707, 233, 55));
    }

    public SnakeTail getTail(int i){
        return snakeTails.get(i);
    }
}
