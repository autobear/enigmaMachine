import enigma.*;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Scanner;
import java.util.Timer;

/**
 *
 */
public class console {
    public static void main(String[] args) throws Exception {
        String oristr="";
        String secstr="";
        String bacstr="";

        String path= System.getProperty("user.dir")+"\\src\\main\\resources\\";

        Properties plugboard_properties = new Properties();
        plugboard_properties.load(new FileInputStream(path+"plugboard.properties"));

        LinkedList<Properties> rotor_properties_List=new LinkedList<>();
        Properties r1=new Properties();
        r1.load(new FileInputStream(path+"rotor1.properties"));
        rotor_properties_List.add(r1);
        Properties r2=new Properties();
        r2.load(new FileInputStream(path+"rotor2.properties"));
        rotor_properties_List.add(r2);

        Properties reflector_properties = new Properties();
        reflector_properties.load(new FileInputStream(path+"reflector.properties"));

        machineFactory factory=new machineFactory();
        enigmaMachine_Prototype enigma1 =factory.produceEnigmaMachinePrototype(plugboard_properties,rotor_properties_List,reflector_properties);
        enigmaMachine_Prototype enigma2 =factory.produceEnigmaMachinePrototype(plugboard_properties,rotor_properties_List,reflector_properties);
        enigma1.set_initial_password(new int[]{26,26});
        enigma2.set_initial_password(new int[]{26,26});
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.nextLine();
            char[] chars=new char[str.length()];
            char[] chars2=new char[str.length()];
            for(int i=0;i<str.length();i++){
                char c=str.charAt(i);
                if(c==' '){
                    chars[i]=' ';
                    chars2[i]=' ';
                    continue;
                }
                int k=enigmaUtils.getInstance().Alphabet2Int(c);
                int T=enigma1.transform(k);
                int m=enigma2.transform(T);
                chars[i]=enigmaUtils.getInstance().Int2Alphabet(T);
                chars2[i]=enigmaUtils.getInstance().Int2Alphabet(m);
            }
            oristr=oristr+str;
            secstr=secstr+new String(chars);
            bacstr=bacstr+new String(chars2);
//            System.out.println("ori:"+str+"||to:"+new String(chars)+"||back:"+new String(chars2));
            System.out.println("\rORI:"+ oristr);
            System.out.println("\rSEC:"+ secstr);
            System.out.println("\rBAK:"+ bacstr);
        }

//        System.out.print("\r"+ outstr);

    }
}
