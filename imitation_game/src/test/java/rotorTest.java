import enigma.enigmaUtils;
import enigma.plugboard;
import enigma.rotor;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Scanner;

public class rotorTest {
    public static void main(String[] args) throws Exception {
        String path= System.getProperty("user.dir")+"\\src\\main\\resources\\";
        LinkedList<Properties> rotor_properties_List=new LinkedList<>();

        Properties r1=new Properties();
        r1.load(new FileInputStream(path+"rotor1.properties"));
        rotor_properties_List.add(r1);

        Properties r2=new Properties();
        r2.load(new FileInputStream(path+"rotor2.properties"));
        rotor_properties_List.add(r2);

        rotor rotor1=new rotor(r1);
        rotor rotor2=new rotor(r1);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.next();
            char[] chars=new char[str.length()];
            char[] chars2=new char[str.length()];
            for(int i=0;i<str.length();i++){
                char c=str.charAt(i);
                int k=enigmaUtils.getInstance().Alphabet2Int(c);

                int t=rotor1.positive_output(k);
                int q=rotor1.reverse_output(t);

                int rb=rotor2.positive_output(q);
                int rc=rotor2.reverse_output(rb);

                chars[i]=enigmaUtils.getInstance().Int2Alphabet(q);
                chars2[i]=enigmaUtils.getInstance().Int2Alphabet(rc);
                rotor1.auto_trun_up();
//               if(rotor1.auto_trun_up()){
//                   rotor2.auto_trun_up();
//               }
//                System.out.println("00000");
            }
            System.out.println("ori:"+str+"||to:"+new String(chars)+"||back:"+new String(chars2));
        }


    }
}
