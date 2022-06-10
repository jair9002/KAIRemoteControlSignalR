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
    Boolean play_pause_toggle;
    Boolean mute_toggle ;

    //consturctor
    PlayFragment(SignalRConnect signalRConnect){
        this.signalRConnect = signalRConnect;
        play_pause_toggle = true;
        mute_toggle = true;
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
                if(play_pause_toggle) {
                    signalRConnect.send("PlayButton");
                    //pause 버튼 만들어 주세요
                    play_pause_toggle=false;
                    playBtn.setImageResource(R.drawable.pause);
                }else{
                    signalRConnect.send("PauseButton");
                    play_pause_toggle=true;
                    playBtn.setImageResource(R.drawable.play_arrow);
                }


            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalRConnect.send("NextPlayButton");
            }
        });

        //volume controller
        voulmeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getContext(),Integer.toString(voulmeSeekBar.getProgress()),Toast.LENGTH_SHORT).show();
                signalRConnect.send("Volume", Integer.toString(voulmeSeekBar.getProgress()));
            }
        });


        muteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mute_toggle) {
                    signalRConnect.send("MuteOn");
                    muteBtn.setImageResource(R.drawable.volumen_off);
                    mute_toggle=false;

                }else{
                    signalRConnect.send("MuteOff");
                    muteBtn.setImageResource(R.drawable.volume_on);
                    mute_toggle=true;
                }
            }
        });
        korBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"한국어 버전",Toast.LENGTH_SHORT).show();
                signalRConnect.send("KORPlay");
            }
        });

        engBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"영어 버전",Toast.LENGTH_SHORT).show();
                signalRConnect.send("ENGPlay");
            }
        });

        return viewGroup;
    }
}
