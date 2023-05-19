package ru.tinkoff.edu.java.bot.service.commands.impl;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.commands.Command;

@Component
public class HelpCommand implements Command {
    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "вывести окно с командами";
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(),"/start -- зарегистрировать пользователя\n" +
                "/help -- вывести окно с командами\n" +
                "/track -- начать отслеживание ссылки\n" +
                "/untrack -- прекратить отслеживание ссылки\n" +
                "/list -- показать список отслеживаемых ссылок");
    }
}
