package com.ice.effective.generic;/**
 * @author: ice
 * @create: 2019/4/11
 **/

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

/**
 *@ClassName MyStack
 *@Description TODO
 *@Author liubin
 *@Date 2019/4/11 3:55 PM
 **/
public class MyStack<E> extends Stack<E> {

    /**
     * 通过通配符增强方法的灵活性，<？extends E>可以使方法接收所有E类型的子类型（即设定上边界）
     * @param iterator
     */
    public void pushAllElements(Iterator<? extends E> iterator){
        for(;iterator.hasNext();) {
            push(iterator.next());
        }
    }

    /**
     * 通过通配符增强方法的灵活性，<？super E>可以使方法接收所有E类型的父类型（即设定下边界）
     * @param collection
     */
    public void popAll(Collection<? super E> collection){
        while (!isEmpty()){
            collection.add(pop());
        }
    }
}
