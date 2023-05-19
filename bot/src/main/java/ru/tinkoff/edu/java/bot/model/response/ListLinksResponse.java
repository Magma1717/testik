package ru.tinkoff.edu.java.bot.model.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;




public record ListLinksResponse(
        List<LinkResponse> links,
        Integer size)   {

    @Override
    public String toString() {
        String result = "";
        for(LinkResponse link: links){
            result += link.url() + "\n";
        }
        return result;
    }}