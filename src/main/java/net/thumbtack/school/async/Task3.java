package net.thumbtack.school.async;

import rx.Observable;
import rx.Subscriber;

import static java.lang.Thread.sleep;

public class Task3 {
    public static void main(String[] args) {
        Observable<Integer> myObservable = Observable.create(
                sub ->  {
                    for (int i = 0; i < 10; i++) {
                        int j = i % 2 == 0 ? -i : i;
                        sub.onNext(j);
                        try {
                            sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //if (i == 7) {
                        //    sub.onError(new IllegalStateException());
                        //}
                    }
                    sub.onCompleted();
                }

        );
        myObservable.subscribe(new MySubscriber());

    }

}

class MySubscriber extends Subscriber<Integer> {

    public void onCompleted() {
        System.out.println("finish");
    }

    public void onError(Throwable ex) {
        ex.printStackTrace();
    }

    public void onNext(Integer s) {
        System.out.println(s);
    }

}