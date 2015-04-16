package www.cmsmarthome.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Setting_Lamp extends Activity {

    User u1 = new User();
    SumTime s1 = new SumTime();
    Graph g1 = new Graph();

    private String TimerID;
    private String UserDetailsID;
    private String IP_Address;

    private Button btnSetMax;
    private Button btnSetWatt;
    private Button btnClearTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_lamp);

        Intent intent = getIntent();
        UserDetailsID = intent.getStringExtra("UserDetailsID");
        TimerID = intent.getStringExtra("TimerID");

        //get IP and Port
        u1.getData(UserDetailsID);
        IP_Address = u1.strIP;
        //

        btnSetMax = (Button) findViewById(R.id.btn_setMax);
        btnSetWatt = (Button) findViewById(R.id.btn_setWatt);
        btnClearTime = (Button) findViewById(R.id.btn_clear);

        final AlertDialog.Builder Dialog = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        final AlertDialog.Builder DialogResetTime = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        btnSetMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final View Viewlayout = inflater.inflate(R.layout.setmaximum_dialog, (ViewGroup) findViewById(R.id.layout_setmaximum));
                Dialog.setIcon(R.drawable.ic_launcher);
                Dialog.setTitle("อายุการใช้งานสูงสุดของหลอดไฟ");
                Dialog.setView(Viewlayout);

                final EditText mBedRoom1 = (EditText) Viewlayout.findViewById(R.id.etMaxBed1);
                mBedRoom1.setSelection(mBedRoom1.getText().length());
                final EditText mBedRoom2 = (EditText) Viewlayout.findViewById(R.id.etMaxBed2);
                final EditText mToilet = (EditText) Viewlayout.findViewById(R.id.etMaxToi);
                final EditText mSaloonRoom1 = (EditText) Viewlayout.findViewById(R.id.etMaxSal1);
                final EditText mSaloonRoom2 = (EditText) Viewlayout.findViewById(R.id.etMaxSal2);
                final EditText mSaloonRoom3 = (EditText) Viewlayout.findViewById(R.id.etMaxSal3);
                final EditText mSaloonRoom4 = (EditText) Viewlayout.findViewById(R.id.etMaxSal4);
                final EditText mSaloonRoom5 = (EditText) Viewlayout.findViewById(R.id.etMaxSal5);
                final EditText mOfficeRoom1 = (EditText) Viewlayout.findViewById(R.id.etMaxOff1);
                final EditText mOfficeRoom2 = (EditText) Viewlayout.findViewById(R.id.etMaxOff2);
                final EditText mCookRoom1 = (EditText) Viewlayout.findViewById(R.id.etMaxCok1);
                final EditText mCookRoom2 = (EditText) Viewlayout.findViewById(R.id.etMaxCok2);
                final EditText mParkRoom1 = (EditText) Viewlayout.findViewById(R.id.etMaxFon1);
                final EditText mParkRoom2 = (EditText) Viewlayout.findViewById(R.id.etMaxFon2);
                final EditText mFontDoor = (EditText) Viewlayout.findViewById(R.id.etMaxPar);

                //get MaxTime
                s1.getMaxTime(IP_Address);
                mBedRoom1.setText(s1.mBedroom1);
                mBedRoom2.setText(s1.mBedroom2);
                mToilet.setText(s1.mToiletroom1);
                mSaloonRoom1.setText(s1.mSaloonroom1);
                mSaloonRoom2.setText(s1.mSaloonroom2);
                mSaloonRoom3.setText(s1.mSaloonroom3);
                mSaloonRoom4.setText(s1.mSaloonroom4);
                mSaloonRoom5.setText(s1.mSaloonroom5);
                mOfficeRoom1.setText(s1.mOfficeroom1);
                mOfficeRoom2.setText(s1.mOfficeroom2);
                mCookRoom1.setText(s1.mCookroom1);
                mCookRoom2.setText(s1.mCookroom2);
                mParkRoom1.setText(s1.mParkroom1);
                mParkRoom2.setText(s1.mParkroom2);
                mFontDoor.setText(s1.mFontDoor1);
                //End get MaxTime

                // Button OK
                Dialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        //close Dialog
                        dialog.dismiss();

                        String BedR1, BedR2, ToiR, Sal1, Sal2, Sal3, Sal4, Sal5, Or1, Or2, Co1, Co2, Pr1, Pr2, Fd;

                        BedR1 = mBedRoom1.getText().toString();
                        BedR2 = mBedRoom2.getText().toString();
                        ToiR = mToilet.getText().toString();
                        Sal1 = mSaloonRoom1.getText().toString();
                        Sal2 = mSaloonRoom2.getText().toString();
                        Sal3 = mSaloonRoom3.getText().toString();
                        Sal4 = mSaloonRoom4.getText().toString();
                        Sal5 = mSaloonRoom5.getText().toString();
                        Or1 = mOfficeRoom1.getText().toString();
                        Or2 = mOfficeRoom2.getText().toString();
                        Co1 = mCookRoom1.getText().toString();
                        Co2 = mCookRoom2.getText().toString();
                        Pr1 = mParkRoom1.getText().toString();
                        Pr2 = mParkRoom2.getText().toString();
                        Fd = mFontDoor.getText().toString();

                        s1.setMaxTime(BedR1, BedR2, ToiR, Sal1, Sal2, Sal3, Sal4, Sal5, Or1, Or2, Co1, Co2, Pr1, Pr2, Fd, IP_Address);
                        Toast.makeText(getBaseContext(), "ทำการบันทึกสำเร็จแล้ว", Toast.LENGTH_SHORT).show();

                    }
                })
                        // Button Cancel
                        .setNegativeButton("ยกเลิก",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                Dialog.create();
                Dialog.show();
            }
        });

        btnSetWatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View Viewlayout = inflater.inflate(R.layout.setwatt_dialog, (ViewGroup) findViewById(R.id.layout_setwatt));
                Dialog.setIcon(R.drawable.ic_launcher);
                Dialog.setTitle("ระบุค่า watt ของหลอดไฟ");
                Dialog.setView(Viewlayout);

                final EditText wBedRoom1 = (EditText) Viewlayout.findViewById(R.id.etWattBed1);
                wBedRoom1.setSelection(wBedRoom1.getText().length());
                final EditText wBedRoom2 = (EditText) Viewlayout.findViewById(R.id.etWattBed2);
                final EditText wToilet = (EditText) Viewlayout.findViewById(R.id.etWattToi);
                final EditText wSaloonRoom1 = (EditText) Viewlayout.findViewById(R.id.etWattSal1);
                final EditText wSaloonRoom2 = (EditText) Viewlayout.findViewById(R.id.etWattSal2);
                final EditText wSaloonRoom3 = (EditText) Viewlayout.findViewById(R.id.etWattSal3);
                final EditText wSaloonRoom4 = (EditText) Viewlayout.findViewById(R.id.etWattSal4);
                final EditText wSaloonRoom5 = (EditText) Viewlayout.findViewById(R.id.etWattSal5);
                final EditText wOfficeRoom1 = (EditText) Viewlayout.findViewById(R.id.etWattOff1);
                final EditText wOfficeRoom2 = (EditText) Viewlayout.findViewById(R.id.etWattOff2);
                final EditText wCookRoom1 = (EditText) Viewlayout.findViewById(R.id.etWattCok1);
                final EditText wCookRoom2 = (EditText) Viewlayout.findViewById(R.id.etWattCok2);
                final EditText wParkRoom1 = (EditText) Viewlayout.findViewById(R.id.etWattFon1);
                final EditText wParkRoom2 = (EditText) Viewlayout.findViewById(R.id.etWattFon2);
                final EditText wFontDoor = (EditText) Viewlayout.findViewById(R.id.etWattPar);

                //get Watts
                g1.getWatt(IP_Address);
                wBedRoom1.setText(g1.wBedroom1);
                wBedRoom2.setText(g1.wBedroom2);
                wToilet.setText(g1.wToiletroom1);
                wSaloonRoom1.setText(g1.wSaloonroom1);
                wSaloonRoom2.setText(g1.wSaloonroom2);
                wSaloonRoom3.setText(g1.wSaloonroom3);
                wSaloonRoom4.setText(g1.wSaloonroom4);
                wSaloonRoom5.setText(g1.wSaloonroom5);
                wOfficeRoom1.setText(g1.wOfficeroom1);
                wOfficeRoom2.setText(g1.wOfficeroom2);
                wCookRoom1.setText(g1.wCookroom1);
                wCookRoom2.setText(g1.wCookroom2);
                wParkRoom1.setText(g1.wParkroom1);
                wParkRoom2.setText(g1.wParkroom2);
                wFontDoor.setText(g1.wFontDoor1);
                //End get MaxTime

                // Button OK
                Dialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        //close Dialog
                        dialog.dismiss();

                        String BedR1, BedR2, ToiR, Sal1, Sal2, Sal3, Sal4, Sal5, Or1, Or2, Co1, Co2, Pr1, Pr2, Fd;

                        BedR1 = wBedRoom1.getText().toString();
                        BedR2 = wBedRoom2.getText().toString();
                        ToiR = wToilet.getText().toString();
                        Sal1 = wSaloonRoom1.getText().toString();
                        Sal2 = wSaloonRoom2.getText().toString();
                        Sal3 = wSaloonRoom3.getText().toString();
                        Sal4 = wSaloonRoom4.getText().toString();
                        Sal5 = wSaloonRoom5.getText().toString();
                        Or1 = wOfficeRoom1.getText().toString();
                        Or2 = wOfficeRoom2.getText().toString();
                        Co1 = wCookRoom1.getText().toString();
                        Co2 = wCookRoom2.getText().toString();
                        Pr1 = wParkRoom1.getText().toString();
                        Pr2 = wParkRoom2.getText().toString();
                        Fd = wFontDoor.getText().toString();

                        g1.setWatt(BedR1, BedR2, ToiR, Sal1, Sal2, Sal3, Sal4, Sal5, Or1, Or2, Co1, Co2, Pr1, Pr2, Fd, IP_Address);
                        Toast.makeText(getBaseContext(), "ทำการบันทึกสำเร็จแล้ว", Toast.LENGTH_SHORT).show();

                    }
                })
                        // Button Cancel
                        .setNegativeButton("ยกเลิก",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                Dialog.create();
                Dialog.show();
            }
        });

        btnClearTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View ViewlayoutReset = inflater.inflate(R.layout.reset_time_dialog, (ViewGroup) findViewById(R.id.layout_resetTime));
                DialogResetTime.setIcon(R.drawable.ic_launcher);
                DialogResetTime.setTitle("ล้างเวลาของหลอดไฟ");
                DialogResetTime.setView(ViewlayoutReset);

                final EditText reBr1 = (EditText) ViewlayoutReset.findViewById(R.id.et1);
                final EditText reBr2 = (EditText) ViewlayoutReset.findViewById(R.id.et2);
                final EditText reTr1 = (EditText) ViewlayoutReset.findViewById(R.id.et3);
                final EditText reSr1 = (EditText) ViewlayoutReset.findViewById(R.id.et4);
                final EditText reSr2 = (EditText) ViewlayoutReset.findViewById(R.id.et5);
                final EditText reSr3 = (EditText) ViewlayoutReset.findViewById(R.id.et6);
                final EditText reSr4 = (EditText) ViewlayoutReset.findViewById(R.id.et7);
                final EditText reSr5 = (EditText) ViewlayoutReset.findViewById(R.id.et8);
                final EditText reOr1 = (EditText) ViewlayoutReset.findViewById(R.id.et9);
                final EditText reOr2 = (EditText) ViewlayoutReset.findViewById(R.id.et10);
                final EditText reCr1 = (EditText) ViewlayoutReset.findViewById(R.id.et11);
                final EditText reCr2 = (EditText) ViewlayoutReset.findViewById(R.id.et12);
                final EditText rePr1 = (EditText) ViewlayoutReset.findViewById(R.id.et13);
                final EditText rePr2 = (EditText) ViewlayoutReset.findViewById(R.id.et14);
                final EditText reFd1 = (EditText) ViewlayoutReset.findViewById(R.id.et15);

                final Button btnBr1 = (Button) ViewlayoutReset.findViewById(R.id.btnReset1);
                final Button btnBr2 = (Button) ViewlayoutReset.findViewById(R.id.btnReset2);
                final Button btnTr1 = (Button) ViewlayoutReset.findViewById(R.id.btnReset3);
                final Button btnSr1 = (Button) ViewlayoutReset.findViewById(R.id.btnReset4);
                final Button btnSr2 = (Button) ViewlayoutReset.findViewById(R.id.btnReset5);
                final Button btnSr3 = (Button) ViewlayoutReset.findViewById(R.id.btnReset6);
                final Button btnSr4 = (Button) ViewlayoutReset.findViewById(R.id.btnReset7);
                final Button btnSr5 = (Button) ViewlayoutReset.findViewById(R.id.btnReset8);
                final Button btnOr1 = (Button) ViewlayoutReset.findViewById(R.id.btnReset9);
                final Button btnOr2 = (Button) ViewlayoutReset.findViewById(R.id.btnReset10);
                final Button btnCr1 = (Button) ViewlayoutReset.findViewById(R.id.btnReset11);
                final Button btnCr2 = (Button) ViewlayoutReset.findViewById(R.id.btnReset12);
                final Button btnPr1 = (Button) ViewlayoutReset.findViewById(R.id.btnReset13);
                final Button btnPr2 = (Button) ViewlayoutReset.findViewById(R.id.btnReset14);
                final Button btnFd1 = (Button) ViewlayoutReset.findViewById(R.id.btnReset15);

                //get Sumtimes
                s1.getDataTimer(TimerID, IP_Address);
                reBr1.setText(s1.strBedroom1);
                reBr2.setText(s1.strBedroom2);
                reTr1.setText(s1.strToiletroom1);
                reSr1.setText(s1.strSaloonroom1);
                reSr2.setText(s1.strSaloonroom2);
                reSr3.setText(s1.strSaloonroom3);
                reSr4.setText(s1.strSaloonroom4);
                reSr5.setText(s1.strSaloonroom5);
                reOr1.setText(s1.strOfficeroom1);
                reOr2.setText(s1.strOfficeroom2);
                reCr1.setText(s1.strCookroom1);
                reCr2.setText(s1.strCookroom2);
                rePr1.setText(s1.strParkroom1);
                rePr2.setText(s1.strParkroom2);
                reFd1.setText(s1.strFontDoor1);

                btnBr1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "BedRoom1");
                        reBr1.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา ห้องนอน(หลอดที่ 1) สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnBr2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "BedRoom2");
                        reBr2.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา ห้องนอน(หลอดที่ 2) สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnTr1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "ToiletRoom1");
                        reTr1.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา หลอดไฟห้องน้ำ สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnSr1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "SaloonRoom1");
                        reSr1.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา ห้องรับแขก(หลอดที่ 1) สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnSr2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "SaloonRoom2");
                        reSr2.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา ห้องรับแขก(หลอดที่ 2) สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnSr3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "SaloonRoom3");
                        reSr3.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา ห้องรับแขก(หลอดที่ 3) สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnSr4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "SaloonRoom4");
                        reSr4.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา ห้องรับแขก(หลอดที่ 4) สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnSr5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "SaloonRoom5");
                        reSr5.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา ห้องรับแขก(หลอดที่ 5) สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnOr1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "OfficeRoom1");
                        reOr1.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา ห้องทำงาน(หลอดที่ 1) สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnOr2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "OfficeRoom2");
                        reOr2.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา ห้องทำงาน(หลอดที่ 2) สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnCr1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "CookRoom1");
                        reCr1.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา ห้องครัว(หลอดที่ 1) สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnCr2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "CookRoom2");
                        reCr2.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา ห้องครัว(หลอดที่ 2) สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnPr1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "ParkRoom1");
                        rePr1.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา หน้าบ้าน(หลอดที่ 1) สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnPr2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "ParkRoom2");
                        rePr2.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา หน้าบ้าน(หลอดที่ 2) สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });
                btnFd1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.ResetData(IP_Address, "FontDoor1");
                        reFd1.setText("00:00:00");
                        Toast.makeText(getBaseContext(), "ล้างเวลา หลอดไฟที่จอดรถ สำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    }
                });

                DialogResetTime.setNegativeButton("ยกเลิก",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                DialogResetTime.create();
                DialogResetTime.show();
            }
        });
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
}
