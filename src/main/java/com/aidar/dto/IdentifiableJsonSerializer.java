package com.aidar.dto;

import com.aidar.model.Identifiable;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Aidar Shaifutdinov.
 */
public class IdentifiableJsonSerializer extends JsonSerializer<Identifiable> {

    @Override
    public void serialize(Identifiable identifiable, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(identifiable.getId());
    }

}
