import enigma.enigmaUtils;
import enigma.plugboard;
import enigma.reflector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

public class reflectorTest {
    public static void main(String[] args) throws Exception {
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
        Properties reflector_properties = new Properties();
        reflector_properties.load(new FileInputStream(path+"reflector.properties"));
        reflector ref= new reflector(reflector_properties);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.next();
            char[] chars = new char[str.length()];
            char[] chars2 = new char[str.length()];
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                int k = enigmaUtils.getInstance().Alphabet2Int(c);
                int t = ref.output(k);
                int m = ref.output(t);
                chars[i] = enigmaUtils.getInstance().Int2Alphabet(t);
                chars2[i] = enigmaUtils.getInstance().Int2Alphabet(m);
            }
            System.out.println("ori:" + str + "||to:" + new String(chars) + "||back:" + new String(chars2));
        }
    }



}
