package com.example.kairemotecontrolsignalr;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class PrototypeActivity extends AppCompatActivity {
    LayoutInflater inflater;
    protected ViewGroup headerLayout;
    //FragmentManager fm;
    //FragmentTransaction ft;
    String tempColor;
    String colorString[]= new String[5];
    int monitor_temp_num;
    SignalRConnect signalRConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prototype_button);

        headerLayout = (ViewGroup) findViewById(R.id.cardLinearLayout);

        colorString[0]="red";
        colorString[1]="blue";
        colorString[2]="green";
        colorString[3]="aqua";
        colorString[4]="purple";

        fillHeader(5);

        headerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("push","layoutPush");
                setPupSize();
            }
        });



    }
    // 지구본 클릭해서 ip주소랑 포트 입력하고 인터넷 연결
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.secure_connect_scan: {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("IP 주소 입력");


                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                final TextView ipText = new TextView(this);
                ipText.setText("IP Address");
                linearLayout.addView(ipText);

                final EditText address_input = new EditText(this);
                linearLayout.addView(address_input);

                final TextView portText = new TextView(this);
                portText.setText("Port");
                linearLayout.addView(portText);

                final EditText port_input = new EditText(this);
                linearLayout.addView(port_input);

                alert.setView(linearLayout);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String ip_value = address_input.getText().toString();
                        String port_value = port_input.getText().toString();
                        signalRConnect = new SignalRConnect();
                        signalRConnect.connect(ip_value,port_value);
                    }
                }); //setPositiveButton end
                alert.show();
            }
        } //switch end
        return false;
    } //onOptionsItemSelected end

    private void setPupSize() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int widthPixels = metrics.widthPixels * 4/10;
        int heightPixels = metrics.heightPixels * 4/10;

        Log.d("origin_size",Integer.toString(metrics.heightPixels));
        Log.d("size",Integer.toString(heightPixels));

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(widthPixels,heightPixels);

        params.gravity = Gravity.CENTER;

        headerLayout.setLayoutParams(params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.prototype_menu, menu);
        return true;
    }

    public void fillHeader(int cardCount){
        headerLayout.removeAllViews();

        LayoutInflater cardInflater = getLayoutInflater();
        for (int i=0; i<cardCount; i++){
            View cardView = cardInflater.inflate(R.layout.header_card,headerLayout,false);
            headerLayout.addView(cardView);

            TextView textView = cardView.findViewById(R.id.title);
            textView.setText((i+1)+"번 모니터");
            monitor_temp_num=i+1;
            tempColor = colorString[i];
            Log.d("nowColor",tempColor);

            cardView.setOnClickListener(new View.OnClickListener() {
                int monitor_num= monitor_temp_num;
                String Color = tempColor;
                @Override
                public void onClick(View v) {
                    //프래그먼트 호출
                    //setPupSize();
                    switchFragment(Color, monitor_num);
                }
            });
        }
    }

    public void switchFragment(String color, int monitor_temp_num) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_bottom,R.anim.slide_out_top);
        ft.replace(R.id.frame_layout, new ButtonFragment(color, monitor_temp_num));
        ft.commit();
    }
}