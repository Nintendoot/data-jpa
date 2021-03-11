package by.nintendo.datajpa.resource;

import by.nintendo.datajpa.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionResource extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,String> err=new HashMap<>();
       for(FieldError fieldError:ex.getFieldErrors()){
           err.put(fieldError.getField(),fieldError.getDefaultMessage());
       }
       return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchUserException.class)
        public ResponseEntity<Object> ex(NoSuchUserException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> ex(UserAlreadyExistsException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.FOUND);
    }

    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<Object> ex(PetNotFoundException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PetStatusException.class)
    public ResponseEntity<Object> ex(PetStatusException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PetAlreadyExistsException.class)
    public ResponseEntity<Object> ex(PetAlreadyExistsException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.FOUND);
    }

    @ExceptionHandler(NoOrdersException.class)
    public ResponseEntity<Object> ex(NoOrdersException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.FOUND);
    }

    @ExceptionHandler(NoSuchOrderException.class)
    public ResponseEntity<Object> ex(NoSuchOrderException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.FOUND);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Object> ex(WrongPasswordException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.FOUND);
    }

}
