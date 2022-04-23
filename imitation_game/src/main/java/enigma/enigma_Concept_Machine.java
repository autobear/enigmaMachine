package enigma;

import java.util.LinkedList;

/**
 * 挖坑人：AUTO_BEAR
 */
//恩尼格玛密码机概念机
public interface enigma_Concept_Machine {
     //接线板
     plugboard plugboard = null;
     //转子组
     LinkedList<rotor> rotorGroup=null;
     //反射板
     reflector reflector=null;
     //键入信号
     char key_in = 0;
     //显示信号
     char key_out=0;
     int[] password=null;
     //机器的进制
     int bath=0;
     /*
       设置初始转子密码，获得当前转子显示的密码
      */
     boolean set_initial_password(int[] initial_password);
     int[] get_password();
     //输入并输出一个值
     int transform(int c);
     //转子组每次做完transform后自动进位，换成下一张表
     void rotorGroup_autoCarry();
}
