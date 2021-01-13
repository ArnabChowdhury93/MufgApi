package com.mufg.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "Move")
public class Move {

    @JsonProperty("O")
    @JacksonXmlProperty(localName = "O")
    private String o;

    @JsonProperty("L")
    @JacksonXmlProperty(localName = "L")
    private Integer l;

    @JsonProperty("R")
    @JacksonXmlProperty(localName = "R")
    private Integer r;

    @JsonProperty("F")
    @JacksonXmlProperty(localName = "F")
    private Integer f;

    @JsonProperty("B")
    @JacksonXmlProperty(localName = "B")
    private Integer b;

}
