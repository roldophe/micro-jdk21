package dev.radom.loan.service.impl;

import dev.radom.loan.constant.LoanConstant;
import dev.radom.loan.dto.LoanDto;
import dev.radom.loan.entity.Loan;
import dev.radom.loan.exception.LoanAlreadyExistsException;
import dev.radom.loan.exception.ResourceNotFoundException;
import dev.radom.loan.mapper.LoanMapper;
import dev.radom.loan.repository.LoansRepository;
import dev.radom.loan.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * Implementation of the LoansService interface providing
 * the business logic for managing loans.
 */
@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoansRepository loansRepository;

    /**
     * Creates a new loan for the given mobile number.
     * 
     * @param mobileNumber the mobile number of the customer
     * @throws LoanAlreadyExistsException if a loan already exists for the given mobile number
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoans= loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber " + mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    /**
     * Fetches the loan details for the given mobile number.
     * 
     * @param mobileNumber the mobile number of the customer
     * @return the loan details as a LoanDto
     * @throws ResourceNotFoundException if no loan is found for the given mobile number
     */
    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoanMapper.mapToLoanDto(loan, new LoanDto());
    }

    /**
     * Updates the loan details based on the provided LoanDto.
     * 
     * @param loanDto the loan details to update
     * @return true if the update was successful
     * @throws ResourceNotFoundException if no loan is found for the given loan number
     */
    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loan loan = loansRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loanDto.getLoanNumber()));
        LoanMapper.mapToLoan(loanDto, loan);
        loansRepository.save(loan);
        return true;
    }

    /**
     * Deletes the loan details for the given mobile number.
     * 
     * @param mobileNumber the mobile number of the customer
     * @return true if the delete was successful
     * @throws ResourceNotFoundException if no loan is found for the given mobile number
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.deleteById(loan.getLoanId());
        return true;
    }

    /**
     * Creates a new Loan object with default values for a given mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     * @return a new Loan object
     */
    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstant.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstant.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstant.NEW_LOAN_LIMIT);
        return newLoan;
    }
}