package my.com.toru.pagelibtest.mockup.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by toruchoi on 25/01/2018.
 */

@Database(entities = {UserMockData.class}, version = 1, exportSchema = false)
public abstract class UserDB extends RoomDatabase {
    private static final String TAG = UserDB.class.getSimpleName();

    public abstract UserDao getUserDao();

    private static UserDB instance;

    public synchronized static UserDB get(Context ctx){
        if(instance == null){
            instance = Room.databaseBuilder(ctx.getApplicationContext(), UserDB.class, "UserDatabase")
                        .fallbackToDestructiveMigration()
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadExecutor().execute(() -> get(ctx.getApplicationContext()).getUserDao().insertAllUsers(dummyDatas()));
                            }
                        })
                        .build();
        }
        return instance;
    }
    public static void destroyDB(){
        instance = null;
    }

    private static List<UserMockData> dummyDatas(){
        Log.w(TAG, "dummyDatas");
        LinkedList<UserMockData> dataList = new LinkedList<>();

        UserMockData data = new UserMockData();
        data.name = "Toru";
        data.age = 30;
        data.address = "KLCC";
        dataList.add(data);

        data = new UserMockData();
        data.name = "Sean";
        data.age = 40;
        data.address = "Desa Park";
        dataList.add(data);

        data = new UserMockData();
        data.name = "Kar Heng";
        data.age = 34;
        data.address = "Glennmarie";
        dataList.add(data);

        data = new UserMockData();
        data.name = "Su Lynn";
        data.age = 30;
        data.address = "Subang";
        dataList.add(data);

        data = new UserMockData();
        data.name = "Natalia";
        data.age = 33;
        data.address = "Dang Wangi";
        dataList.add(data);

        data = new UserMockData();
        data.name = "Austeja";
        data.age = 30;
        data.address = "Bangsar";
        dataList.add(data);

        return dataList;
    }
}