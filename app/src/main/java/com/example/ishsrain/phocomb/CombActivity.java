package com.example.ishsrain.phocomb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Arrays;

public class CombActivity extends AppCompatActivity {

  // Sound Text View
  SoundTextView[] textViewArray = new SoundTextView[3];

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comb);

    // Intent Value
    Intent intent = getIntent();
    char sound1 = intent.getCharExtra("sound1", 'ㄱ');
    char sound2 = intent.getCharExtra("sound2", 'ㅏ');
    char sound3 = intent.getCharExtra("sound3", 'ㅇ');

    // Find TextView
    textViewArray[0] = (SoundTextView) findViewById(R.id.sound1);
    textViewArray[1] = (SoundTextView) findViewById(R.id.sound2);
    textViewArray[2] = (SoundTextView) findViewById(R.id.sound3);

    // Assign Sound
    textViewArray[0].sound = sound1;
    textViewArray[1].sound = sound2;
    textViewArray[2].sound = sound3;
  }

  // Function for Character Combination
  final char[] CHO_SUNG = {'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};
  final char[] JUNG_SUNG	= {'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'};
  final char[] JONG_SUNG	= {' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};

  public char characterCombination(char cho, char jung, char jong) {

    char ret_val;

    int choIndex = Arrays.binarySearch(CHO_SUNG, cho);
    int jungIndex = Arrays.binarySearch(JUNG_SUNG, jung);
    int jongIndex = Arrays.binarySearch(JONG_SUNG, jong);

    ret_val = (char)(0xAC00 + ((choIndex*21)+jungIndex)*28+jongIndex);
//    Log.d("Combination Output", ""+ret_val);

    return ret_val;
  }
}
