import java.util.ArrayList;

public class TopLadder extends Ladder{
    private ArrayList<TopLadder> topLadders = new ArrayList<TopLadder>();

    public TopLadder(){
        super();
        createTop();
    }

    public TopLadder(int x, int y, int pos){
        super(x, y, pos);
    }

    public void createTop(){
        topLadders.add(new TopLadder(653, 395, 25));
        topLadders.add(new TopLadder(491, 341, 39));
        topLadders.add(new TopLadder(437, 125, 80));
        topLadders.add(new TopLadder(653, 125, 76));
        topLadders.add(new TopLadder(761, 125, 74));
        topLadders.add(new TopLadder(869, 71, 89));
    }

    public TopLadder gettopLadder(int i){
        return topLadders.get(i);
    }
}
