### java学习之练习题（day5选择题）

```java
1.下面的程序 编译运行后，在屏幕上显示的结果是（ A ）
public class test {
	public static void main(String args[]) {
		int x,y;
		x=5>>2;
		y=x>>>2;
		System.out.println(y);
	}
}

A 0
B 2
C 5
D 80

**解析**
5 >> 2 
>>，有符号右移位，将运算数的二进制整体右移指定位数，整数高位用0补齐，负数高位用1补齐（保持负数符号不变）。
相当于除法  最后5/2的平方 为1
>>>，无符号右移位，不管正数还是负数，高位都用0补齐（忽略符号位）
0001右移两位0000
所以结果为0

```

```java
2.以下代码结果是什么( C )
public class foo {
	public static void main(String sgf[]) {
		StringBuffer a=new StringBuffer(“A”);
		StringBuffer b=new StringBuffer(“B”);
		operate(a,b);
		System.out.println(a+”.”+b);
	}
	static void operate(StringBuffer x,StringBuffer y) {
		x.append(y);
		y=x;
	}
}

A 代码可以编译运行，输出“AB.AB”。
B 代码可以编译运行，输出“A.A”。
C 代码可以编译运行，输出“AB.B”。
D 代码可以编译运行，输出“A.B”。

**解析**
在main方法中a，b为引用储存的是对象StringBuffer的地址，所以调用方法时，将它们两个指向的地址传入了方法中，x.append(y)是在x对象的末尾追加了y对象，通过引用操作对象，这个对象是a指向的对象。

y=x，对引用来说，赋值等于将地址赋值给引用，所以这个意义在于x，y都指向了x指向的对象。
然而b的指向却没有改变。

```

```java
3.在JAVA中，假设A有构造方法A(int a)，则在类A的其他构造方法中调用该构造方法和语句格式应该为（ B ）

A this.A(x)
B this(x)
C super(x)
D A(x)
    
**解析**
A.这是调用普通方法的写法
C.这时显示调用父类构造方法
D.调用静态方法　
```

```java
4.下面代码的运行结果是（ C ）
public static void main(String[] args){
	String s;
	System.out.println("s="+s);
}

A 代码编程成功，并输出”s=”
B 代码编译成功，并输出”s=null”
C 由于String s没有初始化，代码不能编译通过。
D 代码编译成功，但捕获到NullPointException异常
    
**解析**
局部变量必须初始化。

```

```java
5.装箱、拆箱操作发生在: ( C )
A 类与对象之间
B 对象与对象之间
C 引用类型与值类型之间
D 引用类型与引用类型之间

**解析**
 装箱转换是指将一个值类型隐式地转换成一个object 类型，或者把这个值类型转换成一个被该值类型应用的接口类型interface-type。把一个值类型的值装箱，也就是创建一个object 实例并将这个值复制给这个object。

拆箱转换是指将一个对象类型显式地转换成一个值类型，或是将一个接口类型显式地转换成一个执行该接口的值类型。拆箱的过程分为两步：首先，检查这个对象实例，看它是否为给定的值类型的装箱值。然后，把这个实例的值拷贝给值类型的变量
```

```java
6.一个以”.java”为后缀的源文件( C )
A 只能包含一个类，类名必须与文件名相同
B 只能包含与文件名相同的类以及其中的内部类
C 只能有一个与文件名相同的public类，可以包含其他类
D 可以包含任意类
    
```

