package ru.tinkoff.edu.java.bot.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ApiErrorResponse(
        String description,
        String code,
        @JsonProperty(value = "exception_name")
        String exceptionName,
        @JsonProperty(value = "exception_message")
        String exceptionMessage) {


}