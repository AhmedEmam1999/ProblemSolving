package com.Package;

public class Department {
    private int Depart_ID;
    private String Depart_Name;

    public Department() {
    }

    public Department(int depart_ID, String depart_Name) {
        Depart_ID = depart_ID;
        Depart_Name = depart_Name;
    }

    public int getDepart_ID() {
        return Depart_ID;
    }

    public void setDepart_ID(int depart_ID) {
        Depart_ID = depart_ID;
    }

    public String getDepart_Name() {
        return Depart_Name;
    }

    public void setDepart_Name(String depart_Name) {
        Depart_Name = depart_Name;
    }
}
