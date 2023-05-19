package ru.tinkoff.edu.java.bot.service.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.service.commands.Command;

import java.util.List;
@Component
@AllArgsConstructor
public class Bot {
    TelegramBot telegramBot;
    ScrapperClient scrapperClient;
    BotStarter botStarter;

    public void start() {
        commandsMenu(botStarter.commands());
        telegramBot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                Message message = update.message();
                if (message != null) {
                    SendMessage result = botStarter.process(update).parseMode(ParseMode.Markdown);
                    SendResponse sendResponse = telegramBot.execute(result);
               //    Keyboard forceReply = new ForceReply();
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public void commandsMenu(List<Command> commandList) {
        List<BotCommand> botCommandList = commandList.stream().map(Command::toApiCommand).toList();
        SetMyCommands setMyCommands = new SetMyCommands(botCommandList.toArray(botCommandList.toArray(new BotCommand[0])));
        telegramBot.execute(setMyCommands);
    }
}
