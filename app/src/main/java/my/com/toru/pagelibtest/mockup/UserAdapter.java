package my.com.toru.pagelibtest.mockup;

import android.arch.paging.PagedListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import my.com.toru.pagelibtest.R;
import my.com.toru.pagelibtest.mockup.dao.UserMockData;

import static my.com.toru.pagelibtest.mockup.dao.UserMockData.diffCallback;

/**
 * Created by toruchoi on 25/01/2018.
 */

public class UserAdapter extends PagedListAdapter<UserMockData, UserAdapter.UserViewHolder> {

    protected UserAdapter() {
        super(diffCallback);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        UserMockData mockData = getItem(position);
        holder.bind(mockData);
    }

    static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView userName;

        public static UserViewHolder createViewHolder(View view){
            return new UserViewHolder(view);
        }

        public UserViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
        }

        protected void bind(UserMockData userData){
            userName.setText(userData.name);
        }
    }
}