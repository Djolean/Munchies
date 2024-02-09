package com.ingsoftware.munchies.config;

import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SlackAppConfig {

    @Value("${slack.bot-token}")
    private String botToken;

    @Value("${slack.signing-secret}")
    private String signingSecret;

    @Bean
    public AppConfig loadSingleWorkspaceAppConfig() {
        return AppConfig.builder()
                .singleTeamBotToken(botToken)
                .signingSecret(signingSecret)
                .build();

    }
    @Bean
    public App initSlackApp(AppConfig config) {
        App app = new App(config);

        app.command("/hello", (req, ctx) -> ctx.ack(r -> r.text("Hello, Welcome to Munchies!")));

        System.out.println(signingSecret);
        System.out.println(botToken);
        app.start();
        return app;
    }
}