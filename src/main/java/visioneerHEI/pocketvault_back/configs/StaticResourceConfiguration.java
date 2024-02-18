package visioneerHEI.pocketvault_back.configs;

import java.net.MalformedURLException;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class StaticResourceConfiguration implements WebMvcConfigurer {
  private final StorageServiceProperties storageServiceProperties;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    try {
      String path =
          Paths.get(storageServiceProperties.path()).toAbsolutePath().toUri().toURL().toString();
      registry.addResourceHandler("/res/**").addResourceLocations(path);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
