package org.lytsiware.core.jackson.component;

public class NoOpCard implements Card {


    private int value;

    private Type type;


    public NoOpCard(int value, Type type) {
        this.value = value;
        this.type = type;
    }

    public NoOpCard() {
    }

    public int getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
