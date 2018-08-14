package com.rxbus;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class RxBus {
    private static final RxBus INSTANCE = new RxBus();
    private final PublishSubject<Event> bus = PublishSubject.create();

    public static RxBus getInstance() {
        return INSTANCE;
    }

    public Disposable register(Consumer<Event> onNext) {
        return bus
                .subscribe(onNext);
    }

    public Disposable register(String tag, Consumer<Event> onNext) {
        return bus
                .filter(event -> event.getTag().equals(tag))
                .subscribe(onNext);
    }

    public void post(String tag, Object value) {
        bus.onNext(new Event(tag, value));
    }
}