package io.github.caoshen.androidadvance.kotlin.coroutine;

import android.os.SystemClock;

public class SuspendFunction {

    public void getAllFeeds() {
        getUserInfo(new Callback() {
            @Override
            public void onSuccess(String user) {
                if (user != null) {
                    System.out.println(user);
                    getFriendList(user, new Callback() {
                        @Override
                        public void onSuccess(String friendList) {
                            if (friendList != null) {
                                System.out.println(friendList);
                                getFeedList(friendList, new Callback() {
                                    @Override
                                    public void onSuccess(String feedList) {
                                        if (feedList != null) {
                                            System.out.println(feedList);
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }

    private void getFeedList(String friendList, Callback callback) {
        new Thread(() -> {
            SystemClock.sleep(1000L);
            if (callback != null) {
                callback.onSuccess("feedList");
            }
        }).start();
    }

    private void getFriendList(String user, Callback callback) {
        new Thread(() -> {
            SystemClock.sleep(1000L);
            if (callback != null) {
                callback.onSuccess("friendList");
            }
        }).start();
    }

    private void getUserInfo(Callback callback) {
        new Thread(() -> {
            SystemClock.sleep(1000L);
            if (callback != null) {
                callback.onSuccess("user");
            }
        }).start();
    }

    public interface Callback {
        void onSuccess(String response);
    }
}
