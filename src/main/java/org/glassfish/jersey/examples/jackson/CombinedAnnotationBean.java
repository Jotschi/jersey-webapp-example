package org.glassfish.jersey.examples.jackson;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TODO javadoc.
 * 
 * @author Jakub Podlesak (jakub.podlesak at oracle.com)
 */
@XmlRootElement(name = "account")
public class CombinedAnnotationBean {

	@JsonProperty("value")
	int x;

	public CombinedAnnotationBean(int x) {
		this.x = x;
	}

	public CombinedAnnotationBean() {
		this(15);
	}
}