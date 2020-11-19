## day_13

> 有了对象的多态性以后，内存中实际上是加载了子类特有的属性和方法的，但是由于变量声明为父类类型，导致编译时，只能调用父类中声明的属性和方法。子类特有的属性和方法不能调用。
>
> 所以说，如何才能调用子类特有的属性和方法？
> 答案：向下转型：使用强制类型转换符。

```
		Man m1 = (Man)p2;
		m1.earnMoney();
		m1.isSmoking = true;
```

## instanceof

1. 关键字的使用
   ```a instanceof A``` 含义是：判断对象a是否是类A的实例。如果是，返回true；如果不是，返回false。
2. 使用情境：为了避免在向下转型时出现ClassCastException的异常，我们在向下转型之前，先
   进行instanceof的判断，一旦返回true，就进行向下转型。如果返回false，不进行向下转型。

如果 a instanceof A返回true,则 a instanceof B也返回true(其中，类B是类A的父类。)

##  java.lang.Object类
1. Object类是所有Java类的根父类

2. 如果在类的声明中未使用extends关键字指明其父类，则默认父类为java.lang.Object类 

3. Object类中的功能(属性、方法)就具有通用性。

   属性：无

   方法：equals() / toString() / getClass() ```反射讲``` /hashCode()```集合``` / clone()```很少用```  / finalize()

   wait() 、 notify()、notifyAll()

   > Object类只声明了一个空参的构造器

## == 和 equals() 区别

1. == 的使用：

   == ：运算符

   + 可以使用在基本数据类型变量和引用数据类型变量中

   + 如果比较的是基本数据类型变量：比较两个变量保存的数据是否相等。（不一定类型要相同）

   + 如果比较的是引用数据类型变量：比较两个对象的地址值是否相同.即两个引用是否指向同一个对象实体

> == 符号使用时，必须保证符号左右两边的变量类型一致。

> ==符号的作用是比较两个对象的地址值是否相同

2. equals()方法的使用：

   + 是一个方法，而非运算符

   + 只能适用于引用数据类型

   + Object类中equals()的定义：

     ```
     public boolean equals(Object obj) {
         return (this == obj);
         }
     ```

     

> Object类中定义的equals()和==的作用是相同的：比较两个对象的地址值是否相同.即两个引用是否指向同一个对象实体

> 像String、Date、File、包装类等都重写了Object类中的equals()方法。重写以后，比较的不是两个引用的地址是否相同，而是比较两个对象的"实体内容"是否相同。

> 通常情况下，我们自定义的类如果使用equals()的话，也通常是比较两个对象的"实体内容"是否相同。那么我们就需要对Object类中的equals()进行重写.

重写的原则：**比较两个对象的实体内容是否相同.**

## Object类中toString()的使用：

1. 当我们输出一个对象的引用时，实际上就是调用当前对象的toString()

2. Object类中toString()的定义：

```
public String toString() {
   return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```

像String、Date、File、包装类等都重写了Object类中的toString()方法。

使得在调用对象的toString()时，返回"实体内容"信息

自定义类也可以重写toString()方法，当调用此方法时，返回对象的"实体内容"

```
public class ToStringTest {
	public static void main(String[] args) {
		
		Customer cust1 = new Customer("Tom",21);
		System.out.println(cust1.toString());
		System.out.println(cust1);
		
		String str = new String("MM");
		System.out.println(str);//MM
		
		Date date = new Date(4534534534543L);
		System.out.println(date.toString());
		
	}
}
```



## Java中的JUnit单元测试

 步骤：
 1.选中当前工程 - 右键选择：build path - add libraries - JUnit 4 - 下一步
 2.创建Java类，进行单元测试。
   此时的Java类要求：① 此类是public的  ②此类提供公共的无参的构造器
 3.此类中声明单元测试方法。
   此时的单元测试方法：方法的权限是public,没有返回值，没有形参

 4.此单元测试方法上需要声明注解：@Test,并在单元测试类中导入：**import org.junit.Test;**

 5.声明好单元测试方法以后，就可以在方法体内测试相关的代码。
 6.写完代码以后，左键双击单元测试**方法名**，右键：**run as** - **JUnit Test**

 说明：
 1.如果执行结果没有任何异常：绿条
 2.如果执行结果出现异常：红条

> ps:以上皆为视频中的方法，在实际操作中，点击build path之后可能需要自己寻找add libraries，此外，视频中默认的是JUnit4，但自己操作时默认的是JUnit5.

> 操作中还可以简单操作，即直接```@Test```，回车，```public void test(){}```然后在提示里add就行了

> 开发中不要自己建立一个名叫test的类，避免误用自己的类



## 包装类

包装类的使用:

1. 基本数据类型 --->包装类：调用包装类的构造器

	@Test
	public void test1(){
		
		int num1 = 10;
		System.out.println(num1.toString());
		Integer in1 = new Integer(num1);
		System.out.println(in1.toString());
	}
2. 包装类--->基本数据类型:调用包装类Xxx的xxxValue()