package enigma;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 挖坑人：AUTO_BEAR
 */
/*
机器本体，这里只能输入26个字母，密码设置也用26个字母代替1-26对应A-Z不区分大小写
 */
public class enigmaMachine_Prototype implements enigma_Concept_Machine {
    //有多台密码机的情况下需要给一个名字，避免混乱
    private String name = "enigmaMachine_Prototype";
    //接线板
    private plugboard plugboard;
    //转子组,为了还原实际机器,转子的级数是从左往右递减
    private LinkedList<rotor> rotorGroup;
    //反射板
    private reflector reflector;
    //键入信号
    private char key_in;
    //显示信号
    private char key_out;
    //password的每一位存放rotorGroup对应数的Position，显示那里可以选择反转过来或者不反转
    private int[] password = null;
    //初始密码只读
    private int[] ints_password = null;
    //
    private int base;

    public enigmaMachine_Prototype(
            Properties plugboard_properties,
            LinkedList<Properties> rotor_properties_List,
            Properties reflector_properties
    ) {
        this.plugboard = new plugboard(plugboard_properties);
        rotorGroup = new LinkedList<>();
        for (Properties rotor_properties : rotor_properties_List) {
            rotorGroup.add(new rotor(rotor_properties));
        }
        this.reflector = new reflector(reflector_properties);
        //
        //根据转子个数自动设置初始密码，3转子=111=AAA
        password = new int[rotorGroup.size()];
        for (int i = 0; i < password.length; i++) {
            password[i] = rotorGroup.get(i).getPosition();
        }
        ints_password = password.clone();
        //todo:最好校验一下他们的base是不是一样
        for (int i = 0; i < rotorGroup.size(); i++) {
            if (rotorGroup.get(i).getBase() != this.reflector.getBase()) {
                System.out.println("恩尼格玛密码机" + this.name + "初始化失败请检验参数");
                return;
            }
        }
        this.base = this.reflector.getBase();
    }

    //设置密码，可能失败，失败回滚
    @Override
    public boolean set_initial_password(int[] initial_password) {
        int[] password_now = password.clone();
        boolean success = true;
        for (int i = 0; i < initial_password.length; i++) {
            if (i > password.length - 1) break;
            password[i] = initial_password[i];
        }
        //生效
        for (int i = 0; i < password.length; i++) {
            success = rotorGroup.get(i).turn_to(password[i]);
            if (!success) break;
        }
        if (!success) {
            password = password_now.clone();
        }
        ints_password = password.clone();
        return success;
    }

    public boolean set_initial_password(char[] initial_password) {
        return false;
    }

    public boolean set_initial_password(String initial_password) {
        return false;
    }

    @Override
    public int[] get_password() {
        return password;
    }

    public String get_password_str() {
        char[] chars = new char[password.length];
        for (int i = 0; i < password.length; i++) {
            chars[i] = enigmaUtils.getInstance().Int2Alphabet(password[i]);
        }
        return String.valueOf(chars);
    }

    @Override
    public int transform(int input) {
        input = this.plugboard.output(input);
        for (int i = 0; i < rotorGroup.size(); i++) {
            input = rotorGroup.get(i).positive_output(input);
        }
        input = reflector.output(input);
        for (int i = rotorGroup.size() - 1; i >= 0; i--) {
            input = rotorGroup.get(i).reverse_output(input);
        }
        input = this.plugboard.output(input);
        //密码机键盘抬起的时候自动进位
        rotorGroup_autoCarry();
        return input;
    }

    @Override
    public void rotorGroup_autoCarry() {
        boolean run = true;
        //最后一位如果还是run代表又触发新的一轮进位

        for (rotor r : rotorGroup) {
            run = r.auto_trun_up();
            if (!run) break;
        }

        //更新当前显示的密码
        for (int i = 0; i < password.length; i++) {
            password[i] = rotorGroup.get(i).getPosition();
        }
    }

    public String getName() {
        return name;
    }

    public enigma.plugboard getPlugboard() {
        return plugboard;
    }

    public LinkedList<rotor> getRotorGroup() {
        return rotorGroup;
    }

    public enigma.reflector getReflector() {
        return reflector;
    }

    public char getKey_in() {
        return key_in;
    }

    public char getKey_out() {
        return key_out;
    }

    public int[] getPassword() {
        return password;
    }

    public int getBase() {
        return base;
    }
}
