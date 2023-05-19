package ru.tinkoff.edu.java.bot.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.model.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.model.response.LinkResponse;
import ru.tinkoff.edu.java.bot.service.client.ScrapperClient;

@RestController
@AllArgsConstructor
public class ScrapperTestController {
    private ScrapperClient scrapperClient;

    @PostMapping(value = "/tg-chat/{id}",
            produces = {"application/json"})
    public ResponseEntity<Mono> registerChat(@PathVariable("id") Long id ){
        return new ResponseEntity<Mono>(scrapperClient.registerChat(id), HttpStatus.OK);

    }

    @DeleteMapping(value = "/tg-chat/{id}",
            produces = {"application/json"})
    public ResponseEntity<Mono> deleteChat(@PathVariable("id") Long id){
        return new ResponseEntity<Mono>(scrapperClient.deleteChat(id), HttpStatus.OK);
    }



    @PostMapping(value = "/links",
            produces = {"application/json"})
    public ResponseEntity<LinkResponse> addLink(Long tgChatId,@Valid @RequestBody AddLinkRequest addLinkRequest) {
        return new ResponseEntity<>(scrapperClient.addLink(tgChatId, addLinkRequest) ,HttpStatus.OK);

    }


}