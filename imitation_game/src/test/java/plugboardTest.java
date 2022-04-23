import enigma.enigmaUtils;
import enigma.plugboard;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

public class plugboardTest {
    public static void main(String[] args) throws Exception {
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
        Properties plugboard_properties = new Properties();
        plugboard_properties.load(new FileInputStream(path + "plugboard.properties"));
        plugboard P1 = new plugboard(plugboard_properties);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.next();
            char[] chars = new char[str.length()];
            char[] chars2 = new char[str.length()];
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                int k = enigmaUtils.getInstance().Alphabet2Int(c);
                int t = P1.output(k);
                int m = P1.output(t);
                chars[i] = enigmaUtils.getInstance().Int2Alphabet(t);
                chars2[i] = enigmaUtils.getInstance().Int2Alphabet(m);
            }
            System.out.println("ori:" + str + "||to:" + new String(chars) + "||back:" + new String(chars2));
        }
    }
}
