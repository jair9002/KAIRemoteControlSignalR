package com.example.kaiactivitypackage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.kairemotecontrolsignalr.ButtonFragment;
import com.example.kairemotecontrolsignalr.R;

public class AllPlayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_play);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();

        ft.replace(R.id.play_frame, new PlayFragment());
        ft.commit();
    }
}
