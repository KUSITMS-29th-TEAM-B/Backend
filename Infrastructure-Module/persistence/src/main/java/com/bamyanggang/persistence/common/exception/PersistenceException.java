package com.bamyanggang.persistence.common.exception;

import com.bamyanggang.commonmodule.exception.CustomException;

public class PersistenceException extends CustomException {
    public static final String CODE_PREFIX = "PERSISTENCE";

    public PersistenceException(int errorCode, String message) {
        super(CODE_PREFIX, errorCode, message);
    }

    public static class NotFound extends PersistenceException {
        public NotFound() {
            super(1, "해당 데이터를 찾을 수 없습니다.");
        }
    }

}
