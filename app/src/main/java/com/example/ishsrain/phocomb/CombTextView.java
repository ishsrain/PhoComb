package com.example.ishsrain.phocomb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CombTextView extends AppCompatTextView {

  // Main Context
  private Context mainContext;

  // Character
  char sound;

  public CombTextView(Context context) {
    super(context);
  }

  public CombTextView(Context context, AttributeSet attrs) {
    super(context, attrs);

    // Main Activity
    mainContext = context;

    // Init Setting
    setTextSize(300);
    setTextColor(Color.parseColor("#565656"));
    //setText(sound+"");
//    Typeface face=Typeface.createFromAsset(context.getAssets(), "Helvetica_Neue.ttf");
//    this.setTypeface(face);
  }

  public CombTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    setText(sound+"");
  }
}
