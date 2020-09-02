package com.zsc.example.nobody.classreflection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: nobody_demo
 * @description: 比较类
 * @author: zhangsc
 * @create: 2020-03-02 11:26
 **/
public class ClassAttrValCompare {
    private DifferenceAttr differenceAttr;

    public ClassAttrValCompare() {
        differenceAttr = new DifferenceAttr();
    }
    /**
     * 比较单个值
     * @param objA
     * @return
     */
    public DifferenceAttr compareValue(Object objA) {
        DifferenceAttr differAttr = new DifferenceAttr();
        try{
            List<String> differenceAttrs = new ArrayList<String>();
            List<DifferenceAttr> childDifferenceAttr = new ArrayList<DifferenceAttr>();
            Class<?> clazzA = objA.getClass();
            Method[] methods = clazzA.getDeclaredMethods();
            Object result = null;
            for(Method method:methods) {
                if(method.getName().startsWith("get")) {
                    result = method.invoke(objA, null);
                    if(result==null) {
                        continue;
                    }
                    if(result instanceof List) {
                        List<Object> childList = (List)result;
                        for(Object object:childList) {
                            childDifferenceAttr.add(compareValue(object));
                        }
                    }else {
                        String nameTrim = method.getName().substring(3);
                        nameTrim = nameTrim.substring(0,1).toLowerCase()+nameTrim.substring(1);
                        differenceAttrs.add(nameTrim);
                    }
                }
            }
            differAttr.setDifferenceAttrs(differenceAttrs);
            differAttr.setChildrenDifference(childDifferenceAttr);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return differAttr;
    }
    /**
     * 两个对象值的对比（objA为对比）
     * @param objA
     * @param objB
     * @return
     */
    public DifferenceAttr compareValue(Object objA,Object objB) {
        DifferenceAttr differenceAttr = new DifferenceAttr();
        //值都为空，则返回
        if(objA==null&&objB==null) {
            return null;
        }
        //objA不为空，objB为空,则把返回objA所有字段
        if(objA!=null&&objB==null) {
            return compareValue(objA);
        }
        //当前类差异集合
        List<String> differenceAttrs = new ArrayList<String>();
        //子集合查询集合
        List<DifferenceAttr> childrenDifference = new ArrayList<DifferenceAttr>();

        try {
            Class<?> clazzA = objA.getClass();
            Class<?> clazzB = objB.getClass();
            Method[] methods = clazzA.getDeclaredMethods();
            Object resultA = null;
            Object resultB = null;
            Method methodB = null;
            for(Method method:methods) {
                if(method.getName().startsWith("get")) {
                    methodB = clazzB.getMethod(method.getName(), null);
                    resultB = methodB.invoke(objB, null);
                    resultA = method.invoke(objA, null);
                    if(resultA==null&&resultB==null) {
                        continue;
                    }

                    if(resultA==null&&resultB!=null) {
                        differenceAttrs.add(method.getName());
                    }else if(resultA!=null&&resultB==null) {
                        differenceAttrs.add(method.getName());
                    }else {

                        if(!(resultA instanceof List)) {
                            if(!resultA.equals(resultB)) {
                                String nameTrim = method.getName().substring(3);
                                nameTrim = nameTrim.substring(0,1).toLowerCase()+nameTrim.substring(1);
                                differenceAttrs.add(nameTrim);
                            }
                        }else {
                            List<Object> listA = (List<Object>)resultA;
                            List<Object> listB = (List<Object>)resultB;

                            for(int i=0;i<listA.size();i++) {
                                DifferenceAttr childAttr = null;
                                if(i>=listB.size()) {
                                    childAttr = compareValue(listA.get(i), null);
                                }else {
                                    childAttr = compareValue(listA.get(i), listB.get(i));
                                }
                                childrenDifference.add(childAttr);
                            }
                        }
                    }
                    differenceAttr.setChildrenDifference(childrenDifference);
                    differenceAttr.setDifferenceAttrs(differenceAttrs);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return differenceAttr;
    }
    //比较返回的类
    public class DifferenceAttr{
        private List<String> differenceAttrs;
        private List<DifferenceAttr> childrenDifference;

        public List<String> getDifferenceAttrs() {
            return differenceAttrs;
        }
        public void setDifferenceAttrs(List<String> differenceAttrs) {
            this.differenceAttrs = differenceAttrs;
        }
        public List<DifferenceAttr> getChildrenDifference() {
            return childrenDifference;
        }
        public void setChildrenDifference(List<DifferenceAttr> childrenDifference) {
            this.childrenDifference = childrenDifference;
        }
    }
    public DifferenceAttr getDifferenceAttr() {
        return differenceAttr;
    }
    public void setDifferenceAttr(DifferenceAttr differenceAttr) {
        this.differenceAttr = differenceAttr;
    }

    public static void main(String[] args) {
        ClassAttrValCompare attrCompare = new ClassAttrValCompare();

        ObjectTest object1 = new ObjectTest();
        object1.setMessage("这是个测试信息1");
        object1.setReturnFlag("Y");
        object1.setVisible("Y");
        object1.setNumber(1);

//        List<ChildObject> childList1 = new ArrayList<ChildObject>();
//        ChildObject child1_1 = new ChildObject();
//        child1_1.setAddress("测试地址1_1");
//        child1_1.setChildName("测试姓名");
//        child1_1.setChildValue("测试值1_1");
//
//        ChildObject child1_2 = new ChildObject();
//        child1_2.setAddress("测试地址1_2");
//        child1_2.setChildName("测试姓名");
//        child1_2.setChildValue("测试值1_2");
//
//        childList1.add(child1_1);
//        childList1.add(child1_2);
//        object1.setList(childList1);

        ObjectTest object2 = new ObjectTest();
        object2.setMessage("这是个测试信息2");
        object2.setReturnFlag("Y");
        object2.setVisible("N");
        object2.setNumber(2);

//        List<ChildObject> childList2 = new ArrayList<ChildObject>();
//        ChildObject child2_1 = new ChildObject();
//        child2_1.setAddress("测试地址2_1");
//        child2_1.setChildName("测试姓名");
//        child2_1.setChildValue("测试值2_1");
//
//        ChildObject child2_2 = new ChildObject();
//        child2_2.setAddress("测试地址2_2");
//        child2_2.setChildName("测试姓名");
//        child2_2.setChildValue("测试值2_2");
//
//        childList2.add(child2_1);
//        childList2.add(child2_2);
//        object2.setList(childList2);

        DifferenceAttr attr = attrCompare.compareValue(object1, object2);

        attrCompare.printTrace(attr);

    }

    public void printTrace(DifferenceAttr attr) {
        System.out.println("*******打印开始********");
        for(String str:attr.getDifferenceAttrs()) {
            System.out.println(str);
        }
        if(attr.getChildrenDifference()!=null) {
            System.out.println("********打印子节点开始*********");
            for(DifferenceAttr tempAttr:attr.getChildrenDifference()) {
                printTrace(tempAttr);
            }
        }
    }
}
