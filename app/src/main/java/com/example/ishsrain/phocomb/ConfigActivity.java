package com.example.ishsrain.phocomb;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import com.example.ishsrain.phocomb.ConfigListAdapter.CONFIG;

public class ConfigActivity extends AppCompatActivity {

  // Intent
  Intent intent;

  // List View
  GridView gridView;

  // Button
  Button button;

  // TextView
  TextView[] menu = new TextView[5];
  TextView[] sidemenu = new TextView[2];
  TextView[] sideline = new TextView[2];

  // List Menu
  ConfigListAdapter[] adapter = new ConfigListAdapter[12];
  static final String[][] MENU_STRING = {
      {"장애 유형", "반복 횟수", "교육 시간", "자극 유무"},
      {"장애 없음", "지적 장애", "언어 장애", "자폐성 장애", "정신 장애", "학습 장애", "기타"},
      {"제한 없음", "10회", "20회", "30회"},
      {"제한 없음", "10분", "20분", "30분"},
      {"자극 없음", "자극 있음"},
      {"음소", "음절", "단어"},
      {"자음", "모음", "받침"},
      {"가", "나", "다", "라", "마"},
      {"동물", "식물", "가족", "학교", "직업", "운동"},
      {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"},
      {"ㅏ", "ㅓ", "ㅗ", "ㅜ", "ㅡ", "ㅣ"},
      {" ", "ㄱ", "ㄴ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ"}
//      {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ",
//          "ㅎ", "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ",
//          "ㅠ", "ㅡ", "ㅢ", "ㅣ", " ", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ",
//          "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"}
  };

  // Config
  ConfigObject config;
  int type = 0;
  int number = 0;
  int time = 0;
  int stimuli = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_config);

    // Intent
    intent = new Intent(this, MainActivity.class);

    // Config Data
    config = new ConfigObject();

    // TextView
    menu[0] = (TextView) findViewById(R.id.menu1);
    menu[1] = (TextView) findViewById(R.id.menu2);
    menu[2] = (TextView) findViewById(R.id.menu3);
    menu[3] = (TextView) findViewById(R.id.menu4);
    menu[4] = (TextView) findViewById(R.id.menu5);
    sidemenu[0] = (TextView) findViewById(R.id.sidemenu1);
    sidemenu[1] = (TextView) findViewById(R.id.sidemenu2);
    sideline[0] = (TextView) findViewById(R.id.sideline1);
    sideline[1] = (TextView) findViewById(R.id.sideline2);

