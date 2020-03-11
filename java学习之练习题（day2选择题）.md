### java学习之练习题（day2选择题）

```java
1.A 派生出子类 B ， B 派生出子类 C ，并且在 java 源代码有如下声明：
1. A a0=new A();
2. A a1=new B();
3. A a2=new C();
问以下哪个说法是正确的？（ D ）

A 只有第一行能通过编译
B 第1、2行能通过编译，但第3行编译出错
C 第1、2、3行能通过编译，但第2、3行运行时出错
D 第1行，第2行和第3行的声明都是正确的

**解析**
因为继承具有多态性
创建父类的变量并赋予其子类的对象，会自动向上转型
```

```java
2.下面代码将输出什么内容：（ B ）
public class SystemUtil{
	public static boolean isAdmin(String userId){
		return userId.toLowerCase()=="admin";
	}
	public static void main(String[] args){
		System.out.println(isAdmin("Admin"));
	}
}

A true
B false
C 1
D 编译错误

**解析**
因为toLowerCase()方法底层实现是new了一个字符串
return new String(result, 0, len + resultOffset);
== 比较的是地址。
equals 比较的话 先比较地址，如果相等，直接返回true
然后看比较的对象是不是String类型，如果不是直接返回false
如果是的话，则依次比较每个字符，如果全部相等，则返回true
```

```java 
3.阅读如下代码。 请问，对语句行 test.hello(). 描述正确的有（ A ）
package NowCoder;
class Test {
	public static void hello() {
		System.out.println("hello");
	}
}
public class MyApplication {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test=null;
		test.hello();
	}
}

A 能编译通过，并正确运行
B 因为使用了未初始化的变量，所以不能编译通过
C 以错误的方式访问了静态方法
D 能编译通过，但因变量为null，不能正常运行

**解析**
我们类的静态成员不依赖实例对象，静态成员在类编译的时候就已经被创建好了，经测试后发现，无论是是使用类名，还是使用我们定义的类变量都是可以访问的，哪怕它已经被实例化。
		Test test= new Test();
        test.sayHello();
        Test.sayHello();
        System.out.println(test.x);
        System.out.println(Test.x);
```

```java 
4.在使用super和this关键字时，以下描述正确的是（ A ）

A 在子类构造方法中使用super（）显示调用父类的构造方法，super（）必须写在子类构造方法的第一
行，否则编译不通过
B super（）和this（）不一定要放在构造方法内第一行
C this（）和super（）可以同时出现在一个构造函数中
D this（）和super（）可以在static环境中使用，包括static方法和static语句块

**解析**
super()在第一行的原因就是: 子类有可能访问了父类对象, 比如在构造函数中使用父类对象的成员函数和变量, 在成员初始化使用了父类, 在代码块中使用了父类等, 所以为保证在子类可以访问父类对象之前要完成对父类对象的初始化

　　this()在第一行的原因就是: 为保证父类对象初始化的唯一性. 我们假设一种情况, 类B是类A的子类, 如果this()可以在构造函数的任意行使用, 那么会出现什么情况呢? 首先程序运行到构造函数B()的第一行, 发现没有调用this()和super(), 就自动在第一行补齐了super() , 完成了对父类对象的初始化, 然后返回子类的构造函数继续执行, 当运行到构造函数B()的"this(2) ;"时, 调用B类对象的B(int) 构造函数, 在B(int)中, 还会对父类对象再次初始化! 这就造成了对资源的浪费, 当然也有可能造成某些意想不到的结果, 不管怎样, 总之是不合理的, 所以this() 不能出现在除第一行以外的其他行!

在创建子类对象时，父类的构造方法会先执行，因为子类中所有构造方法的第一行有默认的隐式super()语句。
在子类中重载父类构造方法的时候，如果父类的构造方法时无参数的，可以不必写出super();有参数是必须写出的
```

```java
5.如下代码的 结果是什么? ( B )
class Base {
	Base() {
		System.out.print("Base");
	}
}
public class Alpha extends Base {
	public static void main( String[] args ) {
		new Alpha();
		//调用父类无参的构造方法
		new Base();
	}
}

A Base
B BaseBase
C 编译失败
D 代码运行但没有输出
E 运行时抛出异常

**解析**
子类构造器的默认第一行就是super()，默认调用直接父类的无参构造。构造方法只会被调用，不会被继承
```

```java 
6.如下代码的输出结果是什么? ( D )
public class Test {
	public int aMethod(){
		static int i = 0;
		i++;
		return i;
	}
	public static void main(String args[]){
		Test test = new Test();
		test.aMethod();
		int j = test.aMethod();
		System.out.println(j);
	}
}

A 0
B 1
C 2
D 编译失败

**解析**
Java中静态变量只能在类主体中定义，不能在方法中定义。 静态变量属于类所有而不属于方法。

```

```java 
7. 下列哪一种叙述是正确的（ D ）

A abstract修饰符可修饰字段、方法和类
B 抽象方法的body部分必须用一对大括号{ }包住
C 声明抽象方法，大括号可有可无
D 声明抽象方法不可写出大括号

**解析**
abstract修饰方法和类
抽象方法没有方法体，有没有方法体看有没有大括号。
```

```java
8.下列说法正确的有：（ C ）

A class中的constructor不可省略
B constructor必须与class同名，但方法不能与class同名
C constructor在一个对象被new 时执行
D 一个class只能定义一个constructor

**解析**
构造方法省略时，略后JVM会自动创建一个无参的构造函数
方法可以和类名同名的，和构造方法唯一的区别就是，构造方法没有返回值

```

```java 
9.选项中哪一行代码可以替换 /*add code here*/ 而不产生编译错误( A )
public abstract class MyClass {
	public int constInt = 5;
	//add code here
	public void method() {
	}
}

A public abstract void method(int a);
B consInt=constInt+5;
C public int method();
D public abstract void anotherMethod(){}

**解析**
抽象类中，使用抽象abstract 修饰的方法没有方法体；普通方法则要有方法体。
```

```java
10.java 中哪个关键字可以对对象加互斥锁？( B )
A transient
B synchronized
C serialize
D static

**解析**
transient，反序列化修饰符，序列化的时候，类中的属性都会被复制，但是被transient和static修饰的属性不会被复制。
synchronized，同步锁，被synchronized修饰的方法或者代码块，同一时刻只允许被一个线程访问。
volatile：用来确保将变量的跟新操作通知到其他线程，当把变量声明为volatile类型后，编译器与运行时都会注意到这个变量是共享的，因此不会将该变量上的操作与其他内存操作一起重排序。然而，在访问volatile变量时不会执行加锁操作，因此也就不会使执行线程阻塞，因此volatile变量是一种比 synchronized关键字更轻量级的同步机制。
static，静态修饰符。可修饰成员变量和方法，内部类/接口，不可修饰局部变量和外部类/接口。
```

