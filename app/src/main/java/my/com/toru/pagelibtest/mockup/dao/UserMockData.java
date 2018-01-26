package my.com.toru.pagelibtest.mockup.dao;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;

/**
 * Created by toruchoi on 25/01/2018.
 */

@Entity(tableName = "UserTable")
public class UserMockData {
    private static final String TAG = UserMockData.class.getSimpleName();

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    public long userId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "grade")
    public String grade;

    @ColumnInfo(name = "gender")
    public int gender;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMockData that = (UserMockData) o;

        if (userId != that.userId) return false;
        if (age != that.age) return false;
        if (gender != that.gender) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return grade != null ? grade.equals(that.grade) : that.grade == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + gender;
        return result;
    }

    @Override
    public String toString() {
        return "UserMockData{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", grade='" + grade + '\'' +
                ", gender=" + gender +
                '}';
    }

    public static DiffCallback<UserMockData> diffCallback = new DiffCallback<UserMockData>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserMockData oldItem, @NonNull UserMockData newItem) {
            return oldItem.userId == newItem.userId;
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserMockData oldItem, @NonNull UserMockData newItem) {
            return oldItem.equals(newItem);
        }
    };
}