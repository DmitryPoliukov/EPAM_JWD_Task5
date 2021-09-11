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
        private final String COLOR_STEM = "colorStem";
        private final String TEMPERATURE = "temperature";
        private final String SOIL = "soil";
        private final String AVERAGE_FLOWER_SIZE = "averageFlowerSize";
        private final String WATERING = "watering";
        private final String MULTIPLYING = "multiplying";
        private final String IS_PHOTOPHILOUS = "isPhotophilous";

        private boolean flagColorLeaf = false;
        private boolean flagColorStem = false;
        private boolean flagTemperature = false;
        private boolean flagSoil = false;
        private boolean flagAverageFlowerSize = false;
        private boolean flagWatering = false;
        private boolean flagMultiplying = false;
        private boolean flagIsPhotophilous = false;

        private Flower flower;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            switch (qName) {
                case ACANTHUS:
                    flower = new Acanthus();
                    break;
                case CACTUS:
                    flower = new Cactus();
                    break;
                case COLOR_LEAF:
                    flagColorLeaf = true;
                    break;
                case COLOR_STEM:
                    flagColorStem = true;
                    break;
                case TEMPERATURE:
                    flagTemperature = true;
                    break;
                case SOIL:
                    flagSoil = true;
                    break;
                case AVERAGE_FLOWER_SIZE:
                    flagAverageFlowerSize = true;
                    break;
                case WATERING:
                    flagWatering = true;
                    break;
                case MULTIPLYING:
                    flagMultiplying = true;
                    break;
                case IS_PHOTOPHILOUS:
                    flagIsPhotophilous = true;
                    break;
            }

            for (int i = 0; i < attributes.getLength(); i++) {
                attributesCharacters(attributes.getLocalName(i), attributes.getValue(i));
            }
        }

        public void attributesCharacters(String attribute, String value) {

            switch (attribute) {
                case ID:
                    flower.setId(value);
                    break;
                case NAME:
                    flower.setName(value);
                    break;
                case ORIGIN:
                    flower.setOrigin(value);
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if(flagColorLeaf) {
                flower.setColorLeaf(new String(ch, start, length));
                flagColorLeaf = false;
            } else if(flagColorStem) {
                flower.setColorStem(new String(ch, start, length));
                flagColorStem = false;
            } else if (flagTemperature) {
                flower.setTemperature(Integer.parseInt(new String(ch, start, length)));
                flagTemperature = false;
            } else if (flagSoil) {
                flower.setSoil(new String(ch, start, length));
                flagSoil = false;
            } else if(flagAverageFlowerSize) {
                flower.setAverageFlowerSize(Integer.parseInt(new String(ch, start, length)));
                flagAverageFlowerSize = false;
            } else if (flagWatering) {
                flower.setWatering(Integer.parseInt(new String(ch, start, length)));
                flagWatering = false;
            } else if (flagMultiplying) {
                flower.setMultiplying(new String(ch, start, length));
                flagMultiplying = false;
            } else if (flagIsPhotophilous) {
                flower.setPhotophilous(Boolean.parseBoolean(new String(ch, start, length)));
                flagIsPhotophilous = false;
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






