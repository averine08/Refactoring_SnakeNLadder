public class Ladder extends Possition{
    public Ladder(){}

    public Ladder(int x, int y, int pos){
        this.setX(x);
        this.setY(y);
        this.setPos(pos);
    }

    public Ladder getPossition(){
        return this;
    }
}
