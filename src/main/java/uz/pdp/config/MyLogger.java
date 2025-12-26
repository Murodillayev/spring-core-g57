package uz.pdp.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
@Aspect
public class MyLogger {

    @Before(value = "execution(* uz.pdp.config.Calculator.*(*,*))")
    public void writeLog(JoinPoint joinPoint) {
        String operationName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println(new Date() + " | " + operationName + " " + Arrays.toString(args));
    }

    @AfterReturning(value = "execution(* uz.pdp.config.Calculator.*(*,*))")
    public void writeLogAfterReturning(JoinPoint joinPoint) {
        String operationName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println(new Date() + " | Success calculated  " + operationName + Arrays.toString(args));
    }

    @After(value = "execution(* uz.pdp.config.Calculator.*(*,*))")
    public void writeLogAfter(JoinPoint joinPoint) {
        System.out.println("Error bolsa ham bolmasa ham chiqadi");
    }

    @AfterThrowing(value = "execution(* uz.pdp.config.Calculator.*(*,*))")
    public void writeLogHappenError(JoinPoint joinPoint) {
        System.out.println(new Date() + " | Error calculated  " + joinPoint.getSignature().getName());
    }

    @Around(value = "execution(Double uz.pdp.config.Calculator.subtract(*,*))")
    public Double writeLogAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Metod ishlashidan avval ");
        Double originalMethodReturnValue = (Double)joinPoint.proceed();
        System.out.println("Metod ishlab bolganidan keyin ");

        return originalMethodReturnValue * 2;
    }

}
