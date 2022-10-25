package com.ssafy.backend.response;

import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.entity.Comment;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@Setter
public class GetCommentRes extends BaseResponseBody {
    List<Comment> commentList;

    public static GetCommentRes of(List<Comment> commentList, Integer statusCode, String message) {
        GetCommentRes res = new GetCommentRes();
        res.setCommentList(commentList);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
