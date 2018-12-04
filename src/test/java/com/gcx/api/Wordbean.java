package com.gcx.api;

/**
 * @auther : root
 * @date :  2018/11/9 16:24
 * @description :
 */
public class Wordbean {


    String name;
    String phone;
    String sex;
    String xl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getXl() {
        return xl;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    @Override
    public String toString() {
        return "Wordbean{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", xl='" + xl + '\'' +
                '}';
    }
}
