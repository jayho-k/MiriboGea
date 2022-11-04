package com.ssafy.backend.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@ApiModel("CreateArticleRequest")
public class CreateArticleReq {

    @NotNull
    @ApiModelProperty(name="제목", example = "잃어버린 강아지를 찾아주세요.")
    private String title;

    @NotNull
    @ApiModelProperty(name="내용", example = "이 강아지의 특징은 꼬리가 짧고 점이 있어요.")
    private String content;

    @NotNull
    @ApiModelProperty(name="게시판 카테고리", example = "freeBoard")
    private String category;

    @ApiModelProperty(name="사진 URL", example = "https://www.naver.com/")
    private String picURL;

}
