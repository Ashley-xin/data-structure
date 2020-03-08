### java学习之练习题（day1选择题）

```java
1.执行如下程序，输出结果是（ C ）
class Test
{
    private int data;
    int result = 0;
    public void m()
    {
        result += 2;
        data += 2;
        System.out.print(result + " " + data);
    }
}

class ThreadExample extends Thread
{
    private Test mv;
    public ThreadExample(Test mv)
    {
        this.mv = mv;
    }
    public void run()
    {
        synchronized(mv)
        {
            mv.m();
        }
    }
}

class ThreadTest {
    public static void main(String args[]) {
        Test mv = new Test ( );
        Thread t1 = new ThreadExample ( mv );
        Thread t2 = new ThreadExample ( mv );
        Thread t3 = new ThreadExample ( mv );
        t1.start ( );
        t2.start ( );
        t3.start ( );
    }
}

```

```
A 0 22 44 6
B 2 42 42 4
C 2 24 46 6
D 4 44 46 6
```

```java
**解析**   使用synchronized关键字加同步锁线程依次操作m() 
			t1.start();使得result=2,data=2,输出即为2 2 
			t2.start();使得result=4,data=4,输出即为4 4 
			t3.start();使得result=6,data=6,输出即为6 6  
			System.out.print(result +" "+ data);因为print()方法不会换行,输出结果为 2 24 46 6
```

```java
2.要使对象具有序列化能力，则其类应该实现如下哪个接口( A )。
A java.io.Serializable
B java.lang.Cloneable,
C java.lang.CharSequence
D java.lang.Comparable



**解析**    
java.io.Serializable接口是一个标志性接口，在接口内部没有定义任何属性与方法。只是用于标志此接口的实现类可以被序列化与反序列化。
java.lang.Cloneable接口实现克隆方法的；Object.clone() 方法可以合法地对该类实例进行按字段复制
java.lang.CharSequence是一个描述字符串结构的接口，常用三个子类String，StringBuffer,StirngBuilder类
java.lang.Comparable此接口强行对实现它的每个类的对象进行整体排序。这种排序被称为类的自然排序，类的 compareTo 方法被称为它的自然比较方法。
实现类基于 compareTo() 方法的排序被称为自然排序。而 compareTo() 方法的排序被称为它的自然排序。具体的排序原则可由实现类根据需要而定。用户在重写 compareTo() 方法以定制比较逻辑时，需要确保其余等价性判断方法 equals() 保持一致，即 e1.equals((Object)e2) 和e1.compareTo((Object)e2)==0 具有相同的值，这样的话我们就称自然顺序就和 equals 一致。
实现此接口的对象列表（和数组）可以通过 Collections.sort（和 Arrays.sort）进行自动排序。实现此接口的对象可以用作有序映射中的键或有序集合中的元素，无需指定比较器。
Comparable 接口只有一个方法 compareTo(Object obj)
其中
this < obj 返回负
this = obj 返回 0
this > obj 返回正
序列化 (Serialization)是将对象的状态信息转换为可以存储或传输的形式的过程。在序列化期间，对象将其当前状态写入到临时或持久性存储区。以后，可以通过从存储区中读取或反序列化对象的状态，重新创建该对象
```

```java
3.下列选项中属于面向对象设计方法主要特征的是（ A ）。
A 继承
B 自顶向下
C 模块化
D 逐步求精

**解析**
面向对象设计方法主要特征是  封装  继承  多态
```

```java
4.关于下列程序段的输出结果，说法正确的是：（ D ）
public class MyClass{
	static int i;
	public static void main(String argv[]){
	System.out.println(i);
	}
}

A 有错误，变量i没有初始化。
B null
C 1
D 0
    
**解析**
成员变量 在类加载时被创建,并且进行初始化
局部变量 没有默认初始化值，必须先赋值后使用，否则通不过编译

关于成员变量 和 局部变量的区别
1.成员变量是独立于方法外的变量，局部变量类方法中的变量，所有类的成员变量可以通过this来引用
2.成员变量：包括实例变量和类变量，用static修饰的是类变量，不用static修饰的是实例变量。
3.局部变量：包括形参，方法局部变量，代码块局部变量，存在于方法的参数列表和方法定义中以及代码块中。
4.成员变量可以被public，protect，private，static等修饰符修饰，而局部变量不能被控制修饰符及 static修饰；两者都可以定义成final型。
5.成员变量存储在堆，局部变量存储在栈。局部变量的作用域仅限于定义它的方法，在该方法的外部无法访问它。成员变量的作用域在整个类内部都是可见的，所有成员方法都可以使用它。如果访问权限允许，还可以在类的外部使用成员变量。

6.局部变量的生存周期与方法的执行期相同。当方法执行到定义局部变量的语句时，局部变量被创建；执行到它所在的作用域的最后一条语句时，局部变量被销毁。类的成员变量，如果是实例成员变量，它和对象的生存期相同。而静态成员变量的生存期是整个程序运行期。
7。成员变量在类加载或实例被创建时，系统自动分配内存空间，并在分配空间后自动为成员变量指定初始化值，初始化值为默认值，基本类型的默认值为0，复合类型的默认值为null。（被final修饰且没有static的必须显式赋值），局部变量在定义后必须经过显式初始化后才能使用，系统不会为局部变量执行初始化。
8.局部变量可以和成员变量同名，且在使用时，局部变量具有更高的优先级，直接使用同名访问，访问的是局部变量，如需要访问成员变量可以用this.变量名访问。

八大基本数据类型的默认值如下~
boolean（false）	short（0）	byte（0）	float（0.0F或0.0f）	
double（0.0）	long（0L）	char（‘\u0000’） int（0）

```

