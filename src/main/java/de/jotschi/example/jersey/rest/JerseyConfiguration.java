package de.jotschi.example.jersey.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfiguration extends ResourceConfig {

	/**
	 * Create an instance of the REST Application
	 */
	public JerseyConfiguration() {
		// tell Jersey in which package the resources can be found
		packages("de.jotschi.example.jersey.rest").register(CustomObjectMapperProvider.class).register(JacksonFeature.class);
		// .register(MoxyJsonFeature)

	}
}