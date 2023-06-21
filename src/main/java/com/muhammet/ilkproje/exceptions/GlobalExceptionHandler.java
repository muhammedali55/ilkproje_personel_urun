package com.muhammet.ilkproje.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import static com.muhammet.ilkproje.exceptions.ErrorType.*;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j // loglama için
public class GlobalExceptionHandler {

    /**
     * Hata msajları çok fazla olabileceği için tüm hatalar için ayrı ayrı log lama ve denetim mekanizmezı yazmak
     * doğru dğeildir.
     * bu method sayesinde  diğer methodlarda olan olayların tümü burada loglanabilir
     * tetiklenmesi gereken ya da DB te yazılması gereken işlemler olursa burada yapılabilir.
     */
    private ErrorMessage createErrorMessage(ErrorType errorType, Exception exception){
        log.error("Hata oluştu",exception);
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }


    /**
     * Mutlaka yakalanamayacak hatalar için en genel hata yakalma türünü ekleyiniz.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException ex){
        return new ResponseEntity<>(createErrorMessage(INTERNAL_SERVER_ERROR,ex),INTERNAL_SERVER_ERROR.getHttpStatus());
    }

    @ExceptionHandler(IlkprojeException.class)
    public ResponseEntity<ErrorMessage> handleIlkproeException(IlkprojeException ex){
        return new ResponseEntity<>(createErrorMessage(ex.getErrorType(),ex),ex.getErrorType().getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createErrorMessage(errorType,exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleInvalidFormatException(
            InvalidFormatException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createErrorMessage(errorType,exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handlePSQLException(
            DataIntegrityViolationException exception) {
        ErrorType errorType = REGISTER_KULLANICIADI_KAYITLI;
        return new ResponseEntity<>(createErrorMessage(errorType,exception), errorType.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MethodArgumentTypeMismatchException exception) {

        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createErrorMessage(errorType,exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MissingPathVariableException exception) {

        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createErrorMessage(errorType,exception), errorType.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        ErrorType errorType = BAD_REQUEST_ERROR;
        List<String> fields = new ArrayList();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(e -> fields.add(e.getField() + ": " + e.getDefaultMessage()));
        ErrorMessage errorMessage = createErrorMessage(errorType,exception);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage, errorType.getHttpStatus());
    }


}
