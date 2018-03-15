package com.blue.mvpdemol.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blue.mvpdemol.R;
import com.blue.mvpdemol.model.User;

import java.util.List;

/**
 * Created by cj on 2018/1/24.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.Holder> {


    private List<User> data;
    public Context context;

    public UsersAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    class Holder extends RecyclerView.ViewHolder{
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
