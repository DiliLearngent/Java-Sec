package org.dili.proxy;

public class Ouc implements University{
    @Override
    public String getName() {
        return "OUC";
    }

    @Override
    public void Info(String place) {
        System.out.println("I am located in " + place);
    }
}
