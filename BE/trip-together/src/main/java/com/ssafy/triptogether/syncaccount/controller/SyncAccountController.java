package com.ssafy.triptogether.syncaccount.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.triptogether.auth.data.request.PinVerifyRequest;
import com.ssafy.triptogether.global.data.response.ApiResponse;
import com.ssafy.triptogether.global.data.response.StatusCode;
import com.ssafy.triptogether.syncaccount.data.request.MainSyncAccountUpdateRequest;
import com.ssafy.triptogether.syncaccount.data.request.SyncAccountDeleteRequest;
import com.ssafy.triptogether.syncaccount.data.request.SyncAccountSaveRequest;
import com.ssafy.triptogether.syncaccount.data.response.BankAccountsLoadResponse;
import com.ssafy.triptogether.syncaccount.data.response.SyncAccountsLoadResponse;
import com.ssafy.triptogether.syncaccount.service.SyncAccountLoadService;
import com.ssafy.triptogether.syncaccount.service.SyncAccountSaveService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/account/v1/sync-account")
@RequiredArgsConstructor
public class SyncAccountController {
	// Service
	private final SyncAccountLoadService syncAccountLoadService;
	private final SyncAccountSaveService syncAccountSaveService;

	@GetMapping("/sync-accounts")
	public ResponseEntity<ApiResponse<SyncAccountsLoadResponse>> syncAccountsLoad() {
		SyncAccountsLoadResponse syncAccountsLoadResponse = syncAccountLoadService.syncAccountsLoad(1L);

		return ApiResponse.toResponseEntity(
			HttpStatus.OK, StatusCode.SUCCESS_SYNC_ACCOUNTS_LOAD, syncAccountsLoadResponse
		);
	}

	@PostMapping("/sync-accounts")
	public ResponseEntity<ApiResponse<Void>> syncAccountSave(
		@RequestBody @Valid SyncAccountSaveRequest syncAccountSaveRequest
	) {
		PinVerifyRequest pinVerifyRequest = PinVerifyRequest.builder()
			.pinNum(syncAccountSaveRequest.pinNum())
			.build();
		syncAccountSaveService.syncAccountSave(1L, pinVerifyRequest, syncAccountSaveRequest);

		return ApiResponse.emptyResponse(
			HttpStatus.CREATED, StatusCode.SUCCESS_SYNC_ACCOUNTS_SAVE
		);
	}

	@PatchMapping("/sync-accounts")
	public ResponseEntity<ApiResponse<Void>> mainSyncAccountUpdate(
		@RequestBody @Valid MainSyncAccountUpdateRequest mainSyncAccountUpdateRequest
	) {
		syncAccountSaveService.mainSyncAccountUpdate(1L, mainSyncAccountUpdateRequest);

		return ApiResponse.emptyResponse(
			HttpStatus.OK, StatusCode.SUCCESS_MAIN_SYNC_ACCOUNT_UPDATE
		);
	}

	@DeleteMapping
	public ResponseEntity<ApiResponse<Void>> syncAccountDelete(
		@RequestBody @Valid SyncAccountDeleteRequest syncAccountDeleteRequest
	) {
		PinVerifyRequest pinVerifyRequest = PinVerifyRequest.builder()
			.pinNum(syncAccountDeleteRequest.pinNum())
			.build();
		syncAccountSaveService.syncAccountDelete(1L, pinVerifyRequest, syncAccountDeleteRequest);

		return ApiResponse.emptyResponse(
			HttpStatus.NO_CONTENT, StatusCode.SUCCESS_SYNC_ACCOUNT_DELETE
		);
	}

	@GetMapping("/bank-accounts")
	public ResponseEntity<ApiResponse<BankAccountsLoadResponse>> bankAccountsLoad() {
		BankAccountsLoadResponse bankAccountsLoadResponse = syncAccountLoadService.bankAccountsLoad(1L);

		return ApiResponse.toResponseEntity(
			HttpStatus.OK, StatusCode.SUCCESS_BANK_ACCOUNTS_LOAD, bankAccountsLoadResponse
		);
	}
}
