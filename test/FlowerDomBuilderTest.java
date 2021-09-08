import by.epamtc.poliukov.builder.AbstractFlowerBuilder;
import by.epamtc.poliukov.builder.FlowerBuilderFactory;
import by.epamtc.poliukov.exception.IncorrectInputException;
import by.epamtc.poliukov.exception.ParserException;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;

public class FlowerDomBuilderTest {
    @Test
    public void parse() throws IncorrectInputException, ParserException, ParserConfigurationException {
        FlowerBuilderFactory flowerBuilderFactory = new FlowerBuilderFactory();
        AbstractFlowerBuilder abstractFlowerBuilder = flowerBuilderFactory.createFlowerBuilder("DOM");
        abstractFlowerBuilder.buildFlowerList("E:\\JWD\\Tasks_java\\EPAM_JWD_task5\\src\\by\\epamtc\\poliukov\\resources\\flowers.xml");

        Assert.assertEquals(abstractFlowerBuilder.getFlowerSet().size(), 16);
    }
}