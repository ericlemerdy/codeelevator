package name.lemerdy.eric.config;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.inject.Guice.createInjector;
import static com.google.inject.util.Modules.override;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.util.Modules;

public class CodeElevatorContextListener extends GuiceServletContextListener {

  private Iterable<Module> overrides;

  public CodeElevatorContextListener() {
    this.overrides = newArrayList();
  }

  public CodeElevatorContextListener(Module... overrides) {
    this.overrides = newArrayList(overrides);
  }

  @Override
  protected Injector getInjector() {
    return createInjector(Modules.override(new CodeElevatorServletModule()).with(overrides));
  }
}
