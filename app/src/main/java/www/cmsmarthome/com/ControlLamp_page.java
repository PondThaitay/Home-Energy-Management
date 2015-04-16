package www.cmsmarthome.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.os.SystemClock;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import InternetDetect.ConnectionDetector;

public class ControlLamp_page extends Activity {

    Context context = this;

    User u1 = new User();
    Control_Process p1 = new Control_Process();
    SumTime s1 = new SumTime();
    Graph g1 = new Graph();

    private String IP_Address;
    private String Port;
    private String Username;
    private String UserDetailsID;
    private String TimerID;

    //voice command
    private Button btnVoice;
    final static int RESULT_SPEECH = 1;
    private String voiceResult;

    private String BedroomON = "turn on bedroom";
    private String BedroomOFF = "turn off bedroom";

    private String ToiletON = "turn on the toilet";
    private String ToiletOFF = "turn off the toilet";

    private String SaloonON = "turn on saloon";
    private String SaloonOFF = "turn off saloon";

    private String CookON = "turn on cook";
    private String CookOFF = "turn off cook";

    private String OfficeON = "turn on office";
    private String OfficeOFF = "turn off office";

    private String ParkON = "turn on park";
    private String ParkOFF = "turn off park";

    private String FrontDoorON = "turn on front door";
    private String FrontDoorOFF = "turn off front door";
    //

    //Status Lamp
    private String statusLamp1;
    private String statusLamp2;
    private String statusLamp3;
    private String statusLamp4;
    private String statusLamp5;
    private String statusLamp6;
    private String statusLamp7;
    private String statusLamp8;
    private String statusLamp9;
    private String statusLamp10;
    private String statusLamp11;
    private String statusLamp12;
    private String statusLamp13;
    private String statusLamp14;
    private String statusLamp15;
    //

    public Timer cTime;

    //Switch Lamp
    private Switch sw1;
    private Switch sw2;
    private Switch sw3;
    private Switch sw4;
    private Switch sw5;
    private Switch sw6;
    private Switch sw7;
    private Switch sw8;
    private Switch sw9;
    private Switch sw10;
    private Switch sw11;
    private Switch sw12;
    private Switch sw13;
    private Switch sw14;
    private Switch sw15;
    //

    //pic Lamp
    private ImageView lamp1;
    private ImageView lamp2;
    private ImageView lamp3;
    private ImageView lamp4;
    private ImageView lamp5;
    private ImageView lamp6;
    private ImageView lamp7;
    private ImageView lamp8;
    private ImageView lamp9;
    private ImageView lamp10;
    private ImageView lamp11;
    private ImageView lamp12;
    private ImageView lamp13;
    private ImageView lamp14;
    private ImageView lamp15;
    //

    Boolean isInternetPresent = false;
    ConnectionDetector cd;

    //wiget for CountTime
    private TextView textTimer1 = null;
    private TextView textTimer2 = null;
    private TextView textTimer3 = null;
    private TextView textTimer4 = null;
    private TextView textTimer5 = null;
    private TextView textTimer6 = null;
    private TextView textTimer7 = null;
    private TextView textTimer8 = null;
    private TextView textTimer9 = null;
    private TextView textTimer10 = null;
    private TextView textTimer11 = null;
    private TextView textTimer12 = null;
    private TextView textTimer13 = null;
    private TextView textTimer14 = null;
    private TextView textTimer15 = null;
    //

    //count time
    private long startTime1 = 0L;
    private long startTime2 = 0L;
    private long startTime3 = 0L;
    private long startTime4 = 0L;
    private long startTime5 = 0L;
    private long startTime6 = 0L;
    private long startTime7 = 0L;
    private long startTime8 = 0L;
    private long startTime9 = 0L;
    private long startTime10 = 0L;
    private long startTime11 = 0L;
    private long startTime12 = 0L;
    private long startTime13 = 0L;
    private long startTime14 = 0L;
    private long startTime15 = 0L;

    private Handler myHandler1 = new Handler();
    private Handler myHandler2 = new Handler();
    private Handler myHandler3 = new Handler();
    private Handler myHandler4 = new Handler();
    private Handler myHandler5 = new Handler();
    private Handler myHandler6 = new Handler();
    private Handler myHandler7 = new Handler();
    private Handler myHandler8 = new Handler();
    private Handler myHandler9 = new Handler();
    private Handler myHandler10 = new Handler();
    private Handler myHandler11 = new Handler();
    private Handler myHandler12 = new Handler();
    private Handler myHandler13 = new Handler();
    private Handler myHandler14 = new Handler();
    private Handler myHandler15 = new Handler();

