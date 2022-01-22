package me.yuuki.provider.payment.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;
    public static <T> CommonResult<T> empty(Integer code, String message) {
        return new CommonResult<>(code, message, null);
    }
    public static <T> CommonResult<T> success(T t) {
        return new CommonResult<>(200, "success", null);
    }
    public static <T> CommonResult<T> success(String message, T t) {
        return new CommonResult<>(200, message, null);
    }
}