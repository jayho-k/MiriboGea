package com.ssafy.backend.response;

import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.entity.Board;
import com.ssafy.backend.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ArticleDetailResponse")
public class ArticleDetailRes extends BaseResponseBody {

    Board board;
    boolean likeState;
    Long likeCount;
    public static ArticleDetailRes of(Integer statusCode, String message, Board board,boolean likeState,Long likeCount) {
        ArticleDetailRes res = new ArticleDetailRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setBoard(board);
        res.setLikeState(likeState);
        res.setLikeCount(likeCount);
        return res;
    }
}
