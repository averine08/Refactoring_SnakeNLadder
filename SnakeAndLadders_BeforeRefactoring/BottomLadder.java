import java.util.ArrayList;

public class BottomLadder extends Ladder{
    private ArrayList<BottomLadder> bottomLadders = new ArrayList<BottomLadder>();

    public BottomLadder(){
        super();
        createBottom();
    }

    public BottomLadder(int x, int y, int pos){
        super(x, y, pos);
    }

    public void createBottom(){
        bottomLadders.add(new BottomLadder(599, 503, 4));
        bottomLadders.add(new BottomLadder(437, 395, 21));
        bottomLadders.add(new BottomLadder(545, 179, 63));
        bottomLadders.add(new BottomLadder(545, 287, 43));
        bottomLadders.add(new BottomLadder(869, 395, 29));
        bottomLadders.add(new BottomLadder(923, 125, 71));
    }

    public BottomLadder getBottomLadder(int i){
        return bottomLadders.get(i);
    }
}
