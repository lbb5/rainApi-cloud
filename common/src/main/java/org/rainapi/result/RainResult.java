package org.rainapi.result;

import org.rainapi.enums.RainResultEnum;

public class RainResult<T> {
    public Integer code;
    public String message;

    public T data;

    private RainResult() {
    }

    private RainResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private RainResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> RainResult<T> success(T data) {
        return new RainResult<>(200, "success", data);
    }

    public static <T> RainResult<T> fail() {
        return new RainResult<>(500, "fail");
    }

    public static <T> RainResult<T> buildResult(Integer code, String message, T data) {
        return new RainResult<>(code, message, data);
    }
    public static <T> RainResult<T> buildResult(RainResultEnum rainResultEnum, T data) {
        return new RainResult<>(rainResultEnum.getCode(), rainResultEnum.getMessage(), data);
    }

    public static <T> RainResult<T> buildResult(RainResultEnum rainResultEnum) {
        return new RainResult<>(rainResultEnum.getCode(), rainResultEnum.getMessage());
    }
}
