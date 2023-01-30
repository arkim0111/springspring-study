package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // aop
//@Component
public class TimeTraceApp {

    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")

    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();// ms 단위로 진행시간 측정
        System.out.println("START : " + joinPoint.toString() );
        try {
           /* Object result = joinPoint.proceed();// 다음 메소드로 진행
            return result;*/
            return joinPoint.proceed(); // inline : control + T
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms" );
        }

        
    }
}
