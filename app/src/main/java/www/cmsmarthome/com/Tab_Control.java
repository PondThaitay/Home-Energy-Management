package www.cmsmarthome.com;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class Tab_Control extends TabActivity {

    private String getUserDetails;
    private String getTimerID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_host);

        getUserDetails = getIntent().getStringExtra("UserDetailsID");
        getTimerID = getIntent().getStringExtra("TimerID");

        TabHost tabHost = getTabHost();

        // Tab for Control Lamp
        TabSpec Control_Lamp = tabHost.newTabSpec("Control Lamp");
        Control_Lamp.setIndicator("", getResources().getDrawable(R.drawable.contol_lamp_tab));
        Intent ctr_page = new Intent(this, ControlLamp_page.class);
        ctr_page.putExtra("UserDetailsID", getUserDetails);
        ctr_page.putExtra("TimerID", getTimerID);
        Control_Lamp.setContent(ctr_page);

        // Tab for Set Time Control
        TabSpec SetTime_Lamp = tabHost.newTabSpec("Set Time Control");
        SetTime_Lamp.setIndicator("", getResources().getDrawable(R.drawable.settime_lamp_tab));
        Intent setTime_page = new Intent(this, SetTime_page.class);
        setTime_page.putExtra("UserDetailsID", getUserDetails);
        SetTime_Lamp.setContent(setTime_page);

        // Tab for Mode Work
        TabSpec Mode_Lamp = tabHost.newTabSpec("Mode");
        Mode_Lamp.setIndicator("", getResources().getDrawable(R.drawable.mode_lamp_tab));
        Intent mode_page = new Intent(this, Tab_Mode.class);
        mode_page.putExtra("UserDetailsID", getUserDetails);
        Mode_Lamp.setContent(mode_page);

        // Tab for See Time Sum Lamp
        TabSpec sTime_Lamp = tabHost.newTabSpec("Time Sum");
        sTime_Lamp.setIndicator("", getResources().getDrawable(R.drawable.seetime_lamp_tab));
        Intent seeTime_page = new Intent(this, SeeTime_page.class);
        seeTime_page.putExtra("UserDetailsID", getUserDetails);
        seeTime_page.putExtra("TimerID", getTimerID);
        sTime_Lamp.setContent(seeTime_page);

        // Tab for Graph Unit
        TabSpec graph = tabHost.newTabSpec("Graph Unit");
        graph.setIndicator("", getResources().getDrawable(R.drawable.graph_lamp_tab));
        Intent graph_page = new Intent(this, Graph_page.class);
        graph_page.putExtra("UserDetailsID", getUserDetails);
        graph_page.putExtra("TimerID", getTimerID);
        graph.setContent(graph_page);

        // Tab for Setting Lamp
        TabSpec SettingLamp = tabHost.newTabSpec("Setting Lamp");
        SettingLamp.setIndicator("", getResources().getDrawable(R.drawable.setting_lamp_tab));
        Intent LampSetting = new Intent(this, Setting_Lamp.class);
        LampSetting.putExtra("UserDetailsID", getUserDetails);
        graph_page.putExtra("TimerID", getTimerID);
        SettingLamp.setContent(LampSetting);

        // Tab for Update Account
        TabSpec UpdateAccount = tabHost.newTabSpec("Account Setting");
        UpdateAccount.setIndicator("", getResources().getDrawable(R.drawable.setting_account_tab));
        Intent AccountSetting = new Intent(this, UpdateAccount_page.class);
        AccountSetting.putExtra("UserDetailsID", getUserDetails);
        UpdateAccount.setContent(AccountSetting);

        // Adding all TabSpec to TabHost
        tabHost.addTab(Control_Lamp);
        tabHost.addTab(SetTime_Lamp);
        tabHost.addTab(Mode_Lamp);
        tabHost.addTab(graph);
        tabHost.addTab(sTime_Lamp);
        tabHost.addTab(UpdateAccount);
        tabHost.addTab(SettingLamp);
    }
}