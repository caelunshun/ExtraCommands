package me.caelunshun.extracommands.placeholder;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlaceholderUtilTest {

  @Test
  public void applyPlaceholders() {
    String input = "Today is {day}";
    String result = PlaceholderUtil.applyPlaceholders(input, ImmutableMap.of("day", "Monday"));
    assertEquals(result, "Today is Monday");

    input = "Tomorrow is the first {day} of {month}";
    result = PlaceholderUtil.applyPlaceholders(input, ImmutableMap.of("day", "Tuesday", "month", "June"));
    assertEquals(result, "Tomorrow is the first Tuesday of June");
  }
}