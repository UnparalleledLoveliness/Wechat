package com.shopping.cloudShopping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnResult<T> {

    public static final ReturnResult PARAMERROR = new ReturnResult(ReturnResult.FAIL, "请检查参数");

    public static final ReturnResult SUCCESS_RESULT = new ReturnResult(ReturnResult.SUCCESS, "success");

    public static final String SUCCESS = "success";

    public static final String FAIL = "fail";

    private String status;

    private String message;

    private T data;

    public ReturnResult(String status, String message ) {
        this.status = status;
        this.message = message;
    }
}
