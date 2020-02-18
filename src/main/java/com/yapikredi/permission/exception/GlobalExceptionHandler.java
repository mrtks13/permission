package com.yapikredi.permission.exception;

import com.yapikredi.permission.dto.ErrorDto;
import com.yapikredi.permission.util.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;


@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {


    @ExceptionHandler({EmployeeNotFoundException.class, PermissionDayNotEnoughException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDto handleEmployeeNotFoundExceptionn(EmployeeNotFoundException e) {
        return new ErrorDto(e.getMessage(), HttpStatus.NOT_FOUND.toString());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, DateTimeParseException.class})
    public ErrorDto handleBindingErrors(Exception ex) {
        return new ErrorDto(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE.toString());
    }


    @ExceptionHandler({PermissionAppException.class,ValidationInputException.class})
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ErrorDto handlePermissionAppException(Exception e) {
        return getErrorOutput(e);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto handleAllxception(Exception e) {
        return getErrorOutput(e);
    }


    @ExceptionHandler({IllegalArgumentException.class,})
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public ErrorDto handleAuthenticationException(IllegalArgumentException e) {
        return getErrorOutput(e);
    }

    private ErrorDto getErrorOutput(Exception e) {
        Translator.toLocale(e.getMessage());
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrrorMessage(Translator.toLocale(e.getMessage()));
        return errorDto;
    }
}
