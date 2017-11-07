package com.example.ishsrain.phocomb;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

  // Roll Text View
  RollTextView[] textViewArray = new RollTextView[3];

  // Sequence Thread
  SequenceThread sequenceThread;

  // CombActivity Intent
  Intent combIntent;
  Intent configIntent;

  // Data
  ConfigObject config;

  // ImageView
  ImageView button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Find TextView
    textViewArray[0] = (RollTextView) findViewById(R.id.roll1);
    textViewArray[1] = (RollTextView) findViewById(R.id.roll2);
    textViewArray[2] = (RollTextView) findViewById(R.id.roll3);

    // Pre Intent
    Intent preIntent = getIntent();
    config = (ConfigObject) preIntent.getSerializableExtra("config");
//    if(config != null) {
//      Toast.makeText(this, config.toString(), Toast.LENGTH_LONG);
//    }

    String[] s1 = new String[config.s1.size()];
    String[] s2 = new String[config.s2.size()];
    String[] s3 = new String[config.s3.size()];

    for(int i=0; i<s1.length; i++) {
      s1[i] = config.s1.get(i);
    }

    for(int i=0; i<s2.length; i++) {
      s2[i] = config.s2.get(i);
    }

    for(int i=0; i<s3.length; i++) {
      s3[i] = config.s3.get(i);
    }

    textViewArray[0].characters = s1;
    textViewArray[1].characters = s2;
    textViewArray[2].characters = s3;

    // Load Sound
//    String[][] Character = {
//        {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㄲ", "ㄸ", "ㅃ", "ㅆ", "ㅉ"},
//        {"ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅘ", "ㅝ", "ㅙ", "ㅞ", "ㅢ"},
//        {" ", "ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅅ", "ㅇ"}
//    };
//    String[][] selectedCharacter = {
//        {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ"},
//        {"ㅏ", "ㅓ", "ㅗ", "ㅜ", "ㅡ", "ㅣ"},
//        {" ", "ㄱ", "ㄴ", "ㄹ", "ㅁ", "ㅅ", "ㅇ"}
//    };
//    textViewArray[0].characters = selectedCharacter[0];
//    textViewArray[1].characters = selectedCharacter[1];
//    textViewArray[2].characters = selectedCharacter[2];

    // Check Sequence Thread Start
    sequenceThread = new SequenceThread();
    sequenceThread.start();

    // Next Intent Init
    combIntent = new Intent(this, CombActivity.class);
    configIntent = new Intent(this, ConfigActivity.class);

    // Button
    button = (ImageView) findViewById(R.id.button);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        configIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(configIntent);
      }
    });

    // Activity Conversion Animation
    overridePendingTransition(0, 0);
  }

  // Sequence Thread for Click
  class SequenceThread extends Thread {

    public void SequenceThread() {
      // Initialization
    }

    public void run() {
      try {

        // First TextView
        textViewArray[0].allowed = true;

        // Infinite Loop
        while (true) {

          // Check All Selected
          boolean AllSelected = true;
          for (int i = 0; i < textViewArray.length; i++) {
            if (textViewArray[i].selected == false) {
              AllSelected = false;
              break;
            } else {
              // Allowed to Next Click
              if (i + 1 < textViewArray.length) {
                textViewArray[i + 1].allowed = true;
              }
            }
          }

          // Select Completed
          if (AllSelected == true) {

            // Wait
            Thread.sleep(2000);

            // Character Combination
            char sound1 = textViewArray[0].getText().charAt(0);
            char sound2 = textViewArray[1].getText().charAt(0);
            char sound3 = textViewArray[2].getText().charAt(0);

            // Put to Intent
            combIntent.putExtra("sound1", sound1);
            combIntent.putExtra("sound2", sound2);
            combIntent.putExtra("sound3", sound3);
            combHandler.sendEmptyMessage(0);

            // Text View Reset
            for (int i = 0; i < textViewArray.length; i++) {
              textViewArray[i].allowed = false;
              textViewArray[i].selected = false;
              textViewArray[i].rotationHandler.sendEmptyMessage(textViewArray[i].INIT_MESSAGE);
            }
            textViewArray[0].allowed = true;
          }

          // Loop Speed
          Thread.sleep(100);
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  Handler combHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      startActivity(combIntent);
    }
  };
}
