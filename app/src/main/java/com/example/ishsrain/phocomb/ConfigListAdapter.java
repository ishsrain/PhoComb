package com.example.ishsrain.phocomb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;

public class ConfigListAdapter extends BaseAdapter {

  // Menu Type
  public enum CONFIG {
    GENERAL_MENU, EDUCATION_MENU, TYPE_MENU, REPEAT_MENU, TIME_MENU, STIMULI_MENU, SOUND_MENU, SOUND1_MENU, SOUND2_MENU, SOUND3_MENU, CHAR_MENU, WORD_MENU
  }
  CONFIG menuType = CONFIG.GENERAL_MENU;

  // ArrayList
  private ArrayList<ConfigItem> listViewItemList = new ArrayList<ConfigItem>();

  // xmlResource
  int xmlResource;

  public ConfigListAdapter(int resource, String[] list, CONFIG menuType) {
    // XML
    xmlResource = resource;
    // List
    for (int i = 0; i < list.length; i++) {
      ConfigItem item = new ConfigItem();
      item.setText(list[i]);
      listViewItemList.add(item);
    }
    //Config
    this.menuType = menuType;
  }

  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    final int pos = i;
    final Context context = viewGroup.getContext();

    // View
    if (view == null) {
      LayoutInflater inflater = (LayoutInflater) context
          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      view = inflater.inflate(xmlResource, viewGroup, false);
    }

    // Data
    ConfigItem item = listViewItemList.get(pos);

    TextView textView = (TextView) view.findViewById(R.id.textView);
    textView.setText(item.getText());

    if (xmlResource == R.layout.config_list_check) {
      CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
      checkBox.setClickable(false);
      checkBox.setChecked(item.getCheck());
    }

    return view;
  }

  @Override
  public int getCount() {
    return listViewItemList.size();
  }

  @Override
  public ConfigItem getItem(int i) {
    return listViewItemList.get(i);
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

}
