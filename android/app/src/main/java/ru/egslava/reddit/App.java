package ru.egslava.reddit;

import android.app.Application;

import java.util.Collections;

import ru.egslava.reddit.data.DB;
import ru.egslava.reddit.data.PostEntity;

/**
 * Created by egslava@gmail.com on 21/06/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DB.INSTANCE.fillDemo();
    }
}
