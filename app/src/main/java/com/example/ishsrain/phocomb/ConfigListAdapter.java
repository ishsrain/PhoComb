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

  // ArrayList
  private ArrayList<ConfigItem> listViewItemList = new ArrayList<ConfigItem>();

  // xmlResource
  int xmlResource;

  public ConfigListAdapter(int resource, String[] list) {
    // XML
    xmlResource = resource;
    // List
    for (int i = 0; i < list.length; i++) {
      ConfigItem item = new ConfigItem();
      item.setText(list[i]);
      listViewItemList.add(item);
    }
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

    if(xmlResource == R.layout.config_list_check) {
      CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
      checkBox.setChecked(item.getCheck());
    }

    return view;
  }

  @Override
  public int getCount() {
    return listViewItemList.size();
  }

  @Override
  public Object getItem(int i) {
    return listViewItemList.get(i);
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

}
