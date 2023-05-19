package ru.tinkoff.edu.java.bot.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.exception.BadRequestException;
import ru.tinkoff.edu.java.bot.exception.DataNotFoundException;
import ru.tinkoff.edu.java.bot.model.response.ApiErrorResponse;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handlerException(RuntimeException e) {
        log.error(e.getMessage(), e);
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                "По данному запросу данных нет",
                "404",
                e.getClass().getName(),
                e.getMessage());
        return apiErrorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handlerException(Exception e) {
        log.error(e.getMessage(), e);
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                "Некорректные параметры запроса",
                "400",
                e.getClass().getName(),
                e.getMessage());
        return apiErrorResponse;
    }

}
