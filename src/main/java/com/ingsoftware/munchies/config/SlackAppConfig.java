package com.ingsoftware.munchies.config;

import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SlackAppConfig {

    @Value("${SLACK_BOT_TOKEN}")
    private String slackBotToken;

    @Value("${SLACK_SIGNING_SECRET}")
    private String slackSigningSecret;

    @Bean
    public AppConfig appConfig() {
        return AppConfig.builder()
                .singleTeamBotToken(slackBotToken)
                .signingSecret(slackSigningSecret)
                .build();

    }
    @Bean
    public App initSlackApp(AppConfig config) {
        App apiApp = new App();
        if (config.getClientId() != null) {
            apiApp.asOAuthApp(true);
        }
        apiApp.command("/hello", (req, ctx) -> {
            return ctx.ack("Hello, Welcome to Munchies!");
        });
        return apiApp;
    }

}