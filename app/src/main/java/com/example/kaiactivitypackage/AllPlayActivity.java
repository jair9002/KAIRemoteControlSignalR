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

import com.example.kairemotecontrolsignalr.ButtonFragment;
import com.example.kairemotecontrolsignalr.R;
import com.example.kairemotecontrolsignalr.SignalRConnect;



public class AllPlayActivity extends Fragment {
    Button backBtn; //메인화면으로 이동
    SignalRConnect signalRConnect;
    Main main;

    public AllPlayActivity(SignalRConnect signalRConnect){
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
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_all_play, container, false);

        backBtn = viewGroup.findViewById(R.id.back_main);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalRConnect.send("BackMain");
                main.change_to_frame("Main");
            }
        });

        getChildFragmentManager().beginTransaction().replace(R.id.play_frame, new PlayFragment(signalRConnect)).commit();

        return viewGroup;
    }
}
