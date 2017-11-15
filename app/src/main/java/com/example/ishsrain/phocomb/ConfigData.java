package com.example.ishsrain.phocomb;

import java.io.Serializable;
import java.util.ArrayList;


public class ConfigData implements Serializable {

  private static final long serialVersionUID = 1L;

  // Menu Type
  public static final int GENERAL_MENU = 0;
  public static final int TYPE_MENU = 1;
  public static final int REPEAT_MENU = 2;
  public static final int TIME_MENU = 3;
  public static final int STIMULI_MENU = 4;
  public static final int EDUCATION_MENU = 5;
  public static final int SOUND_MENU = 6;
  public static final int CHAR_MENU = 7;
  public static final int WORD_MENU = 8;
  public static final int SOUND1_MENU = 9;
  public static final int SOUND2_MENU = 10;
  public static final int SOUND3_MENU = 11;

  ArrayList<String> s1 = new ArrayList<String>();
  ArrayList<String> s2 = new ArrayList<String>();
  ArrayList<String> s3 = new ArrayList<String>();

  ArrayList<ConfigObject> mConfig = new ArrayList<ConfigObject>();

  public ConfigData() {

  }

  void update(ConfigObject config, int i) {
    int type = config.getType();

    ConfigObject sameConfig = find(type);
    if(sameConfig != null) {
      sameConfig.setCheck(i, config.getCheck(i));
    }
  }

  ConfigObject add(String[] list, int type) {

    ConfigObject config = find(type);
    if(config == null) {
      // New Config
      config = new ConfigObject();

      // Set
      for(int i=0; i<list.length; i++) {
        config.setType(type);
        config.add(list[i]);

        if(type == SOUND1_MENU || type == SOUND2_MENU || type == SOUND3_MENU) {
          config.setCheck(i,true);
        }
      }
      config.setCheck(0,true);

      // Add
      mConfig.add(config);
    }

    return config;
  }

  ConfigObject find(int type) {
    ConfigObject config = null;

    for(int i=0; i<mConfig.size(); i++) {
      if(mConfig.get(i).getType() == type) {
        config = mConfig.get(i);
        break;
      }
    }

    return config;
  }

  void makeSelection() {

    // Make String
    for(int i=0; i<mConfig.size(); i++) {

      // Get Config Obeject
      ConfigObject config = mConfig.get(i);

      // Get Type
      int type = config.getType();

      if(type == SOUND1_MENU) {
        for(int j=0; j<config.getSize(); j++) {
          if(config.getCheck(j) == true) {
            s1.add(config.getText(j));
          }
        }
      } else if(type == SOUND2_MENU) {
        for(int j=0; j<config.getSize(); j++) {
          if(config.getCheck(j) == true) {
            s2.add(config.getText(j));
          }
        }
      } else if(type == SOUND3_MENU) {
        for(int j=0; j<config.getSize(); j++) {
          if(config.getCheck(j) == true) {
            s3.add(config.getText(j));
          }
        }
      }
    }
  }
}