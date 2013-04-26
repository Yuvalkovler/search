/**
 * Copyright 2013 Cloudera Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloudera.cdk.morphline.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Helpers to parse HOCON from external resources, and to traverse and read parts of a HOCON data
 * structure.
 */
public final class Configs {
  
  /** Loads the given config file from the classpath */
  public static Config parse(String resourceBaseName) {
    return ConfigFactory.load(resourceBaseName);
  }
  
  /** Loads the given config file from the local file system */
  public static Config parse(File file) throws IOException {
    if (!file.exists()) {
      throw new FileNotFoundException("File not found: " + file);
    }
    if (!file.canRead()) {
      throw new IOException("Insufficient permissions to read file: " + file);
    }
    Config config = ConfigFactory.parseFile(file);
    config = ConfigFactory.load(config);
    config.checkValid(ConfigFactory.defaultReference()); // eagerly validate aspects of tree config  
    return config;
  }
  
  public static String getString(Config config, String path, String defaults) {
    if (config.hasPath(path)) {
      return config.getString(path);
    } else {
      return defaults;
    }
  }
  
  public static String getString(Config config, String path) {
    return config.getString(path);
  }
  
  public static List<String> getStringList(Config config, String path, List<String> defaults) {
    if (config.hasPath(path)) {
      return config.getStringList(path);
    } else {
      return defaults;
    }
  }
  
  public static List<String> getStringList(Config config, String path) {
    return config.getStringList(path);
  }
  
  public static List<? extends Config> getConfigList(Config config, String path, List<? extends Config> defaults) {
    if (config.hasPath(path)) {
      return config.getConfigList(path);
    } else {
      return defaults;
    }
  }

  public static List<? extends Config> getConfigList(Config config, String path) {
    return config.getConfigList(path);
  }

  public static Config getConfig(Config config, String path, Config defaults) {
    if (config.hasPath(path)) {
      return config.getConfig(path);
    } else {
      return defaults;
    }
  }

  public static Config getConfig(Config config, String path) {
    return config.getConfig(path);
  }

  public static boolean getBoolean(Config config, String path, boolean defaults) {
    if (config.hasPath(path)) {
      return config.getBoolean(path);
    } else {
      return defaults;
    }
  }
  
  public static boolean getBoolean(Config config, String path) {
    return config.getBoolean(path);
  }
  
  public static int getInt(Config config, String path, int defaults) {
    if (config.hasPath(path)) {
      return config.getInt(path);
    } else {
      return defaults;
    }
  }
  
  public static int getInt(Config config, String path) {
    return config.getInt(path);
  }  

  public static long getLong(Config config, String path, long defaults) {
    if (config.hasPath(path)) {
      return config.getLong(path);
    } else {
      return defaults;
    }
  }
  
  public static long getLong(Config config, String path) {
    return config.getLong(path);
  }  

  public static double getDouble(Config config, String path, double defaults) {
    if (config.hasPath(path)) {
      return config.getDouble(path);
    } else {
      return defaults;
    }
  }
  
  public static double getDouble(Config config, String path) {
    return config.getDouble(path);
  }  

//  public static Config parse(File file) throws IOException {
//    if (!file.exists()) {
//      throw new FileNotFoundException("File not found: " + file);
//    }
//    if (!file.canRead()) {
//      throw new IOException("Insufficient permissions to read file: " + file);
//    }
//    String path = file.getAbsolutePath();
//    String oldConfigResource = System.clearProperty("config.resource");
//    String oldConfigFile = System.setProperty("config.file", path);
//    String oldConfigUrl = System.clearProperty("config.url");
//    try {
//      Config config = ConfigFactory.load();
//      config.checkValid(ConfigFactory.defaultReference()); // eagerly validate aspects of tree config  
//      return config;
//    } finally {
//      setSystemProperty("config.resource", oldConfigResource);
//      setSystemProperty("config.file", oldConfigFile);
//      setSystemProperty("config.url", oldConfigUrl);
//    }
//  }
//  
//  private static String setSystemProperty(String name, String value) {
//    if (value == null) {
//      return System.clearProperty(name);
//    } else {
//      return System.setProperty(name, value);
//    }    
//  }
  
}
