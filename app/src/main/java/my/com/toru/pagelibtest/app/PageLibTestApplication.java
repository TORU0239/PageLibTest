package my.com.toru.pagelibtest.app;

import android.app.Application;

/**
 * Created by toruchoi on 26/01/2018.
 */

public class PageLibTestApplication extends Application {
    private static final String TAG = PageLibTestApplication.class.getSimpleName();

    private static PageLibTestApplication application;

    public static PageLibTestApplication getApplication(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = PageLibTestApplication.this;
    }
}