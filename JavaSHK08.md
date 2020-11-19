# day15

## abstract 关键字

1. abstract：抽象的

2. abstract可以用来修饰类和方法

   + abstract修饰类之后，不可以用来实例化，即不可构造对象

     抽象类中一定有构造器，便于子类实例化时调用

     开发中，都会提供抽象类的子类，让子类实例化，完成相关操作

   + 修饰方法时，需要保证方法所在的类不能造对象，即所在类必须为抽象类，但抽象类中不一定有抽象方法

     抽象方法只有方法的声明，没有方法体

     若子类重写了父类中的所有的抽象方法后，此子类方可实例化

     若子类没有重写父类中的所有的抽象方法，则此子类也是一个抽象类，需要使用abstract修饰

3. abstract使用上的注意点：

   abstract不能用来修饰：属性、构造器等结构

   abstract不能用来修饰私有方法、静态方法、final的方法、final的类

## interface接口

接口的使用

1. 接口使用interface来定义

2. Java中，接口和类是并列的两个结构

3. 如何定义接口：定义接口中的成员

   3.1 JDK7及以前：只能定义全局常量和抽象方法

   > 全局常量：public static final的.但是书写时，可以省略不写
   >
   > 抽象方法：public abstract的

   3.2 JDK8：除了定义全局常量和抽象方法之外，还可以定义静态方法、默认方法（略）

4. 接口中**不能定义构造器**的！意味着接口不可以实例化

5. Java开发中，接口通过让类去实现(**implements**)的方式来使用.

   如果实现类**覆盖了接口中的所有抽象方法**，则此实现类就可以实例化

   如果实现类没有**覆盖接口中所有的抽象方法**，则此实现类仍为一个抽象类

6. Java类可以实现多个接口   --->弥补了Java单继承性的局限性

   格式：```class AA extends BB implements CC,DD,EE```

7. 接口与接口之间可以继承，而且可以多继承

8. 接口的具体使用，体现多态性

9. 接口，实际上可以看做是一种规范

	知识点1：接口中定义的静态方法，只能通过接口来调用。
	知识点2：通过实现类的对象，可以调用接口中的默认方法。如果实现类重写了接口中的默认方法，调用时，仍然调用的是重写以后的方法
	知识点3：如果子类(或实现类)继承的父类和实现的接口中声明了同名同参数的默认方法，那么子类在没有重写此方法的情况下，默认调用的是父类中的同名同参数的方法。-->类优先原则
	知识点4：如果实现类实现了多个接口，而这多个接口中定义了同名同参数的默认方法，
	那么在实现类没有重写此方法的情况下，报错。-->接口冲突。
	这就需要我们必须在实现类中重写此方法

```
public class InterfaceTest {
	public static void main(String[] args) {
		System.out.println(Flyable.MAX_SPEED);
		System.out.println(Flyable.MIN_SPEED);
//		Flyable.MIN_SPEED = 2;//不能被赋值，原因为已经被final修饰，不可改变
		
		Plane plane = new Plane();
		plane.fly();
	}
}
interface Flyable{
	//全局常量
	public static final int MAX_SPEED = 7900;//第一宇宙速度
	int MIN_SPEED = 1;//省略了public static final
	
	//抽象方法
	public abstract void fly();
	
	//省略了public abstract
	void stop();
	
	//Interfaces cannot have constructors意思是：接口中不能有构造器，故接口不能被实例化
	//ps:抽象类也不能实例化，但可以有构造器
//	public Flyable(){
//		
//	}
}
interface Attackable{
	void attack();
}
class Plane implements Flyable{
	@Override
	public void fly() {
		System.out.println("通过引擎起飞");
	}
	@Override
	public void stop() {
		System.out.println("驾驶员减速停止");
	}
}
abstract class Kite implements Flyable{
	@Override
	public void fly() {
	}
}
//先让父类继承，之后再实现接口
class Bullet extends Object implements Flyable,Attackable,CC{
	@Override
	public void attack() {
		// TODO Auto-generated method stub
	}
	@Override
	public void fly() {
		// TODO Auto-generated method stub
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}
	@Override
	public void method1() {
		// TODO Auto-generated method stub
	}
	@Override
	public void method2() {
		// TODO Auto-generated method stub
	}
}
```




## 内部类

1. Java中允许将一个类A声明在另一个类B中，则类A就是内部类，类B称为外部类

2. 内部类的分类：成员内部类（静态、非静态）  vs 局部内部类(方法内、代码块内、构造器内)

3. 成员内部类：

   + 一方面，作为外部类的成员：

     调用外部类的结构

     可以被static修饰

     可以被4种不同的权限修饰

   + 另一方面，作为一个类：

     类内可以定义属性、方法、构造器等

     可以被final修饰，表示此类不能被继承。言外之意，不使用final，就可以被继承

     可以被abstract修饰


```
public class InnerClassTest {
	public static void main(String[] args) {
		//创建Dog实例(静态的成员内部类):
		Person.Dog dog = new Person.Dog();//这样是因为Dog是Person类内部的类
		dog.show();
		//创建Bird实例(非静态的成员内部类):
//		Person.Bird bird = new Person.Bird();//错误的
		Person p = new Person();
		Person.Bird bird = p.new Bird();//注意，这里是p.new 不是new p.是通过对象调用类内部的结构
		bird.sing();
		System.out.println();
		bird.display("黄鹂");	
	}
}
class Person{
	String name = "小明";
	int age;
	public void eat(){
		System.out.println("人：吃饭");
	}
	//静态成员内部类
	static class Dog{
		String name;
		int age;
		public void show(){
			System.out.println("卡拉是条狗");
//			eat();//静态的不能调用非静态
		}	
	}
	//非静态成员内部类
	class Bird{
		String name = "杜鹃";
		public Bird(){	
		}
		public void sing(){
			System.out.println("我是一只小小鸟");
			Person.this.eat();//调用外部类的非静态属性|Person类的this.方法
			eat();
			System.out.println(age);
		}
		public void display(String name){
			System.out.println(name);//方法的形参
			System.out.println(this.name);//内部类的属性
			System.out.println(Person.this.name);//外部类的属性
		}
	}
	public void method(){
		//局部内部类
		class AA{	
		}
	}
	{
		//局部内部类
		class BB{	
		}
	}
	public Person(){
		//局部内部类
		class CC{	
		}
	}
}
```

