package dev.radom.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)
@Data
public class AccountDto {

    @Schema(
            description = "Account number of the customer"
    )
    @NotEmpty(message = "Account number should not be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Account number should be 10 digits")
    private String accountNumber;

    @Schema(
            description = "Account type of the customer",
            example = "Savings"
    )
    @NotEmpty(message = "Account type should not be empty")
    private String accountType;

    @Schema(
            description = "Branch address of the customer"
    )
    @NotEmpty(message = "Branch address should not be empty")
    private String branchAddress;
}
