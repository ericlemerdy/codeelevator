package name.lemerdy.eric.config;

import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;
import static java.lang.Boolean.TRUE;

import name.lemerdy.eric.resource.Elevator;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.jaxrs.JsonMappingExceptionMapper;
import org.codehaus.jackson.jaxrs.JsonParseExceptionMapper;

import com.google.common.collect.ImmutableMap;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class CodeElevatorServletModule extends JerseyServletModule {

  @Override
  protected void configureServlets() {
    bindResources();
    bindFrameworkClasses();
    serve("/*").with(GuiceContainer.class, ImmutableMap.<String, String> of( //
        FEATURE_POJO_MAPPING, TRUE.toString()));
  }

  private void bindResources() {
    bind(Elevator.class);
    bind(Doc.class);
  }

  private void bindFrameworkClasses() {
    bind(JacksonJsonProvider.class).asEagerSingleton();
    bind(JacksonJaxbJsonProvider.class).asEagerSingleton();
    bind(JsonParseExceptionMapper.class).asEagerSingleton();
    bind(JsonMappingExceptionMapper.class).asEagerSingleton();
  }

}