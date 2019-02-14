package com.yuan.greendaodemo2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2019/2/14 0014.
 */
@Entity
public class StudentBean {
    @Id(autoincrement =true)
   public long id;
   public String name;
   public int age;
   public String sex;
public String getSex() {
    return this.sex;
}
public void setSex(String sex) {
    this.sex = sex;
}
public int getAge() {
    return this.age;
}
public void setAge(int age) {
    this.age = age;
}
public String getName() {
    return this.name;
}
public void setName(String name) {
    this.name = name;
}
public long getId() {
    return this.id;
}
public void setId(long id) {
    this.id = id;
}
@Generated(hash = 331884957)
public StudentBean(long id, String name, int age, String sex) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.sex = sex;
}
@Generated(hash = 2097171990)
public StudentBean() {
}
}
