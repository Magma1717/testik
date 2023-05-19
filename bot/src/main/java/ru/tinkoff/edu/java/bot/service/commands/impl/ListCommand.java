package ru.tinkoff.edu.java.bot.service.commands.impl;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.model.response.ListLinksResponse;
import ru.tinkoff.edu.java.bot.service.LinkService;
import ru.tinkoff.edu.java.bot.service.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.service.commands.Command;

import java.util.List;

@Component
public class ListCommand implements Command{
    private ScrapperClient scrapperClient;

    private final String LIST_IS_EMPTY = "список пуст";

    public ListCommand(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "показать список отслеживаемых ссылок";
    }

    @Override
    public SendMessage handle(Update update) {
        Long tgChatId = update.message().chat().id();
        try {
            ListLinksResponse listLinksResponse =
                    scrapperClient.getLink(tgChatId);
            if(listLinksResponse.size() != 0) {
                return new SendMessage(tgChatId, listLinksResponse.toString());
            }else
                return new SendMessage(tgChatId, LIST_IS_EMPTY);
        }catch (Exception e){
            return new SendMessage(tgChatId, e.getMessage());
        }

    }
}
