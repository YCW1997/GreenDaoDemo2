package com.yuan.greendaodemo2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2019/2/14 0014.
 */

public class DBManager {

    private final static String dbName = "test_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    public static DBManager getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    public SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    //插入一条记录
    public void insertUser(StudentBean studentBean) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        StudentBeanDao studentBeanDao = daoSession.getStudentBeanDao();
        studentBeanDao.insert(studentBean);
    }

    //插入数据
    public void insertUserList(List<StudentBean> studentBeans){
        if (studentBeans==null||studentBeans.isEmpty()){
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        StudentBeanDao studentBeanDao = daoSession.getStudentBeanDao();
        studentBeanDao.insertInTx(studentBeans);
    }

    //删除一条记录
    public void deleteUser(StudentBean studentBean) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        StudentBeanDao studentBeanDao = daoSession.getStudentBeanDao();
        studentBeanDao.delete(studentBean);
    }

    //更新一条记录
    public void updateUser(StudentBean studentBean) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        StudentBeanDao studentBeanDao = daoSession.getStudentBeanDao();
        studentBeanDao.update(studentBean);
    }

    //查询用户列表
    public List<StudentBean> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        StudentBeanDao studentBeanDao = daoSession.getStudentBeanDao();
        QueryBuilder<StudentBean> qb = studentBeanDao.queryBuilder();
        List<StudentBean> list = qb.list();
        return list;
    }

    //查询用户列表
    public List<StudentBean> queryUserList(long id) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        StudentBeanDao userDao = daoSession.getStudentBeanDao();
        QueryBuilder<StudentBean> qb = userDao.queryBuilder();
        qb.where(StudentBeanDao.Properties.Age.gt(id)).orderAsc(StudentBeanDao.Properties.Id);
        List<StudentBean> list = qb.list();
        return list;
    }

}