package com.ice.spring.config.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * aop，这里定义一个切面，进行方法执行的增强
 * @ClassName LogAspect
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/4 11:22 AM
 **/
@Aspect
public class LogAspect {

//    @Around("execution(public int com.ice.spring.config.aop.config.MyCalculator.calculate(int,int)))")
//    public Object doAround(ProceedingJoinPoint proceedingJoinPoint){
//        System.out.println("环绕通知，执行方法之前");
//        Object result = null;
//        try {
//            result = proceedingJoinPoint.proceed();
//        } catch (Throwable throwable) {
//            System.out.println("环绕通知，捕获异常后");
//            throwable.printStackTrace();
//        }
//        System.out.println("环绕通知，执行方法之后");
//        return result;
//    }
    /**
     * 前置通知：这里可以通过表达式指定拦截的具体规则
     * 表达式中可以进行模糊匹配
     * public int com.ice.spring.config.aop.config.MyCalculator.*(..)
     */
    @Before("execution(public int com.ice.spring.config.aop.config.MyCalculator.calculate(int,int))")
    public void doBefore(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName());
        System.out.println("这里进行方法执行前的日志打印");
    }

    @After("execution(public int com.ice.spring.config.aop.config.MyCalculator.calculate(int,int))")
    public void doAfter(){
        System.out.println("这里进行方法执行后的日志打印");
    }

    @AfterReturning(value = "execution(public int com.ice.spring.config.aop.config.MyCalculator.calculate(int,int))",returning = "result")
    public void doAfterReturning(Object result){
        System.out.println("这里进行方法执行返回的日志打印,result:"+result);
    }

    @AfterThrowing(value = "execution(public int com.ice.spring.config.aop.config.MyCalculator.calculate(int,int))",throwing = "exception")
    public void doAfterThrowing(Exception exception){
        System.out.println("这里进行方法执行异常的日志打印"+exception.getMessage());
    }

    @Around("execution(public int com.ice.spring.config.aop.config.MyCalculator.calculate(int,int)))")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("环绕通知，执行方法之前");
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println("环绕通知，捕获异常后");
            throwable.printStackTrace();
        }
        System.out.println("环绕通知，执行方法之后");
        return result;
    }

    /**
     * 定义切点，多个通知指定拦截同一方法时，可以抽出来定义一个切点（解耦）,在@Before("pointcut()")指定即可
     */
    @Pointcut("execution(public int com.ice.spring.config.aop.config.MyCalculator.calculate(int,int))")
    public void pointcut(){
    }
}
