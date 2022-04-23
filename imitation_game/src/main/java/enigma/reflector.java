package enigma;

import java.util.*;

//反射器
//26个输入节点，并且两两连接，连接顺序随机，比如1和15是一对，那么输入1则输出15
//每个批次的反射器连接顺序一致
//不可转动
//1级带动2级，2级带动3级，26进制。26*26*26=17576
/*
读取配置表得到输入和输出的映射
不可转动
 */
public class reflector {
    private int base;
    //轮盘本体，被等分为base份，每一格都存放一个node
    //正面的node两两一组互相指向
    private LinkedList<node> Circular_linked_list;

    public reflector(Properties properties) {
        System.out.println("初始化反射板开始");
        Circular_linked_list = new LinkedList<>();
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            int num1=0,num2=0;
            if (enigmaUtils.getInstance().isNumber(key) && enigmaUtils.getInstance().isNumber(value)) {
                 num1 = Integer.parseInt(String.valueOf(key));
                 num2 = Integer.parseInt(String.valueOf(value));
            } else if (enigmaUtils.getInstance().isAlphabet(key) && enigmaUtils.getInstance().isAlphabet(value)) {
                 num1 = enigmaUtils.getInstance().Alphabet2Int(key.charAt(0));
                 num2 = enigmaUtils.getInstance().Alphabet2Int(value.charAt(0));
            }
            if(num1!=0&&num2!=0&&num1!=num2) {
                node N = new node(num1);
                node O = new node(num2);
                N.bind_the_other(O);
                O.bind_the_other(N);
                Circular_linked_list.add(N);
                Circular_linked_list.add(O);
            }
        }
        //对Circular_linked_list进行排序
        Collections.sort(Circular_linked_list, new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return o1.getNumbering()- o2.getNumbering();
            }
        });
        this.base=Circular_linked_list.size();
        for(node n:Circular_linked_list){
            System.out.print(n.getNumbering()+"和"+n.getInverse_node().getNumbering()+"连接||");
        }
        System.out.println();
        for(node n:Circular_linked_list){
            System.out.print(enigmaUtils.getInstance().Int2Alphabet(n.getNumbering())+"和"+enigmaUtils.getInstance().Int2Alphabet(n.getInverse_node().getNumbering())+"连接||");
        }
        System.out.println("\n初始化反射板完毕");
    }
    public int output(int input){
        if(input<1||input>this.base){
            return -1;
        }
        input--;
        return Circular_linked_list.get(input).getInverse_node().getNumbering();
    }

    public int getBase() {
        return base;
    }
}
