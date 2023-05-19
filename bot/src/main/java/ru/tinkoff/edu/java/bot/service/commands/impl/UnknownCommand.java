package ru.tinkoff.edu.java.bot.service.commands.impl;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.commands.Command;

@Component
public class UnknownCommand implements Command{
    @Override
    public String command() {
        return "";
    }

    @Override
    public String description() {
        return "неизвестная команда";
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(),"неизвестная команда");
    }
}
