package www.cmsmarthome.com;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Graph_page extends FragmentActivity {

    User u1 = new User();
    Graph g1 = new Graph();

    private Button btnBar;
    private Button btnLine;
    private Button btnBarYear;
    private Button btnLineYear;

    private TextView txtUnit;

    private Spinner sDay, sMonth, sYear;

    private String statusD, statusM, statusY;

    private int Pday;
    private int Pmonth;
    private int Pyear;

    private String TimerID;
    private String UserDetailsID;
    private String IP_Address;

    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);

        Intent intent = getIntent();
        UserDetailsID = intent.getStringExtra("UserDetailsID");
        TimerID = intent.getStringExtra("TimerID");

        //get IP and Port
        u1.getData(UserDetailsID);
        IP_Address = u1.strIP;
        //

        btnBar = (Button) findViewById(R.id.button);
        btnLine = (Button) findViewById(R.id.button2);
        //btnBarYear = (Button)findViewById(R.id.button3);
        //btnLineYear = (Button)findViewById(R.id.button4);
        txtUnit = (TextView) findViewById(R.id.textView);
        txtUnit.setPaintFlags(txtUnit.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        sDay = (Spinner) findViewById(R.id.sDay);
        sMonth = (Spinner) findViewById(R.id.sMonth);
        sYear = (Spinner) findViewById(R.id.sYear);

        //Current DD/MM/YYYY
        Pday = Integer.valueOf(g1.d);
        Pmonth = Integer.valueOf(g1.m);
        Pyear = Integer.valueOf(g1.y);

        ToDay();

        //AdapterDay
        final String[] day = getResources().getStringArray(R.array.day);
        ArrayAdapter<String> arrayAdapterDay = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, day);
        sDay.setAdapter(arrayAdapterDay);

        //AdapterMonth
        final String[] month = getResources().getStringArray(R.array.month);
        ArrayAdapter<String> arrayAdapterMonth = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, month);
        sMonth.setAdapter(arrayAdapterMonth);
        sMonth.setGravity(17);

        //AdapterYear
        final String[] year = getResources().getStringArray(R.array.year);
        ArrayAdapter<String> arrayAdapterYear = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, year);
        sYear.setAdapter(arrayAdapterYear);

        sDay.setSelection(Pday);
        sMonth.setSelection(Pmonth);
        sYear.setSelection(Pyear);

        sDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("-")) {
                    statusD = null;
                } else {
                    statusD = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).toString().equals(month[0])) {
                    statusM = null;
                } else if (parent.getItemAtPosition(position).toString().equals(month[1])) {
                    statusM = "1";
                } else if (parent.getItemAtPosition(position).toString().equals(month[2])) {
                    statusM = "2";
                } else if (parent.getItemAtPosition(position).toString().equals(month[3])) {
                    statusM = "3";
                } else if (parent.getItemAtPosition(position).toString().equals(month[4])) {
                    statusM = "4";
                } else if (parent.getItemAtPosition(position).toString().equals(month[5])) {
                    statusM = "5";
                } else if (parent.getItemAtPosition(position).toString().equals(month[6])) {
                    statusM = "6";
                } else if (parent.getItemAtPosition(position).toString().equals(month[7])) {
                    statusM = "7";
                } else if (parent.getItemAtPosition(position).toString().equals(month[8])) {
                    statusM = "8";
                } else if (parent.getItemAtPosition(position).toString().equals(month[9])) {
                    statusM = "9";
                } else if (parent.getItemAtPosition(position).toString().equals(month[10])) {
                    statusM = "10";
                } else if (parent.getItemAtPosition(position).toString().equals(month[11])) {
                    statusM = "11";
                } else if (parent.getItemAtPosition(position).toString().equals(month[12])) {
                    statusM = "12";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("-")) {
                    statusY = null;
                } else {
                    statusY = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        g1.getWatt(IP_Address);

        btnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckSelect();

                System.out.println(g1.wBedroom1 + "\n" + g1.wBedroom2 + "\n" + g1.wToiletroom1 + "\n" + g1.wSaloonroom1 + "\n" + g1.wSaloonroom2 + "\n"
                        + g1.wSaloonroom3 + "\n" + g1.wSaloonroom4 + "\n" + g1.wSaloonroom5 + "\n" + g1.wOfficeroom1 + "\n" + g1.wOfficeroom2 + "\n"
                        + g1.wCookroom1 + "\n" + g1.wCookroom2 + "\n" + g1.wParkroom1 + "\n" + g1.wParkroom2 + "\n" + g1.wFontDoor1);

                //Bar
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new Graph.PlaceholderFragmentBarDM())
                        .commit();
            }
        });

        btnLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckSelect();

                System.out.println(g1.wBedroom1 + "\n" + g1.wBedroom2 + "\n" + g1.wToiletroom1 + "\n" + g1.wSaloonroom1 + "\n" + g1.wSaloonroom2 + "\n"
                        + g1.wSaloonroom3 + "\n" + g1.wSaloonroom4 + "\n" + g1.wSaloonroom5 + "\n" + g1.wOfficeroom1 + "\n" + g1.wOfficeroom2 + "\n"
                        + g1.wCookroom1 + "\n" + g1.wCookroom2 + "\n" + g1.wParkroom1 + "\n" + g1.wParkroom2 + "\n" + g1.wFontDoor1);

                //Line
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new Graph.PlaceholderFragmentLineDM())
                        .commit();
            }
        });

       /* btnBarYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new PlaceholderFragmentBar())
                        .commit();
                txtUnit.setText("หน่วยไฟที่ใช้ : 1,000 หน่วย");
            }
        });

        btnLineYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new PlaceholderFragmentLine())
                        .commit();
                txtUnit.setText("หน่วยไฟที่ใช้ : 1,000 หน่วย");
            }
        });*/

    }//End onCreate

    public void CheckSelect() {
        if (statusD != null && statusM != null && statusY != null) {
            if (statusD.equals(Pday) && statusM.equals(Pmonth) && statusY.equals(Pyear)) {
                String status = "1";
                //Toast.makeText(getBaseContext(),"OK : "+ statusD+"="+Pday,Toast.LENGTH_SHORT).show();
                g1.getTimeRoomDay(statusD, statusM, statusY, status, TimerID, IP_Address);

                System.out.println(g1.BedRoom + "\n" + g1.ToiletRoom + "\n" + g1.SaloonRoom + "\n"
                        + g1.OfficeRoom + "\n" + g1.CookRoom + "\n" + g1.ParkRoom + "\n" + g1.FontDoor);
                txtUnit.setText("หน่วยไฟฟ้ารวม (วัน) : " + String.valueOf(g1.resultDay));
                Toast.makeText(getBaseContext(), "คุณได้เลือกดูกราฟราย วัน", Toast.LENGTH_SHORT).show();
            } else {
                String status = "0";
                //Toast.makeText(getBaseContext(),"Result : "+ statusD+"\n"+statusM+"\n"+statusY+"\n",Toast.LENGTH_SHORT).show();
                g1.getTimeRoomDay(statusD, statusM, statusY, status, TimerID, IP_Address);

                System.out.println(g1.BedRoom + "\n" + g1.ToiletRoom + "\n" + g1.SaloonRoom + "\n"
                        + g1.OfficeRoom + "\n" + g1.CookRoom + "\n" + g1.ParkRoom + "\n" + g1.FontDoor);
                txtUnit.setText("หน่วยไฟฟ้ารวม (วัน) : " + String.valueOf(g1.resultDay));
                Toast.makeText(getBaseContext(), "คุณได้เลือกดูกราฟราย วัน", Toast.LENGTH_SHORT).show();
            }
        } else if (statusD == null && statusM != null && statusY != null) {
            if (statusM.equals(Pmonth) && statusY.equals(Pyear)) {
                //Toast.makeText(getBaseContext(),"OK : "+ statusM +"="+Pmonth + "/" + Pyear,Toast.LENGTH_SHORT).show();
                g1.getTimeRoomMonth(statusM, statusY, "1", TimerID, IP_Address);

                System.out.println(g1.BedRoom + "\n" + g1.ToiletRoom + "\n" + g1.SaloonRoom + "\n"
                        + g1.OfficeRoom + "\n" + g1.CookRoom + "\n" + g1.ParkRoom + "\n" + g1.FontDoor);
                txtUnit.setText("หน่วยไฟฟ้ารวม (เดือน) : " + String.valueOf(g1.resultDay));
                Toast.makeText(getBaseContext(), "คุณได้เลือกดูกราฟราย เดือน", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(getBaseContext(),"Result : "+ statusM +"\n"+statusY,Toast.LENGTH_SHORT).show();
                g1.getTimeRoomMonth(statusM, statusY, "0", TimerID, IP_Address);

                System.out.println(g1.BedRoom + "\n" + g1.ToiletRoom + "\n" + g1.SaloonRoom + "\n"
                        + g1.OfficeRoom + "\n" + g1.CookRoom + "\n" + g1.ParkRoom + "\n" + g1.FontDoor);
                txtUnit.setText("หน่วยไฟฟ้ารวม (เดือน) : " + String.valueOf(g1.resultDay));
                Toast.makeText(getBaseContext(), "คุณได้เลือกดูกราฟราย เดือน", Toast.LENGTH_SHORT).show();
            }
        } else if (statusD == null && statusM == null && statusY != null) {
            if (statusY.equals(Pyear)) {
                //Toast.makeText(getBaseContext(),"OK : "+statusY+"="+Pyear,Toast.LENGTH_SHORT).show();
                g1.getTimeRoomYear(statusY, "1", TimerID, IP_Address);

                System.out.println(g1.BedRoom + "\n" + g1.ToiletRoom + "\n" + g1.SaloonRoom + "\n"
                        + g1.OfficeRoom + "\n" + g1.CookRoom + "\n" + g1.ParkRoom + "\n" + g1.FontDoor);
                txtUnit.setText("หน่วยไฟฟ้ารวม (ปี) : " + String.valueOf(g1.resultDay));
                Toast.makeText(getBaseContext(), "คุณได้เลือกดูกราฟราย ปี", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(getBaseContext(),"Result : "+statusY,Toast.LENGTH_SHORT).show();
                g1.getTimeRoomYear(statusY, "0", TimerID, IP_Address);

                System.out.println(g1.BedRoom + "\n" + g1.ToiletRoom + "\n" + g1.SaloonRoom + "\n"
                        + g1.OfficeRoom + "\n" + g1.CookRoom + "\n" + g1.ParkRoom + "\n" + g1.FontDoor);
                txtUnit.setText("หน่วยไฟฟ้ารวม (ปี) : " + String.valueOf(g1.resultDay));
                Toast.makeText(getBaseContext(), "คุณได้เลือกดูกราฟราย ปี", Toast.LENGTH_SHORT).show();
            }
        } else {
            g1.calculateUnit("0", "0", "0", "0", "0", "0", "0");
            txtUnit.setText("หน่วยไฟฟ้ารวม : 0");
            Toast.makeText(getBaseContext(), "รูปแบบการเลือกไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
        }

        //Bar
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new Graph.PlaceholderFragmentBarDM())
                .commit();
        //Line
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new Graph.PlaceholderFragmentLineDM())
                .commit();

    }

    public void ToDay() {

        g1.getWatt(IP_Address);

        String d = String.valueOf(g1.cDay);
        String m = String.valueOf(g1.m);
        String y = String.valueOf(g1.cYear);

        Log.e("See", d + "\n" + m + "\n" + y);

        String status = "1";
        g1.getTimeRoomDay(d, m, y, status, TimerID, IP_Address);

        //Bar
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new Graph.PlaceholderFragmentBarDM())
                .commit();
        txtUnit.setText("หน่วยไฟฟ้ารวม (วัน) : " + String.valueOf(g1.resultDay));

        System.out.println(g1.BedRoom + "\n" + g1.ToiletRoom + "\n" + g1.SaloonRoom + "\n"
                + g1.OfficeRoom + "\n" + g1.CookRoom + "\n" + g1.ParkRoom + "\n" + g1.FontDoor);

        System.out.println(g1.wBedroom1 + "\n" + g1.wBedroom2 + "\n" + g1.wToiletroom1 + "\n" + g1.wSaloonroom1 + "\n" + g1.wSaloonroom2 + "\n"
                + g1.wSaloonroom3 + "\n" + g1.wSaloonroom4 + "\n" + g1.wSaloonroom5 + "\n" + g1.wOfficeroom1 + "\n" + g1.wOfficeroom2 + "\n"
                + g1.wCookroom1 + "\n" + g1.wCookroom2 + "\n" + g1.wParkroom1 + "\n" + g1.wParkroom2 + "\n" + g1.wFontDoor1);

        Toast.makeText(getBaseContext(), "กราฟวันนี้", Toast.LENGTH_SHORT).show();
    }

    //BackPressed
    public void onBackPressed() {
        final AlertDialog.Builder da1 = new AlertDialog.Builder(this.getParent(), AlertDialog.THEME_HOLO_DARK);

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

}//End Class Graph_page

