package visioneerHEI.pocketvault_back.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.UUID;
import javax.management.InvalidAttributeValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import visioneerHEI.pocketvault_back.configs.StorageServiceProperties;

@Service
@RequiredArgsConstructor
public class StorageService {
  private static final ArrayList<String> staticSubDirs = new ArrayList<>();
  private final StorageServiceProperties properties;

  public void register(String string) {
    staticSubDirs.add(string);
    init();
  }

  private void init() {
    if (getRoot().exists()) {
      initSubdirs();
    } else {
      getRoot().mkdirs();
      initSubdirs();
    }
  }

  private File getRoot() {
    return new File(properties.path());
  }

  private void initSubdirs() {
    staticSubDirs.forEach(
        folder -> {
          File subFolder = new File(properties.path(), folder);

          if (!subFolder.exists()) {
            subFolder.mkdirs();
          }
        });
  }

  public String save(MultipartFile file, String path)
      throws InvalidAttributeValueException, IOException {
    if (!staticSubDirs.contains(path)) {
      throw new InvalidAttributeValueException();
    }
    String fileName = UUID.randomUUID() + file.getOriginalFilename();
    Files.copy(
        file.getInputStream(),
        Path.of(properties.path(), path, fileName),
        StandardCopyOption.REPLACE_EXISTING);
    return fileName;
  }
}