```java
7.下列哪个说法是正确的（ D ）
A ConcurrentHashMap使用synchronized关键字保证线程安全
B HashMap实现了Collction接口
C Array.asList方法返回java.util.ArrayList对象
D SimpleDateFormat是线程不安全的
    
 **解析**
hashMap在单线程中使用大大提高效率，在多线程的情况下使用hashTable来确保安全。hashTable中使用synchronized关键字来实现安全机制，但是synchronized是对整张hash表进行锁定即让线程独享整张hash表，在安全同时造成了浪费。concurrentHashMap采用分段加锁的机制来确保安全
Arrays.asList()
 
concurrenthashmap取消了segment分段锁，采用cas和synchronized来保证并发安全，数据结构跟hashmap1.8的结构类似，数组+链表或红黑二叉树

将一个数组转化为一个List对象，这个方法返回一个ArrayList类型的对象， 这个ArrayList类并非java.util.ArrayList类，而是Arrays类的静态内部类！用这个对象对列表进行添加删除更新操作，就会报UnsupportedOperationException异常。

Arrays.ArrayList 是工具类 Arrays 的一个内部静态类，它没有完全实现List的方法，而 ArrayList直接实现了List 接口，实现了List所有方法。

长度不同 和 实现的方法不同
Arrays.ArrayList是一个定长集合，因为它没有重写add,remove方法，所以一旦初始化元素后，集合的size就是不可变的。

参数赋值方式不同
Arrays.ArrayList将外部数组的引用直接通过“=”赋予内部的泛型数组，所以本质指向同一个数组。
ArrayList是将其他集合转为数组后copy到自己内部的数组的。

多个线程同时对一个SimpleDateFormat对象进行操作的时候，就会出现错乱。
例如全局的private static final SimpleDateFormat df = new SimpleDateFormat(“yyyy-MM-dd HH:mm:ss”);
查看SimpleDateFormat 源码：calendar为protected类型的全局变量，当线程A设置了calendar.setTime(date);
线程B又设置了一次，那么calendar值就变了，线程A在subFormat方法中用到的calendar就不是自己设置的而是线程B设置的。
无状态：无状态方法的好处之一，就是它在各种环境下，都可以安全的调用。衡量一个方法是否是有状态的，就看它是否改动了其它的东西，比如全局变量，比如实例的字段。format 方法在运行过程中改动了 SimpleDateFormat 的 calendar 字段，所以，它是有状态的。
1.每个线程使用的时候创建新的SimpleDateFormat，将有线程安全问题的对象由共享变为局部私有都能避免多线程问题
2.使用同步：同步 SimpleDateFormat 对象
3.使用 ThreadLocal
public class DateSyncUtil {
        private ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
        };
        public Date parse(String dateStr){
            return threadLocal.get().parse(dateStr);
        }
        public String format(Date date) {
            return threadLocal.get().format(date);
        }
 }
 说明：使用 ThreadLocal, 也是将共享变量变为独享，线程独享肯定能比方法独享在并发环境中能减少不少创建对象的开销。如果对性能要求比较高的情况下，一般推荐使用这种方法。
```

```java
8.以下说法错误的是（ D ）
A 虚拟机中没有泛型，只有普通类和普通方法
B 所有泛型类的类型参数在编译时都会被擦除
C 创建泛型对象时请指明类型，让编译器尽早的做参数检查
D 泛型的类型擦除机制意味着不能在运行时动态获取List<T>中T的实际类型

    
**解析**
1.创建泛型对象的时候，一定要指出类型变量T的具体类型。争取让编译器检查出错误，而不是留给JVM运行的时候抛出类不匹配的异常。 2、JVM如何理解泛型概念 —— 类型擦除。事实上，JVM并不知道泛型，所有的泛型在编译阶段就已经被处理成了普通类和方法。 处理方法很简单，我们叫做类型变量T的擦除(erased) 。 总结：泛型代码与JVM ① 虚拟机中没有泛型，只有普通类和方法。 ② 在编译阶段，所有泛型类的类型参数都会被Object或者它们的限定边界来替换。(类型擦除) ③ 在继承泛型类型的时候，桥方法的合成是为了避免类型变量擦除所带来的多态灾难。 无论我们如何定义一个泛型类型，相应的都会有一个原始类型被自动提供。原始类型的名字就是擦除类型参数的泛型类型的名字


// 通过反射得到T的真实类型
ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
T的真实类型 = (Class) pt.getActualTypeArguments()[0];//取第一个泛型的类型
```

```java
9.下列代码执行结果为（ A ）
public static void main(String args[])throws InterruptedException{    
	Thread t=new Thread(new Runnable() {    
		public void run() {        
			try {            
				Thread.sleep(2000);        
			} catch (InterruptedException e) {            
				throw new RuntimeException(e);	
			}
			System.out.print("2");
		}
	});
	t.start();
	t.join();
	System.out.print("1");
}
A 21
B 12
C 可能为12，也可能为21
D 以上答案都不对

**解析**
```

```java
10.指出以下程序运行的结果是( B )
public class Example{
	String str=new String("good");
	char[]ch={'a','b','c'};
	public static void main(String args[]){
		Example ex=new Example();
		ex.change(ex.str,ex.ch);
		System.out.print(ex.str+" and ");
		System.out.print(ex.ch);
	}
	public void change(String str,char ch[]){
		//引用类型变量，传递的是地址，属于引用传递。
		str="test ok";
		ch[0]='g';
	}
}

A good and abc
B good and gbc
C test ok and abc
D test ok and gbc
**解析**
传值和引用中有静态变量，私有变量，clone等问题，对于基本数据类型和对象变量，存在不一样的操作：

基本类型变量：传递值的副本（副本变，本身不变）
对象变量：传递引用的副本（副本变，本身也变）【即复制指向地址的指针】

不论Java参数的类型是什么，一律传递的都是参数的副本

在Java中存在一个比较特殊的类型，String，也是对象型变量，只不过String是一个非可变类，使得值传递和引用传递没有区别


```

