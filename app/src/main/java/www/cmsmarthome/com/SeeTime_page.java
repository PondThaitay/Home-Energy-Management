package www.cmsmarthome.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SeeTime_page extends Activity {

    User u1 = new User();
    SumTime s1 = new SumTime();

    private Timer cTime;

    private String TimerID;
    private String UserDetailsID;
    private String IP_Address;

    private EditText etBedroom1;
    private EditText etBedroom2;
    private EditText etToiletroom1;
    private EditText etSaloonroom1;
    private EditText etSaloonroom2;
    private EditText etSaloonroom3;
    private EditText etSaloonroom4;
    private EditText etSaloonroom5;
    private EditText etCookroom1;
    private EditText etCookroom2;
    private EditText etOfficeroom1;
    private EditText etOfficeroom2;
    private EditText etParkroom1;
    private EditText etParkroom2;
    private EditText etFontDoor1;

    private ImageView tvShow1;
    private ImageView tvShow2;
    private ImageView tvShow3;
    private ImageView tvShow4;
    private ImageView tvShow5;
    private ImageView tvShow6;
    private ImageView tvShow7;
    private ImageView tvShow8;
    private ImageView tvShow9;
    private ImageView tvShow10;
    private ImageView tvShow11;
    private ImageView tvShow12;
    private ImageView tvShow13;
    private ImageView tvShow14;
    private ImageView tvShow15;

    private Button btnRefresh;

    private ProgressDialog progressDialog;

    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sumtime_old);

        Intent intent = getIntent();
        UserDetailsID = intent.getStringExtra("UserDetailsID");
        TimerID = intent.getStringExtra("TimerID");

        //get IP and Port
        u1.getData(UserDetailsID);
        IP_Address = u1.strIP;
        //

        etBedroom1 = (EditText) findViewById(R.id.et1Bedroom);
        etBedroom2 = (EditText) findViewById(R.id.et2Bedroom);
        etToiletroom1 = (EditText) findViewById(R.id.et1Toiletroom);
        etSaloonroom1 = (EditText) findViewById(R.id.et1Saloonroom);
        etSaloonroom2 = (EditText) findViewById(R.id.et2Saloonroom);
        etSaloonroom3 = (EditText) findViewById(R.id.et3Saloonroom);
        etSaloonroom4 = (EditText) findViewById(R.id.et4Saloonroom);
        etSaloonroom5 = (EditText) findViewById(R.id.et5Saloonroom);
        etCookroom1 = (EditText) findViewById(R.id.et1Cookroom);
        etCookroom2 = (EditText) findViewById(R.id.et2Cookroom);
        etOfficeroom1 = (EditText) findViewById(R.id.et1Officeroom);
        etOfficeroom2 = (EditText) findViewById(R.id.et2Officeroom);
        etParkroom1 = (EditText) findViewById(R.id.et1Parkroom);
        etParkroom2 = (EditText) findViewById(R.id.et2Parkroom);
        etFontDoor1 = (EditText) findViewById(R.id.et1FontDoor);

        tvShow1 = (ImageView) findViewById(R.id.tvShow1);
        tvShow2 = (ImageView) findViewById(R.id.tvShow2);
        tvShow3 = (ImageView) findViewById(R.id.tvShow3);
        tvShow4 = (ImageView) findViewById(R.id.tvShow4);
        tvShow5 = (ImageView) findViewById(R.id.tvShow5);
        tvShow6 = (ImageView) findViewById(R.id.tvShow6);
        tvShow7 = (ImageView) findViewById(R.id.tvShow7);
        tvShow8 = (ImageView) findViewById(R.id.tvShow8);
        tvShow9 = (ImageView) findViewById(R.id.tvShow9);
        tvShow10 = (ImageView) findViewById(R.id.tvShow10);
        tvShow11 = (ImageView) findViewById(R.id.tvShow11);
        tvShow12 = (ImageView) findViewById(R.id.tvShow12);
        tvShow13 = (ImageView) findViewById(R.id.tvShow13);
        tvShow14 = (ImageView) findViewById(R.id.tvShow14);
        tvShow15 = (ImageView) findViewById(R.id.tvShow15);

        Refresh();

        cTime = new Timer();
        cTime.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Refresh();
                    }
                });
            }
        }, 0, 600000);

        /*btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = ProgressDialog.show(SeeTime_page.this,"", "กำลังตรวจสอบการอัพเดท...");

                new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            messageHandler.sendEmptyMessage(0);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });*/

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setColorScheme(android.R.color.holo_blue_dark,
                android.R.color.holo_blue_light);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeLayout.setRefreshing(false);
                        Refresh();
                        Toast.makeText(getBaseContext(), "อัพเดทข้อมูลเวลาสำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
            }
        });
    }//End onCreate

    /*private Handler messageHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Refresh();
            Toast.makeText(getBaseContext(), "อัพเดทข้อมูลเวลาสำเร็จแล้ว", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    };*/

    public void Refresh() {
        s1.getDataTimer(TimerID, IP_Address);
        s1.getMaxTime(IP_Address);

        etBedroom1.setText(s1.strBedroom1);
        etBedroom2.setText(s1.strBedroom2);

        etToiletroom1.setText(s1.strToiletroom1);

        etSaloonroom1.setText(s1.strSaloonroom1);
        etSaloonroom2.setText(s1.strSaloonroom2);
        etSaloonroom3.setText(s1.strSaloonroom3);
        etSaloonroom4.setText(s1.strSaloonroom4);
        etSaloonroom5.setText(s1.strSaloonroom5);

        etOfficeroom1.setText(s1.strOfficeroom1);
        etOfficeroom2.setText(s1.strOfficeroom2);

        etCookroom1.setText(s1.strCookroom1);
        etCookroom2.setText(s1.strCookroom2);

        etParkroom1.setText(s1.strParkroom1);
        etParkroom2.setText(s1.strParkroom2);

        etFontDoor1.setText(s1.strFontDoor1);

        s1.SplitString(s1.strBedroom1, s1.mBedroom1, s1.strBedroom2, s1.mBedroom2, s1.strToiletroom1, s1.mToiletroom1, s1.strSaloonroom1, s1.mSaloonroom1
                , s1.strSaloonroom2, s1.mSaloonroom2, s1.strSaloonroom3, s1.mSaloonroom3, s1.strSaloonroom4, s1.mSaloonroom4, s1.strSaloonroom5, s1.mSaloonroom5
                , s1.strOfficeroom1, s1.mOfficeroom1, s1.strOfficeroom2, s1.mOfficeroom2, s1.strCookroom1, s1.mCookroom1, s1.strCookroom2, s1.mCookroom2
                , s1.strParkroom1, s1.mParkroom1, s1.strParkroom2, s1.mParkroom2, s1.strFontDoor1, s1.mFontDoor1);

        if (s1.spBedroom1.equals("1")) {
            tvShow1.setImageResource(R.drawable.warning);
        } else {
            tvShow1.setImageResource(R.drawable.before);
        }

        if (s1.spBedroom2.equals("1")) {
            tvShow2.setImageResource(R.drawable.warning);
        } else {
            tvShow2.setImageResource(R.drawable.before);
        }

        if (s1.spToiletroom1.equals("1")) {
            tvShow3.setImageResource(R.drawable.warning);
        } else {
            tvShow3.setImageResource(R.drawable.before);
        }

        if (s1.spSaloonroom1.equals("1")) {
            tvShow4.setImageResource(R.drawable.warning);
        } else {
            tvShow4.setImageResource(R.drawable.before);
        }
        if (s1.spSaloonroom2.equals("1")) {
            tvShow5.setImageResource(R.drawable.warning);
        } else {
            tvShow5.setImageResource(R.drawable.before);
        }
        if (s1.spSaloonroom3.equals("1")) {
            tvShow6.setImageResource(R.drawable.warning);
        } else {
            tvShow6.setImageResource(R.drawable.before);
        }
        if (s1.spSaloonroom4.equals("1")) {
            tvShow7.setImageResource(R.drawable.warning);
        } else {
            tvShow7.setImageResource(R.drawable.before);
        }
        if (s1.spSaloonroom5.equals("1")) {
            tvShow8.setImageResource(R.drawable.warning);
        } else {
            tvShow8.setImageResource(R.drawable.before);
        }


        if (s1.spOfficeroom1.equals("1")) {
            tvShow9.setImageResource(R.drawable.warning);
        } else {
            tvShow9.setImageResource(R.drawable.before);
        }
        if (s1.spOfficeroom2.equals("1")) {
            tvShow10.setImageResource(R.drawable.warning);
        } else {
            tvShow10.setImageResource(R.drawable.before);
        }

        if (s1.spCookroom1.equals("1")) {
            tvShow11.setImageResource(R.drawable.warning);
        } else {
            tvShow11.setImageResource(R.drawable.before);
        }
        if (s1.spCookroom2.equals("1")) {
            tvShow12.setImageResource(R.drawable.warning);
        } else {
            tvShow12.setImageResource(R.drawable.before);
        }

        if (s1.spParkroom1.equals("1")) {
            tvShow13.setImageResource(R.drawable.warning);
        } else {
            tvShow13.setImageResource(R.drawable.before);
        }
        if (s1.spParkroom2.equals("1")) {
            tvShow14.setImageResource(R.drawable.warning);
        } else {
            tvShow14.setImageResource(R.drawable.before);
        }

        if (s1.spFontDoor1.equals("1")) {
            tvShow15.setImageResource(R.drawable.warning);
        } else {
            tvShow15.setImageResource(R.drawable.before);
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

}//End Class SeeTime_page

