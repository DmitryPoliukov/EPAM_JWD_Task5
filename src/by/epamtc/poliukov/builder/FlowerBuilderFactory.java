package by.epamtc.poliukov.builder;

import by.epamtc.poliukov.exception.IncorrectInputException;

public class FlowerBuilderFactory {
    public AbstractFlowerBuilder createFlowerBuilder(String type) throws IncorrectInputException {

        BuilderTypes builderTypes = BuilderTypes.valueOf(type);

        switch (builderTypes) {
            case DOM:
                return new FlowerDOMBuilder();
            case SAX:
                return new FlowerSAXBuilder();
            case STAX:
                return new FlowerStAXBuilder();
            default:
                throw new IncorrectInputException(type + "is incorrect");
        }
    }
}
