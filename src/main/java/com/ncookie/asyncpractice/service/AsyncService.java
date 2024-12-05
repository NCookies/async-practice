package com.ncookie.asyncpractice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final EmailService emailService;

    // 정상 동작 O
    public void asyncCall_1() {
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
        
        // 의도한대로 각각 별도의 스레드에서 실행됨
        /*
        [asyncCall_1] :: http-nio-8080-exec-1
        [sendMail] :: defaultTaskExecutor-1
        [sendMailWithCustomThreadPool] :: messagingTaskExecutor-1
         */
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    // 정상 동작 X
    public void asyncCall_2() {
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());

        // 인스턴스를 직접 생성
        // 동일한 스레드가 작업을 처리함
        /*
        [asyncCall_2] :: http-nio-8080-exec-2
        [sendMail] :: http-nio-8080-exec-2
        [sendMailWithCustomThreadPool] :: http-nio-8080-exec-2
         */
        EmailService emailService = new EmailService();
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    // 정상 동작 X
    public void asyncCall_3() {
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());

        // 내부 메소드를 Async로 선언하고 동작
        // 동일한 스레드가 작업을 처리함
        /*
        [asyncCall_3] :: http-nio-8080-exec-3
        [sendMail] :: http-nio-8080-exec-3
         */
        sendMail();
    }

    // 이 메소드를 내부에서 사용하면 실제로는 @Async 어노테이션을 적용시키지 않은 것과 같다.
    @Async
    public void sendMail() {
        System.out.println("[sendMail] :: " + Thread.currentThread().getName());
    }
}
