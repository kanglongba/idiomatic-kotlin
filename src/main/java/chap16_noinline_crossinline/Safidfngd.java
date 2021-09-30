package chap16_noinline_crossinline;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class Safidfngd {
    private static final void doSomething(Object obj, Function1 func) {
        func.invoke(obj);
    }

    public static final void main(String[] args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        String obj$iv = new String();
        Function1 func$iv = (Function1) new Function1<String, String>() {
            @Override
            public String invoke(String s) {
                return s;
            }
        };
        func$iv.invoke(obj$iv);
    }
}
