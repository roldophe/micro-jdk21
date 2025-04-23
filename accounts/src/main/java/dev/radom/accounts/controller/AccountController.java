package dev.radom.accounts.controller;

import dev.radom.accounts.constant.AccountConstant;
import dev.radom.accounts.dto.CustomerDto;
import dev.radom.accounts.dto.ErrorResponseDto;
import dev.radom.accounts.dto.ResponseDto;
import dev.radom.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Tag(
    name = "CRUD REST APIs for Account in Banking System",
    description = "CRUD REST APIs for Account in Banking System to CREATE, FETCH, UPDATE & DELETE account details"
)
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "Create account REST API",
            description = "REST API to create a new account for a customer",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "HTTP Status Created",
                            content = @Content(schema = @Schema(implementation = ResponseDto.class))
                    )
            }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        accountService.createAccount(customerDto);

        return ResponseEntity
                .status(CREATED)
                .body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
    }

    @Operation(summary = "Fetch account details REST API",
            description = "REST API to fetch Customer & Account details based on mobile number",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK",
                            content = @Content(schema = @Schema(implementation = CustomerDto.class))
                    )
            }
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetail(@RequestParam
                                                          @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits")
                                                          String mobileNumber) {

        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);

        return ResponseEntity
                .status(OK)
                .body(customerDto);

    }

    @Operation(
            summary = "Update account details REST API",
            description = "REST API to update Customer & Account details based on mobile number",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK",
                            content = @Content(schema = @Schema(implementation = ResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "417",
                            description = "HTTP Status Expectation Failed",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    )
            }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {

        boolean isUpdated = accountService.updateAccount(customerDto);

        if (isUpdated) {

            return ResponseEntity
                    .status(OK)
                    .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
        }
        else {

            return ResponseEntity
                    .status(INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstant.STATUS_500, AccountConstant.MESSAGE_500));
        }
    }

    @Operation(
            summary = "Delete account details REST API",
            description = "REST API to delete Customer & Account details based on mobile number",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK",
                            content = @Content(schema = @Schema(implementation = ResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "417",
                            description = "HTTP Status Expectation Failed",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    )
            }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                     @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits")
                                                      String mobileNumber) {

        boolean isDeleted = accountService.deleteAccount(mobileNumber);

        if (isDeleted) {

            return ResponseEntity
                    .status(OK)
                    .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
        }
        else {

            return ResponseEntity
                    .status(INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstant.STATUS_500, AccountConstant.MESSAGE_500));
        }
    }
}