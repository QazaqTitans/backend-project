package kz.reserve.backend.configuration;

import kz.reserve.backend.model.ErrorModel;
import kz.reserve.backend.payload.response.ErrorResponse;
import kz.reserve.backend.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException exception) {

        List<ErrorModel> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorModel(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(errorMessages));
    }
}
