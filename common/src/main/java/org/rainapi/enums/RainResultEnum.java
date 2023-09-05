package org.rainapi.enums;

import lombok.Getter;

@Getter
public enum RainResultEnum {
    SUCCESS(200,"success"),
    FAIL(500,"fail");

    private Integer code;

    public String message;

    RainResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
