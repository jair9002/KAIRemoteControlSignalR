package com.example.kaiactivitypackage;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.kairemotecontrolsignalr.R;
import com.example.kairemotecontrolsignalr.SignalRConnect;

public class DetailPlayActivity extends Fragment {

    SignalRConnect signalRConnect;
    Main main;

    Button detailScenarioButton[];
    Button preset_button[];
    int chooseScenarioNum;

    public DetailPlayActivity(SignalRConnect signalRConnect){
        this.signalRConnect=signalRConnect;
    }

    //이게 있어야 상위 액티비티(Main) 클래스(Change_to_frmae) 호출 가능
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        main=(Main)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.activity_detail_play, container, false);

        detailScenarioButton = new Button[3];

        detailScenarioButton[0] = v.findViewById(R.id.detailScenario1);
        detailScenarioButton[1] = v.findViewById(R.id.detailScenario2);
        detailScenarioButton[2] = v.findViewById(R.id.detailScenario3);
        ImageView presetImage = (ImageView) v.findViewById(R.id.presetImage);


        detailScenarioButton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"시나리오 1번 선택",Toast.LENGTH_SHORT).show();
                scenarioButtonColorRadio(detailScenarioButton[0],3);

                signalRConnect.send("DetailScenario1");
                chooseScenarioNum = 1;
            }
        });

        detailScenarioButton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"시나리오 2번 선택",Toast.LENGTH_SHORT).show();
                scenarioButtonColorRadio(detailScenarioButton[1],3);
                signalRConnect.send("DetailScenario2");
                chooseScenarioNum = 2;
            }
        });

        detailScenarioButton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"시나리오 3번 선택",Toast.LENGTH_SHORT).show();
                scenarioButtonColorRadio(detailScenarioButton[2],3);
                signalRConnect.send("DetailScenario3");
                chooseScenarioNum = 3;
            }
        });

        //너무 부끄럽다. R.id가 final int 형태이고 참조하기가 까다로워 스트링처리로 일관적으로 할당하기 어렵다.
        preset_button = new Button[10];
        preset_button[0]=v.findViewById(R.id.detailPreset10);
        preset_button[1]=v.findViewById(R.id.detailPreset20);
        preset_button[2]=v.findViewById(R.id.detailPreset30);
        preset_button[3]=v.findViewById(R.id.detailPreset40);
        preset_button[4]=v.findViewById(R.id.detailPreset50);
        preset_button[5]=v.findViewById(R.id.detailPreset60);
        preset_button[6]=v.findViewById(R.id.detailPreset70);
        preset_button[7]=v.findViewById(R.id.detailPreset80);
        preset_button[8]=v.findViewById(R.id.detailPreset90);
        preset_button[9]=v.findViewById(R.id.detailPreset100);


        for(int i=0;i<10;i++){
            int presetValue = i;
            preset_button[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    presetButtonColorRadio(preset_button[presetValue],10);

                    signalRConnect.send("DetailPreset",Integer.toString((presetValue+1)*10));
                }
            });
        }//for end



        getChildFragmentManager().beginTransaction().replace(R.id.play_frame, new PlayFragment(signalRConnect)).commit();


        return v;
    }// onCreateView end


    public void scenarioButtonColorRadio(Button scenarioBtn, int size){
        for(int i=0;i<size;i++) {
            if (scenarioBtn == detailScenarioButton[i]){
                scenarioBtn.setTextColor(Color.BLACK);
                scenarioBtn.setBackgroundColor(Color.WHITE);
                continue;
            }
            detailScenarioButton[i].setTextColor(Color.WHITE);
            detailScenarioButton[i].setBackgroundColor(Color.BLUE);

        }//for end
    }//sBC end

    public void presetButtonColorRadio(Button scenarioBtn, int size){
        for(int i=0;i<size;i++) {
            if (scenarioBtn == preset_button[i]){
                scenarioBtn.setTextColor(Color.BLACK);
                scenarioBtn.setBackgroundColor(Color.WHITE);
                continue;
            }
            preset_button[i].setTextColor(Color.WHITE);
            preset_button[i].setBackgroundColor(Color.BLUE);

        }//for end
    }//sBC end

    public void changePresetImage(int scenarioNum, int presetNum){

        for(int i=1;i<4;i++){
            if(i == scenarioNum){

            }
        } //for end

    } //changePT end
}
