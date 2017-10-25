package com.example.ishsrain.phocomb;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;

class SoundTextView extends android.support.v7.widget.AppCompatTextView {

  // Message Constant
  public static final int ROTATION_MESSAGE = 1;
  public static final int INIT_MESSAGE = 2;

  // Main Context
  private Context MainContext;

  // Question
  public int Index = 0;
  String[] Character;

  // Rotation
  int rIndex = 0;
  boolean Rotation = false;
  RotationThread RotationThread;

  // Sequence
  boolean Allowed = false;
  boolean Selected = false;


  public SoundTextView(Context context) {
    super(context);
  }

  public SoundTextView(Context context, AttributeSet attrs) {
    super(context, attrs);

    // Main Activity
    MainContext = context;

    // Rotation Thread
    RotationThread = new RotationThread();
    RotationThread.start();

    // Init Setting
    setBackgroundColor(Color.parseColor("#565656"));
    setTextSize(300);
//    Typeface face=Typeface.createFromAsset(context.getAssets(), "Helvetica_Neue.ttf");
//    this.setTypeface(face);
  }

  public SoundTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    super.onTouchEvent(event);

    if (event.getAction() == MotionEvent.ACTION_DOWN) {

      if (Allowed == true && Selected == false) {

        // Randome Pick
        Index = (int) (Math.random() * Character.length);

        // Set Clicking Color
        setBackgroundColor(Color.parseColor("#e35959"));
        setTextColor(Color.parseColor("#f4bcbc"));

        // Rotation Start
        rIndex = 0;
        Rotation = true;
      } else {
        // Noting
      }

      return true;
    } else if (event.getAction() == MotionEvent.ACTION_UP) {

      if (Allowed == true && Selected == false) {
        // Rotation End
        Rotation = false;

        // Selected
        Selected = true;

        // Set Text
        setText(Character[Index]);

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
    public void RotationThread () {
      // Initialization
    }

    public void run () {
      try {
        while (true) {
          RotationHandler.sendEmptyMessage(ROTATION_MESSAGE);
          Thread.sleep(50);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  // Rotation Handler for UI
  Handler RotationHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case ROTATION_MESSAGE:
          if (Rotation) {
            setText(Character[rIndex]);

            // Set Next Index
            rIndex++;
            if (rIndex == Character.length) {
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
