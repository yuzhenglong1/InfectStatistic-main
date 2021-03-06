## 前言

​    以下代码规范主要参考《阿里巴巴Java开发手册》（1.4.0）

### 缩进

​    采用4个空格缩进，禁止使用tab字符。

​    如果使用tab缩进，必须设置1个tab为4个空格。

### 变量命名

​    变量命名不以下划线或美元符号开始，不以下划线或美元符号结束。

​    变量命名中不使用拼音与英文混合的命名方式，不使用中文命名。

​    方法名、参数名、成员变量、局部变量都统一使用 lowerCamelCase 风格，必须遵从 驼峰形式。

### 每行最多字符数

​    单行字符数限制不超过 120 个，超出需要换行，换行时遵循如下原则：

​         1） 第二行相对第一行缩进 4 个空格，从第三行开始，不再继续缩进。

​         2） 运算符与下文一起换行。

​         3） 方法调用的点符号与下文一起换行。 

​         4） 方法调用中的多个参数需要换行时，在逗号后进行。

​         5） 在括号前不要换行

### 方法最大行数

​    单个方法的总行数不超过80行。

### 方法、类命名

​	类名使用UpperCamelCase风格，但DO/BO/DTO/VO/AO/PO等情况例外。

### 常量

​    常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，对字长不强加限制。

​    在 long 或者 Long 赋值时，数值后使用大写的 L，不能是小写的 l。

### 空行规则

​    不同逻辑、不同语义、不同业务的代码之间插入一个空行分隔开来以提升可读性。

​    任何情形，没有必要插入多个空行进行隔开。

### 注释规则

​    注释的双斜线与注释内容之间有且仅有一个空格。

### 操作符前后空格

​    运算符的左右两边都需要加一个空格。

### 其他规则

​    类型与中括号紧挨相连来表示数组

#### 	关于括号：

​		1） 左大括号前不换行

​		2） 左大括号后换行

​		3） 右大括号前换行

​		4） 右大括号后还有else等代码则不换行；

​			   表示终止的右大括号后必须换行；

