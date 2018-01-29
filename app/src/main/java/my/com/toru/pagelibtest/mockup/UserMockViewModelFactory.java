package my.com.toru.pagelibtest.mockup;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import my.com.toru.pagelibtest.mockup.dao.UserDao;

/**
 * Created by toruchoi on 25/01/2018.
 */

public class UserMockViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static final String TAG = UserMockViewModelFactory.class.getSimpleName();

    private UserDao dao;

    public UserMockViewModelFactory(UserDao dao) {
        this.dao = dao;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(UserMockViewModel.class)){
            return (T)new UserMockViewModel(dao);
        }
        throw new IllegalStateException("Wrong View Model class is assigned!!");
    }
}
