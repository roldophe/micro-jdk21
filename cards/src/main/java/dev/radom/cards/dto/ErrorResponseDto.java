package dev.radom.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Schema to hold error response information"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            name = "apiPath",
            description = "API path where error occurred",
            example = "/api/accounts/create"
    )
    private String apiPath;

    @Schema(
            name = "errorCode",
            description = "HTTP Status code of the error"
    )
    private HttpStatus errorCode;

    @Schema(
            name = "errorMessage",
            description = "Error message",
            example = "Mobile number already exists"
    )
    private String errorMessage;

    @Schema(
            name = "errorTime",
            description = "Time when error occurred",
            example = "2021-09-01T12:00:00"
    )
    private LocalDateTime errorTime;
}
