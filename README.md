# enigmaMachine
模仿游戏中图灵主要的任务是破解这台恩尼格玛密码机（enigma），他的发明者是亚瑟•谢尔比乌斯和理查德•里特，于1918年制造
在二战中，它们主要担任德军每日电报的加密工作
主要结构由输入设备键盘、输出设备灯盘、加密电路、电源组成
其中加密电路由接线板、输入轮、三级转子、反射器组成

使用方法：
一.通过IDEA运行
打开工程通过console的main方法运行
输入一串字符，控制台输出第一台机器加密的密文，以及第二台机器输入密文后解密的明文，明文与为大写的输入字符串。
二.通过控制台运行
通过Java -jar imitation_game.jar   jar包目录为\imitation_game\out\artifacts\imitation_game_jar
其他步骤与IDEA运行一致

把JAR包和配置传给你的朋友，你输入明文后转为密文然后把密文发给你的朋友，你的朋友运行jar包后输入密文即可获得明文

passwordTableGenerator和reflectorTableGenerator可以生成rotor和reflector的配置文件。
目前代码中转子个数为2，可以修改代码并且添加配置文件rotor3.properties进行转子个数添加

可以优化的地方：
1.控制面板增加初始密码设置和当前密码获取。
2.输入可以支持backspace回退，且密码机自动回到上一次状态。
3.每次输出不再新增一行而是在原来的输出上面进行修改。
4.修改转子和反射板的配置支持更多字符的加密、解密。
