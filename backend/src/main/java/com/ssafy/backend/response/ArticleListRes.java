package com.ssafy.backend.response;

import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.entity.Board;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("ArticleListResponse")
public class ArticleListRes extends BaseResponseBody {

    List<Board> articleList;

    public static ArticleListRes of(Integer statusCode, String message, List<Board> articleList) {
        ArticleListRes res = new ArticleListRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setArticleList(articleList);
        return res;
    }
}
