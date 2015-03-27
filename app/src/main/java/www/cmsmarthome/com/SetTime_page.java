package www.cmsmarthome.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;

public class SetTime_page extends Activity {

    User u1 = new User();
    private String IP_Address;
    private String Port;
    private String UserDetailsID;

    protected static final String DEBUG_TAG = null;

    private String message = "$XPORTSETSW";
    private String AllOff = "$XPORTOFFALL************************************************************************************************************";

    private Socket socket = null;

    private String showSWnum;
    private String LoopSW;

    @SuppressWarnings("unused")
    private int mDate;
    @SuppressWarnings("unused")
    private int mMonth;
    @SuppressWarnings("unused")
    private int mYear;
    @SuppressWarnings("unused")
    private int mDOW;
    private int mHour;
    @SuppressWarnings("unused")
    private int mMinute;
    @SuppressWarnings("unused")
    private int mSec;

    private TimePicker mPickTimeSWON;
    private TimePicker mPickTimeSWOFF;

    OnClickListener checkBox;

    private CheckBox Bedroom;
    private CheckBox Toiletroom;
    private CheckBox Saloonroom;
    private CheckBox Cookroom;
    private CheckBox Officeroom;
    private CheckBox Parkroom;
    private CheckBox FrontDoorroom;
    private CheckBox LoopCheck;

    private TextView RTCview;

