package com.ssafy.twinklebank.account.service;

import com.ssafy.twinklebank.account.data.AccountDeleteRequest;
import com.ssafy.twinklebank.account.data.AccountResponse;
import com.ssafy.twinklebank.account.data.AddAccountRequest;
import com.ssafy.twinklebank.account.domain.Account;
import com.ssafy.twinklebank.account.domain.WithdrawalAgreement;
import com.ssafy.twinklebank.account.repository.AccountRepository;
import com.ssafy.twinklebank.account.repository.WithdrawalAgreementRepository;
import com.ssafy.twinklebank.application.domain.Application;
import com.ssafy.twinklebank.application.repository.ApplicationRepository;
import com.ssafy.twinklebank.application.utils.ApplicationUtils;
import com.ssafy.twinklebank.global.exception.exceptions.category.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ssafy.twinklebank.global.exception.response.ErrorCode.ACCOUNT_NOT_FOUND;
import static com.ssafy.twinklebank.global.exception.response.ErrorCode.UNDEFINED_WITHDRAWAL_AGREEMENT;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountLoadService, AccountSaveService {

    private final AccountRepository accountRepository;
    private final WithdrawalAgreementRepository withdrawalAgreementRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    public List<AccountResponse> getAccounts(String clientId, long memberId) {
        return accountRepository.getAccountList(clientId, memberId);
    }

    @Transactional
    @Override
    public void addLinkedAccount(String clientId, AddAccountRequest addAccountRequest) {
        // Account Not Found
        Account account = finAccountByUuid(addAccountRequest.accountUuid());

        Application application = ApplicationUtils.getApplication(applicationRepository, clientId);

        WithdrawalAgreement withdrawalAgreement = WithdrawalAgreement.builder()
            .account(account)
            .application(application)
            .build();

        withdrawalAgreementRepository.save(withdrawalAgreement);
    }

    @Transactional
    @Override
    public void deleteLinkedAccount(String clientId, long memberId, AccountDeleteRequest accountDeleteRequest) {
        // find account & application & withdrawal agreement
        Account account = finAccountByUuid(accountDeleteRequest.accountUuid());
        Application application = ApplicationUtils.getApplication(applicationRepository, clientId);
        WithdrawalAgreement withdrawalAgreement = withdrawalAgreementRepository.findByAccountAAndApplication(account, application)
                .orElseThrow(() -> new NotFoundException("LinkedAccountDelete", UNDEFINED_WITHDRAWAL_AGREEMENT));

        // delete withdrawal agreement
        withdrawalAgreementRepository.delete(withdrawalAgreement);
    }

    private Account finAccountByUuid(String accountUuid) {
        return accountRepository.findAccountByUuid(accountUuid)
                .orElseThrow(() -> new NotFoundException("addLinkedAccount: account", ACCOUNT_NOT_FOUND, accountUuid));
    }
}
