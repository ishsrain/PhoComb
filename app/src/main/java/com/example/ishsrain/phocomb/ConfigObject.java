package com.example.ishsrain.phocomb;

import java.io.Serializable;
import java.util.ArrayList;

public class ConfigObject implements Serializable {

  private static final long serialVersionUID = 1L;

  int mType;

  ArrayList<ConfigItem> mItem = new ArrayList<ConfigItem>();

  public ConfigObject() {

  }

  void add(String str){
    ConfigItem item = new ConfigItem();
    item.setText(str);
    mItem.add(item);
  }

  String getText(int position) {
    return mItem.get(position).getText();
  }

  void setText(int position,String str) {
    mItem.get(position).setText(str);
  }

  boolean getCheck(int position) {
    return mItem.get(position).getCheck();
  }

  void setCheck(int position, boolean check) {
    mItem.get(position).setCheck(check);
  }

  int getSize() {
    return mItem.size();
  }

  int getType() {
    return mType;
  }

  void setType(int type) {
    mType = type;
  }

  public class ConfigItem  implements Serializable {

    private static final long serialVersionUID = 1L;

    String mText;
    Boolean mCheck = false;

    public ConfigItem() {

    }

    public void setText(String str) {
      mText = str;
    }

    public void setCheck(Boolean ckeck) {
      mCheck = ckeck;
    }

    public String getText() {
      return mText;
    }

    public Boolean getCheck() {
      return mCheck;
    }
  }

}