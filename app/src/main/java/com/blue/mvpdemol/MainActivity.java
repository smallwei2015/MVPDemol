package com.blue.mvpdemol;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.blue.mvpdemol.adapter.UsersAdapter;
import com.blue.mvpdemol.model.User;
import com.blue.mvpdemol.present.UserPresent;
import com.blue.mvpdemol.view.UserView;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;

import java.util.List;

public class MainActivity extends MvpLceActivity<SwipeRefreshLayout, List<User>, UserView, UserPresent> implements UserView {

    public RecyclerView recyclerView;
    private UsersAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        contentView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(true);
            }
        });

        recyclerView = ((RecyclerView) findViewById(R.id.recyclerView));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new UsersAdapter(this);
        recyclerView.setAdapter(mAdapter);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //enterPictureInPictureMode();

            Intent intent = new Intent(this, ViewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);
            startActivity(intent);
        }


        Log.w("vode","this is added code");
    }

    @NonNull
    @Override
    public UserPresent createPresenter() {
        return new UserPresent();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return pullToRefresh ? "load data error" : "load data error retry";
    }

    @Override
    public void setData(List<User> data) {
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        contentView.setRefreshing(true);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }
}
