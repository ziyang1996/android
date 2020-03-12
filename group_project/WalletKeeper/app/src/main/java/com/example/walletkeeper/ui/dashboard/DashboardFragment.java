package com.example.walletkeeper.ui.dashboard;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.walletkeeper.DB.Database;
import com.example.walletkeeper.R;
import com.example.walletkeeper.ui.dashboard.DashboardViewModel;
import com.example.walletkeeper.ui.notifications.NotificationsViewModel;

public class DashboardFragment extends Fragment {


    //数据库创建
    private Database database;
    private SQLiteDatabase db;

    private Button button1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        database = new Database(getActivity(), "account", null, 4);
        db = database.getReadableDatabase();
        button1=getActivity().findViewById(R.id.button1);
        //从数据库查询获取所有数据
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button1) {
                    //Toast.makeText(getActivity(), "111", Toast.LENGTH_LONG).show();

                    Cursor cursor = db.query("account", null, null, null, null, null, null);
                    boolean succeed = cursor.moveToFirst();  //从数据库第一行开始查
                    //Toast.makeText(getActivity(), "222", Toast.LENGTH_LONG).show();
                    if (succeed) {
                        Toast.makeText(getActivity(), "333", Toast.LENGTH_LONG).show();

                        double money = cursor.getDouble(cursor.getColumnIndex("money"));
                        String comments = cursor.getString(cursor.getColumnIndex("comment"));
                        int type = cursor.getInt(cursor.getColumnIndex("type"));
                        String s = "" + money + "元" + "\n" + comments + "\n" + "type=" + type;
                        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();

                        //查完一条之后调用cursor.moveToNext()把cursor的位置移动到下一条

                    }
                    cursor.close();
                }
            }
        });
    }
}