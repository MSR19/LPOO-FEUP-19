package com.aor.refactoring.example4;

import java.util.HashSet;
import java.util.Set;

public class Supervisor extends Worker {
    private Set<Worker> supervisee = new HashSet<>();
    
    public Supervisor(String name, String phone, String username, String password) {
        super(name, phone, username, password);
    }
    
    public void addSupervisee(Worker worker) {
        supervisee.add(worker);
    }

    public boolean isSupervisee(Worker worker) {
        return supervisee.contains(worker);
    }
}
