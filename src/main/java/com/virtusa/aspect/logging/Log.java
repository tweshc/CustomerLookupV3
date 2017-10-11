package com.virtusa.aspect.logging;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

  public Logger logger;
  FileHandler fileHandler;
  
  public Log(String fileName) {
    
    File file = new File(fileName);
    
    try {
      
      if (!file.exists()) {file.createNewFile();}
      
      fileHandler = new FileHandler(fileName, true);
      logger = Logger.getLogger("applicationLogs");
      SimpleFormatter simpleFormatter = new SimpleFormatter();
      fileHandler.setFormatter(simpleFormatter);
      
    }catch (SecurityException | IOException ex) {
      System.out.println("Error with creating file: " + fileName);
    }
  }
}
