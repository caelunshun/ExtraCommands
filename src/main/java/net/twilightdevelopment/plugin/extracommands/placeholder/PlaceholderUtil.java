package net.twilightdevelopment.plugin.extracommands.placeholder;

import net.twilightdevelopment.plugin.extracommands.ExtraCommands;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderUtil {
  public static String applyPlaceholders(String message, Map<String, String> placeholders) {
    String result = message;
    for (Map.Entry<String, String> entry : placeholders.entrySet()) {
      StringBuffer buffer = new StringBuffer();
      Pattern pattern = Pattern.compile("\\{" + entry.getKey() + "}");
      Matcher matcher = pattern.matcher(result);
      while (matcher.find()) {
        matcher.appendReplacement(buffer, entry.getValue());
      }
      matcher.appendTail(buffer);
      result = buffer.toString();
    }
    return result;
  }
}
