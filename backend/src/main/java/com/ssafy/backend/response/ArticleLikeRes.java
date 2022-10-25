package com.ssafy.backend.response;

import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.entity.Board;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ArticleLikeResponse")
public class ArticleLikeRes extends BaseResponseBody {

    boolean state;

    public static ArticleLikeRes of(Integer statusCode, String message, boolean state) {
        ArticleLikeRes res = new ArticleLikeRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setState(state);
        return res;
    }
}
