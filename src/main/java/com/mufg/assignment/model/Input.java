package com.mufg.assignment.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "Input")
public class Input implements Serializable {

    private static final long serialVersionUID = 21L;

    @JsonProperty("Position")
    @JacksonXmlProperty(localName = "Position")
    private Position position;

    @JsonProperty("Move")
    @JacksonXmlProperty(localName = "Move")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Move> moves = new ArrayList<>();
}
