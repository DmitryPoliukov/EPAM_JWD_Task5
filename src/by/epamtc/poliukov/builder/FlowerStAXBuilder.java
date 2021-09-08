package by.epamtc.poliukov.builder;

import by.epamtc.poliukov.entity.Acanthus;
import by.epamtc.poliukov.entity.Cactus;
import by.epamtc.poliukov.entity.Flower;
import by.epamtc.poliukov.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FlowerStAXBuilder extends AbstractFlowerBuilder {

    private Logger logger = LogManager.getLogger(FlowerStAXBuilder.class);

    private final String ID = "id";
    private final String NAME = "name";
    private final String ORIGIN = "origin";
    private final String CACTUS = "cactus";
    private final String ACANTHUS = "acanthus";
    private final String COLOR_LEAF = "colorLeaf";
    private final String COLOR_STEAM = "colorSteam";
    private final String TEMPERATURE = "temperature";
    private final String SOIL = "soil";
    private final String AVERAGE_FLOWER_SIZE = "averageFlowerSize";
    private final String WATERING = "watering";
    private final String MULTIPLYING = "multiplying";
    private final String IS_PHOTOPHILOUS = "isPhotophilous";

    private static final String DEFAULT_ORIGIN = "Unknown";

    @Override
    public void buildFlowerList(String fileName) throws ParserException {
        Flower flower = null;
        FileInputStream inputStream = null;
        logger.info("Start StAX Parser");

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        try {
            inputStream = new FileInputStream(new File(fileName));

            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream);

            /** are there any more elements */
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                /** reading content of tags*/
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();

                    if(startElement.getName().getLocalPart().equals(ACANTHUS)) {
                        flower = new Acanthus();
                        attributeParsing(flower, startElement);
                    } else if (startElement.getName().getLocalPart().equals(CACTUS)) {
                        flower = new Cactus();
                        attributeParsing(flower, startElement);
                    } else if(startElement.getName().getLocalPart().equals(COLOR_LEAF)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        flower.setColorLeaf(xmlEvent.asCharacters().getData());
                    } else if(startElement.getName().getLocalPart().equals(COLOR_STEAM)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        flower.setColorStem(xmlEvent.asCharacters().getData());
                    } else if(startElement.getName().getLocalPart().equals(TEMPERATURE)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        flower.setTemperature(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals(SOIL)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        flower.setSoil(xmlEvent.asCharacters().getData());
                    } else if(startElement.getName().getLocalPart().equals(AVERAGE_FLOWER_SIZE)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        flower.setAverageFlowerSize(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if(startElement.getName().getLocalPart().equals(WATERING)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        flower.setWatering(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if(startElement.getName().getLocalPart().equals(MULTIPLYING)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        flower.setMultiplying(xmlEvent.asCharacters().getData());
                    } else if(startElement.getName().getLocalPart().equals(IS_PHOTOPHILOUS)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        flower.setPhotophilous(Boolean.parseBoolean(xmlEvent.asCharacters().getData()));
                    }
                }

                /** create flower, when closing tag are met */
                if(xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if(endElement.getName().getLocalPart().equals(ACANTHUS) ||
                            endElement.getName().getLocalPart().equals(CACTUS)) {
                        flowerHashSet.add(flower);
                        logger.info(getFlowerSet().toString());
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new ParserException("fail in staxbuilder", e);
        }
    }


    private void attributeParsing(Flower flower, StartElement startElement) {

        String flowerId = startElement.getAttributeByName(new QName(ID)).getValue();
        if(flowerId != null) {
            flower.setId(flowerId);
        }
        String flowerName = startElement.getAttributeByName(new QName(NAME)).getValue();
        if(flowerName != null) {
            flower.setName(flowerName);
        }
        if (startElement.getAttributeByName(new QName(ORIGIN)) == null) {
            flower.setOrigin(DEFAULT_ORIGIN);
        } else {
            String flowerOrigin = startElement.getAttributeByName(new QName(ORIGIN)).getValue();
            if(flowerOrigin != null) {
                flower.setOrigin(flowerOrigin);
            }
        }
    }
}
