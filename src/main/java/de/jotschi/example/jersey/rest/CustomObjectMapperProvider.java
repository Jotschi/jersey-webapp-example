package de.jotschi.example.jersey.rest;

import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.examples.jackson.CombinedAnnotationBean;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

public class CustomObjectMapperProvider implements ContextResolver<ObjectMapper> {

	final ObjectMapper defaultObjectMapper;
	final ObjectMapper combinedObjectMapper;

	public CustomObjectMapperProvider() {
		defaultObjectMapper = createDefaultMapper();
		combinedObjectMapper = createCombinedObjectMapper();
	}

	@Override
	public ObjectMapper getContext(final Class<?> type) {

		if (type == CombinedAnnotationBean.class) {
			return combinedObjectMapper;
		} else {
			return defaultObjectMapper;
		}
	}

	private static ObjectMapper createCombinedObjectMapper() {
		return new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, true).configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
				.setAnnotationIntrospector(createJaxbJacksonAnnotationIntrospector());
	}

	private static ObjectMapper createDefaultMapper() {
		final ObjectMapper result = new ObjectMapper();
		result.enable(SerializationFeature.INDENT_OUTPUT);

		return result;
	}

	private static AnnotationIntrospector createJaxbJacksonAnnotationIntrospector() {

		final AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
		final AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();

		return AnnotationIntrospector.pair(jacksonIntrospector, jaxbIntrospector);
	}
}
