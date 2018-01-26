package my.com.toru.pagelibtest.mockup;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import my.com.toru.pagelibtest.R;
import my.com.toru.pagelibtest.mockup.dao.UserDao;
import my.com.toru.pagelibtest.mockup.dao.UserMockData;

/**
 * Created by toruchoi on 25/01/2018.
 */

public class UserMockViewModel extends ViewModel {
    private static final String TAG = UserMockViewModel.class.getSimpleName();

    public LiveData<PagedList<UserMockData>> usersList;

    private UserDao userDao;

    public UserMockViewModel(UserDao dao){
        Log.w(TAG, "Constructed!!");
        userDao = dao;
        if(userDao.getTotalUserCount() <= 1){
            userDao.insertAllUsers(dummyDatas());
        }
        usersList = new LivePagedListBuilder<>(dao.getAllUsersInDesc(), 5).build();
    }

    /*
    * Event Handler
    */

    public void onClickForFab(View view){
        Log.w(TAG, "onClickForFab");
        // showing dialog to make a new user data
        showDialog(view.getContext());
    }

    private void showDialog(Context context){
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_user_data, null, false);

        final TextInputLayout nameLayout = dialogView.findViewById(R.id.input_namelayout);
        final TextInputLayout addrLayout = dialogView.findViewById(R.id.input_addresslayout);
        final TextInputLayout agesLayout = dialogView.findViewById(R.id.input_agelayout);

        final TextInputEditText nameEt = dialogView.findViewById(R.id.input_username);
        final TextInputEditText addressEt = dialogView.findViewById(R.id.input_user_address);
        final TextInputEditText ageEt = dialogView.findViewById(R.id.input_user_age);

        final AlertDialog dialog = new AlertDialog.Builder(context)
                                .setTitle("Input user data!")
                                .setView(dialogView)
                                .setPositiveButton("Confirmed",null)
                                .setNegativeButton("Cancel",null)
                                .create();

        dialog.setOnShowListener(dialog1 -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                    .setOnClickListener(v -> {
                        String name = nameEt.getEditableText().toString();
                        String address = addressEt.getEditableText().toString();
                        int age = Integer.parseInt(ageEt.getEditableText().toString());

                        boolean isName = false;
                        boolean isAddr = false;
                        boolean isAges = false;

                        if(name.length() == 0){
                            nameLayout.setErrorEnabled(true);
                            nameLayout.setError("Input Valid Name");
                        }
                        else{
                            nameLayout.setErrorEnabled(false);
                            isName = true;
                        }

                        if(address.length() == 0){
                            addrLayout.setErrorEnabled(true);
                            addrLayout.setError("Not Valid Address");
                        }
                        else {
                            addrLayout.setErrorEnabled(false);
                            isAddr = true;
                        }

                        if(age <=0){
                            agesLayout.setErrorEnabled(true);
                            agesLayout.setError("Are you kidding me?");
                        }
                        else{
                            agesLayout.setErrorEnabled(false);
                            isAges = true;
                        }

                        if(isName && isAddr && isAges){
                            UserMockData newUser = new UserMockData();
                            newUser.name = name;
                            newUser.address = address;
                            newUser.age = age;
                            userDao.insertUser(newUser);
                            Toast.makeText(context, "Success!!", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
                        }
                    });
        });
        dialog.show();
    }

    // TODO: calling REST API, updating Database.
    private List<UserMockData> dummyDatas(){
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