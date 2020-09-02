package com.zsc.example.nobody.proxy.fastClass;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.reflect.FastClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-06-02 14:49
 **/
public class FastClassDemo {
    public static void main(String[] args) throws Exception {
        // 保留生成的FastClass类文件
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "com.sun.");
       /* System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Class delegateClass = DelegateClass.class;

        // Java Reflect
        // 反射构造类
        Constructor delegateConstructor = delegateClass.getConstructor(String.class);
        // 创建委托类实例
        DelegateClass delegateInstance = (DelegateClass) delegateConstructor.newInstance("Tom");
        // 反射方法类
        Method addMethod = delegateClass.getMethod("add", String.class, int.class);
        // 调用方法
        addMethod.invoke(delegateInstance, "Tom", 30);

        Method updateMethod = delegateClass.getMethod("update");
        updateMethod.invoke(delegateInstance);
*/


        /**
         * FastClass不使用反射类（Constructor或Method）来调用委托类方法，而是动态生成一个新的类（继承FastClass），
         * 向类中写入委托类实例直接调用方法的语句，用模板方式解决Java语法不支持问题，同时改善Java反射性能。
         *
         * 动态类为委托类方法调用语句建立索引，使用者根据方法签名（方法名+参数类型）得到索引值，再通过索引值进入相应的方法调用语句，得到调用结果。
         */
        // CGLib FastClass
        // FastClass动态子类实例
        FastClass fastClass = FastClass.create(DelegateClass.class);
        // 创建委托类实例
        DelegateClass fastInstance = (DelegateClass) fastClass.newInstance(
                new Class[] {String.class}, new Object[]{"Jack"});
        // 调用委托类方法
        fastClass.invoke("add", new Class[]{ String.class, int.class}, fastInstance,
                new Object[]{ "Zhangsc", 25});
        fastClass.invoke("update", new Class[]{}, fastInstance, new Object[]{});
    }
}
