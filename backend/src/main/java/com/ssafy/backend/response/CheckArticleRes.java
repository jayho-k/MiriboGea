package com.ssafy.backend.response;

import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.entity.Board;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CheckArticleRes extends BaseResponseBody {

    boolean checked;

    public static CheckArticleRes of(Integer statusCode, String message, boolean checked){
        CheckArticleRes res = new CheckArticleRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setChecked(checked);
        return res;
    }
}

