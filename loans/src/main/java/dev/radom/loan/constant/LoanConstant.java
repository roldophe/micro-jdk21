/**
 * Constants related to Loan entity.
 *
 * @author radom
 */
package dev.radom.loan.constant;
public final class LoanConstant {

    private LoanConstant() {
        // restrict instantiation
    }

    public static final String HOME_LOAN = "Home Loan";
    public static final int NEW_LOAN_LIMIT = 1_00_000;

    // HTTP status codes
    public static final String STATUS_201 = "201";
    public static final String STATUS_200 = "200";
    public static final String STATUS_417 = "417";

    // HTTP status messages
    public static final String MESSAGE_201 = "Loan created successfully";
    public static final String MESSAGE_200 = "Request processed successfully";
    public static final String MESSAGE_417_UPDATE = "Update operation failed. Please try again or contact Dev team";
    public static final String MESSAGE_417_DELETE = "Delete operation failed. Please try again or contact Dev team";

}