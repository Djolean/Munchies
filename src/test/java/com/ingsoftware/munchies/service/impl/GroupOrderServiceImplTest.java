package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class GroupOrderServiceImplTest {
    @InjectMocks
    private GroupOrderServiceImpl groupOrderService;

    @Test
    void calculateTimeRemaining() {
    }
    @Test
    void formatTimeTest() {

        int remainingTimeSeconds = 600;

        String formattedTime = groupOrderService.formatTime(remainingTimeSeconds);

        assertEquals("10:00", formattedTime);
    }

    @Test
    void calculateTimeRemainingTest() {

        GroupOrderResponseDTO response = mock(GroupOrderResponseDTO.class);
        when(response.getGroupOrderTimeout()).thenReturn(5);
        when(response.getCreatedDate()).thenReturn(Instant.now().minusSeconds(120));

        int remainingTime = groupOrderService.calculateTimeRemaining(response);

        assertEquals(180, remainingTime);
    }

    @Test
    void calculateTimeRemainingTestException() {

        GroupOrderResponseDTO response = mock(GroupOrderResponseDTO.class);
        when(response.getGroupOrderTimeout()).thenReturn(5);
        when(response.getCreatedDate()).thenReturn(Instant.now().minusSeconds(120));

        int remainingTime = groupOrderService.calculateTimeRemaining(response);

        assertEquals(180, remainingTime);
    }
}