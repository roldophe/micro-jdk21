package dev.radom.accounts.service;

import dev.radom.accounts.dto.CustomerDto;

public interface AccountService {



    /**
     * Creates a new account for the given customer.
     *
     * @param customerDto the customer information used to create the account
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Retrieves the account details for the given mobile number.
     *
     * @param mobileNumber the mobile number of the customer whose details are to be fetched
     * @return the customer details
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * Updates the account details for the given customer.
     *
     * @param customerDto the customer information used to update the account
     * @return true if the account details were updated successfully, false otherwise
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * Deletes the account for the given mobile number.
     *
     * @param mobileNumber the mobile number of the customer whose account is to be deleted
     * @return true if the account was deleted successfully, false otherwise
     */
    boolean deleteAccount(String mobileNumber);
}
