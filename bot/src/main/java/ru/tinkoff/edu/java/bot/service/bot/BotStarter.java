package ru.tinkoff.edu.java.bot.service.bot;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.service.commands.Command;
import ru.tinkoff.edu.java.bot.service.commands.impl.*;

import java.util.List;

@Component
public class BotStarter {
    private List<Command> commands;
    private UnknownCommand unknownCommand;

    private ScrapperClient scrapperClient;

    public BotStarter(HelpCommand helpCommand
            , ListCommand listCommand
            , StartCommand startCommand
            , TrackCommand trackCommand
            , UntrackCommand untrackCommand
            , UnknownCommand unknownCommand
            , ScrapperClient scrapperClient
    ) {
        this.commands = List.of(helpCommand, listCommand, startCommand, trackCommand, untrackCommand);
        this.unknownCommand = unknownCommand;
        this.scrapperClient = scrapperClient;
    }

    public List<Command> commands() {
        return this.commands;
    }

    public SendMessage process(Update update) {
        Message message = update.message();
        Command rightCommand = commands.stream()
                .filter(command -> message.text().equals(command.command()) ||
                        message.replyToMessage() != null &&
                                message.replyToMessage().text().equals(command.description()))
                .findFirst().orElse(unknownCommand);
        return rightCommand.handle(update);

//        if (!isReply(message)) {
//            Command rightCommand = commands.stream()
//                    .filter(command -> message.text().equals(command.command()))
//                    .findFirst().orElse(unknownCommand);
//            return rightCommand.handle(update);
//        } else {
////          подразумевается, что ссылка всегда валидная на данный момент
//            scrapperClient.addLink(message.chat().id(), new AddLinkRequest(message.text()));
//            return new SendMessage(message.chat().id(), "отслеживаю ссылку");
//        }
    }

    public boolean isReply(Message message) {
        Message reply = message.replyToMessage();
        return reply != null && reply.text().equals("введите ссылку для отслеживания");
    }
}
