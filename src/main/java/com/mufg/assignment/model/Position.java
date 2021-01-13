package com.mufg.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "Position")
public class Position {

    @JsonProperty("Direction")
    @JacksonXmlProperty(localName = "Direction")
    private String direction;

    @JsonProperty("X")
    @JacksonXmlProperty(localName = "X")
    private String x;

    @JsonProperty("Y")
    @JacksonXmlProperty(localName = "Y")
    private String y;
}
