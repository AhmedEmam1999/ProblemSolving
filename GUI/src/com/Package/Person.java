package com.Package;

public class Person {
    private int ID,Age;
    private String Name,Gender,Phone;

    public Person() {
    }

    public Person(int ID, String phone, int age, String name, String gender) {
        this.ID = ID;
        Phone = phone;
        Age = age;
        Name = name;
        Gender = gender;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPhone() {
        return String.valueOf(Phone);
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
