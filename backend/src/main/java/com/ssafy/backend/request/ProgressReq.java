package com.ssafy.backend.request;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class ProgressReq {
    @NotNull
    int progress;
}
