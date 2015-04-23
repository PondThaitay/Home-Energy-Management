package www.cmsmarthome.com;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class Tab_Mode extends TabActivity {

    private String getUserDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_mode);

        getUserDetails = getIntent().getStringExtra("UserDetailsID");

        TabHost tabHost = getTabHost();

        // Tab for Saving Mode
        TabSpec SavigMode_Lamp = tabHost.newTabSpec("Saving Mode");
        SavigMode_Lamp.setIndicator("", getResources().getDrawable(R.drawable.saving_mode_tab));
        Intent SavigMode_page = new Intent(this, Mode_page.class);
        SavigMode_page.putExtra("UserDetailsID", getUserDetails);
        SavigMode_Lamp.setContent(SavigMode_page);

        // Tab for Dimmer Mode
        TabSpec DimmerMode_Lamp = tabHost.newTabSpec("Dimmer Mode");
        DimmerMode_Lamp.setIndicator("", getResources().getDrawable(R.drawable.dimmer_mode_tab));
        Intent DimmerMode_page = new Intent(this, DimmerMode_page.class);
        DimmerMode_page.putExtra("UserDetailsID", getUserDetails);
        DimmerMode_Lamp.setContent(DimmerMode_page);

        // Tab for Full Mode
        TabSpec FullMode_Lamp = tabHost.newTabSpec("Full Mode");
        FullMode_Lamp.setIndicator("", getResources().getDrawable(R.drawable.full_mode_tab));
        Intent FullMode_page = new Intent(this, FullMode_page.class);
        FullMode_page.putExtra("UserDetailsID", getUserDetails);
        FullMode_Lamp.setContent(FullMode_page);

        // Adding all TabSpec to TabHost
        tabHost.addTab(SavigMode_Lamp);
        tabHost.addTab(DimmerMode_Lamp);
        tabHost.addTab(FullMode_Lamp);
    }//End onCreate
}//End Class Tab_Mode