package me.yuuki.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;
    public static <T> CommonResult<T> empty(Integer code, String message) {
        return new CommonResult<>(code, message, null);
    }
    public static <T> CommonResult<T> success(T t) {
        return new CommonResult<>(200, "success", t);
    }
    public static <T> CommonResult<T> success(String message, T t) {
        return new CommonResult<>(200, message, t);
    }
}