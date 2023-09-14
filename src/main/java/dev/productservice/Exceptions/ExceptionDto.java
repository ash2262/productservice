package dev.ashish.productservice.Exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExceptionDto {
    private HttpStatus errorCode;
    private String message;

    public  ExceptionDto(HttpStatus status,String message){
        this.errorCode=status;
        this.message=message;
    }
}
