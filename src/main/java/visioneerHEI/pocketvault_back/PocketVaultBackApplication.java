package visioneerHEI.pocketvault_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import visioneerHEI.pocketvault_back.configs.RsaKeyProperties;
import visioneerHEI.pocketvault_back.configs.StorageServiceProperties;

@SpringBootApplication
@EnableConfigurationProperties({RsaKeyProperties.class, StorageServiceProperties.class})
public class PocketVaultBackApplication {
  public static void main(String[] args) {
    SpringApplication.run(PocketVaultBackApplication.class, args);
  }
}
