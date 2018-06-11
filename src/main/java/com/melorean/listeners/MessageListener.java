package com.melorean.listeners;



import com.melorean.services.MessageService;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.MessageUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener extends ListenerAdapter{

    @Autowired
    private MessageService service;

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        Message msg = event.getMessage();
        if (msg.getContentRaw().startsWith(":stats")){
            event.getChannel().sendMessage(service.getServerStat(event.getGuild())).queue();
        }
    }

    @Override
    public void onMessageUpdate(MessageUpdateEvent event){
        System.out.println(event.getAuthor());
    }

}
