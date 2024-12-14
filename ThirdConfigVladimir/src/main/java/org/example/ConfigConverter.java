package org.example;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ConfigConverter {

    public static void main(String[] args) {
        try {
            // Читаем YAML из стандартного ввода
            InputStream input = Files.newInputStream(Path.of(args[0]));
            Yaml yaml = new Yaml();
            Map<String, Object> yamlData = yaml.load(input);

            // Конвертируем YAML в учебный конфигурационный язык
            String result = convertToCustomConfig(yamlData);
            System.out.println(result);
        } catch (YAMLException e) {
            System.err.println("Ошибка синтаксиса YAML: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
    }

    public static String convertToCustomConfig(Map<String, Object> yamlData) throws Exception {
        StringBuilder output = new StringBuilder();

        for (Map.Entry<String, Object> entry : yamlData.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                output.append("{\n");
                Map<String, Object> map = (Map<String, Object>) value;
                for (Map.Entry<String, Object> mapEntry : map.entrySet()) {
                    output.append(mapEntry.getKey())
                            .append(" => ")
                            .append(mapEntry.getValue())
                            .append(",\n");
                }
                output.append("}\n");
            } else if (value instanceof Number) {
                output.append(value).append(" -> ").append(name).append(";\n");
            } else {
                throw new Exception("Неподдерживаемый тип значения для ключа: " + name);
            }
        }

        return output.toString();
    }
}
