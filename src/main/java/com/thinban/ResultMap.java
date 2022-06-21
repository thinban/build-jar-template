package com.thinban;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultMap<T> implements Serializable {
    private String code;
    private String msg;
    private T info;
}