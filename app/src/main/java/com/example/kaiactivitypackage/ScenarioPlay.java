package com.example.kaiactivitypackage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.kairemotecontrolsignalr.R;
import com.example.kairemotecontrolsignalr.SignalRConnect;

public class ScenarioPlay extends Fragment {
    Button scenarioS1;
    Button scenarioS2;
    Button scenarioS3;
    Button backBtn;
    Main main;
    SignalRConnect signalRConnect;

    public ScenarioPlay(SignalRConnect signalRConnect){
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
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_scenario_play, container, false);

        scenarioS1 = viewGroup.findViewById(R.id.scenarioButton1);
        scenarioS2 = viewGroup.findViewById(R.id.scenarioButton2);
        scenarioS3 = viewGroup.findViewById(R.id.scenarioButton3);
        backBtn = viewGroup.findViewById(R.id.back_main);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalRConnect.send("BackMain");
                main.change_to_frame("Main");
            }
        });

        scenarioS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalRConnect.send("Scenario1");
            }
        });

        scenarioS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalRConnect.send("Scenario2");
            }
        });

        scenarioS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalRConnect.send("Scenario3");
            }
        });

        getChildFragmentManager().beginTransaction().replace(R.id.play_frame, new PlayFragment(signalRConnect)).commit();

        return viewGroup;
    }//onCreateView end
}
