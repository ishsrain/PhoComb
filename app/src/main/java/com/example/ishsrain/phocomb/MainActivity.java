package com.example.ishsrain.phocomb;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

  // Sound Text View
  SoundTextView[] TextViewArray = new SoundTextView[3];

  // Sequence Thread
  SequenceThread SequenceThread;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Find TextView
    TextViewArray[0] = (SoundTextView) findViewById(R.id.sound1);
    TextViewArray[1] = (SoundTextView) findViewById(R.id.sound2);
    TextViewArray[2] = (SoundTextView) findViewById(R.id.sound3);

    // Load Sound
//    String[][] Character = {
//        {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㄲ", "ㄸ", "ㅃ", "ㅆ", "ㅉ"},
//        {"ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅘ", "ㅝ", "ㅙ", "ㅞ", "ㅢ"},
//        {" ", "ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅅ", "ㅇ"}
//    };
    String[][] Character = {
        {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ"},
        {"ㅏ", "ㅓ", "ㅗ", "ㅜ", "ㅡ", "ㅣ"},
        {" ", "ㄱ", "ㄴ", "ㄹ", "ㅁ", "ㅅ", "ㅇ"}
    };
    TextViewArray[0].Character = Character[0];
    TextViewArray[1].Character = Character[1];
    TextViewArray[2].Character = Character[2];

    // Check Sequence Thread Start
    SequenceThread = new SequenceThread();
    SequenceThread.start();
  }

  // Sequence Thread for Click
  class SequenceThread extends Thread {

    public void SequenceThread () {
      // Initialization
    }
    public void run () {
      try {

        // First TextView
        TextViewArray[0].Allowed = true;

        // Infinite Loop
        while (true) {

          // Check All Selected
          boolean AllSelected = true;
          for (int i = 0; i < TextViewArray.length; i++) {
            if (TextViewArray[i].Selected == false) {
              AllSelected = false;
              break;
            } else {
              // Allowed to Next Click
              if (i + 1 < TextViewArray.length) {
                TextViewArray[i + 1].Allowed = true;
              }
            }
          }

          // Select Completed
          if (AllSelected == true) {

            // Wait
            Thread.sleep(2000);

            // Text View Reset
            for (int i = 0; i < TextViewArray.length; i++) {
              TextViewArray[i].Allowed = false;
              TextViewArray[i].Selected = false;
              TextViewArray[i].RotationHandler.sendEmptyMessage(TextViewArray[i].INIT_MESSAGE);
            }
            TextViewArray[0].Allowed = true;

            // Character Combination
            char sound1 = TextViewArray[0].getText().charAt(0);
            char sound2 = TextViewArray[1].getText().charAt(0);
            char sound3 = TextViewArray[2].getText().charAt(0);
            char sound4 = CharacterCombination(sound1, sound2, sound3);
          }

          // Loop Speed
          Thread.sleep(100);
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  // Function for Character Combination
  final char[] ChoSung = {'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};
  final char[] JungSung	= {'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'};
  final char[] JongSung	= {' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};

  public char CharacterCombination(char ch1, char ch2, char ch3) {

    char ret_val;

    int a = Arrays.binarySearch(ChoSung, ch1);
    int b = Arrays.binarySearch(JungSung, ch2);
    int c = Arrays.binarySearch(JongSung, ch3);

    ret_val = (char)(0xAC00 + ((a*21)+b)*28+c);
//    Log.d("Combination Output", ""+ret_val);

    return ret_val;
  }
}
