package com.ssafy.triptogether.auth.validator.pin;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

import com.ssafy.triptogether.auth.data.request.PinVerifyRequest;
import com.ssafy.triptogether.global.exception.exceptions.category.UnAuthorizedException;
import com.ssafy.triptogether.global.exception.response.ErrorCode;
import com.ssafy.triptogether.member.domain.Member;
import com.ssafy.triptogether.member.repository.MemberRepository;
import com.ssafy.triptogether.member.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Aspect
public class PinVerifyAspect {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Before("@annotation(PinVerify)")
    public void pinVerifyAdvice(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Long memberId = (Long) args[0];
        PinVerifyRequest pinVerifyRequest = (PinVerifyRequest) args[1];

        Member member = MemberUtils.findByMemberId(memberRepository, memberId);
        // Todo: inputPin 을 암호화한 후 member 의 PIN 과 비교
        if (!passwordEncoder.matches(pinVerifyRequest.pinNum(), member.getPinNum())) {
            throw new UnAuthorizedException("PinVerify", ErrorCode.PIN_NOT_AUTHENTICATED);
        }
    }
}
