package by.epamtc.poliukov.builder;

import by.epamtc.poliukov.entity.Acanthus;
import by.epamtc.poliukov.entity.Cactus;
import by.epamtc.poliukov.entity.Flower;
import by.epamtc.poliukov.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class FlowerSAXBuilder extends AbstractFlowerBuilder {
    private Logger logger = LogManager.getLogger(FlowerSAXBuilder.class);
    private Handler handler = new Handler();

    public FlowerSAXBuilder() {
    }

    private class Handler extends DefaultHandler {

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

        private boolean flagColorLeaf = false;
        private boolean flagColorSteam = false;
        private boolean flagTemperature = false;
        private boolean flagSoil = false;
        private boolean flagAverageFlowerSize = false;
        private boolean flagWatering = false;
        private boolean flagMultiplying = false;
        private boolean flagIsPhotophilous = false;

        private Flower flower;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals(ACANTHUS)) {
                flower = new Acanthus();
            } else if (qName.equals(CACTUS)) {
                flower = new Cactus();
            } else if (qName.equals(COLOR_LEAF)) {
                flagColorLeaf = true;
            } else if (qName.equals(COLOR_STEAM)) {
                flagColorSteam = true;
            } else if (qName.equals(TEMPERATURE)) {
                flagTemperature = true;
            } else if (qName.equals(SOIL)) {
                flagSoil = true;
            } else if (qName.equals(AVERAGE_FLOWER_SIZE)) {
                flagAverageFlowerSize = true;
            } else if (qName.equals(WATERING)) {
                flagWatering = true;
            } else if (qName.equals(MULTIPLYING)) {
                flagMultiplying = true;
            } else if (qName.equals(IS_PHOTOPHILOUS)) {
                flagIsPhotophilous = true;
            }

            for (int i = 0; i < attributes.getLength(); i++) {
                attributesCharacters(attributes.getLocalName(i), attributes.getValue(i));
            }
        }

        public void attributesCharacters(String attribute, String value) {

            if (attribute.equals(ID)) {
                flower.setId(value);
            } else if (attribute.equals(NAME)) {
                flower.setName(value);
            } else if (attribute.equals(ORIGIN)) {
                flower.setOrigin(value);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals(ACANTHUS) || (qName.equals(CACTUS))) {
                flowerHashSet.add(flower);
                logger.info(getFlowerSet().toString());
                logger.info("\nParsing ended");
            }
        }
    }


    @Override
    public void buildFlowerList(String fileName) throws ParserException {
        logger.info("Start SAX Parser");
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(fileName, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ParserException("Fail in SAXBuilder", e);
        }
    }
}






