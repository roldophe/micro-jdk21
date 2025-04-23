package dev.radom.accounts.mapper;

import dev.radom.accounts.dto.AccountDto;
import dev.radom.accounts.entity.Account;

public interface AccountMapper {

    static AccountDto mapToAccountDto(Account account, AccountDto accountDto) {

        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranchAddress(account.getBranchAddress());
        return accountDto;
    }

    static Account mapToAccount(AccountDto accountDto, Account account) {

        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBranchAddress(accountDto.getBranchAddress());
        return account;
    }
}
