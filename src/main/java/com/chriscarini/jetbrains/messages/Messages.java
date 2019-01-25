package com.chriscarini.jetbrains.messages;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.PropertyKey;


public class Messages {
  @NonNls
  private static final String BUNDLE = "messages.envVarMain";

  @NonNls
  private static final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE);

  public static String get(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
    String value = bundle.getString(key);

    if (params.length > 0) {
      return MessageFormat.format(value, params);
    }

    return value;
  }
}
