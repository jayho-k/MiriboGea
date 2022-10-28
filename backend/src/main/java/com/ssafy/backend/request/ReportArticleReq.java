package com.ssafy.backend.request;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportArticleReq {

    @NotNull
    private String content;

//    @NotNull
//    // unread, warning, notWarning
//    private String state;
}
