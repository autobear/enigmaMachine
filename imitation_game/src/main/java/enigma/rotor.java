package enigma;

import java.util.*;

//转轮
//不同批次的转轮内部结构不同
//单个批次的三个转轮也不同
//单个批次的一组转轮是一样的，也就是说世界上有不少一模一样的恩格码机
//三个转轮的状态组成一张密码表
//26个输入节点，每个节点对应一个输出节点，顺序随机
//可以转动
/*
方法1：输入一个值输出与之对应的值
方法2：转动到某个位置
方法3：得到当前位置状态
方法4：向上转动
方法5：向下转动
方法6：归位
 */
public class rotor {
    private int base;//转轮进制:英文中26个字母
    private int position;//在1-26之间，向上+1，向下-1，26+1=1，1-1=26，轮盘外面显示的数字
    //轮盘本体，被等分为base份，每一格都存放两个node组成的一个零件part
    private LinkedList<part> Circular_linked_list;

    //    private Map<Integer,Integer> Mapping_table;
    public rotor(Properties properties) {
        System.out.println("初始化转轮开始");
        //如果无效的配置文件，拒绝初始化
        if (properties == null) {
            System.out.println("无效的配置文件");
            return;
        }
        //临时生成两个TreeSet用来存放正反两条nodes
        TreeSet<node> frontSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                node n1 = (node) o1;
                node n2 = (node) o2;
                return n1.getNumbering() - n2.getNumbering();
            }
        });
        TreeSet<node> backSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                node n1 = (node) o1;
                node n2 = (node) o2;
                return n1.getNumbering() - n2.getNumbering();
            }
        });
        for (String key : properties.stringPropertyNames()) {
            int num1 = 0, num2 = 0;
            String value = properties.getProperty(key);
            if (enigmaUtils.getInstance().isNumber(key) && enigmaUtils.getInstance().isNumber(value)) {
                num1 = Integer.parseInt(String.valueOf(key));
                num2 = Integer.parseInt(String.valueOf(value));
            } else if (enigmaUtils.getInstance().isAlphabet(key) && enigmaUtils.getInstance().isAlphabet(value)) {
                num1 = enigmaUtils.getInstance().Alphabet2Int(key.charAt(0));
                num2 = enigmaUtils.getInstance().Alphabet2Int(value.charAt(0));
            }
            if(num1==0||num2==0){
                System.out.println("初始化中断请检查转轮的配置");
                return;
            }
            node Z = new node(num1);
            node F = new node(num2);
            Z.bind_the_other(F);
            F.bind_the_other(Z);
            frontSet.add(Z);
            backSet.add(F);
        }
        if (frontSet.size() == backSet.size()) {
            Circular_linked_list = new LinkedList<>();
            this.position = 1;
            this.base = frontSet.size();
            for (int i = 0; i < this.base; i++) {
                Circular_linked_list.add(new part(frontSet.pollFirst(), backSet.pollFirst()));
            }
        }
        System.out.println("从正面检查");
        for (part p:Circular_linked_list){
            System.out.print(p.getFront_node().getNumbering()+"-->"+p.getFront_node().getInverse_node().getNumbering()+"||");
        }
        System.out.println();
        for (part p:Circular_linked_list){
            System.out.print(enigmaUtils.getInstance().Int2Alphabet(p.getFront_node().getNumbering())+"-->"+enigmaUtils.getInstance().Int2Alphabet(p.getFront_node().getInverse_node().getNumbering())+"||");
        }
        System.out.println();
        System.out.println("从反面检查");
        for (part p:Circular_linked_list){
            System.out.print(p.getBack_node().getNumbering()+"-->"+p.getBack_node().getInverse_node().getNumbering()+"||");
        }
        System.out.println();
        for (part p:Circular_linked_list){
            System.out.print(enigmaUtils.getInstance().Int2Alphabet(p.getBack_node().getNumbering())+"-->"+enigmaUtils.getInstance().Int2Alphabet(p.getBack_node().getInverse_node().getNumbering())+"||");
        }
        System.out.println();
        System.out.println("初始化转轮完毕");
    }

    //trun_to 1-base,有返回状态:成功或者失败
    //成型后这部分代码之后可以优化
    public boolean turn_to(int num) {
        if (num > this.base || num <= 0) {
            return false;
        }
        //计算位移
        int displacement = num - this.position;
        if (displacement > 0) {
            for (int i = 0; i < displacement; i++) {
                Circular_linked_list.add(Circular_linked_list.pollFirst());
            }
        } else if (displacement < 0) {
            displacement = -displacement;
            for (int i = 0; i < displacement; i++) {
                Circular_linked_list.addFirst(Circular_linked_list.pollLast());
            }
        } else {
            //==0 do nothing
        }
        this.position = num;
        return true;
    }

    /**
     * 向上转动和向下转动N格，返回为是否成功转动
     *
     * @param num
     * @return
     */
    public boolean turn_up(int num) {
        int k=(this.base+((this.position+num)%this.base))%this.base;
        return turn_to(k==0?this.base:k);
    }
    /**
     * 自动向上转动1格，返回为是否进位，如果进位下一个转子也要向上转一格
     *
     * @return
     */
    public boolean auto_trun_up() {
        int now = this.position;
        boolean success = turn_up(1);
        if (success) {
            int next = this.position;
            if (now == this.base && next == 1) {
                return true;
            }
        }
        return false;
    }

    public int positive_output(int input) {
        if (input < 1 || input > this.base) {
            return -1;
        }
        --input;
        return Circular_linked_list.get(input).getFront_node().getInverse_node().getNumbering() >= this.position ? Circular_linked_list.get(input).getFront_node().getInverse_node().getNumbering() - this.position + 1 : this.base + Circular_linked_list.get(input).getFront_node().getInverse_node().getNumbering()-this.position + 1;
    }

    public int reverse_output(int input) {
        if (input < 1 || input > this.base) {
            return -1;
        }
        --input;
        return Circular_linked_list.get(input).getBack_node().getInverse_node().getNumbering() >= this.position ? Circular_linked_list.get(input).getBack_node().getInverse_node().getNumbering() - this.position + 1 : this.base + Circular_linked_list.get(input).getBack_node().getInverse_node().getNumbering() -this.position + 1;
    }

    public int getPosition() {
        return position;
    }
    public int getBase(){
        return this.base;
    }
}
