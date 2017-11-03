package com.example.ishsrain.phocomb;

public class ConfigItem {

  String text;
  Boolean check = false;

  public ConfigItem() {

  }

  public void setText(String str) {
    text = str;
  }

  public void setCheck(Boolean ck) {
    check = ck;
  }

  public String getText() {
    return text;
  }

  public Boolean getCheck() {
    return check;
  }
}
