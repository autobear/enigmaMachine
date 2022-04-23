package enigma;

import java.util.LinkedList;
import java.util.Properties;

//机器工厂，用来生成密码机
public class machineFactory {
    public enigmaMachine_Prototype produceEnigmaMachinePrototype(
            Properties plugboard_properties,
            LinkedList<Properties> rotor_properties_List,
            Properties reflector_properties)
    {
        enigmaMachine_Prototype machine= new enigmaMachine_Prototype(plugboard_properties,rotor_properties_List,reflector_properties);
        if(machine.getBase()==0){
            return null;
        }
        return machine;
    }
}
