package com.ssafy.backend.response;

import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.entity.Report;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetReportRes extends BaseResponseBody{
    List<Report> reportList;
    public static GetReportRes of(List<Report> reportList, Integer statusCode, String message) {
        GetReportRes res = new GetReportRes();
        res.setReportList(reportList);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
