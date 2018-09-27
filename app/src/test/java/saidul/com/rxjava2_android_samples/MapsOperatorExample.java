package saidul.com.rxjava2_android_samples;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 9/28/18.
 */
public class MapsOperatorExample {

    @Test
    public void doSometings() {

        System.out.println(Thread.currentThread() + " start");

        Single<List<APiUser>> apiSingleObserable = Single.fromCallable(new Callable<List<APiUser>>() {
            @Override
            public List<APiUser> call() throws Exception {
                System.out.println(Thread.currentThread() +" Data parsing");

                List<APiUser> list = new ArrayList<>();
                list.add(new APiUser("user1"));
                list.add(new APiUser("user2"));
                list.add(new APiUser("user3"));
                return list;
            }
        });

        System.out.println(Thread.currentThread() + " End");


        apiSingleObserable.
                observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map(new Function<List<APiUser>, List<AppUser>>() {
                    @Override
                    public List<AppUser> apply(List<APiUser> aPiUsers) throws Exception {
                        List<AppUser> appUserList = new ArrayList<>();

                        for (APiUser apiUser : aPiUsers) {
                            appUserList.add(new AppUser(apiUser.getName(), "other"));
                        }

                        return appUserList;
                    }
                })
                .subscribe((new SingleObserver<List<AppUser>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println(Thread.currentThread() + " onSubscribe");
                    }

                    @Override
                    public void onSuccess(List<AppUser> APiUsers) {
                        System.out.println(Thread.currentThread() + " onSuccess");

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(Thread.currentThread() + " onError");
                    }
                }));


    }

    private class APiUser {
        String name;

        public APiUser(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private class AppUser {
        private String mName;
        private String mOther;

        public AppUser(String name, String other) {

            mName = name;
            mOther = other;
        }
    }
}
