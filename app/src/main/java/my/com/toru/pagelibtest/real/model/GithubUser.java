package my.com.toru.pagelibtest.real.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;

import com.google.gson.annotations.SerializedName;

/**
 * Created by toruchoi on 29/01/2018.
 */

@Entity(tableName = "GithubUser")
public class GithubUser {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_idd")
    public long userId;

    @SerializedName("login")
    @ColumnInfo(name = "first_name")
    public String firstName;
    public String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GithubUser user = (GithubUser) o;

        if (userId != user.userId) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null)
            return false;
        return address != null ? address.equals(user.address) : user.address == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static DiffCallback<GithubUser> callback = new DiffCallback<GithubUser>() {
        @Override
        public boolean areItemsTheSame(@NonNull GithubUser oldItem, @NonNull GithubUser newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull GithubUser oldItem, @NonNull GithubUser newItem) {
            return oldItem.equals(newItem);
        }
    };
}