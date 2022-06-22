package com.example.kaiactivitypackage;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.kairemotecontrolsignalr.MainActivity;
import com.example.kairemotecontrolsignalr.R;
import com.example.kairemotecontrolsignalr.SignalRConnect;

public class ScenarioPlay extends Fragment {
    Button scenarioS1;
    Button scenarioS2;
    Button scenarioS3;
    Button scenarioButton[];
    MainActivity main;
    SignalRConnect signalRConnect;

    public ScenarioPlay(SignalRConnect signalRConnect){
        this.signalRConnect=signalRConnect;
    }

    //이게 있어야 상위 액티비티(Main) 클래스(Change_to_frmae) 호출 가능
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        main=(MainActivity) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_scenario_play, container, false);
        scenarioButton = new Button[3];

        scenarioButton[0] = viewGroup.findViewById(R.id.scenarioButton1);
        scenarioButton[1] = viewGroup.findViewById(R.id.scenarioButton2);
        scenarioButton[2] = viewGroup.findViewById(R.id.scenarioButton3);

        scenarioButton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"시나리오 1번 선택",Toast.LENGTH_SHORT).show();
                scenarioButtonColorRadio(scenarioButton[0]);
                signalRConnect.send("Scenario1");
            }
        });

        scenarioButton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"시나리오 2번 선택",Toast.LENGTH_SHORT).show();
                scenarioButtonColorRadio(scenarioButton[1]);
                signalRConnect.send("Scenario2");
            }
        });

        scenarioButton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"시나리오 3번 선택",Toast.LENGTH_SHORT).show();
                scenarioButtonColorRadio(scenarioButton[2]);
                signalRConnect.send("Scenario3");
            }
        });

        getChildFragmentManager().beginTransaction().replace(R.id.play_frame, new PlayFragment(signalRConnect)).commit();

        return viewGroup;
    }//onCreateView end

    public void scenarioButtonColorRadio(Button scenarioBtn){
        for(int i=0;i<3;i++) {
            if (scenarioBtn == scenarioButton[i]){
                scenarioBtn.setTextColor(Color.BLACK);
                //scenarioBtn.setBackgroundColor(Color.WHITE);
                scenarioBtn.setBackground();
                continue;
            }
            scenarioButton[i].setTextColor(Color.WHITE);
            scenarioButton[i].setBackgroundColor(Color.BLUE);

        }//for end
    }//sBC end
}
