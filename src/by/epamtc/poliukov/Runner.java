package by.epamtc.poliukov;

import by.epamtc.poliukov.builder.FlowerDOMBuilder;
import by.epamtc.poliukov.builder.FlowerSAXBuilder;
import by.epamtc.poliukov.builder.FlowerStAXBuilder;
import by.epamtc.poliukov.exception.ParserException;



public class Runner {
    public static void main(String[] args) {
        FlowerDOMBuilder flowersDOMBuilder = new FlowerDOMBuilder();
        String fileName = "src/by/epamtc/poliukov/resources/flowers.xml";
        try {
            flowersDOMBuilder.buildFlowerList(fileName);
        } catch (ParserException e) {
            e.printStackTrace();
        }
        System.out.println(flowersDOMBuilder.getFlowerSet().toString());
        System.out.println("____________________________________");
        FlowerSAXBuilder flowerSaxBuilder = new FlowerSAXBuilder();
        try {
            flowerSaxBuilder.buildFlowerList("src/by/epamtc/poliukov/resources/flowers.xml");
        } catch (ParserException e) {
            e.printStackTrace();
        }
        System.out.println(flowerSaxBuilder.getFlowerSet().toString());
        System.out.println("___________________");
        FlowerStAXBuilder flowerStAXBuilder = new FlowerStAXBuilder();
        try {
            flowerStAXBuilder.buildFlowerList("src/by/epamtc/poliukov/resources/flowers.xml");
        } catch (ParserException e) {
            e.printStackTrace();
        }
        System.out.println(flowerStAXBuilder.getFlowerSet().toString());
    }
}
