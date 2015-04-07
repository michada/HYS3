package es.uvigo.esei.daa;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import es.uvigo.esei.daa.rest.UsuariosResource;

@ApplicationPath("/rest/*")
public class DAAExampleApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		return new HashSet<>(Arrays.asList(UsuariosResource.class));
	}
	
	@Override
	public Map<String, Object> getProperties() {
		// Activates JSON automatic conversion in JAX-RS
		return Collections.singletonMap(
			"com.sun.jersey.api.json.POJOMappingFeature", true
		);
	}
}
