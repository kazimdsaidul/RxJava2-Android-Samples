package saidul.com.rxjava2_android_samples;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 9/27/18.
 */
public class Example1 {

    @Test
    public void  helloTest(){
        System.out.println(Thread.currentThread());

        getObservable()
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .subscribe(getObserver());

    }

    private Observable<String> getObservable(){

        System.out.println(Thread.currentThread()+ "data maker");

        return Observable.just("Cricket", "Footblall");
    }

    public Observer<String> getObserver(){
        return  new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

                System.out.println(Thread.currentThread()+" Disposable ");
            }

            @Override
            public void onNext(String s) {
                System.out.println(Thread.currentThread()+" onNext "+s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(Thread.currentThread()+" onError");
            }

            @Override
            public void onComplete() {
                System.out.println(Thread.currentThread()+"  onComplete");
            }
        };
    }


}
