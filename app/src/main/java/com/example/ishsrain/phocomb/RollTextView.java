package com.example.ishsrain.phocomb;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.support.v7.widget.AppCompatTextView;

class RollTextView extends AppCompatTextView {

  // Message Constant
  public static final int ROTATION_MESSAGE = 1;
  public static final int INIT_MESSAGE = 2;

  // Main Context
  private Context mainContext;

  // Question
  public int randomIndex = 0;
  String[] characters;

  // Rotation
  int rIndex = 0;
  boolean rotation = false;
  RotationThread rotationThread;

  // Sequence
  boolean allowed = false;
  boolean selected = false;

  public RollTextView(Context context) {
    super(context);
  }

  public RollTextView(Context context, AttributeSet attrs) {
    super(context, attrs);

    // Main Activity
    mainContext = context;

    // Rotation Thread
    rotationThread = new RotationThread();
    rotationThread.start();

    // Init Setting
    setBackgroundColor(Color.parseColor("#565656"));
    setTextSize(300);
//    Typeface face=Typeface.createFromAsset(context.getAssets(), "Helvetica_Neue.ttf");
//    this.setTypeface(face);
  }

  public RollTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    super.onTouchEvent(event);

    if (event.getAction() == MotionEvent.ACTION_DOWN) {

      if (allowed == true && selected == false) {

        // Randome Pick
        randomIndex = (int) (Math.random() * characters.length);

        // Set Clicking Color
        setBackgroundColor(Color.parseColor("#e35959"));
        setTextColor(Color.parseColor("#f4bcbc"));

        // Rotation Start
        rIndex = 0;
        rotation = true;
      } else {
        // Noting
      }

      return true;
    } else if (event.getAction() == MotionEvent.ACTION_UP) {

      if (allowed == true && selected == false) {
        // Rotation End
        rotation = false;

        // Selected
        selected = true;

        // Set Text
        setText(characters[randomIndex]);

        // Set Releasing Color
        setBackgroundColor(Color.parseColor("#FFFFFF"));
        setTextColor(Color.parseColor("#565656"));
      } else {
        // Sound
      }

      return true;
    }

    return false;
  }

  // Rotation Handler for Looping
  class RotationThread extends Thread {

    public void RotationThread() {
      // Initialization
    }

    public void run() {
      try {
        while (true) {
          rotationHandler.sendEmptyMessage(ROTATION_MESSAGE);
          Thread.sleep(50);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  // Rotation Handler for UI
  Handler rotationHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case ROTATION_MESSAGE:
          if (rotation) {
            setText(characters[rIndex]);

            // Set Next Index
            rIndex++;
            if (rIndex == characters.length) {
              rIndex = 0;
            }
          }
          break;
        case INIT_MESSAGE:
          // Set Clicking Color
          setBackgroundColor(Color.parseColor("#565656"));
          setText(" ");
          break;
      }
    }
  };
}
