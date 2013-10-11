package name.lemerdy.eric.config;

import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;
import static java.lang.Boolean.TRUE;

import name.lemerdy.eric.resource.ElevatorResource;

import com.google.common.collect.ImmutableMap;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class CodeElevatorServletModule extends JerseyServletModule {

  @Override
  protected void configureServlets() {
    bindResources();
    serve("/*").with(GuiceContainer.class, ImmutableMap.<String, String> of( //
        FEATURE_POJO_MAPPING, TRUE.toString()));
  }

  private void bindResources() {
    bind(ElevatorResource.class);
  }
}