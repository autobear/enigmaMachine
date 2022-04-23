import java.util.Iterator;
import java.util.LinkedHashSet;

public class reflectorTableGenerator {
    public static void main(String[] args) {
        LinkedHashSet set=new LinkedHashSet();
        while (set.size()!=26){
            set.add(Integer.valueOf((int) (Math.random()*(26)+1)));
        }
        Iterator<Integer> f = set.iterator();
        while (f.hasNext()){
            System.out.println(f.next()+"="+ f.next());
        }
    }
}
