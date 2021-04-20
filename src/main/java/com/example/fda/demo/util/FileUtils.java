package com.example.fda.demo.util;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** Utility class providing functionality related to File */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtils {

  /**
   * Method providing functionality to load file contents
   *
   * @param filename name of the file
   * @return an instance of {@link String} containing file contents
   * @throws IOException type of exception might be thrown
   */
  public static String loadFileContents(String filename) throws IOException {
    ClassLoader classLoader = FileUtils.class.getClassLoader();
    File file = new File(classLoader.getResource(filename).getFile());
    file = new File(file.getAbsolutePath().replace("%40", "@")); // Fix issues on jenkins
    return Files.asCharSource(file, StandardCharsets.UTF_8).read();
  }

  /**
   * Method providing functionality for getting an instance of {@link Properties} from a file
   *
   * @param filename name of the file
   * @return an instance of {@link Properties}
   * @throws IOException type of exception might be thrown
   */
  public static Properties loadPropertiesFromFile(String filename) throws IOException {
    return parsePropertiesString(loadFileContents(filename));
  }

  /**
   * Method providing functionality to parse and get an instance of {@link Properties} from a {@link
   * String}
   *
   * @param s an instance of {@link String} containing content
   * @return an instance of {@link Properties}
   * @throws IOException type of exception might be thrown
   */
  public static Properties parsePropertiesString(String s) throws IOException {
    // grr at load() returning void rather than the Properties object
    // so this takes 3 lines instead of "return new Properties().load(...);"
    final Properties p = new Properties();
    p.load(new StringReader(s));
    return p;
  }
}
