package br.com.github.gabrielotsuka.storesystem.handler;

import br.com.github.gabrielotsuka.storesystem.error.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException){
        ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder
                .newBuilder()
                .title("Resource Not Found")
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(rnfDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException manvException){
        List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String messages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        ValidationErrorDetails manvDetails = ValidationErrorDetails.Builder
                .newBuilder()
                .title("Field Validation Error")
                .status(HttpStatus.BAD_REQUEST.value())
                .fieldMessage(messages)
                .field(fields)
                .build();
        return new ResponseEntity<>(manvDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException (ConstraintViolationException cveException){
        HttpStatus error422 = HttpStatus.UNPROCESSABLE_ENTITY;
        ConstraintViolationExceptionDetails cveDetails = ConstraintViolationExceptionDetails.Builder
                .newBuilder()
                .title("Element already registered.")
                .status(error422.value())
                .build();
        return new ResponseEntity<>(cveDetails, error422);
    }

    @ExceptionHandler(NotEnoughProductsException.class)
    public ResponseEntity<?> handleNotEnoughProductsException (NotEnoughProductsException nepException){
        HttpStatus error422 = HttpStatus.UNPROCESSABLE_ENTITY;
        NotEnoughProductsDetails nepDetails = NotEnoughProductsDetails.Builder
                .newBuilder()
                .title("Not enough products in stock")
                .status(error422.value())
                .build();
        return new ResponseEntity<>(nepDetails, error422);
    }

    public ResponseEntity<?> handleClosedOrderException (ClosedOrderException coException){
        HttpStatus error422 = HttpStatus.UNPROCESSABLE_ENTITY;
        ClosedOrderDetails coDetails = ClosedOrderDetails.Builder
                .newBuilder()
                .title("Trying to manipulate closed order.")
                .status(error422.value())
                .build();
        return new ResponseEntity<>(coDetails, error422);
    }
}
