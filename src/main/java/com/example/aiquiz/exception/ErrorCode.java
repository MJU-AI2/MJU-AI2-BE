package com.example.aiquiz.exception;
import java.io.Serializable;

public interface ErrorCode  extends Serializable {
    int getStatus();

    String getCode();

    String getMessage();
}