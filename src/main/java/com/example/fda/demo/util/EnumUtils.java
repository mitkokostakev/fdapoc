package com.example.fda.demo.util;

import com.google.common.base.CaseFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** Utility class for operations on Enums */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumUtils {

  /**
   * Method finding the enum value from a camelCase input {@link String}
   *
   * <p>i.e. "someFieldName" and getting the corresponding enum value SOME_FIELD_NAME
   *
   * @param clazz the Enum class
   * @param name {@link String} containing the name of a field in a camelCase format
   * @param <E> type of the Enum class
   * @return Enum value
   */
  public static <E extends Enum<E>> E of(Class<E> clazz, String name) {
    String upperUnderscore = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name);
    return Enum.valueOf(clazz, upperUnderscore);
  }
}