    long timeInMillies1 = 0L;
    long timeInMillies2 = 0L;
    long timeInMillies3 = 0L;
    long timeInMillies4 = 0L;
    long timeInMillies5 = 0L;
    long timeInMillies6 = 0L;
    long timeInMillies7 = 0L;
    long timeInMillies8 = 0L;
    long timeInMillies9 = 0L;
    long timeInMillies10 = 0L;
    long timeInMillies11 = 0L;
    long timeInMillies12 = 0L;
    long timeInMillies13 = 0L;
    long timeInMillies14 = 0L;
    long timeInMillies15 = 0L;

    long timeSwap1 = 0L;
    long timeSwap2 = 0L;
    long timeSwap3 = 0L;
    long timeSwap4 = 0L;
    long timeSwap5 = 0L;
    long timeSwap6 = 0L;
    long timeSwap7 = 0L;
    long timeSwap8 = 0L;
    long timeSwap9 = 0L;
    long timeSwap10 = 0L;
    long timeSwap11 = 0L;
    long timeSwap12 = 0L;
    long timeSwap13 = 0L;
    long timeSwap14 = 0L;
    long timeSwap15 = 0L;

    long finalTime1 = 0L;
    long finalTime2 = 0L;
    long finalTime3 = 0L;
    long finalTime4 = 0L;
    long finalTime5 = 0L;
    long finalTime6 = 0L;
    long finalTime7 = 0L;
    long finalTime8 = 0L;
    long finalTime9 = 0L;
    long finalTime10 = 0L;
    long finalTime11 = 0L;
    long finalTime12 = 0L;
    long finalTime13 = 0L;
    long finalTime14 = 0L;
    long finalTime15 = 0L;
    //

    private TextView tvCA;

