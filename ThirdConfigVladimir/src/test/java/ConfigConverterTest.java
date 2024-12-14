import org.example.ConfigConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigConverterTest {


    @Test
    public void testInvalidYaml() {
        Map<String, Object> invalidYaml = new HashMap<>();
        invalidYaml.put("key1", null); // Некорректное значение

        assertThrows(Exception.class, () -> ConfigConverter.convertToCustomConfig(invalidYaml));
    }
}
