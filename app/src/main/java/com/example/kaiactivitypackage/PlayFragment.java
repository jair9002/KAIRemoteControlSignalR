package com.example.kaiactivitypackage;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kairemotecontrolsignalr.R;
import com.example.kairemotecontrolsignalr.SignalRConnect;

public class PlayFragment extends Fragment {


    ImageButton skipPreBtn;
    ImageButton playBtn;
    ImageButton nextBtn;
    SeekBar voulmeSeekBar;
    ImageButton muteBtn;
    Button korBtn;
    Button engBtn;
    SignalRConnect signalRConnect;
    PlayFragment(SignalRConnect signalRConnect){
        this.signalRConnect = signalRConnect;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_play, container, false);

        skipPreBtn = (ImageButton) viewGroup.findViewById(R.id.skip_previous);
        playBtn = (ImageButton) viewGroup.findViewById(R.id.play_arrow);
        nextBtn = (ImageButton) viewGroup.findViewById(R.id.skip_next);
        voulmeSeekBar = (SeekBar) viewGroup.findViewById(R.id.volume_seekbar);
        muteBtn = (ImageButton) viewGroup.findViewById(R.id.volumne_button);
        korBtn = (Button) viewGroup.findViewById(R.id.kor_button);
        engBtn = (Button) viewGroup.findViewById(R.id.eng_button);

        skipPreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"reset",Toast.LENGTH_SHORT).show();
                signalRConnect.send("ResetButton");

            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalRConnect.send("PlayButton");
                //pause 버튼 만들어 주세요
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalRConnect.send("NextPlayButton");
            }
        });

        /*
        skipPreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */

        muteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalRConnect.send("Mute");
                //Mute on , Mute off 로 분리해서 생각해야해
            }
        });
        korBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalRConnect.send("KORPlay");
            }
        });
        engBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalRConnect.send("ENGPlay");
            }
        });


        return viewGroup;
    }
}