    private Button btnCloseAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_lamp);

        Intent intent = getIntent();
        UserDetailsID = intent.getStringExtra("UserDetailsID");
        TimerID = intent.getStringExtra("TimerID");

        sw1 = (Switch) findViewById(R.id.switchLamp1);
        sw2 = (Switch) findViewById(R.id.switchLamp2);
        sw3 = (Switch) findViewById(R.id.switchLamp3);
        sw4 = (Switch) findViewById(R.id.switchLamp4);
        sw5 = (Switch) findViewById(R.id.switchLamp5);
        sw6 = (Switch) findViewById(R.id.switchLamp6);
        sw7 = (Switch) findViewById(R.id.switchLamp7);
        sw8 = (Switch) findViewById(R.id.switchLamp8);
        sw9 = (Switch) findViewById(R.id.switchLamp9);
        sw10 = (Switch) findViewById(R.id.switchLamp10);
        sw11 = (Switch) findViewById(R.id.switchLamp11);
        sw12 = (Switch) findViewById(R.id.switchLamp12);
        sw13 = (Switch) findViewById(R.id.switchLamp13);
        sw14 = (Switch) findViewById(R.id.switchLamp14);
        sw15 = (Switch) findViewById(R.id.switchLamp15);

        lamp1 = (ImageView) findViewById(R.id.imageViewLamp1);
        lamp2 = (ImageView) findViewById(R.id.imageViewLamp2);
        lamp3 = (ImageView) findViewById(R.id.imageViewLamp3);
        lamp4 = (ImageView) findViewById(R.id.imageViewLamp4);
        lamp5 = (ImageView) findViewById(R.id.imageViewLamp5);
        lamp6 = (ImageView) findViewById(R.id.imageViewLamp6);
        lamp7 = (ImageView) findViewById(R.id.imageViewLamp7);
        lamp8 = (ImageView) findViewById(R.id.imageViewLamp8);
        lamp9 = (ImageView) findViewById(R.id.imageViewLamp9);
        lamp10 = (ImageView) findViewById(R.id.imageViewLamp10);
        lamp11 = (ImageView) findViewById(R.id.imageViewLamp11);
        lamp12 = (ImageView) findViewById(R.id.imageViewLamp12);
        lamp13 = (ImageView) findViewById(R.id.imageViewLamp13);
        lamp14 = (ImageView) findViewById(R.id.imageViewLamp14);
        lamp15 = (ImageView) findViewById(R.id.imageViewLamp15);

        btnVoice = (Button) findViewById(R.id.btnVoice);

        //get IP and Port
        u1.getData(UserDetailsID);
        IP_Address = u1.strIP;
        Port = u1.strPort;
        Username = u1.strUsername;
        //

        textTimer1 = (TextView) findViewById(R.id.countTime1);
        textTimer2 = (TextView) findViewById(R.id.countTime2);
        textTimer3 = (TextView) findViewById(R.id.countTime3);
        textTimer4 = (TextView) findViewById(R.id.countTime4);
        textTimer5 = (TextView) findViewById(R.id.countTime5);
        textTimer6 = (TextView) findViewById(R.id.countTime6);
        textTimer7 = (TextView) findViewById(R.id.countTime7);
        textTimer8 = (TextView) findViewById(R.id.countTime8);
        textTimer9 = (TextView) findViewById(R.id.countTime9);
        textTimer10 = (TextView) findViewById(R.id.countTime10);
        textTimer11 = (TextView) findViewById(R.id.countTime11);
        textTimer12 = (TextView) findViewById(R.id.countTime12);
        textTimer13 = (TextView) findViewById(R.id.countTime13);
        textTimer14 = (TextView) findViewById(R.id.countTime14);
        textTimer15 = (TextView) findViewById(R.id.countTime15);

        tvCA = (TextView) findViewById(R.id.tvCA);
        tvCA.setPaintFlags(tvCA.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btnCloseAll = (Button) findViewById(R.id.btnCloseAll);

        CheckStatus();

        lamp1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsLamps("lamp1");
            }
        });

        lamp2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsLamps("lamp2");
            }
        });

        btnVoice.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-EN");
                startActivityForResult(intent, RESULT_SPEECH);
            }
        });

        sw1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw1.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONL1");
                    StartCountTimeLamp1();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFL1");
                    PauseCountTimeLamp1();
                }
            }
        });

        sw2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw2.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONL2");
                    StartCountTimeLamp2();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFL2");
                    PauseCountTimeLamp2();
                }
            }
        });

        sw3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw3.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONL3");
                    StartCountTimeLamp3();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFL3");
                    PauseCountTimeLamp3();
                }
            }
        });

        sw4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw4.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONL4");
                    StartCountTimeLamp4();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFL4");
                    PauseCountTimeLamp4();
                }
            }
        });

        sw5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw5.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONL5");
                    StartCountTimeLamp5();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFL5");
                    PauseCountTimeLamp5();
                }
            }
        });

        sw6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw6.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONL6");
                    StartCountTimeLamp6();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFL6");
                    PauseCountTimeLamp6();
                }
            }
        });

        sw7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw7.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONLB");
                    StartCountTimeLamp7();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFLB");
                    PauseCountTimeLamp7();
                }
            }
        });

        sw8.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw8.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONLC");
                    StartCountTimeLamp8();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFLC");
                    PauseCountTimeLamp8();
                }
            }
        });

        sw9.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw9.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONL7");
                    StartCountTimeLamp9();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFL7");
                    PauseCountTimeLamp9();
                }
            }
        });

        sw10.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw10.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONL8");
                    StartCountTimeLamp10();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFL8");
                    PauseCountTimeLamp10();
                }
            }
        });

        sw11.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw11.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONL9");
                    StartCountTimeLamp11();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFL9");
                    PauseCountTimeLamp11();
                }
            }
        });

        sw12.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw12.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONLA");
                    StartCountTimeLamp12();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFLA");
                    PauseCountTimeLamp12();
                }
            }
        });

        sw13.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw13.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONR1");
                    StartCountTimeLamp13();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFR1");
                    PauseCountTimeLamp13();
                }
            }
        });

        sw14.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw14.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONR2");
                    StartCountTimeLamp14();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFR2");
                    PauseCountTimeLamp14();
                }
            }
        });

        sw15.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (sw15.isChecked() == true) {
                    p1.Control(IP_Address, Port, "$XPORT=ONLD");
                    StartCountTimeLamp15();
                } else {
                    p1.Control(IP_Address, Port, "$XPORT=OFLD");
                    PauseCountTimeLamp15();
                }
            }
        });

        btnCloseAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgCloseAll = "$XPORT=OFALLL";
                p1.Control(IP_Address, Port, msgCloseAll);
                Toast.makeText(getBaseContext(), "ทำการปิดไฟทุกดวงสำเร็จแล้ว", Toast.LENGTH_SHORT).show();
            }
        });

        updateTimer();

        //s1.UpdateData(textTimer1.getText().toString() , textTimer2.getText().toString());

    }//End onCreate

    //Method Voice is Android Systems
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_SPEECH && resultCode == RESULT_OK && data != null) {
            ArrayList<String> arr_data = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            voiceResult = arr_data.get(0);

            voiceResult.trim();

            if (voiceResult.equalsIgnoreCase(BedroomON)) {
                p1.Control(IP_Address, Port, "$XPORT=ONBEDR");
            } else if (voiceResult.equalsIgnoreCase(BedroomOFF)) {
                p1.Control(IP_Address, Port, "$XPORT=OFBEDR");
            } else if (voiceResult.equalsIgnoreCase(ToiletON)) {
                p1.Control(IP_Address, Port, "$XPORT=ONTOIR");
            } else if (voiceResult.equalsIgnoreCase(ToiletOFF)) {
                p1.Control(IP_Address, Port, "$XPORT=OFTOIR");
            } else if (voiceResult.equalsIgnoreCase(SaloonON)) {
                p1.Control(IP_Address, Port, "$XPORT=ONSALR");
            } else if (voiceResult.equalsIgnoreCase(SaloonOFF)) {
                p1.Control(IP_Address, Port, "$XPORT=OFSALR");
            } else if (voiceResult.equalsIgnoreCase(CookON)) {
                p1.Control(IP_Address, Port, "$XPORT=ONCOOR");
            } else if (voiceResult.equalsIgnoreCase(CookOFF)) {
                p1.Control(IP_Address, Port, "$XPORT=OFCOOR");
            } else if (voiceResult.equalsIgnoreCase(OfficeON)) {
                p1.Control(IP_Address, Port, "$XPORT=ONFICR");
            } else if (voiceResult.equalsIgnoreCase(OfficeOFF)) {
                p1.Control(IP_Address, Port, "$XPORT=OFFICR");
            } else if (voiceResult.equalsIgnoreCase(ParkON)) {
                p1.Control(IP_Address, Port, "$XPORT=ONPARR");
            } else if (voiceResult.equalsIgnoreCase(ParkOFF)) {
                p1.Control(IP_Address, Port, "$XPORT=OFPARR");
            } else if (voiceResult.equalsIgnoreCase(FrontDoorON)) {
                p1.Control(IP_Address, Port, "$XPORT=ONFTDR");
            } else if (voiceResult.equalsIgnoreCase(FrontDoorOFF)) {
                p1.Control(IP_Address, Port, "$XPORT=OFFTDR");
            }

        }

        Toast.makeText(getBaseContext(), voiceResult, Toast.LENGTH_SHORT).show();
    }