    private Button btnSetTime;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settime_lamp);

        Intent intent = getIntent();
        UserDetailsID = intent.getStringExtra("UserDetailsID");

        //get IP and Port
        u1.getData(UserDetailsID);
        IP_Address = u1.strIP;
        Port = u1.strPort;
        //

        mPickTimeSWON = (TimePicker) findViewById(R.id.TimePickerON);
        mPickTimeSWOFF = (TimePicker) findViewById(R.id.TimePickerOFF);

        Bedroom = (CheckBox) findViewById(R.id.chBedroom);
        Toiletroom = (CheckBox) findViewById(R.id.chToilet);
        Saloonroom = (CheckBox) findViewById(R.id.chSaloon);
        Cookroom = (CheckBox) findViewById(R.id.chCook);
        Officeroom = (CheckBox) findViewById(R.id.chOffice);
        Parkroom = (CheckBox) findViewById(R.id.chPark);
        FrontDoorroom = (CheckBox) findViewById(R.id.chFrontDoor);

        LoopCheck = (CheckBox) findViewById(R.id.checkBox1);

        RTCview = (TextView) findViewById(R.id.textViewRTC);

        btnSetTime = (Button) findViewById(R.id.btnSetTimes);

        mPickTimeSWON.setIs24HourView(true);
        mPickTimeSWOFF.setIs24HourView(true);

        final Calendar c = Calendar.getInstance();

        mDate = c.get(Calendar.DATE);
        mMonth = c.get(Calendar.MONTH);
        mYear = c.get(Calendar.YEAR);

        mDOW = c.get(Calendar.DAY_OF_WEEK);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mSec = c.get(Calendar.SECOND);

        mPickTimeSWON.setCurrentHour(mHour);
        mPickTimeSWOFF.setCurrentHour(mHour);

        final AlertDialog.Builder da = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_DARK);
        checkBox = new OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter = 0;

                if (Bedroom.isChecked()) {
                    counter++;
                }
                if (Toiletroom.isChecked()) {
                    counter++;
                }
                if (Saloonroom.isChecked()) {
                    counter++;
                }
                if (Cookroom.isChecked()) {
                    counter++;
                }
                if (Officeroom.isChecked()) {
                    counter++;
                }
                if (Parkroom.isChecked()) {
                    counter++;
                }
                if (FrontDoorroom.isChecked()) {
                    counter++;
                }

                if (counter >= 3) {
                    da.setTitle("เกิดข้อผิดผลาด");
                    da.setIcon(R.drawable.ic_launcher);
                    da.setMessage("เลือกห้องเพื่อตั้งเวลาได้สูงสุด 2 ห้อง");
                    da.setPositiveButton("ตั้งเวลาใหม่", null);
                    da.show();

                    Bedroom.setChecked(false);
                    Toiletroom.setChecked(false);
                    Saloonroom.setChecked(false);
                    Cookroom.setChecked(false);
                    Officeroom.setChecked(false);
                    Parkroom.setChecked(false);
                    FrontDoorroom.setChecked(false);
                }
            }
        };

        Bedroom.setOnClickListener(checkBox);
        Toiletroom.setOnClickListener(checkBox);
        Saloonroom.setOnClickListener(checkBox);
        Cookroom.setOnClickListener(checkBox);
        Officeroom.setOnClickListener(checkBox);
        Parkroom.setOnClickListener(checkBox);
        FrontDoorroom.setOnClickListener(checkBox);

        btnSetTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int counter = 0;
                if (Bedroom.isChecked()) {
                    counter++;
                }
                if (Toiletroom.isChecked()) {
                    counter++;
                }
                if (Saloonroom.isChecked()) {
                    counter++;
                }
                if (Cookroom.isChecked()) {
                    counter++;
                }
                if (Officeroom.isChecked()) {
                    counter++;
                }
                if (Parkroom.isChecked()) {
                    counter++;
                }
                if (FrontDoorroom.isChecked()) {
                    counter++;
                }
                //Check Minimum
                if (counter == 0) {
                    da.setTitle("เกิดข้อผิดผลาด");
                    da.setIcon(R.drawable.ic_launcher);
                    da.setMessage("กรุณาเลือกห้องเพื่อตั้งเวลาควบคุม");
                    da.setPositiveButton("ตั้งเวลาใหม่", null);
                    da.show();
                } else {
                    SyncRTC();

                    try {
                        socket = new Socket(IP_Address, Integer.valueOf(Port));
                        PrintWriter out = new PrintWriter(new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())), true);

                        out.println(AllOff);

                        Thread.sleep(1000);

                        out.println(RTCview.getText().toString());

                        Thread.sleep(1000);

                        showSWnum = CheckSW();
                        LoopSW = CheckLoopSW();

                        out.println(message + showSWnum + ":ON:"
                                + mPickTimeSWON.getCurrentHour() + ":"
                                + mPickTimeSWON.getCurrentMinute()
                                + ":OFF:"
                                + mPickTimeSWOFF.getCurrentHour() + ":"
                                + mPickTimeSWOFF.getCurrentMinute() + ":"
                                + LoopSW
                                + ":*************************************************************************************************************");

                        Thread TCPrev = new Thread(new TCPrevThread());
                        TCPrev.start();

                        out.close();
                        socket.close();

                    } catch (Exception e) {
                        Log.e(DEBUG_TAG, e.toString());
                    }
                }//End Check Minimum
            }
        });

    }//End onCreate

    private String CheckLoopSW() {

        String StCheckSW = null;

        if (LoopCheck.isChecked()) {
            StCheckSW = "1";
        } else {
            StCheckSW = "0";
        }
        return StCheckSW;

    }

    private String CheckSW() {
        String SWnumber = null;

        if (Bedroom.isChecked() == true) {
            SWnumber = "1";
        }
        if (Toiletroom.isChecked() == true) {
            SWnumber = "2";
        }
        if (Saloonroom.isChecked() == true) {
            SWnumber = "3";
        }
        if (Cookroom.isChecked() == true) {
            SWnumber = "4";
        }
        if (Officeroom.isChecked() == true) {
            SWnumber = "5";
        }
        if (Parkroom.isChecked() == true) {
            SWnumber = "6";
        }
        if (FrontDoorroom.isChecked() == true) {
            SWnumber = "7";
        }
        //2 room
        //Multi ขึ้น ด้วย Bedroom 2 ห้อง
        if ((Bedroom.isChecked() == true) && (Toiletroom.isChecked() == true)) {
            SWnumber = "B0";
        }
        if ((Bedroom.isChecked() == true) && (Saloonroom.isChecked() == true)) {
            SWnumber = "B1";
        }
        if ((Bedroom.isChecked() == true) && (Cookroom.isChecked() == true)) {
            SWnumber = "B2";
        }
        if ((Bedroom.isChecked() == true) && (Officeroom.isChecked() == true)) {
            SWnumber = "B3";
        }
        if ((Bedroom.isChecked() == true) && (Parkroom.isChecked() == true)) {
            SWnumber = "B4";
        }
        if ((Bedroom.isChecked() == true) && (FrontDoorroom.isChecked() == true)) {
            SWnumber = "B5";
        }
        //
        //Multi ขึ้น ด้วย Toilet 2 ห้อง
        if ((Toiletroom.isChecked() == true) && (Saloonroom.isChecked() == true)) {
            SWnumber = "T0";
        }
        if ((Toiletroom.isChecked() == true) && (Cookroom.isChecked() == true)) {
            SWnumber = "T1";
        }
        if ((Toiletroom.isChecked() == true) && (Officeroom.isChecked() == true)) {
            SWnumber = "T2";
        }
        if ((Toiletroom.isChecked() == true) && (Parkroom.isChecked() == true)) {
            SWnumber = "T3";
        }
        if ((Toiletroom.isChecked() == true) && (FrontDoorroom.isChecked() == true)) {
            SWnumber = "T4";
        }
        //
        //Multi ขึ้น ด้วย Saloon 2 ห้อง
        if ((Saloonroom.isChecked() == true) && (Cookroom.isChecked() == true)) {
            SWnumber = "S0";
        }
        if ((Saloonroom.isChecked() == true) && (Officeroom.isChecked() == true)) {
            SWnumber = "S1";
        }
        if ((Saloonroom.isChecked() == true) && (Parkroom.isChecked() == true)) {
            SWnumber = "S2";
        }
        if ((Saloonroom.isChecked() == true) && (FrontDoorroom.isChecked() == true)) {
            SWnumber = "S3";
        }
        //
        //Multi ขึ้น ด้วย Cook 2 ห้อง
        if ((Cookroom.isChecked() == true) && (Officeroom.isChecked() == true)) {
            SWnumber = "C0";
        }
        if ((Cookroom.isChecked() == true) && (Parkroom.isChecked() == true)) {
            SWnumber = "C1";
        }
        if ((Cookroom.isChecked() == true) && (FrontDoorroom.isChecked() == true)) {
            SWnumber = "C2";
        }
        //
        //Multi ขึ้น ด้วย Office 2 ห้อง
        if ((Officeroom.isChecked() == true) && (Parkroom.isChecked() == true)) {
            SWnumber = "O0";
        }
        if ((Officeroom.isChecked() == true) && (FrontDoorroom.isChecked() == true)) {
            SWnumber = "O1";
        }
        //
        //Multi ขึ้น ด้วย Park 2 ห้อง
        if ((Parkroom.isChecked() == true) && (FrontDoorroom.isChecked() == true)) {
            SWnumber = "P0";
        }
        //
        //end 2 room

        return SWnumber;
    }

    private void SyncRTC() {
        final Calendar c = Calendar.getInstance();

        mDate = c.get(Calendar.DATE);
        mMonth = c.get(Calendar.MONTH);
        mYear = c.get(Calendar.YEAR);
        mDOW = c.get(Calendar.DAY_OF_WEEK);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mSec = c.get(Calendar.SECOND);

        RTCview.setText(new StringBuilder()
                .append("$XPORTSYNRTC:")
                .append(mDate).append(":").append(pad(mMonth)).append(":").append(mYear).append(":").append(mDOW).append(":")
                .append(pad(mHour)).append(":")
                .append(pad(mMinute)).append(":").append(mSec)
                .append(":*"));
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public class TCPrevThread implements Runnable {

        public void run() {

            try {

                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String msg;

                while ((msg = br.readLine()) != null) {
                    Log.d("Comming Msg: ", msg);
                    // Do compare If Data coming in is correct data
                }

                br.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Do compare If Data coming in is correct data
                        Toast.makeText(getBaseContext(), "ตั้งเวลาสำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }
        }
    }

    //BackPressed
    public void onBackPressed() {
        final AlertDialog.Builder da1 = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_DARK);
        TextView msg = new TextView(this);
        msg.setText("หากต้องการออกจาก App โดยสมบูรณ์กรุณากลับไปหน้าแรกก่อน");
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextSize(18);
        msg.setTextColor(Color.WHITE);

        da1.setTitle("เกิดข้อผิดผลาด");
        da1.setIcon(R.drawable.ic_launcher);
        da1.setView(msg);
        da1.setPositiveButton("ปิด", null);
        da1.show();
    }
    //End BackPressed

}//End Class
