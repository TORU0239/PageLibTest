package my.com.toru.pagelibtest.real.ui;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import my.com.toru.pagelibtest.R;
import my.com.toru.pagelibtest.real.model.GithubUser;

/**
 * Created by toruchoi on 29/01/2018.
 */

public class GithubUserAdapter extends PagedListAdapter<GithubUser, GithubUserAdapter.GithubUserHolder> {
    private static final String TAG = GithubUserAdapter.class.getSimpleName();

    private static DiffCallback<GithubUser> callback = new DiffCallback<GithubUser>() {
        @Override
        public boolean areItemsTheSame(@NonNull GithubUser oldItem, @NonNull GithubUser newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull GithubUser oldItem, @NonNull GithubUser newItem) {
            return oldItem.equals(newItem);
        }
    };

    public GithubUserAdapter() {
        super(callback);
    }

    @Override
    public GithubUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_user,parent, false);
        return new GithubUserHolder(view);
    }

    @Override
    public void onBindViewHolder(GithubUserHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    static class GithubUserHolder extends RecyclerView.ViewHolder{

        public GithubUserHolder(View itemView) {
            super(itemView);
        }

        protected void bindData(GithubUser user){
            Log.w(TAG, "user data::" + user.toString());
        }
    }
}
