package com.aor.refactoring.example4;

public class People {
    protected final String name;
    protected final String phone;

    public People(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}
