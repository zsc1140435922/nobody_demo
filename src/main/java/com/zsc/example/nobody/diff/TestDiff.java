package com.zsc.example.nobody.diff;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-11-19 16:04
 **/
public class TestDiff {
    public static void main(String[] args) {
        // given
        int i =0;

//        while (true){
//            if (i==10){
//                return;
//            }
            Javers javers = JaversBuilder.javers().build();

            Person person = new Person(1, "Michael Program");
            Person personAfterModification = new Person(1, "Michael Java");

            // when
            Diff diff = javers.compare(person, personAfterModification);

            // then
            ValueChange change = diff.getChangesByType(ValueChange.class).get(0);

            System.out.println(change.getPropertyName().equals("name"));

            System.out.println(change.getLeft().equals("Michael Program"));
            System.out.println(change.getRight().equals("Michael Java"));
            i ++;
//        }
//        Javers javers = JaversBuilder.javers().build();

//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                while(true){
//                    try {
////                        Javers javers = JaversBuilder.javers().build();
//
//                        Person person = new Person(1, "Michael Program");
//                        Person personAfterModification = new Person(1, "Michael Java");
//
//                        // when
//                        Diff diff = javers.compare(person, personAfterModification);
//
//                        // then
//                        ValueChange change = diff.getChangesByType(ValueChange.class).get(0);
//
//                        System.out.println(change.getPropertyName().equals("name"));
//
//                        System.out.println(change.getLeft().equals("Michael Program"));
//                        System.out.println(change.getRight().equals("Michael Java"));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }).start();
//
    }
}