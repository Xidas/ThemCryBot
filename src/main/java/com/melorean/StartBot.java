package com.melorean;

import com.melorean.listeners.GuildsEventsListener;
import com.melorean.listeners.MessageListener;
import com.melorean.listeners.ReadyListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.melorean")
public class StartBot implements CommandLineRunner {


    @Autowired
    private ReadyListener readyListener;

    @Autowired
    private MessageListener messageListener;

    @Autowired
    private GuildsEventsListener guildsEventsListener;


    public static void main(String[] args) {
        SpringApplication.run(StartBot.class);
    }


    @Override
    public void run(String... args) throws Exception {
        JDA jda = new JDABuilder(AccountType.BOT)
                .setToken("MzYxNDcxMTc3NDU1MDQyNTYy.DVjByA.6L5aiU8Wy1TZ5qv1GmOcBsMxxY4")
                .addEventListener(messageListener)
                .addEventListener(readyListener)
                .addEventListener(guildsEventsListener)
                .buildBlocking();

    }
}
