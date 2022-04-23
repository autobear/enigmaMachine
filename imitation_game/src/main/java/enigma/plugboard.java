package enigma;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//接线板主要作用是让两个字母颠倒，由用户控制
//输入A变成输入D，输出D变成输出A
public class plugboard {
    private Map<Integer, Integer> keyPairs;

    public plugboard(Properties properties) {
        System.out.println("初始化接线板开始");
        keyPairs = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            if (enigmaUtils.getInstance().isNumber(key) && enigmaUtils.getInstance().isNumber(value)) {
                int num1 = Integer.parseInt(String.valueOf(key));
                int num2 = Integer.parseInt(String.valueOf(value));
                keyPairs.put(num1, num2);
                keyPairs.put(num2, num1);
            } else if (enigmaUtils.getInstance().isAlphabet(key) && enigmaUtils.getInstance().isAlphabet(value)) {
                int num1 = enigmaUtils.getInstance().Alphabet2Int(key.charAt(0));
                int num2 = enigmaUtils.getInstance().Alphabet2Int(value.charAt(0));
                keyPairs.put(num1, num2);
                keyPairs.put(num2, num1);
            }
        }
        for(Integer key:keyPairs.keySet()){
            System.out.print(key+"和"+keyPairs.get(key)+"连接||");
        }
        System.out.println();
        for(Integer key:keyPairs.keySet()){
            System.out.print(enigmaUtils.getInstance().Int2Alphabet(key)+"和"+enigmaUtils.getInstance().Int2Alphabet(keyPairs.get(key))+"连接||");
        }
        System.out.println("\n初始化接线板完毕");
    }

    public int output(int input) {
        return keyPairs.containsKey(input) ? keyPairs.get(input) : input;
    }

}
