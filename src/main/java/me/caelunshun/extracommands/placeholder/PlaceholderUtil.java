package me.caelunshun.extracommands.placeholder;

import org.apache.commons.lang.Validate;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderUtil {
  public static String applyPlaceholders(String message, Map<String, String> placeholders) {
    Validate.notNull(message, "Message cannot be null");
    Validate.notNull(placeholders, "Placeholder map cannot be null");
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
