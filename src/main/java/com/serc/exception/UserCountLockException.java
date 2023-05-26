package com.serc.exception;


import org.springframework.security.core.AuthenticationException;

public class UserCountLockException extends AuthenticationException {
    public UserCountLockException(String msg) {
        super(msg);
    }

    public UserCountLockException(String msg,Throwable throwable) {
        super(msg,throwable);
    }
}
