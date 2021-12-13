package com.coffee.point.exception;

import com.coffee.point.dto.exception.ErrorDTO;
import com.coffee.point.dto.exception.FieldErrorDTO;
import com.coffee.point.exception.runtime.BadRequestException;
import com.coffee.point.exception.runtime.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    /**
     * NotFoundException
     *
     * @return message from constructor
     */
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundHandler(EntityNotFoundException ex) {
        return ex.getMessage();
    }

    /**
     * AuthenticationServiceException
     *
     * @return message from constructor
     */
    @ResponseBody
    @ExceptionHandler(AuthenticationServiceException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String AuthenticationServiceHandler(AuthenticationServiceException ex) {
        return ex.getMessage();
    }

    /**
     * BadRequestException
     *
     * @return message
     */
    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO BadRequestHandler(BadRequestException ex) {

        ErrorDTO errorsDTO = new ErrorDTO();
        errorsDTO.setError(ex.getMessage());

        return errorsDTO;
    }

    /**
     * ResponseEntity HANDLE
     */
    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
            @NonNull HttpStatus status, @NonNull WebRequest request) {

        List<FieldErrorDTO> fieldErrors = new ArrayList<>();

        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();

        for (ObjectError o : objectErrors) {

            String fieldName = ((FieldError) o).getField();
            String errorMessage = o.getDefaultMessage();

            FieldErrorDTO f = new FieldErrorDTO();
            f.setField(fieldName);
            f.setMessage(errorMessage);

            fieldErrors.add(f);

        }

        ErrorDTO errorsDTO = new ErrorDTO();
        errorsDTO.setFieldErrors(fieldErrors);

        return new ResponseEntity<>(errorsDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}