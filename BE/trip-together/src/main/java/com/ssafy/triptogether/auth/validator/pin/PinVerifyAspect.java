package com.ssafy.triptogether.auth.validator.pin;

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
@Slf4j
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
        String encodedPinNum = passwordEncoder.encode(pinVerifyRequest.pinNum());
        log.info("origin: {}", member.getPinNum());
        log.info("check: {}", encodedPinNum);
        log.info("result: {}", member.getPinNum().equals(encodedPinNum));
        log.info("result: {}", passwordEncoder.matches(pinVerifyRequest.pinNum(), member.getPinNum()));
        // Todo: 일치하지 않는다면 예외 처리
        if (!passwordEncoder.matches(pinVerifyRequest.pinNum(), member.getPinNum())) {
            throw new UnAuthorizedException("PinVerify", ErrorCode.PIN_NOT_AUTHENTICATED);
        }
    }
}
