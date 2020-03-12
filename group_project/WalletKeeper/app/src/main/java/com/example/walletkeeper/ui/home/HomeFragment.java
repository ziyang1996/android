package com.example.walletkeeper.ui.home;

import java.util.Calendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.walletkeeper.DB.Database;
import com.example.walletkeeper.MainActivity;
import com.example.walletkeeper.R;
import com.example.walletkeeper.Time;

public class HomeFragment extends Fragment {

    private EditText money,comments;
    private Button button;

    private ImageView p11,p12,p13,p21,p22,p23,p31,p32,p33;
    private int choose;

    //数据库创建或打开
    private Database database;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


        money=getActivity().findViewById(R.id.money);
        comments=getActivity().findViewById(R.id.comments);
        button=getActivity().findViewById((R.id.button));
        p11=getActivity().findViewById(R.id.p11);
        p12=getActivity().findViewById(R.id.p12);
        p13=getActivity().findViewById(R.id.p13);
        p21=getActivity().findViewById(R.id.p21);
        p22=getActivity().findViewById(R.id.p22);
        p23=getActivity().findViewById(R.id.p23);
        p31=getActivity().findViewById(R.id.p31);
        p32=getActivity().findViewById(R.id.p32);
        p33=getActivity().findViewById(R.id.p33);
        //初始化
        Origin();
        choose = 0;
        //单选图片
        p11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(v == p11)
                {
                    Origin();
                    if (choose!=1)
                    {
                        choose=1;
                        p11.setImageDrawable(getResources().getDrawable(R.drawable.try1));
                    }
                    else
                    {
                        choose=0;
                    }
                }
            }
        });
        p12.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(v == p12)
                {
                    Origin();
                    if (choose!=2)
                    {
                        choose=2;
                        p12.setImageDrawable(getResources().getDrawable(R.drawable.try1));
                    }
                    else
                    {
                        choose=0;
                    }
                }
            }
        });
        p13.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(v == p13) {
                    Origin();
                    if (choose!=3)
                    {
                        choose=3;
                        p13.setImageDrawable(getResources().getDrawable(R.drawable.try1));
                    }
                    else
                    {
                        choose=0;
                    }
                }
            }
        });
        p21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(v == p21)
                {
                    Origin();
                    if (choose!=4)
                    {
                        choose=4;
                        p21.setImageDrawable(getResources().getDrawable(R.drawable.try1));
                    }
                    else
                    {
                        choose=0;
                    }
                }
            }
        });
        p22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            { if(v == p22) {
                Origin();
                if (choose!=5)
                {
                    choose=5;
                    p22.setImageDrawable(getResources().getDrawable(R.drawable.try1));
                }
                else
                {
                    choose=0;
                }
            }
            }
        });
        p23.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(v == p23)
                {
                    Origin();
                    if (choose!=6)
                    {
                        choose=6;
                        p23.setImageDrawable(getResources().getDrawable(R.drawable.try1));
                    }
                    else
                    {
                        choose=0;
                    }
                }
            }
        });
        p31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            { if(v == p31) {
                Origin();
                if (choose!=7)
                {
                    choose=7;
                    p31.setImageDrawable(getResources().getDrawable(R.drawable.try1));
                }
                else
                {
                    choose=0;
                }
            } }});
        p32.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) { if(v == p32) {
                Origin();
                if (choose!=8) {
                    choose=8;
                    p32.setImageDrawable(getResources().getDrawable(R.drawable.try1));
                }
                else {
                    choose=0;
                }
            } }
        });
        p33.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            { if(v == p33) {
                Origin();
                if (choose!=9)
                {
                    choose=9;
                    p33.setImageDrawable(getResources().getDrawable(R.drawable.try1));
                }
                else
                {
                    choose=0;
                }
            } }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (v==button)
                {
                    String num=money.getText().toString();
                    if (choose==0|| num.equals(""))
                    {
                        String s="Wrong!";
                        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                    }
                    else
                    {

                        Time t=new Time();
                        int m=Integer.parseInt(money.getText().toString());
                        String c=comments.getText().toString();

                        String time=""+t.year+"年"+t.month+"月"+t.day+"日";
                        time=time+"\n"+t.hour+":"+t.minute+":"+t.second;
                        time=time+"\n"+money.getText()+"元\n"+comments.getText();

                        //打开数据库
                        database= new Database(getActivity(),"account", null, 4 );
                        SQLiteDatabase db=database.getWritableDatabase();
                        Insert(db,t,m,c);


                        //Toast.makeText(getActivity(), "111", Toast.LENGTH_LONG).show();
                        Cursor cursor = db.query("account", null,null,null, null, null, null);
                        boolean succeed = cursor.moveToFirst();  //从数据库第一行开始查
                        Toast.makeText(getActivity(), "222", Toast.LENGTH_LONG).show();
                        if (succeed)
                        {
                                double money = cursor.getDouble(cursor.getColumnIndex("money"));
                                String comments = cursor.getString(cursor.getColumnIndex("comment"));
                                int type = cursor.getInt(cursor.getColumnIndex("type"));
                                String s=""+money+"元"+"\n"+comments+"\n"+"type="+type;
                                Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();

                        }
                        cursor.close();
                    }
                }
            }
        });
    }

    private void Origin()
    {
        p11.setImageDrawable(getResources().getDrawable(R.drawable.try2));
        p12.setImageDrawable(getResources().getDrawable(R.drawable.try2));
        p13.setImageDrawable(getResources().getDrawable(R.drawable.try2));
        p21.setImageDrawable(getResources().getDrawable(R.drawable.try2));
        p22.setImageDrawable(getResources().getDrawable(R.drawable.try2));
        p23.setImageDrawable(getResources().getDrawable(R.drawable.try2));
        p31.setImageDrawable(getResources().getDrawable(R.drawable.try2));
        p32.setImageDrawable(getResources().getDrawable(R.drawable.try2));
        p33.setImageDrawable(getResources().getDrawable(R.drawable.try2));
    }
    private void Insert(SQLiteDatabase db,Time t,int m,String c)
    {
        ContentValues values = new ContentValues();
        values.put("year",t.year);
        values.put("month",t.month);
        values.put("day",t.day);
        values.put("hour",t.hour);
        values.put("minute",t.minute);
        values.put("second",t.second);
        values.put("money",m);
        values.put("type",choose);
        values.put("comment",c);
        db.insert("account", null, values);
        values.clear();
        //Toast.makeText(getActivity(),"good",Toast.LENGTH_LONG).show();
    }

}