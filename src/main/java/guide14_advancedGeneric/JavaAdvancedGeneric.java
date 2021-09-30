package guide14_advancedGeneric;

/**
 * Java泛型。
 * <p>
 * 一：基本使用
 * 1.泛型：使用参数化类型的概念，允许我们在不指定具体类型的情况下进行编程。
 * 2.Java在1.5中引入泛型，但是由于要兼容老版本，存在类型擦除的问题。
 * 二：泛型擦除
 * 1.泛型擦除是指通过类型参数合并，将泛型类型实例关联到同一份字节码上。编译器只为泛型类型生成一份字节码，并将其实例关联到这份字节码上。通
 * 俗讲就是在编译后，泛型类型就没有了，泛型类型全部按Object/上界类型解释。比如：不论JavaAdvancedGeneric的泛型是什么，在编译后value就是
 * Object类型。
 * 2.擦除过后：
 * <T> 擦除后变为 Object
 * <T extends A> 擦除后变为 A
 * <? extends A> 擦除后变为 A
 * <? super A> 擦除后变为Object
 * 3.Java 中的泛型是一种特殊的语法糖，通过类型擦除实现，这种泛型称为伪泛型，我们可以反射绕过编译器泛型检查，添加一个不同类型的参数
 * 4.泛型中所谓的类型擦除，其实只是擦除 Code 属性中的泛型信息，在类常量池属性（Signature 属性、LocalVariableTypeTable 属性）中
 * 其实还保留着泛型信息，而类常量池中的属性可以被 class 文件，字段表，方法表等携带，这就使得我们声明的泛型信息得以保留，这也是我们在
 * 运行时可以反射获取泛型信息的根本依据。
 * 5.当需要返回泛型数据时，JVM会为我们做类型强转
 * 三、边界
 * 1.Java 中设置边界使用 extends 关键字，完整语法结构：<T extends Bound> ，Bound 可以是类和接口，如果不指定边界，默认边界为 Object
 * 2.可以设置多个边界，中间使用 & 连接，多个边界中只能有一个边界是类，且类必须放在最前面，类似这种语法结构：<T extends ClassBound & InterfaceBound1 & InterfaceBound2>
 * 3.T 没有 <T super A> 的用法，只有通配符 ? 可以这样用。
 * 四、通配符
 * 1.泛型协变，假设我定义了一个 Class<T> 的泛型类，其中 A 是 B 的子类，同时 Class<A> 也是 Class<B> 的子类，那么我们说 Class 在 T 这个泛型上是协变的
 * 2.泛型逆变，假设我定义了一个 Class<T> 的泛型类，其中 A 是 B 的子类，同时 Class<B> 也是 Class<A> 的子类，那么我们说 Class 在 T 这个泛型上是逆变的
 * 3.泛型不变，假设我定义了一个 Class<T> 的泛型类，其中 A 是 B 的子类，同时 Class<B> 和 Class<A> 没有继承关系，那么我们说 Class 在 T 这个泛型上是不变的
 * 4.Java中的泛型本身是不变的，但是为了实现协变，增加了通配符 ? 和 extends 关键字，为了实现逆变，增加了通配符 ? 和 super 关键字。
 * 5.泛型的上边界通配符语法结构：<? extends Bound>，使得泛型支持协变，它限定的类型是当前上边界类或者其子类，如果是接口的话就是当前
 * 上边界接口或者实现类，使用上边界通配符的变量只读，不可以写，可以添加 null ，但是没意义。
 * 6.泛型的下边界通配符语法结构：<? super Bound>，使得泛型支持逆变，它限定的类型是当前下边界类或者其父类，如果是接口的话就是当前下
 * 边界接口或者其父接口，使用下边界通配符的变量只写，不建议读，因为读出来的是 Object 类型，没有意义。
 * 7.无边界通配符的语法结构：<?>，实际上它等价于 <? extends Object>，也就是说它的上边界是 Object 或其子类，因此使用无界通配符的
 * 变量同样只读，不能写，可以添加 null ，但是没意义
 * 8.当你只想读取值的时候，使用 <? extends T>
 * 9.当你只想写入值的时候，使用 <? super T>
 * 10.当你既想读取值又想写入值的时候，就不要使用通配符
 * 五、? 与 T 的区别
 * 1.? 表示一个未知类型, T 是表示一个确定的类型. 因此,无法使用 ? 像 T 声明变量和使用变量
 * 2.? 主要针对 泛型类的限制, 无法像 T类型参数一样单独存在
 * 3.? 表示 <? extends Object>, 因此它是属于 in类型,无法接收数据, 而T可以
 * 4.? 主要表示使用泛型,T表示声明泛型
 * 5.永远不要在方法返回中使用?,在方法中不会报错,但是方法的接收者将无法正常使用返回值.因为它返回了一个不确定的类型.
 *
 * <p>
 * author: qonyqian
 * created on: 2021/9/20 5:57 下午
 * version：1.0
 * description:
 */

/**
 * 1.定义一个泛型类
 *
 * @param <T>
 */
public class JavaAdvancedGeneric<T> {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    /**
     * 2.定义一个泛型方法
     *
     * @param info
     * @param <K>
     */
    public <K> void printInfo(K info) {
        if (info == null) {
            return;
        }
        System.out.println("info = " + info.toString());
    }


    /**
     * 3.定义一个泛型接口
     *
     * @param <M>
     */
    interface IJAD<M> {

        M getValue();

        <N> void saveData(N data);
    }

    /**
     * 注意这里的<String>，并不是表示字符串类型，而是泛型代号叫：String，与JADImplTwo中的 M 一样。
     *
     * @param <String>
     */
    public static class JADImplOne<String> implements IJAD<String> {
        private String value;

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public <N> void saveData(N data) {
            System.out.println("saveData = " + data);
        }
    }

    public static class JADImplTwo<M> implements IJAD<M> {
        private M value;

        public void setValue(M value) {
            this.value = value;
        }

        @Override
        public M getValue() {
            return value;
        }

        @Override
        public <N> void saveData(N data) {
            System.out.println("saveData = " + data);
        }
    }

    public static void main(String[] args) {
        //使用泛型类
        JavaAdvancedGeneric<String> jag = new JavaAdvancedGeneric<>();
        jag.setValue("edison");
        String name = jag.getValue();
        //使用泛型方法
        jag.printInfo("edison");
        jag.printInfo(100);
        //使用泛型接口
        JADImplOne<Integer> one = new JADImplOne<>();
        one.setValue(100);
        one.saveData("edison");
        JADImplTwo<String> two = new JADImplTwo<>();
        two.setValue("qony");
        two.saveData(200);
        two.saveData("edison");
    }
}
