package com.example.kairemotecontrolsignalr;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ButtonFragment extends Fragment {
    String color;
    int monitor_num;
    public ViewGroup layout1;
    public ViewGroup layout2;
    public ViewGroup layout3;
    public View v;

    public ButtonFragment() {

    }

    public ButtonFragment(String colorString) {
        this.color = colorString;
    }

    //몇번 모니터를 클릭했는지 표기하기 위해서
    public ButtonFragment(String colorString, int monitor_num) {
        this.color = colorString;
        this.monitor_num = monitor_num;
    }
    //생성자에서 color를 받아서 모든 버튼 색깔을 색칠하기를 원함


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater layoutInflater = getLayoutInflater();



        //v = layoutInflater.inflate(R.layout.prototype_button, null);
        /*layout1 = v.findViewById(R.id.fragment_layout1);
        layout2 = v.findViewById(R.id.fragment_layout2);
        layout3 = v.findViewById(R.id.fragment_layout3);

        for(int i=0;i<3;i++) {
            Button button = new Button(getContext());

            button.setText(Integer.toString(i+1));
            layout1.addView(button);

        }
        for(int i=0;i<3;i++) {
            Button button = new Button(getContext());
            button.setText(Integer.toString(i+4));
            layout2.addView(button);
        }
        for(int i=0;i<3;i++) {
            Button button = new Button(getContext());
            button.setText(Integer.toString(i+7));
            layout3.addView(button);
        }*/



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = (ViewGroup)inflater.inflate(R.layout.fragment_button,container,false);
        Button button[] = new Button[9];
        TextView monitor_textView = v.findViewById(R.id.monitor_num_tv);
        monitor_textView.setText(monitor_num+"번 모니터");

        button[0]=v.findViewById(R.id.button1);
        button[1]=v.findViewById(R.id.button2);
        button[2]=v.findViewById(R.id.button3);
        button[3]=v.findViewById(R.id.button4);
        button[4]=v.findViewById(R.id.button5);
        button[5]=v.findViewById(R.id.button6);
        button[6]=v.findViewById(R.id.button7);
        button[7]=v.findViewById(R.id.button8);
        button[8]=v.findViewById(R.id.button9);

        for(int i=0;i<9;i++){
            button[i].setText(Integer.toString(i+1));
            button[i].setBackgroundColor(Color.parseColor(color));
            Log.d("color",color);
            //Button button = v.findViewById(R.id.buttonId)
        }


        return v;
    }

}