//

    //Check Status Lamp
    public void CheckStatus() {
        cTime = new Timer();
        cTime.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Actions goes here
                        cd = new ConnectionDetector(getApplicationContext());
                        isInternetPresent = cd.isConnectingToInternet();
                        //
                        if (isInternetPresent) {
                            p1.CheckStatus(IP_Address, Port);

                            statusLamp1 = p1.getStatusLamp1();
                            statusLamp2 = p1.getStatusLamp2();
                            statusLamp3 = p1.getStatusLamp3();
                            statusLamp4 = p1.getStatusLamp4();
                            statusLamp5 = p1.getStatusLamp5();
                            statusLamp6 = p1.getStatusLamp6();
                            statusLamp7 = p1.getStatusLamp7();
                            statusLamp8 = p1.getStatusLamp8();
                            statusLamp9 = p1.getStatusLamp9();
                            statusLamp10 = p1.getStatusLamp10();
                            statusLamp11 = p1.getStatusLamp11();
                            statusLamp12 = p1.getStatusLamp12();
                            statusLamp13 = p1.getStatusLamp13();
                            statusLamp14 = p1.getStatusLamp14();
                            statusLamp15 = p1.getStatusLamp15();

                            if (statusLamp1.equals("SW1=ON")) {
                                sw1.setChecked(true);
                                lamp1.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp1.equals("SW1=OFF")) {
                                sw1.setChecked(false);
                                lamp1.setImageResource(R.drawable.offlamp);
                            }

                            if (statusLamp2.equals("SW2=ON")) {
                                sw2.setChecked(true);
                                lamp2.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp2.equals("SW2=OFF")) {
                                sw2.setChecked(false);
                                lamp2.setImageResource(R.drawable.offlamp);
                            }

                            if (statusLamp3.equals("SW3=ON")) {
                                sw13.setChecked(true);
                                lamp13.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp3.equals("SW3=OFF")) {
                                sw13.setChecked(false);
                                lamp13.setImageResource(R.drawable.offlamp);
                            }
                            if (statusLamp4.equals("SW4=ON")) {
                                sw14.setChecked(true);
                                lamp14.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp4.equals("SW4=OFF")) {
                                sw14.setChecked(false);
                                lamp14.setImageResource(R.drawable.offlamp);
                            }

                            if (statusLamp5.equals("SW5=ON")) {
                                sw3.setChecked(true);
                                lamp3.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp5.equals("SW5=OFF")) {
                                sw3.setChecked(false);
                                lamp3.setImageResource(R.drawable.offlamp);
                            }

                            if (statusLamp6.equals("SW6=ON")) {
                                sw4.setChecked(true);
                                lamp4.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp6.equals("SW6=OFF")) {
                                sw4.setChecked(false);
                                lamp4.setImageResource(R.drawable.offlamp);
                            }
                            if (statusLamp7.equals("SW7=ON")) {
                                sw5.setChecked(true);
                                lamp5.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp7.equals("SW7=OFF")) {
                                sw5.setChecked(false);
                                lamp5.setImageResource(R.drawable.offlamp);
                            }

                            if (statusLamp8.equals("SW8=ON")) {
                                sw6.setChecked(true);
                                lamp6.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp8.equals("SW8=OFF")) {
                                sw6.setChecked(false);
                                lamp6.setImageResource(R.drawable.offlamp);
                            }

                            if (statusLamp9.equals("SW9=ON")) {
                                sw9.setChecked(true);
                                lamp9.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp9.equals("SW9=OFF")) {
                                sw9.setChecked(false);
                                lamp9.setImageResource(R.drawable.offlamp);
                            }

                            if (statusLamp10.equals("SW10=ON")) {
                                sw10.setChecked(true);
                                lamp10.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp10.equals("SW10=OFF")) {
                                sw10.setChecked(false);
                                lamp10.setImageResource(R.drawable.offlamp);
                            }

                            if (statusLamp11.equals("SW11=ON")) {
                                sw11.setChecked(true);
                                lamp11.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp11.equals("SW11=OFF")) {
                                sw11.setChecked(false);
                                lamp11.setImageResource(R.drawable.offlamp);
                            }

                            if (statusLamp12.equals("SW12=ON")) {
                                sw12.setChecked(true);
                                lamp12.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp12.equals("SW12=OFF")) {
                                sw12.setChecked(false);
                                lamp12.setImageResource(R.drawable.offlamp);
                            }
                            //
                            if (statusLamp13.equals("SW13=ON")) {
                                sw7.setChecked(true);
                                lamp7.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp13.equals("SW13=OFF")) {
                                sw7.setChecked(false);
                                lamp7.setImageResource(R.drawable.offlamp);
                            }
                            //
                            if (statusLamp14.equals("SW14=ON")) {
                                sw8.setChecked(true);
                                lamp8.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp14.equals("SW14=OFF")) {
                                sw8.setChecked(false);
                                lamp8.setImageResource(R.drawable.offlamp);
                            }

                            if (statusLamp15.equals("SW15=ON")) {
                                sw15.setChecked(true);
                                lamp15.setImageResource(R.drawable.onlamp);
                            } else if (statusLamp15.equals("SW15=OFF")) {
                                sw15.setChecked(false);
                                lamp15.setImageResource(R.drawable.offlamp);
                            }
                        } else {
                            Toast.makeText(getBaseContext(), "กรุณาเชื่อมต่อ Internet", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }
        }, 0, 400);//refresh rate time interval (ms)
    }//End Check Status Lamp

    //MethodCountTimeLamp 1
    public Runnable updateTimerMethod1 = new Runnable() {

        public void run() {
            timeInMillies1 = SystemClock.uptimeMillis() - startTime1;
            finalTime1 = timeSwap1 + timeInMillies1;
            int seconds = (int) (finalTime1 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer1.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler1.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp1() {
        startTime1 = SystemClock.uptimeMillis();
        myHandler1.postDelayed(updateTimerMethod1, 0);
    }

    public void PauseCountTimeLamp1() {
        timeSwap1 += timeInMillies1;
        myHandler1.removeCallbacks(updateTimerMethod1);
    }
    //

    //MethodCountTimeLamp 2
    public Runnable updateTimerMethod2 = new Runnable() {

        public void run() {
            timeInMillies2 = SystemClock.uptimeMillis() - startTime2;
            finalTime2 = timeSwap2 + timeInMillies2;
            int seconds = (int) (finalTime2 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer2.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler2.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp2() {
        startTime2 = SystemClock.uptimeMillis();
        myHandler2.postDelayed(updateTimerMethod2, 0);
    }

    public void PauseCountTimeLamp2() {
        timeSwap2 += timeInMillies2;
        myHandler2.removeCallbacks(updateTimerMethod2);
    }
    //

    //MethodCountTimeLamp 3
    public Runnable updateTimerMethod3 = new Runnable() {

        public void run() {
            timeInMillies3 = SystemClock.uptimeMillis() - startTime3;
            finalTime3 = timeSwap3 + timeInMillies3;
            int seconds = (int) (finalTime3 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer3.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler3.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp3() {
        startTime3 = SystemClock.uptimeMillis();
        myHandler3.postDelayed(updateTimerMethod3, 0);
    }

    public void PauseCountTimeLamp3() {
        timeSwap3 += timeInMillies3;
        myHandler3.removeCallbacks(updateTimerMethod3);
    }
    //

    //MethodCountTimeLamp 4
    public Runnable updateTimerMethod4 = new Runnable() {

        public void run() {
            timeInMillies4 = SystemClock.uptimeMillis() - startTime4;
            finalTime4 = timeSwap4 + timeInMillies4;
            int seconds = (int) (finalTime4 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer4.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler4.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp4() {
        startTime4 = SystemClock.uptimeMillis();
        myHandler4.postDelayed(updateTimerMethod4, 0);
    }

    public void PauseCountTimeLamp4() {
        timeSwap4 += timeInMillies4;
        myHandler4.removeCallbacks(updateTimerMethod4);
    }
    //

    //MethodCountTimeLamp 5
    public Runnable updateTimerMethod5 = new Runnable() {

        public void run() {
            timeInMillies5 = SystemClock.uptimeMillis() - startTime5;
            finalTime5 = timeSwap5 + timeInMillies5;
            int seconds = (int) (finalTime5 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer5.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler5.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp5() {
        startTime5 = SystemClock.uptimeMillis();
        myHandler5.postDelayed(updateTimerMethod5, 0);
    }

    public void PauseCountTimeLamp5() {
        timeSwap5 += timeInMillies5;
        myHandler5.removeCallbacks(updateTimerMethod5);
    }
    //

    //MethodCountTimeLamp 6
    public Runnable updateTimerMethod6 = new Runnable() {

        public void run() {
            timeInMillies6 = SystemClock.uptimeMillis() - startTime6;
            finalTime6 = timeSwap6 + timeInMillies6;
            int seconds = (int) (finalTime6 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer6.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler6.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp6() {
        startTime6 = SystemClock.uptimeMillis();
        myHandler6.postDelayed(updateTimerMethod6, 0);
    }

    public void PauseCountTimeLamp6() {
        timeSwap6 += timeInMillies6;
        myHandler6.removeCallbacks(updateTimerMethod6);
    }
    //

    //MethodCountTimeLamp 7
    public Runnable updateTimerMethod7 = new Runnable() {

        public void run() {
            timeInMillies7 = SystemClock.uptimeMillis() - startTime7;
            finalTime7 = timeSwap7 + timeInMillies7;
            int seconds = (int) (finalTime7 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer7.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler7.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp7() {
        startTime7 = SystemClock.uptimeMillis();
        myHandler7.postDelayed(updateTimerMethod7, 0);
    }

    public void PauseCountTimeLamp7() {
        timeSwap7 += timeInMillies7;
        myHandler7.removeCallbacks(updateTimerMethod7);
    }
    //

    //MethodCountTimeLamp 8
    public Runnable updateTimerMethod8 = new Runnable() {

        public void run() {
            timeInMillies8 = SystemClock.uptimeMillis() - startTime8;
            finalTime8 = timeSwap8 + timeInMillies8;
            int seconds = (int) (finalTime8 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer8.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler8.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp8() {
        startTime8 = SystemClock.uptimeMillis();
        myHandler8.postDelayed(updateTimerMethod8, 0);
    }

    public void PauseCountTimeLamp8() {
        timeSwap8 += timeInMillies8;
        myHandler8.removeCallbacks(updateTimerMethod8);
    }
    //

    //MethodCountTimeLamp 9
    public Runnable updateTimerMethod9 = new Runnable() {

        public void run() {
            timeInMillies9 = SystemClock.uptimeMillis() - startTime9;
            finalTime9 = timeSwap9 + timeInMillies9;
            int seconds = (int) (finalTime9 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer9.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler9.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp9() {
        startTime9 = SystemClock.uptimeMillis();
        myHandler9.postDelayed(updateTimerMethod9, 0);
    }

    public void PauseCountTimeLamp9() {
        timeSwap9 += timeInMillies9;
        myHandler9.removeCallbacks(updateTimerMethod9);
    }
    //

    //MethodCountTimeLamp 10
    public Runnable updateTimerMethod10 = new Runnable() {

        public void run() {
            timeInMillies10 = SystemClock.uptimeMillis() - startTime10;
            finalTime10 = timeSwap10 + timeInMillies10;
            int seconds = (int) (finalTime10 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer10.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler10.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp10() {
        startTime10 = SystemClock.uptimeMillis();
        myHandler10.postDelayed(updateTimerMethod10, 0);
    }

    public void PauseCountTimeLamp10() {
        timeSwap10 += timeInMillies10;
        myHandler10.removeCallbacks(updateTimerMethod10);
    }
    //

    //MethodCountTimeLamp 11
    public Runnable updateTimerMethod11 = new Runnable() {

        public void run() {
            timeInMillies11 = SystemClock.uptimeMillis() - startTime11;
            finalTime11 = timeSwap11 + timeInMillies11;
            int seconds = (int) (finalTime11 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer11.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler11.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp11() {
        startTime11 = SystemClock.uptimeMillis();
        myHandler11.postDelayed(updateTimerMethod11, 0);
    }

    public void PauseCountTimeLamp11() {
        timeSwap11 += timeInMillies11;
        myHandler11.removeCallbacks(updateTimerMethod11);
    }
    //

    //MethodCountTimeLamp 12
    public Runnable updateTimerMethod12 = new Runnable() {

        public void run() {
            timeInMillies12 = SystemClock.uptimeMillis() - startTime12;
            finalTime12 = timeSwap12 + timeInMillies12;
            int seconds = (int) (finalTime12 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer12.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler12.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp12() {
        startTime12 = SystemClock.uptimeMillis();
        myHandler12.postDelayed(updateTimerMethod12, 0);
    }

    public void PauseCountTimeLamp12() {
        timeSwap12 += timeInMillies12;
        myHandler12.removeCallbacks(updateTimerMethod12);
    }
    //

    //MethodCountTimeLamp 13
    public Runnable updateTimerMethod13 = new Runnable() {

        public void run() {
            timeInMillies13 = SystemClock.uptimeMillis() - startTime13;
            finalTime13 = timeSwap13 + timeInMillies13;
            int seconds = (int) (finalTime13 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer13.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler13.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp13() {
        startTime13 = SystemClock.uptimeMillis();
        myHandler13.postDelayed(updateTimerMethod13, 0);
    }

    public void PauseCountTimeLamp13() {
        timeSwap13 += timeInMillies13;
        myHandler13.removeCallbacks(updateTimerMethod13);
    }
    //

    //MethodCountTimeLamp 14
    public Runnable updateTimerMethod14 = new Runnable() {

        public void run() {
            timeInMillies14 = SystemClock.uptimeMillis() - startTime14;
            finalTime14 = timeSwap14 + timeInMillies14;
            int seconds = (int) (finalTime14 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer14.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler14.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp14() {
        startTime14 = SystemClock.uptimeMillis();
        myHandler14.postDelayed(updateTimerMethod14, 0);
    }

    public void PauseCountTimeLamp14() {
        timeSwap14 += timeInMillies14;
        myHandler14.removeCallbacks(updateTimerMethod14);
    }
    //

    //MethodCountTimeLamp 15
    public Runnable updateTimerMethod15 = new Runnable() {

        public void run() {
            timeInMillies15 = SystemClock.uptimeMillis() - startTime15;
            finalTime15 = timeSwap15 + timeInMillies15;
            int seconds = (int) (finalTime15 / 1000);
            int minutes = seconds / 60;
            int hour = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hour = hour % 24;

            textTimer15.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));

            myHandler15.postDelayed(this, 0);
        }
    };

    public void StartCountTimeLamp15() {
        startTime15 = SystemClock.uptimeMillis();
        myHandler15.postDelayed(updateTimerMethod15, 0);
    }

    public void PauseCountTimeLamp15() {
        timeSwap15 += timeInMillies15;
        myHandler15.removeCallbacks(updateTimerMethod15);
    }
    //

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String currentDate = df.format(c.getTime());

    public void updateTimer() {
        cTime = new Timer();
        cTime.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        s1.UpdateData(textTimer1.getText().toString(), textTimer2.getText().toString(), textTimer3.getText().toString(), textTimer4.getText().toString()
                                , textTimer5.getText().toString(), textTimer6.getText().toString(), textTimer7.getText().toString(), textTimer8.getText().toString()
                                , textTimer9.getText().toString(), textTimer10.getText().toString(), textTimer11.getText().toString(), textTimer12.getText().toString()
                                , textTimer13.getText().toString(), textTimer14.getText().toString(), textTimer15.getText().toString(), currentDate, TimerID);
                    }
                });
            }
        }, 0, 5000);//refresh rate time interval (ms) 1000ms = 1s
    }

    //BackPressed
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_DARK);
        dialog.setTitle("ออกจากระบบ");
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setCancelable(true);
        dialog.setMessage("คุณต้องการออกจากระบบหรือไม่ ?");
        dialog.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                //Save Time Last Before Exit App
                s1.BackupTime(Username, IP_Address, textTimer1.getText().toString(), textTimer2.getText().toString(), textTimer3.getText().toString(), textTimer4.getText().toString()
                        , textTimer5.getText().toString(), textTimer6.getText().toString(), textTimer7.getText().toString(), textTimer8.getText().toString()
                        , textTimer9.getText().toString(), textTimer10.getText().toString(), textTimer11.getText().toString(), textTimer12.getText().toString()
                        , textTimer13.getText().toString(), textTimer14.getText().toString(), textTimer15.getText().toString(), currentDate);

                //Update DB Timer Before Exit App
                s1.UpdateData("00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00"
                        , "00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00", currentDate, TimerID);

                finish();
                Process.killProcess(Process.myPid());
            }
        });

        dialog.setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
    //End BackPressed

   /* protected void onDestroy()
    {
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }*/

    public void DetailsLamps(String checkLamps) {

        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        final View Viewlayout = inflater.inflate(R.layout.details_lamps,
                (ViewGroup) findViewById(R.id.details_lamps));

        final Button btnDel = (Button) Viewlayout.findViewById(R.id.btnDel);

        final EditText etWatts = (EditText) Viewlayout.findViewById(R.id.etWatts);

        final EditText etSumTime = (EditText) Viewlayout.findViewById(R.id.etTimes);

        final TextView tvSumtime = (TextView) Viewlayout.findViewById(R.id.tvSumtime);

        popDialog.setIcon(R.drawable.settings_icon);
        popDialog.setTitle("ตั้งค่าหลอดไฟ");
        popDialog.setView(Viewlayout);

        //get MaxTime
        s1.getMaxTime(IP_Address);
        //get Watts
        g1.getWatt(IP_Address);
        //get Sumtimes
        s1.getDataTimer(TimerID, IP_Address);

        if (checkLamps.equals("lamp1")) {
            etSumTime.setText(s1.mBedroom1);
            etWatts.setText(g1.wBedroom1);
            tvSumtime.setText("เวลารวม : " + s1.strBedroom1);
        } else if (checkLamps.equals("lamp2")) {
            etSumTime.setText(s1.mBedroom2);
            etWatts.setText(g1.wBedroom2);
            tvSumtime.setText("เวลารวม : " + s1.strBedroom2);
        }

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Jedsada", Toast.LENGTH_SHORT).show();
            }
        });

        // Button OK
        popDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                // txtUsername and Password (Dialog)
                EditText watts = (EditText) Viewlayout.findViewById(R.id.etWatts);
                EditText times = (EditText) Viewlayout.findViewById(R.id.etTimes);

                Toast.makeText(context, watts.getText().toString() + "\n" +
                        times.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        })
                // Button Cancel
                .setNegativeButton("ยกเลิก",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        popDialog.create();
        popDialog.show();
    }

}//End Class ControlLamp_page
