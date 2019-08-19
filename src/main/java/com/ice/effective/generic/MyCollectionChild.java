package com.ice.effective.generic;

import java.util.Date;

/**
 * 使用javap -c 查看编译后文件的信息
 * @ClassName MyCollectionChild
 * @Description TODO
 * @Author liubin
 * @Date 2019/4/15 2:59 PM
 **/
public class MyCollectionChild extends MyCollection<Date> {
    public static void main(String[] args) {
        MyCollectionChild myCollectionChild = new MyCollectionChild();
        myCollectionChild.setValue(new Date());
//        无法正常调用,证明确实是重写，不是重载（则可以调用重载的方法，和父类的setVaule(objcet）方法）
//        myCollectionChild.setValue(new Object());
    }

    /**
     * 之类父类方法擦除泛型后为setValue(Object o),子类为setValue（Date date）
     * 分析：这里会先调用编译自动生成的setValue（Object）方法（桥接方法），setValue（Object）里会调用我们重写的setValue（Date）的方法。
     * @param date
     */
    @Override
    public void setValue(Date date) {
        super.setValue(date);
    }

    @Override
    public Date getValue() {
        return super.getValue();
    }
}
class MyCollection<T> {
    private T t;
    public void setValue(T t){
        this.t = t;
    }

    public T getValue(){
        return t;
    }
}
