package com.rxbus;

import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {
    private static final RxBus INSTANCE = new RxBus();
    private final Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

    public static RxBus getInstance() {
        return INSTANCE;
    }

    public Subscription register(Action1<Event> onNext) {
        return bus
                .filter(event -> event.getClass().equals(Event.class))
                .map(obj -> (Event) obj)
                .subscribe(onNext);
    }

    public void post(String tag, Object event) {
        bus.onNext(new Event(event, tag));
    }
}