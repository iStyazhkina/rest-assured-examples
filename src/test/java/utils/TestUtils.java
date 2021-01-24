package utils;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class TestUtils {

    private static FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());

    public static String readFromFile(String path) {
        try (FileInputStream fis = new FileInputStream(new File(path))) {
            return IOUtils.toString(fis, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getRandomEmail() {
        return fakeValuesService.bothify("????##@test.com");
    }
}
