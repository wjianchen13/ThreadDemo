package com.example.threaddemo.test6.test1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestManager {

    private static Map<Class<?>, Object> map = new ConcurrentHashMap<>();

    public static <T> T getCore(Class<T> cls) {
        T t = (T)(map.get(cls));
        String className = cls.getName();
        if(t == null) {
            if (className.equals(Test1.class.getName())) {
                t = (T)createTest1(cls);
            } else if (className.equals(Test2.class.getName())) {
                t = (T)(createTest2(cls));
            }
        }
        return t;
    }

    public static Test1 createTest1(Class cls) {
        Test1 t1 = (Test1)map.get(cls);
        if(t1 == null) {
            synchronized (Test1.class) {
                t1 = (Test1)map.get(cls);
                if (t1 == null) {
                    t1 = new Test1();
                    map.put(cls, t1);
                }
            }
        }

        return t1;
    }

    public static Test2 createTest2(Class cls) {
        Test2 t2 = (Test2)map.get(cls);
        if(t2 == null) {
            synchronized (Test2.class) {
                t2 = (Test2)map.get(cls);
                if (t2 == null) {
                    t2 = new Test2();
                    map.put(cls, t2);
                }
            }
        }

        return t2;
    }

}
