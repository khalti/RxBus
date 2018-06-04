package com.rxbus;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {
    private static final RxBus INSTANCE = new RxBus();
    private final Subject<Object> bus = PublishSubject.create();

    public static RxBus getInstance() {
        return INSTANCE;
    }

    public Disposable register(Consumer<Event> onNext) {
        return bus
                .filter(event -> event.getClass().equals(Event.class))
                .map(obj -> (Event) obj)
                .subscribe(onNext);
    }

    public void post(String tag, Object event) {
        bus.onNext(new Event(event, tag));
    }
}