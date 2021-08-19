package uz.pdp.apppermission.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
@Data
public class ForbiddenException extends RuntimeException{
    private String type;
    private String message;

    public ForbiddenException(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
