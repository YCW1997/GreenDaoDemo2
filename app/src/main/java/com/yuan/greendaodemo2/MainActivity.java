package com.yuan.greendaodemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBManager dbManager = DBManager.getmInstance(this);
        for (int i = 0; i < 100; i++) {
            StudentBean studentBean = new StudentBean();
            studentBean.setId(i);
            studentBean.setAge(i + 1);
            studentBean.setName("张三" + i );
            studentBean.setSex("男");
            dbManager.insertUser(studentBean);
        }
        List<StudentBean> studentList = dbManager.queryUserList();
        for (StudentBean studentBean : studentList) {
            Log.e("TAG", "queryUserList--before-->"
                    + studentBean.getId()
                    + "--" + studentBean.getName()
                    +"--"+studentBean.getAge()
                    +"--"+studentBean.getSex());
            if (studentBean.getSex() == "男") {
                dbManager.deleteUser(studentBean);
            }
            if (studentBean.getName() == "张三") {
                studentBean.setName("李四");
                dbManager.updateUser(studentBean);
            }
        }
        studentList = dbManager.queryUserList(20);
        for (StudentBean studentBean : studentList) {
            Log.e("TAG", "queryUserList--after--->" + studentBean.getId() + "---" + studentBean.getName()+"--"+studentBean.getAge());
        }

    }
}
