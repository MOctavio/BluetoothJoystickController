package com.moctavio.app.bluetoothcontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RelativeLayout layout_joystick;
    ImageView image_joystick, image_border;
    TextView textView1, textView2, textView3, textView4, textView5;

    Joystick js;

    private String getDirectionLabel(Joystick js){
        int direction = js.get8Direction();
        switch (direction){
            case Joystick.STICK_UP:
                return "Direction : Up";
            case Joystick.STICK_UPRIGHT:
                return "Direction : Up Right";
            case Joystick.STICK_RIGHT:
                return "Direction : Right";
            case Joystick.STICK_DOWNRIGHT:
                return "Direction : Down Right";
            case Joystick.STICK_DOWN:
                return "Direction : Down";
            case Joystick.STICK_DOWNLEFT:
                return "Direction : Down Left";
            case Joystick.STICK_LEFT:
                return "Direction : Left";
            case Joystick.STICK_UPLEFT:
                return "Direction : Up Left";
            case Joystick.STICK_NONE:
                return "Direction : Center";
            default:
                return "";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);

        layout_joystick = (RelativeLayout)findViewById(R.id.layout_joystick);

        js = new Joystick(getApplicationContext(), layout_joystick, R.drawable.joystick);
        js.setStickSize(150, 150);
        js.setLayoutSize(500, 500);
        js.setLayoutAlpha(150);
        js.setStickAlpha(100);
        js.setOffset(90);
        js.setMinimumDistance(50);

        layout_joystick.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                js.drawStick(arg1);
                switch (arg1.getAction()){
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        textView1.setText("X : " + String.valueOf(js.getX()));
                        textView2.setText("Y : " + String.valueOf(js.getY()));
                        textView3.setText("Angle : " + String.valueOf(js.getAngle()));
                        textView4.setText("Distance : " + String.valueOf(js.getDistance()));
                        textView5.setText(getDirectionLabel(js));
                        break;
                    case MotionEvent.ACTION_UP:
                        textView1.setText("X :");
                        textView2.setText("Y :");
                        textView3.setText("Angle :");
                        textView4.setText("Distance :");
                        textView5.setText("Direction :");
                        break;
                }
                return true;
            }
        });
    }
}
