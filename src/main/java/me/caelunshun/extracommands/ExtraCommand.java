package me.caelunshun.extracommands;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public class ExtraCommand {
  private Map<String, String> placeholders;

  public void placeholder(String placeholder, String value) {
    if (placeholders == null) placeholders = new HashMap<>();
    placeholders.put(placeholder, value);
  }

  public Map<String, String> getPlaceholders() {
    return placeholders == null ? ImmutableMap.of() : placeholders;
  }
}
