package com.miya10kei.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static java.lang.String.format;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

public class DictConfiguration {

  private static final Properties properties;
  private static final Path home;
  private static final Path propPath;

  static {
    home = Path.of(System.getenv("HOME"), ".dict");
    try {
      if (!Files.exists(home)) {
        Files.createDirectories(home);
      }

      propPath = home.resolve("dict.properties");
      if (!Files.exists(propPath)) {
        Files.createFile(propPath);
      }

      properties = new Properties();
      properties.load(Files.newInputStream(propPath, READ));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String readCurrentDictionary() {
    return properties.getProperty("current_dictionary", "default");
  }

  public static void writeCurrentDictionary(String dictionaryName) {
    properties.put("current_dictionary", dictionaryName);
    try (var out = Files.newOutputStream(propPath, WRITE)) {
      properties.store(out, null);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String readDbUrl() {
    return properties.getProperty("db_url", format("jdbc:sqlite:%s", home.resolve("dict.db").toString()));
  }
}
