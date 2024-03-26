package com.ssafy.triptogether.member.controller;

import com.ssafy.triptogether.auth.data.request.PinVerifyRequest;
import com.ssafy.triptogether.auth.provider.CookieProvider;
import com.ssafy.triptogether.auth.utils.SecurityMember;
import com.ssafy.triptogether.auth.utils.SecurityUtil;
import com.ssafy.triptogether.global.data.response.ApiResponse;
import com.ssafy.triptogether.global.exception.exceptions.category.NotFoundException;
import com.ssafy.triptogether.member.data.PinSaveRequest;
import com.ssafy.triptogether.member.data.PinUpdateRequest;
import com.ssafy.triptogether.member.data.ProfileFindResponse;
import com.ssafy.triptogether.member.data.ProfileUpdateRequest;
import com.ssafy.triptogether.member.data.ReissueResponse;
import com.ssafy.triptogether.member.service.MemberLoadService;
import com.ssafy.triptogether.member.service.MemberSaveService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.triptogether.global.data.response.StatusCode.*;
import static com.ssafy.triptogether.global.exception.response.ErrorCode.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/member/v1/members")
@RestController
public class MemberController {

	private final MemberSaveService memberSaveService;
	private final MemberLoadService memberLoadService;
	private final CookieProvider cookieProvider;

    @PatchMapping
    public ResponseEntity<ApiResponse<Void>> updateProfile(
            @AuthenticationPrincipal SecurityMember securityMember,
            @Valid @RequestBody ProfileUpdateRequest profileUpdateRequest
    ) {
        long memberId = securityMember.getId();
        memberSaveService.updateProfile(memberId, profileUpdateRequest);
        return ApiResponse.emptyResponse(OK, SUCCESS_PROFILE_UPDATE);
    }

    @GetMapping("/{member_id}")
    public ResponseEntity<ApiResponse<ProfileFindResponse>> findProfile(
            @PathVariable("member_id") long memberId
    ) {
        ProfileFindResponse response = memberLoadService.findProfile(memberId);
        return ApiResponse.toResponseEntity(OK, SUCCESS_PROFILE_FIND, response);
    }

    @PostMapping("/pin")
    public ResponseEntity<ApiResponse<Void>> savePin(
            @AuthenticationPrincipal SecurityMember securityMember,
            @Valid @RequestBody PinSaveRequest pinSaveRequest
    ) {
        long memberId = securityMember.getId();
        memberSaveService.savePin(memberId, pinSaveRequest);
        return ApiResponse.emptyResponse(CREATED, SUCCESS_PIN_SAVE);
    }

    @PatchMapping("/pin")
    public ResponseEntity<ApiResponse<Void>> updatePin(
            @AuthenticationPrincipal SecurityMember securityMember,
            @Valid @RequestBody PinUpdateRequest pinUpdateRequest
    ) {
        long memberId = securityMember.getId();
        PinVerifyRequest pinVerifyRequest = PinVerifyRequest.builder().pinNum(pinUpdateRequest.prePinNum()).build();
        memberSaveService.updatePin(memberId, pinVerifyRequest, pinUpdateRequest);
        return ApiResponse.emptyResponse(OK, SUCCESS_PIN_UPDATE);
    }

	@PostMapping("/logout")
	public ResponseEntity<ApiResponse<Void>> logout(
		@AuthenticationPrincipal SecurityMember securityMember,
		HttpServletRequest request
	) {
		String accessToken = SecurityUtil.getAccessToken(request);
		memberSaveService.logout(securityMember, accessToken);
		return ApiResponse.emptyResponse(OK, SUCCESS_LOGOUT);
	}

	@GetMapping("/reissue")
	public ResponseEntity<ApiResponse<ReissueResponse>> reissue(HttpServletRequest request) {
		// 쿠키에서 refresh token 꺼내기
		Cookie cookie = cookieProvider.getCookie(request, "refreshToken").orElseThrow(
			() -> new NotFoundException("MemberController", COOKIE_NOT_FOUND));
		String refreshToken = cookie.getValue();

		return memberLoadService.reissue(refreshToken);
	}

}
