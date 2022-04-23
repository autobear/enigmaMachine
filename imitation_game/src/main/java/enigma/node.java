package enigma;
//转子中每个接触点的输入对应一个输出
//每个接触点有自己的一个编号同时指向另一个接触点
//这两个接触点的位置在轮子的正反面上面，并且相互指向
//以编号作为他们在轮子上面的位置特征
public class node {
    private int numbering;
    //由连线指向另一面的一个接触点
    private node inverse_node;
    public node(int num){
       this.numbering=num;
    }
    public void bind_the_other(node inverse_node){
        this.inverse_node=inverse_node;
    }

    public int getNumbering() {
        return numbering;
    }

    public node getInverse_node() {
        return inverse_node;
    }
}
