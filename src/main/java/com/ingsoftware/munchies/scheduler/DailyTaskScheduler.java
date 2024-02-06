package com.ingsoftware.munchies.scheduler;

import com.ingsoftware.munchies.repository.PasswordResetTokenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Transactional
public class DailyTaskScheduler {


    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Scheduled(cron = "0 0 0 * * *") //every midnight
    public void deleteUnusedTokens() {

        passwordResetTokenRepository.deleteByUsed(true);
    }

}
