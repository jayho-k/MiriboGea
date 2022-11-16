package com.ssafy.backend.response;

import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.entity.Board;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("ArticleCreateResponse")
public class ArticleCreateRes  extends BaseResponseBody {

    List<Board> articleList;
    Long boardId;
    public static ArticleCreateRes of(Integer statusCode, String message, List<Board> articleList,Long boardId) {
        ArticleCreateRes res = new ArticleCreateRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setArticleList(articleList);
        res.setBoardId(boardId);
        return res;
    }
}

