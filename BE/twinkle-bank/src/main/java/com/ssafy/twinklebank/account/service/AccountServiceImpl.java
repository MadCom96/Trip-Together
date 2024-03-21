package com.ssafy.twinklebank.account.service;

import static com.ssafy.twinklebank.global.exception.response.ErrorCode.*;

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
import com.ssafy.twinklebank.global.exception.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountLoadService, AccountSaveService {

    private final AccountRepository accountRepository;
    private final WithdrawalAgreementRepository withdrawalAgreementRepository;
    private final ApplicationRepository applicationRepository;
    // private final ApplicationUtils applicationUtils;
    @Override
    public List<AccountResponse> getAccounts(long clientId, long memberId) {
        return accountRepository.getAccountList(clientId, memberId);
    }

    @Override
    public void addLinkedAccount(long clientId, AddAccountRequest addAccountRequest) {
        // Account Not Found
        Account account = accountRepository.findAccountByUuid(addAccountRequest.accountUUID())
            .orElseThrow(() -> new NotFoundException("addLinkedAccount: account", ACCOUNT_NOT_FOUND, addAccountRequest.accountUUID()));
        // Application Not Found
        Application application = applicationRepository.findById(clientId)
            .orElseThrow(() -> new NotFoundException("addLinkedAccount: application", APPLICATION_NOT_FOUND, clientId));

        WithdrawalAgreement withdrawalAgreement = new WithdrawalAgreement(account, application);
        withdrawalAgreementRepository.save(withdrawalAgreement);
    }
}
