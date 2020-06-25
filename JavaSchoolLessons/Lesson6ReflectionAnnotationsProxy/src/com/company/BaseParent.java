package com.company;

import java.util.*;

public class BaseParent {
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "Tuesday";
    public final String WEDNESDAY = "WEDNESDAY";
    public static final int CONST_INT = 999;

    private UUID id;
    private String name;

    public BaseParent(String name) {
        id = UUID.randomUUID();
        this.name = name;
    }

    public void BaseMethod(int value) {
    }

    private void BaseMethodPri(int value) {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
