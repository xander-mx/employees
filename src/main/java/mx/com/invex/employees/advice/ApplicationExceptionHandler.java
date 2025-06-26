package mx.com.invex.employees.advice;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import mx.com.invex.employees.dtos.ErrorsDTO;
import mx.com.invex.employees.dtos.response.GenericResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    public static final String FAILED = "FAILED";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GenericResponse<Object>> handleHttpMessagetNotReadableException(HttpMessageNotReadableException ex) {
        log.error("[:::::::::: HttpMessageNotReadableException : {} {}", ex.getStackTrace(), "::::::::::]");
        JsonMappingException jme = (JsonMappingException) ex.getCause();
        List<ErrorsDTO> errors = new ArrayList<>();

        for (int i = 0; i < jme.getPath().size(); i++) {
            if(Objects.nonNull(jme.getPath().get(i))) {
                errors.add(ErrorsDTO.builder().detail(jme.getPath().get(i).getFieldName())
                        .status("NOT READABLE").code(BAD_REQUEST.value()).build());
            }
        }
        return ResponseEntity.badRequest().body(getErrors(errors));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<GenericResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("[:::::::::: MethodArgumentNotValidException : {} {}", ex.getMessage(), "::::::::::]");
        return ResponseEntity.badRequest().body(getErrors(ex.getBindingResult().getFieldErrors().stream()
                .map(e -> ErrorsDTO.builder().code(BAD_REQUEST.value())
                        .detail(e.getDefaultMessage()).status(FAILED).build()).toList()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<GenericResponse<Object>> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("[:::::::::: HttpRequestMethodNotSupportedException : {} {}", ex.getMessage(), "::::::::::]");
        return new ResponseEntity<>(
                getErrors(Collections.singletonList(ErrorsDTO.builder().code(METHOD_NOT_ALLOWED.value())
                        .detail(ex.getMessage()).status(FAILED).build())), new HttpHeaders(), METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<GenericResponse<Object>> handleDataAccessException(DataAccessException ex) {
        log.error("[:::::::::: DataAccessException : {} {}", ex.getMessage(), "::::::::::]");
        return ResponseEntity.internalServerError().body(
                getErrors(Collections.singletonList(ErrorsDTO.builder().code(INTERNAL_SERVER_ERROR.value())
                        .detail(ex.getMessage()).status(FAILED).build())));
    }

    @ExceptionHandler(SQLException.class)
    public final ResponseEntity<GenericResponse<Object>> handleSQLException(SQLException ex) {
        log.error("[:::::::::: SQLException : {} {}", ex.getMessage(), "::::::::::]");
        return ResponseEntity.internalServerError().body(
                getErrors(Collections.singletonList(ErrorsDTO.builder().code(INTERNAL_SERVER_ERROR.value())
                        .detail(ex.getMessage()).status(FAILED).build())));
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<GenericResponse<Object>> handleRuntimeExceptions(RuntimeException ex) {
        log.error("[:::::::::: SQLException : {} {}", ex.getMessage(), "::::::::::]");
        return ResponseEntity.internalServerError().body(
                getErrors(Collections.singletonList(ErrorsDTO.builder().code(INTERNAL_SERVER_ERROR.value())
                        .detail(ex.getMessage()).status(FAILED).build())));
    }

    private GenericResponse<Object> getErrors(List<ErrorsDTO> errors) {
        GenericResponse.Data<Object> data = GenericResponse.Data.builder().build();
        return GenericResponse.builder().errors(errors).data(data).build();
    }
}
