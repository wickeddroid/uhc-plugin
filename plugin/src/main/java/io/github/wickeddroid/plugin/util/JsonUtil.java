package io.github.wickeddroid.plugin.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
  public static final Gson GSON = new GsonBuilder().create();

  private JsonUtil() {

  }
}