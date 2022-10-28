package com.ssafy.backend.request;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class ReportCheckReq {
    @NotNull
    private String state;
}
