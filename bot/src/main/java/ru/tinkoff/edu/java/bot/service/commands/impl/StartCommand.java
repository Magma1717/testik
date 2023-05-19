package ru.tinkoff.edu.java.bot.service.commands.impl;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.service.commands.Command;

@Component
public class StartCommand implements Command {
    ScrapperClient scrapperClient;

    public StartCommand(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "зарегистрировать пользователя";
    }

    @Override
    public SendMessage handle(Update update) {
        long tgChatId = update.message().chat().id();
        scrapperClient.registerChat(tgChatId);
        return new SendMessage(update.message().chat().id(),"пользователь зарегестрирован");
    }
}
