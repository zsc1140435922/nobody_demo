package com.zsc.example.nobody.designMode.factory;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-04-21 22:51
 **/
public class GraphFactory {
    /*
     *获得Apple类的实例
     * */
//    public Graph getApple(){
//        return new Apple();
//    }
//
//    /*
//     *获得Banana类的实例
//     * */
//    public Graph getBanana(){
//        return new Banana();
//    }

    public static Graph getGraph(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName(name);
        return (Graph) aClass.newInstance();
    }
}
