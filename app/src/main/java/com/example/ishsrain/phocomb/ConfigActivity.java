package com.example.ishsrain.phocomb;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.example.ishsrain.phocomb.ConfigFragment.OnListFragmentInteractionListener;

public class ConfigActivity extends AppCompatActivity
    implements OnListFragmentInteractionListener {

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

  // List Menu
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

  // Config Data
  ConfigData mConfigData;

  // Main Intent
  Intent mIntent;

  // Fragment Manager
  FragmentManager mFragmentManager;

  // TextView
  TextView[] mMenu = new TextView[5];
  TextView[] mSideMenu = new TextView[2];
  TextView[] mSideLine = new TextView[2];

  // Button
  Button mButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_config);

    // Config Data
    mConfigData = new ConfigData();

    // Main Intent
    mIntent = new Intent(this, MainActivity.class);

    // Fragment Manager
    mFragmentManager = getSupportFragmentManager();

    // Init Fragment (TBA)
    changeFragment(R.layout.config_list_normal, MENU_STRING[GENERAL_MENU], GENERAL_MENU);

    // TextView
    mMenu[0] = (TextView) findViewById(R.id.menu1);
    mMenu[1] = (TextView) findViewById(R.id.menu2);
    mMenu[2] = (TextView) findViewById(R.id.menu3);
    mMenu[3] = (TextView) findViewById(R.id.menu4);
    mMenu[4] = (TextView) findViewById(R.id.menu5);
    mSideMenu[0] = (TextView) findViewById(R.id.sidemenu1);
    mSideMenu[1] = (TextView) findViewById(R.id.sidemenu2);
    mSideLine[0] = (TextView) findViewById(R.id.sideline1);
    mSideLine[1] = (TextView) findViewById(R.id.sideline2);

    mSideMenu[0].setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        // Fragment Change
        changeFragment(R.layout.config_list_normal, MENU_STRING[GENERAL_MENU], GENERAL_MENU);
      }
    });

    mSideMenu[1].setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        // Fragment Change
        changeFragment(R.layout.config_list_normal, MENU_STRING[EDUCATION_MENU], EDUCATION_MENU);
      }
    });

    // Button
    mButton = (Button) findViewById(R.id.button);
    mButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        mConfigData.makeSelection();
        mIntent.putExtra("ConfigData", mConfigData);
        startActivity(mIntent);
      }
    });
  }

  ConfigFragment changeFragment(int resource, String[] list, int type) {

    // Add Config
    ConfigObject configObject = mConfigData.add(list, type);

    // Next Fragment
    ConfigFragment fragment = ConfigFragment.newInstance(resource, configObject);

    // Change
    FragmentTransaction tr = mFragmentManager.beginTransaction();
    tr.replace(R.id.fragment, fragment).addToBackStack(null).commit();

    return fragment;
  }

  public void onListFragmentInteraction(int type) {

    // Menu Color Change
    if (type == GENERAL_MENU ||
        type == TYPE_MENU ||
        type == REPEAT_MENU ||
        type == TIME_MENU ||
        type == STIMULI_MENU) {
      mSideMenu[0].setTextColor(Color.parseColor("#FB0F0F"));
      mSideLine[0].setBackgroundColor(Color.parseColor("#FB0F0F"));
      mSideMenu[1].setTextColor(Color.parseColor("#000000"));
      mSideLine[1].setBackgroundColor(Color.parseColor("#00000000"));
    } else {
      mSideMenu[0].setTextColor(Color.parseColor("#000000"));
      mSideLine[0].setBackgroundColor(Color.parseColor("#00000000"));
      mSideMenu[1].setTextColor(Color.parseColor("#FB0F0F"));
      mSideLine[1].setBackgroundColor(Color.parseColor("#FB0F0F"));
    }

    // Nav Change
    if (type == GENERAL_MENU) {
      mMenu[0].setText("일반");
      mMenu[1].setText("");
      mMenu[2].setText("");
      mMenu[3].setText("");
      mMenu[4].setText("");
    } else if (type == EDUCATION_MENU) {
      mMenu[0].setText("교육");
      mMenu[1].setText("");
      mMenu[2].setText("");
      mMenu[3].setText("");
      mMenu[4].setText("");
    } else if (type == TYPE_MENU) {
      mMenu[0].setText("일반");
      mMenu[1].setText(">");
      mMenu[2].setText("장애 유형");
      mMenu[3].setText("");
      mMenu[4].setText("");
    } else if (type == REPEAT_MENU) {
      mMenu[0].setText("일반");
      mMenu[1].setText(">");
      mMenu[2].setText("반복 횟수");
      mMenu[3].setText("");
      mMenu[4].setText("");
    } else if (type == TIME_MENU) {
      mMenu[0].setText("일반");
      mMenu[1].setText(">");
      mMenu[2].setText("교육 시간");
      mMenu[3].setText("");
      mMenu[4].setText("");
    } else if (type == STIMULI_MENU) {
      mMenu[0].setText("일반");
      mMenu[1].setText(">");
      mMenu[2].setText("자극 유무");
      mMenu[3].setText("");
      mMenu[4].setText("");
    } else if (type == SOUND_MENU) {
      mMenu[0].setText("교육");
      mMenu[1].setText(">");
      mMenu[2].setText("음소");
      mMenu[3].setText("");
      mMenu[4].setText("");
    } else if (type == CHAR_MENU) {
      mMenu[0].setText("교육");
      mMenu[1].setText(">");
      mMenu[2].setText("음절");
      mMenu[3].setText("");
      mMenu[4].setText("");
    } else if (type == WORD_MENU) {
      mMenu[0].setText("교육");
      mMenu[1].setText(">");
      mMenu[2].setText("단어");
      mMenu[3].setText("");
      mMenu[4].setText("");
    } else if (type == SOUND1_MENU) {
      mMenu[0].setText("교육");
      mMenu[1].setText(">");
      mMenu[2].setText("음소");
      mMenu[3].setText(">");
      mMenu[4].setText("자음");
    } else if (type == SOUND2_MENU) {
      mMenu[0].setText("교육");
      mMenu[1].setText(">");
      mMenu[2].setText("음소");
      mMenu[3].setText(">");
      mMenu[4].setText("모음");
    } else if (type == SOUND3_MENU) {
      mMenu[0].setText("교육");
      mMenu[1].setText(">");
      mMenu[2].setText("음소");
      mMenu[3].setText(">");
      mMenu[4].setText("받침");
    }
  }

  public void onListFragmentInteraction(ConfigObject config, int i) {

    // Type
    int type = config.getType();

    // Update Fragment
    if (type == GENERAL_MENU) {

      int next = GENERAL_MENU + i + 1;
      changeFragment(R.layout.config_list_check, MENU_STRING[next], next);

    } else if (type == EDUCATION_MENU) {

      int next = EDUCATION_MENU + i + 1;
      if (next == SOUND_MENU) {
        changeFragment(R.layout.config_list_normal, MENU_STRING[next], next);
      } else {
        changeFragment(R.layout.config_list_check, MENU_STRING[next], next);
      }

    } else if (type == SOUND_MENU) {

      int next = i + 9;
      changeFragment(R.layout.config_list_check, MENU_STRING[next], next);

    } else {

      // Update Config Data
      mConfigData.update(config, i);

    }
  }
}
