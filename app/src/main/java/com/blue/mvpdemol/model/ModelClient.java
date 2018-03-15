package com.blue.mvpdemol.model;

import com.blue.mvpdemol.present.UserPresent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cj on 2018/1/24.
 */

public class ModelClient {
    public static void loadUsers(UserPresent.OnDataLoadedListener listener) {


        List<User> list=new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new User());
        }

        listener.onDataLoaded(list);

    }
}
