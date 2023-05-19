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
public class TrackCommand implements Command {
    private ScrapperClient scrapperClient;

    public TrackCommand(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }
    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "начать отслеживание ссылки";
    }

    public SendMessage handle(Update update) {
        long tgChatId = update.message().chat().id();
        Message message = update.message();
        if (!isReply(message)) {
            return new SendMessage(tgChatId, "введите ссылку для отслеживания").replyMarkup(new ForceReply());
        } else {
            AddLinkRequest addLinkRequest = new AddLinkRequest(message.text());
            LinkResponse linkResponse = scrapperClient.addLink(tgChatId, addLinkRequest);
            return new SendMessage(tgChatId, "отслеживаю " + linkResponse.url());
        }
    }
    public boolean isReply(Message message) {
        Message reply = message.replyToMessage();
        return reply != null && reply.text().equals("введите ссылку для отслеживания");
    }
}
