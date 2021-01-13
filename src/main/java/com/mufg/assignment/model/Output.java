package com.mufg.assignment.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "Output")
public class Output {

    private static final long serialVersionUID = 22L;

    @JacksonXmlProperty(localName = "Position")
    private Position position;
}
