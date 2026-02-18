package peaksoft.restaurantproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.restaurantproject.dto.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Ловит все наши кастомные ошибки (RuntimeException)
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleRuntimeException(RuntimeException e) {
        return ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage()) // Сюда подставится твой текст: "В ресторане нет вакансий"
                .errorType(e.getClass().getSimpleName())
                .build();
    }

    // 2. Ловит ошибки валидации из DTO (когда не прошел @Valid, например @Email или @Min)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();

        // Собираем все сообщения об ошибках полей в одну строку
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
        );

        return ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(errors.toString()) // Выведет что-то вроде "email: Некорректный формат email;"
                .errorType("Validation Error")
                .build();
    }
}