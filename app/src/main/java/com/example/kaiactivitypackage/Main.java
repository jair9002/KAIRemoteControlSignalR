package com.example.kaiactivitypackage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kairemotecontrolsignalr.R;

public class Main extends AppCompatActivity {
    public static final int REQUEST_CODE_AllPlay =101;
    public static final int REQUEST_CODE_ScenarioPlay =102;
    public static final int REQUEST_CODE_DetailPlay =103;
    public static final int REQUEST_CODE_Wait =104;

    Button mainAllPlay;
    Button mainScenarioPlay;
    Button mainDetailPlay;
    Button mainWait;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kai_main);

        mainAllPlay = (Button) findViewById(R.id.mainAllPlay);
        mainAllPlay.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), AllPlayActivity.class);
                startActivityForResult(intent, REQUEST_CODE_AllPlay);
            }
        }); //end cooditoon listner

        mainScenarioPlay = (Button) findViewById(R.id.mainScenarioPlay);
        mainScenarioPlay.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ScenarioPlay.class);
                startActivityForResult(intent, REQUEST_CODE_ScenarioPlay);
            }
        }); //end cooditoon listner

        mainDetailPlay = (Button) findViewById(R.id.mainDetailPlay);
        mainDetailPlay.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DetailPlayActivity.class);
                startActivityForResult(intent, REQUEST_CODE_Wait);
            }
        }); //end cooditoon listner

        mainWait = (Button) findViewById(R.id.mainWait);
        mainWait.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), WaitActicity.class);
                startActivityForResult(intent, REQUEST_CODE_DetailPlay);
            }
        }); //end cooditoon listner


    }
}
