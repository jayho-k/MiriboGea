package com.ssafy.backend.request;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;


@Getter
public class UserScheduleRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Long triggerAt;
}
