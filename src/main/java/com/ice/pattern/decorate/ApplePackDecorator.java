package com.ice.pattern.decorate;

import com.ice.pattern.factory.pack.ApplePack;

/**
 * 装饰器角色，持有真实对象的引用
 * @author: ice
 * @create: 2019/3/20
 **/
public abstract class ApplePackDecorator extends ApplePack  {

    protected ApplePack applePack;
}
