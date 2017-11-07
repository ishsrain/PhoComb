package com.example.ishsrain.phocomb;

import java.io.Serializable;
import java.util.ArrayList;


public class ConfigObject implements Serializable {

  private static final long serialVersionUID = 1L;

  public ArrayList<String> s1 = new ArrayList<String>();
  public ArrayList<String> s2 = new ArrayList<String>();
  public ArrayList<String> s3 = new ArrayList<String>();

  private int type = 0;
  private int number = 0;
  private int time = 0;
  private int stimuli = 0;

  public ConfigObject() {

  }

  // Set Methods
  void setType(int type) {
    this.type = type;
  }

  void setNumber(int number) {
    this.number = number;
  }

  void setTime(int time) {
    this.time = time;
  }

  void setStimuli(int stimuli) {
    this.stimuli = stimuli;
  }

  // Get Methods
  int getType() {
    return type;
  }

  int getNumber() {
    return number;
  }

  int getTime() {
    return time;
  }

  int getStimuli() {
    return stimuli;
  }

  public String toString() {
    String str;
    str = "type: " + type + " number: " + number + " time: " + time + " stimuli : " + stimuli;
    return str;
  }
}
