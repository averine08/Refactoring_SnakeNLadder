import java.util.ArrayList;

public class Ladder {
    private Possition top = new Possition();
    private Possition bottom = new Possition();

    public Ladder(){
        createLadders();
    }

    public Ladder(int xT, int yT, int locT, int xB, int yB, int locB){
        setTop(xT, yT, locT);
        setBottom(xB, yB, locB);
    }

    public void setTop(int x, int y, int loc){
        this.top.setX(x);
        this.top.setY(y);
        this.top.setPos(loc);
    }
    public void setBottom(int x, int y, int loc){
        this.bottom.setX(x);
        this.bottom.setY(y);
        this.bottom.setPos(loc);
    }
    public Possition getTop(){
        return this.top;
    }
    public Possition getBottom(){
        return this.bottom;
    }

    //from public to private -> deficient encapsulation
    private ArrayList<Ladder> newLadders = new ArrayList<Ladder>();

    //from public to private -> deficient encapsulation
    private void createLadders(){
        newLadders.add(new Ladder(653, 395, 25, 599, 503, 4));
        newLadders.add(new Ladder(491, 341, 39, 437, 395, 21));
        newLadders.add(new Ladder(437, 125, 80, 545, 179, 63));
        newLadders.add(new Ladder(653, 125, 76, 545, 287, 43));
        newLadders.add(new Ladder(761, 125, 74, 869, 395, 29));
        newLadders.add(new Ladder(869, 71, 89, 923, 125, 71));
    }

    //giving data of arraylist to another class -> leaky encapsualtion
    // public ArrayList<Ladder> getLadderList(){
    //     createLadders();
    //     return newLadders;
    // }
    public Ladder getLadder(int i){
        return newLadders.get(i);
    }
}
