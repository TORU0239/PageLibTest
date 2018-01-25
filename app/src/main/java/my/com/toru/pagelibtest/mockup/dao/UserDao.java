package my.com.toru.pagelibtest.mockup.dao;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by toruchoi on 25/01/2018.
 */

@Dao
public interface UserDao {
    // Create, Read, Update, Delete

    // inserting user list to database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAllUsers(List<UserMockData> users);

    // insert each item to record
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(UserMockData... user);

    // update user
    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateUser(UserMockData... user);

    // delete user
    @Delete
    public void deleteUser(UserMockData... user);

    // querying all users
    @Query("SELECT * FROM UserTable ")
    public abstract DataSource.Factory<Integer, UserMockData> getAllUsers();

    // querying count of users
    @Query("SELECT COUNT(*) from UserTable")
    int getTotalUserCount();

    @Query("SELECT * FROM UserTable WHERE name LIKE :name")
    public abstract DataSource.Factory<Integer, UserMockData> getUsersFromName(String name);
}