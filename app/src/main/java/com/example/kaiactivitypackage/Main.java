package com.example.kaiactivitypackage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.kairemotecontrolsignalr.R;
import com.example.kairemotecontrolsignalr.SignalRConnect;

import java.util.zip.Inflater;

public class Main extends AppCompatActivity {
    public static final int REQUEST_CODE_AllPlay =101;
    public static final int REQUEST_CODE_ScenarioPlay =102;
    public static final int REQUEST_CODE_DetailPlay =103;
    public static final int REQUEST_CODE_Wait =104;

    ActivityResultLauncher<Intent> activityResultLauncher;

    SignalRConnect signalRConnect;
    FragmentManager fm;
    FragmentTransaction ft;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kai_main);

        getSupportActionBar().setTitle("메인화면");
        fm = getSupportFragmentManager();
        ft =fm.beginTransaction();

        backBtn=findViewById(R.id.back_main);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signalRConnect != null) {
                    signalRConnect.send("BackMain");
                    change_to_frame("Main");
                }
            }
        });
    }//onCreate end

    // 지구본 클릭해서 ip주소랑 포트 입력하고 인터넷 연결
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.secure_connect_scan: {

                if(signalRConnect != null){
                    signalRConnect.disconnect();
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("네트워크 입력");

                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                final TextView ipText = new TextView(this);
                ipText.setText("IP Address");
                linearLayout.addView(ipText);

                final EditText address_input = new EditText(this);
                //address_input.max;
                linearLayout.addView(address_input);

                final TextView portText = new TextView(this);
                portText.setText("Port");
                linearLayout.addView(portText);

                final EditText port_input = new EditText(this);
                linearLayout.addView(port_input);

                LayoutInflater inflater = getLayoutInflater();
                View v1 = inflater.inflate(R.layout.ip_port_alert, null);
                EditText ip_editText = v1.findViewById(R.id.ip_editText);
                EditText port_editText = v1.findViewById(R.id.port_editText);

                alert.setView(v1);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        signalRConnect = new SignalRConnect();
                        //address_input.setText("172.30.1.63");
                        //port_input.setText("58486");

                        String ip_value = address_input.getText().toString();
                        String port_value = port_input.getText().toString();


//                        if(ip_value ==null &&port_value == null){
//                            signalRConnect.connect("172.30.1.146","58486");
//                        }

                        boolean connect = signalRConnect.connect(ip_value,port_value);
                        //Toast.makeText(getActivity(),"인터넷이 연결되지 않음",Toast.LENGTH_SHORT).show();

                        if(connect) {
                            fm.beginTransaction().replace(R.id.controlFrmaeLayout, new MainFragment(signalRConnect)).commit();
                        }else Toast.makeText(getApplicationContext(),"인터넷이 연결되지 않음",Toast.LENGTH_SHORT).show();
                    }
                }); //setPositiveButton end
                alert.show();
            }
        } //switch end
        return false;
    } //onOptionsItemSelected end

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.prototype_menu, menu);
        return true;
    }

    public void change_to_frame(String choosenScenario){
        switch (choosenScenario){
            case "Main" : {
               fm.beginTransaction().replace(R.id.controlFrmaeLayout, new MainFragment(signalRConnect)).commit();
               getSupportActionBar().setTitle("메인화면");
                break;
            }
            case "MainAllPlay": {
                fm.beginTransaction().replace(R.id.controlFrmaeLayout, new AllPlayActivity(signalRConnect)).commit();
                getSupportActionBar().setTitle("전체플레이");
                break;
            }
            case "MainScenarioPlay": {
                fm.beginTransaction().replace(R.id.controlFrmaeLayout, new ScenarioPlay(signalRConnect)).commit();
                getSupportActionBar().setTitle("시나리오별 플레이");
                break;
            }
            case "MainDetailPlay": {

                fm.beginTransaction().replace(R.id.controlFrmaeLayout, new DetailPlayActivity(signalRConnect)).commit();
                getSupportActionBar().setTitle("세부 항목별 플레이");
                break;
            }
            case "MainWait": {
                fm.beginTransaction().replace(R.id.controlFrmaeLayout, new PlayFragment(signalRConnect)).commit();
                break;
            }
        }
    }// change_to_frame

    public void stringResearch(String str){
        for(int i=0;i<str.length();i++){
            str.charAt(i);
        }
    }//stringResearch end

}
