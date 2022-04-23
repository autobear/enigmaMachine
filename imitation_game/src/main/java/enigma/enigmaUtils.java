package enigma;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class enigmaUtils {
    private static volatile enigmaUtils enigmaUtils;
    private static Pattern pattern = Pattern.compile("-?[0-9]+(\\\\.[0-9]+)?");
    private enigmaUtils(){}
    public static enigmaUtils getInstance(){
        if(enigmaUtils==null){
            synchronized (enigmaUtils.class){
                if(enigmaUtils==null){
                    enigmaUtils=new enigmaUtils();
                }
            }
        }
        return enigmaUtils;
    }
    public int Alphabet2Int(char c){
        if(c>='a'&&c<='z'){
            return c-96;
        }else if(c>='A'&&c<='Z'){
            return c-64;
        }
        return 0;
    }
    public char Int2Alphabet(int i){
        if(i<=0||i>26){
            return '!';
        }
        return (char)('A'+i-1);
    }
    public boolean legitimate(String s){
        return isAlphabet(s)||isNumber(s);
    }
    public boolean isAlphabet(String s){
        char c=s.charAt(0);
        if(s.length()==1&&((c>='a'&&c<='z')||(c>='A'&&c<='Z'))){
            return true;
        }
        return false;
    }
    public boolean isNumber(String s){
        // 通过Matcher进行字符串匹配
        Matcher m = pattern.matcher(s);
        // 如果正则匹配通过 m.matches() 方法返回 true ，反之 false
        return m.matches();
    }
}
