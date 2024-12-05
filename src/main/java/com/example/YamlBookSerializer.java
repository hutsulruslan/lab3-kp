package com.example;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;
import java.util.List;

public class YamlBookSerializer {

    public static void serializeBooksToYaml(Book[] book, String filePath) throws IOException {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);

        CustomRepresenter representer = new CustomRepresenter(options);
        representer.addClassTag(Book.class, Tag.MAP);

        Yaml yaml = new Yaml(representer, options);

        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(book, writer);
        }
    }

    public static Book[] deserializeBooksFromYaml(String filePath) {
        LoaderOptions loaderOptions = new LoaderOptions();
        Constructor constructor = new Constructor(Book[].class, loaderOptions);

        Yaml yaml = new Yaml(constructor);

        try (InputStream in = new FileInputStream(filePath)) {
            return yaml.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
