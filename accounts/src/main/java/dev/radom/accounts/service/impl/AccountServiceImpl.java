package dev.radom.accounts.service.impl;

import dev.radom.accounts.constant.AccountConstant;
import dev.radom.accounts.dto.AccountDto;
import dev.radom.accounts.dto.CustomerDto;
import dev.radom.accounts.entity.Account;
import dev.radom.accounts.entity.Customer;
import dev.radom.accounts.exception.CustomerAlreadyExistsException;
import dev.radom.accounts.exception.ResourceNotFoundException;
import dev.radom.accounts.mapper.AccountMapper;
import dev.radom.accounts.mapper.CustomerMapper;
import dev.radom.accounts.repository.AccountRepository;
import dev.radom.accounts.repository.CustomerRepository;
import dev.radom.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    /**
     * Creates a new account for the given customer.
     *
     * @param customerDto the customer data transfer object containing customer details
     */
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());

        if (existingCustomer.isPresent()) {

            throw new CustomerAlreadyExistsException("Customer with mobile number " + customer.getMobileNumber() + " already exists");
        }


        customer.setCreated(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));

    }

    /**
     * Creates a new account.
     *
     * @param customer the customer for whom the account is to be created
     * @return the newly created account
     */
    private Account createNewAccount(Customer customer) {

        Account newAccount = new Account();

        String accountNumber = String.valueOf(System.currentTimeMillis());

        newAccount.setAccountNumber(accountNumber);
        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountType(AccountConstant.SAVINGS);
        newAccount.setBranchAddress(AccountConstant.ADDRESS);
        return newAccount;

    }

    /**
     * Retrieves the account details for the given mobile number.
     *
     * @param mobileNumber the mobile number of the customer whose details are to be fetched
     * @return the customer details
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customer id", customer.getCustomerId()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());

        AccountDto accountDto = AccountMapper.mapToAccountDto(account, new AccountDto());
        customerDto.setAccountDto(accountDto);
        return customerDto;

    }

    /**
     * Updates the account details for the given customer.
     *
     * @param customerDto the customer information used to update the account
     * @return true if the account details were updated successfully, false otherwise
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;

        if (customerDto != null) {

            Account account = accountRepository.findByAccountNumber(customerDto.getAccountDto().getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "account number", customerDto.getAccountDto().getAccountNumber()));

            AccountMapper.mapToAccount(customerDto.getAccountDto(), account);

            account.setUpdated(LocalDateTime.now());
            account.setUpdatedBy("Anonymous");
            account = accountRepository.save(account);

            Integer customerId = account.getCustomerId();

            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "account number", customerDto.getAccountDto().getAccountNumber()));

            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);

            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customer id", customer.getCustomerId()));

        customerRepository.deleteByCustomerId(customer.getCustomerId());
        accountRepository.deleteById(account.getId());

        return true;
    }

}