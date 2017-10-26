package com.example.ishsrain.phocomb;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

public class SoundTextView extends android.support.v7.widget.AppCompatTextView {

  // Main Context
  private Context mainContext;

  // Sound Character
  char sound;

  public SoundTextView(Context context) {
    super(context);
  }

  public SoundTextView(Context context, AttributeSet attrs) {
    super(context, attrs);

    // Main Activity
    mainContext = context;

    // Init Setting
    setTextSize(300);
    setTextColor(Color.parseColor("#DFDFDF"));
    setText(sound+"");
//    Typeface face=Typeface.createFromAsset(context.getAssets(), "Helvetica_Neue.ttf");
//    this.setTypeface(face);
  }

  public SoundTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }
}
