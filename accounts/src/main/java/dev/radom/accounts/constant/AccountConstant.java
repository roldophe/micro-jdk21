/**
 * Constants related to account entity.
 *
 * @author radom
 */
package dev.radom.accounts.constant;

public final class AccountConstant {
    
    /**
     * Private constructor to prevent instantiation of the class.
     */
    private AccountConstant() {
        // restrict instantiation
    }
    
    /**
     * Savings account type.
     */
    public static final String SAVINGS = "Savings";
    /**
     * Default address for accounts.
     */
    public static final String ADDRESS = "123 Main St Chicago, IL 60601";
    /**
     * Status code for successfully creating an account.
     */
    public static final String STATUS_201 = "201";
    /**
     * Message for successfully creating an account.
     */
    public static final String MESSAGE_201 = "Account created successfully";
    /**
     * Status code for successfully fetching account details.
     */
    public static final String STATUS_200 = "200";
    /**
     * Message for successfully fetching account details.
     */
    public static final String MESSAGE_200 = "Account details fetched successfully";

    /**
     * Status code for an operation failure.
     */
    public static final String STATUS_417 = "417";
    /**
     * Message for an operation failure.
     */
    public static final String MESSAGE_417_UPDATE = "Update Operation failed. Please try again or contact Dev Team";

    /**
     * Message for an operation failure.
     */
    public static final String MESSAGE_417_DELETE = "Delete Operation failed. Please try again or contact Dev Team";


    /**
     * Status code for an internal server error.
     */
    public static final String STATUS_500 = "500";
    /**
     * Message for an internal server error.
     */
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev Team";
    
    
}