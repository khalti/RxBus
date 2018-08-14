package com.rxbus;


import io.reactivex.annotations.NonNull;

public class Event {

    private Object value;
    private String tag;

    Event(@NonNull String tag, @NonNull Object value) {
        this.tag = tag;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getTag() {
        return tag;
    }
}
