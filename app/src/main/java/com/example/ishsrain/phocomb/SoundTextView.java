package com.example.ishsrain.phocomb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatTextView;

public class SoundTextView extends AppCompatTextView {

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
    setTextSize(100);
    setTextColor(Color.parseColor("#DFDFDF"));
    setText(sound + "");
//    Typeface face=Typeface.createFromAsset(context.getAssets(), "Helvetica_Neue.ttf");
//    this.setTypeface(face);
  }

  public SoundTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    setText(sound + "");
  }
}