    // Button
    button = (Button) findViewById(R.id.button);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        updateConfig(config);
        intent.putExtra("config", config);
        startActivity(intent);
      }
    });

    // List Adapter
    adapter[0] = new ConfigListAdapter(R.layout.config_list_normal, MENU_STRING[0],
        CONFIG.GENERAL_MENU);
    adapter[1] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[1],
        CONFIG.TYPE_MENU);
    adapter[2] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[2],
        CONFIG.REPEAT_MENU);
    adapter[3] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[3],
        CONFIG.TIME_MENU);
    adapter[4] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[4],
        CONFIG.STIMULI_MENU);
    adapter[5] = new ConfigListAdapter(R.layout.config_list_normal, MENU_STRING[5],
        CONFIG.EDUCATION_MENU);
    adapter[6] = new ConfigListAdapter(R.layout.config_list_normal, MENU_STRING[6],
        CONFIG.SOUND_MENU);
    adapter[7] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[7],
        CONFIG.CHAR_MENU);
    adapter[8] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[8],
        CONFIG.WORD_MENU);
    adapter[9] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[9],
        CONFIG.SOUND1_MENU);
    adapter[10] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[10],
        CONFIG.SOUND2_MENU);
    adapter[11] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[11],
        CONFIG.SOUND3_MENU);

    // List View
    gridView = (GridView) findViewById(R.id.GridView);
    gridView.setAdapter(adapter[0]);
    gridView.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        // Adapter
        ConfigListAdapter adapters = (ConfigListAdapter) adapterView.getAdapter();

        // Item
        ConfigItem item = adapters.getItem(i);

        // Update view (toggle)
        if (!item.getCheck()) {
          item.setCheck(true);
        } else {
          item.setCheck(false);
        }
        adapters.notifyDataSetChanged();

        CONFIG menuType = adapters.menuType;
        if (menuType == CONFIG.GENERAL_MENU) {
          gridView.setNumColumns(1);
          menu[1].setText(">");
          menu[2].setText(MENU_STRING[0][i]);
          menu[3].setText("");
          menu[4].setText("");
          gridView.setAdapter(adapter[i + 1]);
        } else if (menuType == CONFIG.EDUCATION_MENU) {
          menu[1].setText(">");
          menu[2].setText(MENU_STRING[5][i]);
          menu[3].setText("");
          menu[4].setText("");
          gridView.setNumColumns(1);
          gridView.setAdapter(adapter[i + 6]);
        } else if (menuType == CONFIG.TYPE_MENU) {
          config.setType(i);
        } else if (menuType == CONFIG.REPEAT_MENU) {
          config.setNumber(i);
        } else if (menuType == CONFIG.TIME_MENU) {
          config.setTime(i);
        } else if (menuType == CONFIG.STIMULI_MENU) {
          config.setStimuli(i);
        } else if (menuType == CONFIG.SOUND_MENU) {
          menu[3].setText(">");
          menu[4].setText(MENU_STRING[6][i]);
          gridView.setNumColumns(5);
          gridView.setAdapter(adapter[i + 9]);
        }
      }
    });

    sidemenu[0].setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        // Menu Change
        menu[0].setText("일반");
        menu[1].setText("");
        menu[2].setText("");
        menu[3].setText("");
        menu[4].setText("");
        gridView.setNumColumns(1);
        gridView.setAdapter(adapter[0]);

        // Color Change
        sidemenu[0].setTextColor(Color.parseColor("#FB0F0F"));
        sidemenu[1].setTextColor(Color.parseColor("#000000"));
        sideline[0].setBackgroundColor(Color.parseColor("#FB0F0F"));
        sideline[1].setBackgroundColor(Color.parseColor("#00000000"));
      }
    });

    sidemenu[1].setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        // Menu Change
        menu[0].setText("교육");
        menu[1].setText("");
        menu[2].setText("");
        menu[3].setText("");
        menu[4].setText("");
        gridView.setNumColumns(1);
        gridView.setAdapter(adapter[5]);

        sidemenu[0].setTextColor(Color.parseColor("#000000"));
        sidemenu[1].setTextColor(Color.parseColor("#FB0F0F"));
        sideline[0].setBackgroundColor(Color.parseColor("#00000000"));
        sideline[1].setBackgroundColor(Color.parseColor("#FB0F0F"));
      }
    });

  }

  void updateConfig(ConfigObject config) {

    // General
    for (int i = 0; i < adapter[1].getCount(); i++) {
      ConfigItem item = adapter[1].getItem(i);
      if (item.getCheck()) {
        config.setType(i);
      }
    }

    for (int i = 0; i < adapter[2].getCount(); i++) {
      ConfigItem item = adapter[2].getItem(i);
      if (item.getCheck()) {
        config.setNumber(i);
      }
    }

    for (int i = 0; i < adapter[3].getCount(); i++) {
      ConfigItem item = adapter[3].getItem(i);
      if (item.getCheck()) {
        config.setTime(i);
      }
    }

    for (int i = 0; i < adapter[4].getCount(); i++) {
      ConfigItem item = adapter[4].getItem(i);
      if (item.getCheck()) {
        config.setStimuli(i);
      }
    }

    // Selected Sound
    config.s1.clear();
    config.s2.clear();
    config.s3.clear();

    for (int i = 0; i < adapter[9].getCount(); i++) {
      ConfigItem item = adapter[9].getItem(i);
      if (item.getCheck()) {
        config.s1.add(item.getText());
      }
    }

    for (int i = 0; i < adapter[10].getCount(); i++) {
      ConfigItem item = adapter[10].getItem(i);
      if (item.getCheck()) {
        config.s2.add(item.getText());
      }
    }

    for (int i = 0; i < adapter[11].getCount(); i++) {
      ConfigItem item = adapter[11].getItem(i);
      if (item.getCheck()) {
        config.s3.add(item.getText());
      }
    }
  }
}
