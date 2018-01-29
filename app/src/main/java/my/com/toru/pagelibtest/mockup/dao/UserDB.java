package my.com.toru.pagelibtest.mockup.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by toruchoi on 25/01/2018.
 */

@Database(entities = {UserMockData.class}, version = 1)
public abstract class UserDB extends RoomDatabase {
    private static final String TAG = UserDB.class.getSimpleName();

    public abstract UserDao getUserDao();

    private static UserDB instance;

    public synchronized static UserDB get(Context ctx){
        if(instance == null){
            instance = Room.databaseBuilder(ctx.getApplicationContext(), UserDB.class, "UserDatabase")
                        .build();
        }
        return instance;
    }
    public static void destroyDB(){
        instance = null;
    }
}