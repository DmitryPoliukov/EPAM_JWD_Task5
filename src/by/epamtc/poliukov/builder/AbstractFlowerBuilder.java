package by.epamtc.poliukov.builder;

import by.epamtc.poliukov.entity.Flower;
import by.epamtc.poliukov.exception.ParserException;

import javax.xml.parsers.ParserConfigurationException;
import java.util.HashSet;

public abstract class AbstractFlowerBuilder {
    protected HashSet<Flower> flowerHashSet;

    public AbstractFlowerBuilder() {
        flowerHashSet = new HashSet<>();
    }


    public HashSet<Flower> getFlowerSet() {
        return flowerHashSet;
    }

    public abstract void buildFlowerList(String fileName) throws ParserException, ParserConfigurationException;
}
