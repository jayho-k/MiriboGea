package com.ssafy.backend.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateCommentReq {

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdAt;

}
