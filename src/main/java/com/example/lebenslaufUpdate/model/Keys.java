package com.example.lebenslaufUpdate.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "keys")
public class Keys {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "key")
    private List<String> key = new ArrayList<>();

    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }
}