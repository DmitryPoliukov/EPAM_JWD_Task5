package by.epamtc.poliukov.builder;

import by.epamtc.poliukov.entity.Acanthus;
import by.epamtc.poliukov.entity.Cactus;
import by.epamtc.poliukov.entity.Flower;
import by.epamtc.poliukov.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class FlowerDOMBuilder extends AbstractFlowerBuilder {

    private Logger logger = LogManager.getLogger(FlowerDOMBuilder.class);

    private final String ID = "id";
    private final String NAME = "name";
    private final String ORIGIN = "origin";
    private final String CACTUS = "cactus";
    private final String ACANTHUS = "acanthus";
    private final String COLOR_LEAF = "colorLeaf";
    private final String COLOR_STEM = "colorStem";
    private final String TEMPERATURE = "temperature";
    private final String SOIL = "soil";
    private final String AVERAGE_FLOWER_SIZE = "averageFlowerSize";
    private final String WATERING = "watering";
    private final String MULTIPLYING = "multiplying";
    private final String IS_PHOTOPHILOUS = "isPhotophilous";

    @Override
    public void buildFlowerList(String fileName) throws ParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(fileName);
            document.getDocumentElement().normalize();

            //getting a list of children  elements <flowers>

            NodeList acanthusList = document.getElementsByTagName(ACANTHUS);
            for (int i = 0; i < acanthusList.getLength(); i++) {
                Flower acanthus = new Acanthus();
                buildFlower(acanthusList, i, acanthus);
            }
            NodeList cactusList = document.getElementsByTagName(CACTUS);
            for (int i = 0; i < cactusList.getLength(); i++) {
                Flower cactus = new Cactus();
                buildFlower(cactusList, i, cactus);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ParserException("fail in domBuilder", e);
        }
        logger.info(getFlowerSet());
        logger.info("End of DOM Parser");
    }



    public void buildFlower(NodeList flowerList, int i, Flower flower) {

        //filling the object flower and getting text content
        Node node = flowerList.item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element flowerElement = (Element) node;

            flower.setAverageFlowerSize(Integer.parseInt(flowerElement.getElementsByTagName(AVERAGE_FLOWER_SIZE).
                    item(0).getTextContent()));
            flower.setColorLeaf(flowerElement.getElementsByTagName(COLOR_LEAF).item(0).getTextContent());
            flower.setColorStem(flowerElement.getElementsByTagName(COLOR_STEM).item(0).getTextContent());
            flower.setSoil(flowerElement.getElementsByTagName(SOIL).item(0).getTextContent());
            flower.setTemperature(Integer.parseInt(flowerElement.getElementsByTagName(TEMPERATURE).item(0).getTextContent()));
            flower.setWatering(Integer.parseInt(flowerElement.getElementsByTagName(WATERING).item(0).getTextContent()));
            flower.setPhotophilous(Boolean.parseBoolean(flowerElement.getElementsByTagName(IS_PHOTOPHILOUS).item(0).getTextContent()));
            flower.setMultiplying(flowerElement.getElementsByTagName(MULTIPLYING).item(0).getTextContent());

            if (node.hasAttributes()) {
                NamedNodeMap nodeMap = node.getAttributes();
                for (int j = 0; j < nodeMap.getLength(); j++) {
                    Node currentNode = nodeMap.item(j);
                    if (currentNode.getNodeName().equals(ID)) {
                        flower.setId(currentNode.getNodeValue());
                    } else {
                        if (currentNode.getNodeName().equals(NAME)) {
                            flower.setName(currentNode.getNodeValue());
                        } else {
                            if (currentNode.getNodeName().equals(ORIGIN)) {
                                flower.setOrigin(currentNode.getNodeValue());
                            }
                        }

                    }
                }
            }
        } flowerHashSet.add(flower);
        logger.info(getFlowerSet());
    }

}
