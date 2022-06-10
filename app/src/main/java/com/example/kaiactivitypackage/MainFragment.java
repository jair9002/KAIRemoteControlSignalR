package com.example.kaiactivitypackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.kairemotecontrolsignalr.MainActivity;
import com.example.kairemotecontrolsignalr.R;
import com.example.kairemotecontrolsignalr.SignalRConnect;

public class MainFragment extends Fragment {
    Button mainAllPlay;
    Button mainScenarioPlay;
    Button mainDetailPlay;
    Button mainWait;
    SignalRConnect signalRConnect;
    FragmentManager fm;
    FragmentTransaction ft;
    Main main;

    public MainFragment(){

    }

    public MainFragment(SignalRConnect signalRConnect){
        this.signalRConnect = signalRConnect;

    }

    //이게 있어야 상위 액티비티(Main) 클래스(Change_to_frmae) 호출 가능
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        main=(Main)getActivity();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        mainAllPlay = (Button) viewGroup.findViewById(R.id.mainAllPlay);
        mainAllPlay.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){

                if(signalRConnect != null) {
                    signalRConnect.send("MainAllPlay");
                    main.change_to_frame("MainAllPlay");
                }
            }
        }); //end cooditoon listner

        mainScenarioPlay = (Button) viewGroup.findViewById(R.id.mainScenarioPlay);
        mainScenarioPlay.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                if(signalRConnect != null) {
                    signalRConnect.send("MainScenarioPlay");
                    main.change_to_frame("MainScenarioPlay");
                }
            }
        }); //end cooditoon listner

        mainDetailPlay = (Button) viewGroup.findViewById(R.id.mainDetailPlay);
        mainDetailPlay.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                if(signalRConnect != null) {
                    signalRConnect.send("MainDetailPlay");
                    main.change_to_frame("MainDetailPlay");
                }
            }
        }); //end cooditoon listner

        mainWait = (Button) viewGroup.findViewById(R.id.mainWait);
        mainWait.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                if(signalRConnect != null) {
                    signalRConnect.send("MainWait");
                    main.change_to_frame("MainWait");
                }
            }
        }); //end cooditoon listner

        return viewGroup;
    }
}
