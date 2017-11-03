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
import android.widget.ListView;
import android.widget.TextView;

public class ConfigActivity extends AppCompatActivity {

  // Intent
  Intent intent;

  // List View
  ListView listView;

  // Button
  Button button;

  // TextView
  TextView[] menu = new TextView[3];
  TextView[] sidemenu = new TextView[2];
  TextView[] sideline = new TextView[2];

  // List Menu
  ConfigListAdapter[] adapter = new ConfigListAdapter[9];
  static final String[][] MENU_STRING = {
      {"장애 종류", "반복 횟수", "교육 시간", "자극 유무"},
      {"지적 장애", "언어 장애", "자폐성 장애", "정신 장애", "학습 장애", "기타"},
      {"10회", "20회", "30회", "제한 없음"},
      {"10분", "20분", "30분", "제한 없음"},
      {"자극 없음", "자극 있음"},
      {"음소", "음절", "단어"},
      {"ㄱ","ㄴ","ㄷ","ㅏ","ㅑ","ㅓ"},
      {"가","나","다","라","마"},
      {"동물", "식물", "가족", "학교", "직업","운동"}
  };

  // Config
  int menuList = 0;
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

    // Button
    button = (Button) findViewById(R.id.button);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(intent);
        finish();
      }
    });

    // List View
    listView = (ListView) findViewById(R.id.listView);

    adapter[0] = new ConfigListAdapter(R.layout.config_list_normal, MENU_STRING[0]);
    adapter[1] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[1]);
    adapter[2] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[2]);
    adapter[3] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[3]);
    adapter[4] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[4]);
    adapter[5] = new ConfigListAdapter(R.layout.config_list_normal, MENU_STRING[5]);
    adapter[6] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[6]);
    adapter[7] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[7]);
    adapter[8] = new ConfigListAdapter(R.layout.config_list_check, MENU_STRING[8]);


//    listView.setAdapter(adapter[0]);
    listView.setAdapter(adapter[0]);
    listView.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ConfigItem item = (ConfigItem) adapterView.getItemAtPosition(i);
        item.setCheck(true);

        // Save Config
        if(menuList == 0) {
          // MenuList
          menuList = i+1;

          // Menu Change
          if(menuList == 1) {
            menu[0].setText("일반");
            menu[1].setText(">");
            menu[2].setText("장애 종류");
            listView.setAdapter(adapter[1]);
          } else if(menuList == 2) {
            menu[0].setText("일반");
            menu[1].setText(">");
            menu[2].setText("반복 횟수");
            listView.setAdapter(adapter[2]);
          } else if(menuList == 3) {
            menu[0].setText("일반");
            menu[1].setText(">");
            menu[2].setText("교육 시간");
            listView.setAdapter(adapter[3]);
          } else if(menuList == 4) {
            menu[0].setText("일반");
            menu[1].setText(">");
            menu[2].setText("자극 유무");
            listView.setAdapter(adapter[4]);
          }

        } else if(menuList > 0 && menuList < 5) {
          menu[0].setText("일반");
          menu[1].setText("");
          menu[2].setText("");
          listView.setAdapter(adapter[0]);

          if (menuList == 1) {
            // 장애 정도
            type = i;
          } else if (menuList == 2) {
            // 반복 횟수
            number = i;
          } else if (menuList == 3) {
            // 교육 시간
            time = i;
          } else if (menuList == 4) {
            // 자극 유무
            stimuli = i;
          }

          menuList = 0;
        } else if(menuList == 5) {
          menuList = i + 6;

          if (menuList == 6) {
            menu[0].setText("교육");
            menu[1].setText(">");
            menu[2].setText("음소");
            listView.setAdapter(adapter[6]);
          } else if (menuList == 7) {
            menu[0].setText("교육");
            menu[1].setText(">");
            menu[2].setText("음절");
            listView.setAdapter(adapter[7]);
          } else if (menuList == 8) {
            menu[0].setText("교육");
            menu[1].setText(">");
            menu[2].setText("단어");
            listView.setAdapter(adapter[8]);
          }
        } else {
          menu[0].setText("교육");
          menu[1].setText("");
          menu[2].setText("");
          listView.setAdapter(adapter[5]);
          menuList = 5;
        }
      }
    });

    // TextView
    menu[0] = (TextView) findViewById(R.id.menu1);
    menu[1] = (TextView) findViewById(R.id.menu2);
    menu[2] = (TextView) findViewById(R.id.menu3);
    sidemenu[0] = (TextView) findViewById(R.id.sidemenu1);
    sidemenu[1] = (TextView) findViewById(R.id.sidemenu2);
    sideline[0] = (TextView) findViewById(R.id.sideline1);
    sideline[1] = (TextView) findViewById(R.id.sideline2);

    sidemenu[0].setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        // Menu Change
        menu[0].setText("일반");
        menu[1].setText("");
        menu[2].setText("");
        listView.setAdapter(adapter[0]);
        menuList = 0;

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
        listView.setAdapter(adapter[5]);
        menuList = 5;

        sidemenu[0].setTextColor(Color.parseColor("#000000"));
        sidemenu[1].setTextColor(Color.parseColor("#FB0F0F"));
        sideline[0].setBackgroundColor(Color.parseColor("#00000000"));
        sideline[1].setBackgroundColor(Color.parseColor("#FB0F0F"));
      }
    });

  }
}
