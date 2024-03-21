package com.ssafy.triptogether.syncaccount.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.triptogether.auth.data.request.PinVerifyRequest;
import com.ssafy.triptogether.auth.utils.AuthUtils;
import com.ssafy.triptogether.auth.validator.pin.PinVerify;
import com.ssafy.triptogether.global.exception.exceptions.category.BadRequestException;
import com.ssafy.triptogether.global.exception.exceptions.category.NotFoundException;
import com.ssafy.triptogether.global.exception.response.ErrorCode;
import com.ssafy.triptogether.syncaccount.data.request.MainSyncAccountUpdateRequest;
import com.ssafy.triptogether.syncaccount.data.response.SyncAccountsDetail;
import com.ssafy.triptogether.syncaccount.data.response.SyncAccountsLoadResponse;
import com.ssafy.triptogether.syncaccount.domain.SyncAccount;
import com.ssafy.triptogether.syncaccount.repository.SyncAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SyncAccountServiceImpl implements SyncAccountLoadService, SyncAccountSaveService {
	// Repository
	private final SyncAccountRepository syncAccountRepository;

	/**
	 * 사용자의 연동 계좌 목록 조회
	 * @param memberId 요청자의 member_id
	 * @return 연동 계좌 목록
	 */
	@Override
	public SyncAccountsLoadResponse syncAccountsLoad(Long memberId) {
		List<SyncAccountsDetail> syncAccounts = syncAccountRepository.memberSyncAccountsLoad(memberId);
		return SyncAccountsLoadResponse.builder()
			.syncAccountsDetail(syncAccounts)
			.build();
	}

	/**
	 * 사용자의 연동 계좌의 주계좌 설정 변경
	 * @param memberId 요청자의 member_id
	 * @param pinVerifyRequest PIN 인증 요청
	 * @param mainSyncAccountUpdateRequest 주계좌 변경 요청
	 */
	@PinVerify
	@Transactional
	@Override
	public void mainSyncAccountUpdate(Long memberId, PinVerifyRequest pinVerifyRequest, MainSyncAccountUpdateRequest mainSyncAccountUpdateRequest) {
		deactivateCurrentMainSyncAccount(memberId);
		activateNewMainSyncAccount(mainSyncAccountUpdateRequest);
	}

	private void deactivateCurrentMainSyncAccount(Long memberId) {
		SyncAccount syncAccount = syncAccountRepository.findByMemberIdAndIsMain(memberId, true)
			.orElseThrow(
				() -> new NotFoundException("MainSyncAccountUpdate", ErrorCode.SYNC_ACCOUNTS_NOT_FOUND, memberId)
			);
		syncAccount.updateIsMain(false);
	}

	private void activateNewMainSyncAccount(MainSyncAccountUpdateRequest mainSyncAccountUpdateRequest) {
		SyncAccount requestSyncAccount = syncAccountRepository.findByUuid(mainSyncAccountUpdateRequest.uuid())
			.orElseThrow(
				() -> new BadRequestException("MainSyncAccountUpdate", ErrorCode.SYNC_ACCOUNT_BAD_REQUEST,
					mainSyncAccountUpdateRequest.uuid())
			);
		requestSyncAccount.updateIsMain(true);
	}
}
