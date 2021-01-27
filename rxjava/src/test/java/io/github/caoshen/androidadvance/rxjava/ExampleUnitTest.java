package io.github.caoshen.androidadvance.rxjava;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testFromCallable() {
        int id = 1;
        Observable<String> observable = Observable.fromCallable(() -> "from:" + id);
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe:" + d);
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("onNext:" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError:" + e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}