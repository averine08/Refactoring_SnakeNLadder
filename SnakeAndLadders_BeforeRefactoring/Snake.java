public class Snake extends Possition {
    public Snake(){}

    public Snake(int x, int y, int pos){
        this.setX(x);
        this.setY(y);
        this.setPos(pos);
    }

    public Snake getPossition(){
        return this;
    }
}
