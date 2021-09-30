package chap17_variance;

import java.util.ArrayList;
import java.util.List;

public class GenericInvariance {
    public static void main (String... args) {
        varianceTest();
    }

    public static void varianceTest() {
        List<Number> numbers = new ArrayList<>();
        genericVariance(numbers);

        List<Integer> integers = new ArrayList<>();
        //This approach moved the potential run-time exception to a compile time exception
        //genericVariance(integers); // compile error
    }

    /**
     * Java中的泛型是通过类型擦除来实现的。List<Number>编译后，会变成List<Object>。所以,Java中的泛型不支持
     * 协变和逆变（因为类型被擦除了，不知道对象之间的关系）,List<Number> != List<Integer>。但是数组支持这个特性。
     * 子类的数组，是数组的子类。
     * 子类的泛型，不是泛型的子类。
     * 多态性
     * 泛型具有不变性（擦除后，都一样）。Java通过引入extend和super关键字，来实现泛型的协变和逆变。
     * 协变，写入时可能会有类型错误，所以限定只能读
     * 逆变，读出时可能会有类型错误，所以限定只能写
     * @param numbers
     */
    public static void genericVariance(List<Number> numbers) {
        numbers.add(new Long(12));
    }
}
