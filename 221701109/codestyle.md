##    代码风格
###    1.缩进
*    缩进为4个空格，或tab。
###    2.变量命名
*    应符合规范，并且不以下划线、美元符号、小写字母l开头，避免产生歧义。
*    命名不能出现汉语拼音，应保证较高的可读性。
*    类名使用UpperCamelCase风格，必须遵从驼峰形式。
*    方法名、参数名、成员变量、局部变量都统一使用lowerCamelCase风格，必须遵从驼峰形式。
*    中括号是数组类型的一部分。
*    杜绝完全不规范的缩写，避免望文不知义。
###    3.每行最多字符数
*    单行字符数限制不超过100个，超出需要换行，换行时遵循如下原则：第二行相对第一行缩进4个空格，从第三行开始，不再继续缩进。运算符与下文一起换行。方法调用的点符号与下文一起换行。在多个参数超长，逗号后进行换行。在括号前不要换行。
###    4.函数最大行数
*    150行。
###    5.函数、类命名
*    类名使用UpperCamelCase风格，必须遵从驼峰形式。
*    抽象类命名使用Abstract或Base开头；异常类命名使用Exception结尾；测试类命名以它要测试的类的名称开始，以Test结尾。
*    方法名使用lowerCamelCase风格，必须遵从驼峰形式。
###    6.常量
*    常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。
*    long或者Long初始赋值时，必须使用大写的L，不能是小写的l，小写容易跟数字1混淆，造成误解.
###    7.空行规则
*    方法体内的执行语句组、变量的定义语句组、不同的业务逻辑之间或者不同的语义之间插入一个空行。相同业务逻辑和语义之间不需要插入空行。
*    没有必要插入多行空格进行隔开。
###    8.注释规则
*    多行注释/**/，单行注释不设置规则，但要统一。
*    类、类属性、类方法的注释必须使用Javadoc规范，使用/*内容/格式，不得使用//xxx方式。
*    注释掉的代码尽量要配合说明，而不是简单的注释掉。
###    9.操作符前后空格
*    if/for/while/switch/do等保留字与左右括号之间都必须加空格。
*    任何运算符左右必须加一个空格。
*    左括号和后一个字符之间不出现空格；同样，右括号和前一个字符之间也不出现空格。
###    10.其他规则
*    包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用单数形式，但是类名如果有复数含义，类名可以使用复数形式。
*    对于“明确停止使用的代码和配置”，如方法、变量、类、配置文件、动态配置属性等要坚决从程序中清理出去，避免造成过多垃圾。