# 我的java学习笔记

### java基本语法

- **大小写敏感**：Java 是大小写敏感的，这就意味着标识符 Hello 与 hello 是不同的。
- **类名**：对于所有的类来说，类名的首字母应该大写。如果类名由若干单词组成，那么每个单词的首字母应该大写，例如 **MyFirstJavaClass** 。
- **方法名**：所有的方法名都应该以小写字母开头。如果方法名含有若干单词，则后面的每个单词首字母大写。
- **源文件名**：源文件名必须和类名相同。当保存文件的时候，你应该使用类名作为文件名保存（切记 Java 是大小写敏感的），文件名的后缀为 **.java**。（如果文件名和类名不相同则会导致编译错误）。
- **主方法入口**：所有的 Java 程序由 **public static void main(String []args)** 方法开始执行。

### Java 标识符

Java 所有的组成部分都需要名字。类名、变量名以及方法名都被称为标识符。

关于 Java 标识符，有以下几点需要注意：

- 所有的标识符都应该以字母（A-Z 或者 a-z）,美元符（$）、或者下划线（_）开始
- 首字符之后可以是字母（A-Z 或者 a-z）,美元符（$）、下划线（_）或数字的任何字符组合
- 关键字不能用作标识符
- 标识符是大小写敏感的
- 合法标识符举例：age、$salary、_value、__1_value
- 非法标识符举例：123abc、-salary

### Java修饰符

像其他语言一样，Java可以使用修饰符来修饰类中方法和属性。主要有两类修饰符：

- 访问控制修饰符 : default, public , protected, private
- 非访问控制修饰符 : final, abstract, static, synchronized

### Java 变量

- 局部变量
- 类变量（静态变量）
- 成员变量（非静态变量）

### Java 关键字

跟C语言好像啊...

### java注释

类似于C...

'''

 public class HelloWorld {   /* 这是第一个Java程序    *它将打印Hello World    * 这是一个多行注释的示例    */    public static void main(String []args){       // 这是单行注释的示例       /* 这个也是单行注释的示例 */       System.out.println("Hello World");     } } 

'''

## java对象和类

 **对象**：对象是类的一个实例（**对象不是找个女朋友**），有状态和行为。  

 **类**：类是一个模板，它描述一类对象的行为和状态。  

### Java中的类

 可以看成是创建Java对象的模板。 

### 创建对象

 使用关键字new ，分三步：**声明；实例化；初始化**





（src存源码bin存编译成的class文件）

### java变量类型

在Java语言中，所有的变量在使用前必须声明。声明变量的基本格式如下：  

```type identifier [ = value][, identifier [= value] ...] ;```

格式说明：type为Java数据类型。identifier是变量名。可以使用逗号隔开来声明多个同类型变量。

以下列出了一些变量的声明实例。注意有些包含了初始化过程。

> ```int a, b, c;```     // 声明三个int型整数:a、 b、c  
>
> ```int d = 3, e = 4, f = 5;``` // 声明三个整数并赋予初值  
>
> ```byte z = 22;```     // 声明并初始化 z  
>
> ```String s = "runoob";```  // 声明并初始化字符串 s  
>
> ```double pi = 3.14159; ```// 声明了双精度浮点型变量 pi  
>
> ```char x = 'x';    ```// 声明变量 x 的值是字符 'x'  

### Java 局部变量

局部变量在方法、构造方法、或者语句块被执行的时候创建，当它们执行完成后，变量将会被销毁；

## Scanner入门

### next()

将下一个值作为字符串返回

```java
public static void main(){
    Scanner scan = new Scanner(System.in);
    System.out.println("next方式接收：");
    if (scan.hasNext()) {
        String str1 = scan.next();
        System.out.println("输入的数据为：" + str1);
    }
    scan.close();
}
```

### nextLine()

 将回车前的全部内容作为字符串返回 

```
public static void main(){
    Scanner scan = new Scanner(System.in);
    System.out.println("nextLine方式接收：");
    if (scan.hasNextLine()) {    
        String str = scan.nextLine();    
        System.out.println("输入的数据为：" + str);
    }
    scan.close();
}
```

#### 区别

- next()
  - 一定要读取到有效字符后才可以结束输入。直接输入空格、回车，都不能结束输入。
  - 对输入有效字符之前遇到的空白（空格、回车），`next()` 方法会自动将其去掉。
  - 只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符。
  - `next()` 不能得到带有空格的字符串。
- nextLine()
  - 以Enter为结束符,也就是说 `nextLine()`方法返回的是输入回车之前的所有字符。
  - 可以获得空白。

### nextInt() 

 将下一个值作为`int`返回，如果不是`int`类型则报错 

``` java
import java.util.Scanner;
public class Example003 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("nextInt方式接收：");
		if(scan.hasNextInt()) {
		    int i = scan.nextInt();
		    System.out.println("输入的数据为："+ i);
		}
		else {
			System.out.println("next is not int");
		}
		scan.close();
		System.out.println("End");
}
}
```

### 总结

使用之前最好使用```hasNextInt()```方法进行验证，再使用```nextInt()```读取   

#### tips

 `Scanner`对象在调用方法时会阻塞程序的运行   

先从标准输入流`System.in`开始。  

如果使用标准输入流`System.in`来作为参数，实例化`Scanner`对象的话，那么在调用`hasNextXxx()`或者`nextXxx()`时就会阻塞，因为此时输入为空。用户输入之后，`hasNextXxx()`将会从用户输入的左边开始判断，也就是用户输入的最开始。如果验证通过，则返回`true`，否则返回`false`。`hasNextXXX()`方法只会判断游标当前指向位置的内容符不符合验证规则，并不会使游标指向下一个。`nextXxx()`方法会使游标后移。

#### 游标后移

```java
public class Example005 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
        
		System.out.println("next()=>" + scan.next());
		System.out.println("nextInt()=>" + scan.nextInt());
		System.out.println("nextFloat()=>" + scan.nextFloat());

		System.out.println("nextLine()=>" + scan.nextLine());
		System.out.println("next()=>" + scan.next());
		System.out.println("end");

		scan.close();
	}

}
```