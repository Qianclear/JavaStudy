# day16 异常处理

## 异常

在Java语言中，将程序执行中发生的不正常情况称为“异常”

> (开发过程中的语法错误和逻辑错误不是异常) 

**Error:**

Java虚拟机无法解决的严重问题。如：JVM系统内部错误、资源 耗尽等严重情况。比如：```StackOverflowError和OOM```。一般不编写针对性 的代码进行处理。 

+ 栈溢出：```java.lang.StackOverflowError```

  比如说，可以在main函数中调用main函数，实现无限递归，导致栈满

+ 堆溢出：```java.lang.OutOfMemoryError ```
  这里是堆空间满了（简称OOM）

  Integer[] arr = new Integer[1024*1024*1024];



**Exception:** 

> 主要就是讲这个，因为Error不做处理...

其它因编程错误或偶然的外在因素导致的一般性问题，可以使 用针对性的代码进行处理。例如： 

+ 空指针访问 

+ 试图读取不存在的文件 

+ 网络连接中断 

+ 数组角标越界

根据异常的出现时间可以将异常分为

+ **编译时异常**（checked）

  IOException输入输出异常

  FileNotFoundException文件未找到异常

+ **运行时异常**（unchecked）

  NullPointerException空指针异常

  ArrayIndexOutOfBoundsException数组脚标越界异常

  ClassCastException 两个类型间转换不兼容时引发的运行时异常 

  NumberFormatException 数字格式化异常 

  InputMismatchException 输入类型与获取类型不匹配异常 

  ArithmeticException 出现异常的运算条件时，抛出此异常 ，比如1除以0

## 异常处理

异常的处理：抓抛模型

+ 过程一："抛"：程序在正常执行的过程中，一旦出现异常，就会在异常代码处生成一个对应异常的对象。并将此对象抛出。一旦抛出对象以后，其后的代码就不再执行。

+ 关于异常对象的产生：① 系统自动生成的异常对象② 手动的生成一个异常对象，并抛出（throw）

+ 过程二："抓"：可以理解为异常的处理方式：① try-catch-finally  ② throws

  > throw 是异常对象的产生，throws是异常的处理

两种方式

1. try-catch-finally

   自己能处理就自己搞

2. throws + 异常类型

   处理不了交给别人

   

### try-catch-finally

try-catch-finally的使用
```
try{
    //可能出现异常的代码
}catch(异常类型1 变量名1){
    //处理异常的方式1
}catch(异常类型2 变量名2){
    //处理异常的方式2
}catch(异常类型3 变量名3){
    //处理异常的方式3
}
....
finally{
//一定会执行的代码
//这个不一定要写，可以不写
}
```

> finally在异常处理中是一定会执行的，即使是catch中出现了异常

像数据库连接、输入输出流、网络编程Socket等资源，JVM是不能自动的回收的，我们需要自己手动的进行资源释放。此时的资源释放，就需要声明在finally中。

> 对于出问题的代码，可以选中右键Surround With 然后选择Try/catch Block

1. 使用try将可能出现异常代码包装起来，在执行过程中，一旦出现异常，就会生成一个对应异常类的对象，根据此对象的类型，去catch中进行匹配

2. 一旦try中的异常对象匹配到某一个catch时，就进入catch中进行异常的处理。一旦处理完成，就跳出当前的
   try-catch结构（在没有写finally的情况）。继续执行其后的代码

3. catch中的异常类型如果没有子父类关系，则谁声明在上，谁声明在下无所谓。
   catch中的异常类型如果满足子父类关系，则要求子类一定声明在父类的上面。否则，报错

4. 常用的异常对象处理的方式：String  getMessage()    printStackTrace()

5. 在try结构中声明的变量，再出了try结构以后，就不能再被调用

### throws + 异常类型

"**throws + 异常类型**"写在方法的声明处。指明此方法执行时，可能会抛出的异常类型。
一旦当方法体执行时，出现异常，仍会在异常代码处生成一个异常类的对象，此对象满足throws异常类型时，就会被抛出。**异常代码后续的代码，就不再执行！**

> try-catch-finally:真正的将异常给处理掉了。
> throws的方式只是将异常抛给了方法的调用者。  并没有真正将异常处理掉

> 当然，抛出异常需要导包，可以在快捷修复里找到import......



> 如果父类中被重写的方法没有throws方式处理异常，则子类重写的方法也不能使用throws，意味着如果子类重写的方法中有异常，必须使用try-catch-finally方式处理。

>执行的方法a中，先后又调用了另外的几个方法，这几个方法是递进关系执行的。我们建议这几个方法使用throws的方式进行处理。而执行的方法a可以考虑使用try-catch-finally方式进行处理。(把锅集合在一起集中处理)