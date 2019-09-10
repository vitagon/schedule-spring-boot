package com.vitgon.schedule.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.vitgon.schedule.dto.Days;

public class StringToDaysEnumDeserializer extends StdDeserializer<Days> {
	
	public StringToDaysEnumDeserializer() {
		this(null);
	}

	protected StringToDaysEnumDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Days deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return Days.valueOf(p.getText().toUpperCase());
	}
}