```java
5.下列代码的执行结果是：（ B ）
public class Test3{
	public static void main(String args[]){
		System.out.println(100%3);
		System.out.println(100%3.0);
	}
}

A 1和1
B 1和1.0
C 1.0和1
D 1.0和1.0
  
**解析**     
 后面的式子因为除数为double类型，所以结果为double类型   
    
```

```java
6. 在基本 JAVA 类型中，如果不明确指定，整数型的默认是 _int_ 类型，带小数的默认是 _double_ 类型
```

```java
7.方法通常存储在进程中的哪一区（ D ）
A 堆区
B 栈区
C 全局区
D 方法区

**解析**
堆区：唯一目的就是存放对象实例。几乎所有对象实例都在这里分配。
栈区：线程私有的，用于存储局部变量表。
全局区：静态区，静态变量和全局变量的存储区域是一起的，一旦静态区的内存被分配, 静态区的内存直到程序全部结束之后才会被释放。
方法区：存储已经被虚拟机加载的类信息，常量，静态变量。
```

```java
8.不考虑反射，关于私有访问控制符 private 修饰的成员变量，以下说法正确的是（ C ）
A 可以三种类所引用：该类自身、与它在同一包中的其他类，在其他包中的该类的子类
B 可以被两种类访问和引用：该类本身、该类的所有子类
C 只能被该类自身所访问和修改
D 只能被同一个包中的类访问

**解析**
1.public表明该数据成员、成员函数是对所有用户开放的，所有用户都可以直接进行调用
2.private表示私有，私有的意思就是除了类本身之外，任何人都不可以直接使用。
3.protected对于子女、朋友来说，就是public的，可以自由使用，没有任何限制，而对于其他的外部class，protected就变成private

```

```java
9. Math.round(11.5) 等于多少 (). Math.round(-11.5) 等于多少 ( C )
A 11 ,-11
B 11 ,-12
C 12 ,-11
D 12 ,-12
    
**解析**
round方法：
static long round(double a)
此方法返回的参数最接近的long.
static int round(float a)
此方法返回的参数最接近的整数.
    四舍六入五成双:
当有效位数确定后，其后面多余的数字应该舍去，只保留有效数字最末一位，这种修约（舍入）规则是“四舍六入五成双”，也即“4舍6入5凑偶”这里“四”是指≤4 时舍去，”六”是指≥6时进上，”五”指的是根据5后面的数字来定，当5后有数时，舍5入1；当5后无有效数字时，需要分两种情况来讲：①5前为奇数，舍5入1；②5前为偶数，舍5不进。（0是偶数）

1. round方法，在Math中有重载的，一个参数是float，一个是double，对应返回int和long值。返回值是当前值+0.5向下取整（-0.5 --> 0）；
2. ceil方法，没有重载，参数就是double，返回值也是double，但是这个时候就有区别了，向上取整（-0.5 ---> -0.0），注意一下，负号是保留的，和round有区别的；
3. floor，和ceil一样，没有重载，参数和返回值都是double，向下取整（-0.5 --> -1.0）。

```

```java
10.假设 A 类有如下定义，设 a 是 A 类的一个实例，下列语句调用哪个是错误的？（ A ）

public class A
{
	public int i;
	static String s;
	void method1(){}
	static void method2(){}
}

A System.out.println(a.i);
B a.method1();
C A.method1();
D A.method2();

**解析**
static修饰的方法为静态方法；
静态成员变量，静态方法可以直接通过类名或对象名去调用；
而非静态方法，应该是通过对象的实例化去调用。
即：C项应该是 a.method1();

```

