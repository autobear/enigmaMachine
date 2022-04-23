package enigma;

public class part {
    //正面接触点
    private node front_node;
    //反面接触点
    private node back_node;
    //这两个接触点不是一对，只是共享一个位置
    public part(node front_node,node back_node){
        this.front_node=front_node;
        this.back_node=back_node;
    };

    public node getFront_node() {
        return front_node;
    }

    public void setFront_node(node front_node) {
        this.front_node = front_node;
    }

    public node getBack_node() {
        return back_node;
    }

    public void setBack_node(node back_node) {
        this.back_node = back_node;
    }
}
