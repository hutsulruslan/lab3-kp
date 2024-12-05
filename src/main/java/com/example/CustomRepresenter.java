package com.example;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

public class CustomRepresenter extends Representer {

    public CustomRepresenter(DumperOptions options) {
        super(options);
    }

    @Override
    protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue, Tag customTag) {

        if (javaBean instanceof Book && "author".equals(property.getName())) {
            Book book = (Book) javaBean;
            if (book.getPublicationYear() >= 2000) {
                return null;
            }
        }
        return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
    }
}
