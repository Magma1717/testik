package ru.tinkoff.edu.java.bot.service.client;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.model.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.model.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.model.response.LinkResponse;
import ru.tinkoff.edu.java.bot.model.response.ListLinksResponse;

@Service
@RequiredArgsConstructor
public class ScrapperClient {
    private final WebClient webClient;



    public Mono<ResponseEntity<LinkResponse>> postLinks(Long tgChatId, AddLinkRequest requestBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/links")
                        .build())
                .body(Mono.just(requestBody), AddLinkRequest.class)
                .accept(MediaType.APPLICATION_JSON)
                .header("Tg-Chat-Id", String.valueOf(tgChatId))
                .retrieve()
                .toEntity(LinkResponse.class);
    }


    public Mono<ResponseEntity<Void>> registerChat(Long id) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/tg-chat/{id}")
                        .build(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Void.class);
    }

    public Mono<ResponseEntity<Void>> deleteChat(Long id) {
        return webClient.delete()
                .uri(uriBuilder -> uriBuilder.path("/tg-chat/{id}")
                        .build(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Void.class);
    }
    public LinkResponse addLink(Long tgChatId, AddLinkRequest addLinkRequest) {
        return webClient
                .post().uri(uriBuilder -> uriBuilder
                        .path("/links")
                        .build())
                .header("Tg-Chat-Id", String.valueOf(tgChatId))
                .bodyValue(addLinkRequest)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(LinkResponse.class).block();
    }
    public ListLinksResponse getLink(Long tgChatId) {
        return webClient
                .get().uri(uriBuilder -> uriBuilder
                        .path("/links")
                        .build())
                .header("Tg-Chat-Id", String.valueOf(tgChatId))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(ListLinksResponse.class).block();
    }
    public LinkResponse deleteLink(Long tgChatId, AddLinkRequest addLinkRequest) {
        return webClient
                .method(HttpMethod.DELETE)
                .uri(uriBuilder -> uriBuilder
                        .path("/links")
                        .build())
                .bodyValue(addLinkRequest)
                .header("Tg-Chat-Id", String.valueOf(tgChatId))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(LinkResponse.class).block();
    }
}
