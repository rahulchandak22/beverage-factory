package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import entity.Menu;

import java.io.File;
import java.io.IOException;

public class BeverageFactory {
    public static Menu populateMenu() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(new File("src/main/resources/data.yml"), Menu.class);
    }
}
