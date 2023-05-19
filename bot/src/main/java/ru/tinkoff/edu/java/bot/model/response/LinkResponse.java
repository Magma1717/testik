package ru.tinkoff.edu.java.bot.model.response;

import lombok.*;

import java.net.URI;


@Builder
public record LinkResponse(
        Long id,
        String url){
}