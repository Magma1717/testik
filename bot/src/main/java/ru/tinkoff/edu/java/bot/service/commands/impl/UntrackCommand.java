package ru.tinkoff.edu.java.bot.service.commands.impl;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.model.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.model.response.LinkResponse;
import ru.tinkoff.edu.java.bot.service.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.service.commands.Command;

@Component
public class UntrackCommand implements Command {
    @Override
    public String command() {
        return "/untrack";
    }

    @Override
    public String description() {
        return "прекратить отслеживаоние ссылки";
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), "отслеживание ссылки прекращено");
    }
}

