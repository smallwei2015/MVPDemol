package com.blue.mvpdemol.present;

import com.blue.mvpdemol.model.ModelClient;
import com.blue.mvpdemol.model.User;
import com.blue.mvpdemol.view.UserView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

/**
 * Created by cj on 2018/1/24.
 */

public class UserPresent extends MvpBasePresenter<UserView> {
    public interface OnDataLoadedListener {
        void onDataLoaded(List<User> users);
        void onDataLoadFailed(Throwable e);
    }
    public void loadData(final boolean pullToRefresh) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading(pullToRefresh);
        ModelClient.loadUsers(new OnDataLoadedListener () {
            @Override
            public void onDataLoaded(List<User> users) {
                if (!isViewAttached()) {
                    return;
                }
                getView().setData(users);
                getView().showContent();
            }
            @Override
            public void onDataLoadFailed(Throwable e) {
                if (!isViewAttached()) {
                    return;
                }
                getView().showError(e, pullToRefresh);
            }
        });
    }

}
