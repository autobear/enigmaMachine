import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class passwordTableGenerator {
    /*
    码表生成器
     */
    public static void main(String[] args) {
        LinkedHashSet set=new LinkedHashSet();
//        LinkedHashSet set2=new LinkedHashSet();
//        HashSet<Integer> set=new HashSet<>();
//        HashSet<Integer> set2=new HashSet<>();
        while (set.size()!=26){
            set.add(Integer.valueOf((int) (Math.random()*(26)+1)));
        }
//        while (set2.size()!=26){
//            set2.add(Integer.valueOf((int) (Math.random()*(26)+1)));
//        }
        Iterator<Integer> f = set.iterator();
        int i=1;
       while (f.hasNext()){
           System.out.println(i+"="+ f.next());
           i++;
       }
        System.out.println();
    }
}
