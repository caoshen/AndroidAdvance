package io.github.caoshen.androidadvance.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/**
 * @author caoshen
 * @date 2021/1/5
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity-rx";
    private CompositeDisposable mCompositeDisposable;
    private ObservableEmitter<BigInteger> mEmitter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeDisposable = new CompositeDisposable();
//        testRx();
//        testCache();
        testInfinite();
    }

    private void testRx() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "subscribe: thread:" + Thread.currentThread().getName());
                new Thread(() -> {
                    Log.d(TAG, "thread:" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000);
                        emitter.onNext("one");
                        Thread.sleep(3000);
                        emitter.onNext("two");
                        Thread.sleep(3000);
                        emitter.onComplete();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe:thread:" + Thread.currentThread().getName());
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "onNext:thread:" + Thread.currentThread().getName());
                Log.d(TAG, "onNext:" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError:thread:" + Thread.currentThread().getName());
                Log.d(TAG, "onError:" + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete:thread:" + Thread.currentThread().getName());
                Log.d(TAG, "onComplete:");
            }
        };
        observable.subscribe(observer);
    }

    public void testMerge() {
        Observable<Integer> observable1 = Observable.create(emitter -> emitter.onNext(1));

        Observable<String> observable2 = Observable.create(emitter -> {
            emitter.onNext("one");
        });

        Observable<? extends Serializable> merge = Observable.merge(observable1, observable2);

        BiFunction<Integer, String, String> zipper = new BiFunction<Integer, String, String>() {
            @NonNull
            @Override
            public String apply(@NonNull Integer integer, @NonNull String s) throws Exception {
                return integer + s;
            }
        };
        Observable<String> zip = Observable.zip(observable1, observable2, zipper);
    }

    public void testCache() {
        Observable<Object> ints = Observable.create(emitter -> {
            Log.d(TAG, "create");
            emitter.onNext(42);
            emitter.onComplete();
        }).cache();
        Log.d(TAG, "starting");
        Disposable subscribe = ints.subscribe(i -> Log.d(TAG, "testCache: Element A:" + i));
        Disposable subscribe1 = ints.subscribe(i -> Log.d(TAG, "testCache: Element B:" + i));
        Log.d(TAG, "testCache: exit");
        mCompositeDisposable.add(subscribe);
        mCompositeDisposable.add(subscribe1);
    }

    public void testInfinite() {
        Observable<BigInteger> naturalNumbers = Observable.create(emitter -> {
            mEmitter = emitter;
            BigInteger i = BigInteger.ZERO;
            while (!emitter.isDisposed()) {
                emitter.onNext(i);
                i = i.add(BigInteger.ONE);
            }
        });
        Disposable subscribe = naturalNumbers.subscribe(x -> Log.d(TAG, "testInfinite: " + x));
        mCompositeDisposable.add(subscribe);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    Observable<String> rxLoad(int id) {
        return Observable.fromCallable(() -> "" + id);
    }
}
