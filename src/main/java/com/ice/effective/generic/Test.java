package com.ice.effective.generic;/**
 * @author: ice
 * @create: 2019/4/11
 **/

import java.util.*;

/**
 *@ClassName Test
 *@Description TODO
 *@Author liubin
 *@Date 2019/4/11 10:38 AM
 **/
public class Test {
    public static void main(String[] args) {
        //目前已经可以完成根据语言所做的泛型推导与泛型方法所做的相同。
//        Map<String,Object> map = new HashMap<>(16);
    }

    /**
     * 测试泛型利用限制通配符提升灵活性
     */
    public static void test1(){
        List<Integer> intList = new ArrayList<>();
        MyStack<Number> stack = new MyStack<>();
        stack.pushAllElements(intList.iterator());
        Collection<Object> collection = new ArrayList<>();
        stack.popAll(collection);


    }

    /**
     * 测试通配符下类型推导
     */
    public static void test2(){
        ArrayList<Double> strings = new ArrayList<>();
        LinkedList<Integer> integers = new LinkedList<>();
        List<Number> union = union(strings, integers);
    }

    //泛型方法，合并同种类型的list集合
    public static <T> List<T> union(List<? extends T> list1,List<? extends T> list2){
        String a = "";
        List<T> list = new ArrayList<T>(list1);
        list.addAll(list2);
        return list;
    }

    /**
     * 获取集合中最大的元素
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> T max(List<? extends T> list){
        T result = null;
        for(Iterator<? extends T> iterator=list.iterator();iterator.hasNext();){
            T next = iterator.next();
            if(result==null){
                result = next;
                continue;
            }else{
                if(result.compareTo(next)>0){
                    result = next;
                }
            }
        }
        return result;
    }

    /**
     *  若方法声明中类型参数只出现一次，考虑使用通配符来代替
     * @param list
     * @param i
     * @param j
     */
    public static void swap(List<?> list,int i,int j){
        //对通配符类型的list操作是不允许的，出了插入null之外
        //解决办法：编写一个辅助方法，来捕捉通配符类型
//        list.set(i,list.set(j,list.get(i)));
        swapHelper(list,i,j);
    }

    /**
     * swap方法的帮助类，为了捕捉类型，辅助方法必须是泛型方法。
     * @param list
     * @param i
     * @param j
     * @param <E>
     */
    public static <E> void swapHelper(List<E> list,int i,int j){
        list.set(i,list.set(j,list.get(i)));
    }
}
