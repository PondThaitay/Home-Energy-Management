package www.cmsmarthome.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class DimmerMode_page extends Activity{

	Control_Process c1 = new Control_Process();
	User u1 = new User();
	
	private String IP_Address;
	private String Port;
	private String UserDetailsID;

	private SeekBar Lamp1;
    private SeekBar Lamp2;
    private SeekBar Lamp3;
    private SeekBar Lamp4;
    private SeekBar Lamp6;
    private SeekBar Lamp7;
    private SeekBar Lamp9;
    private SeekBar Lamp10;
    private SeekBar Lamp11;
    private SeekBar Lamp13;

    private TextView valueLamp1;
    private TextView valueLamp2;
    private TextView valueLamp3;
    private TextView valueLamp4;
    private TextView valueLamp6;
    private TextView valueLamp7;
    private TextView valueLamp9;
    private TextView valueLamp10;
    private TextView valueLamp11;
    private TextView valueLamp13;

    private ImageView imLamp1;
    private ImageView imLamp2;
    private ImageView imLamp3;
    private ImageView imLamp4;
    private ImageView imLamp6;
    private ImageView imLamp7;
    private ImageView imLamp9;
    private ImageView imLamp10;
    private ImageView imLamp11;
    private ImageView imLamp13;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dimmer_mode);
		
		Intent intent= getIntent();
    	UserDetailsID = intent.getStringExtra("UserDetailsID");
		    	
		//get IP and Port
		u1.getData(UserDetailsID);
		IP_Address = u1.strIP;
		Port = u1.strPort;
		//

		Lamp1 = (SeekBar)findViewById(R.id.seekBarLamp1);
        Lamp2 = (SeekBar)findViewById(R.id.seekBarLamp2);
        Lamp3 = (SeekBar)findViewById(R.id.seekBarLamp3);
        Lamp4 = (SeekBar)findViewById(R.id.seekBarLamp4);
        Lamp6 = (SeekBar)findViewById(R.id.seekBarLamp6);
        Lamp7 = (SeekBar)findViewById(R.id.seekBarLamp7);
        Lamp9 = (SeekBar)findViewById(R.id.seekBarLamp9);
        Lamp10 = (SeekBar)findViewById(R.id.seekBarLamp10);
        Lamp11 = (SeekBar)findViewById(R.id.seekBarLamp11);
        Lamp13 = (SeekBar)findViewById(R.id.seekBarLamp13);

        valueLamp1 = (TextView)findViewById(R.id.txtDimLamp1);
        valueLamp2 = (TextView)findViewById(R.id.txtDimLamp2);
        valueLamp3 = (TextView)findViewById(R.id.txtDimLamp3);
        valueLamp4 = (TextView)findViewById(R.id.txtDimLamp4);
        valueLamp6 = (TextView)findViewById(R.id.txtDimLamp6);
        valueLamp7 = (TextView)findViewById(R.id.txtDimLamp7);
        valueLamp9 = (TextView)findViewById(R.id.txtDimLamp9);
        valueLamp10 = (TextView)findViewById(R.id.txtDimLamp10);
        valueLamp11 = (TextView)findViewById(R.id.txtDimLamp11);
        valueLamp13 = (TextView)findViewById(R.id.txtDimLamp13);

        imLamp1 = (ImageView)findViewById(R.id.imageViewDimLamp1);
        imLamp2 = (ImageView)findViewById(R.id.imageViewDimmer2);
        imLamp3 = (ImageView)findViewById(R.id.imageViewDimmer3);
        imLamp4 = (ImageView)findViewById(R.id.imageViewDimmer4);
        imLamp6 = (ImageView)findViewById(R.id.imageViewDimmer6);
        imLamp7 = (ImageView)findViewById(R.id.imageViewDimmer7);
        imLamp9 = (ImageView)findViewById(R.id.imageViewDimmer9);
        imLamp10 = (ImageView)findViewById(R.id.imageViewDimmer10);
        imLamp11 = (ImageView)findViewById(R.id.imageViewDimmer11);
        imLamp13 = (ImageView)findViewById(R.id.imageViewDimmer13);

        //ห้องนอน
		Lamp1.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
	        {
	        	public void onProgressChanged(SeekBar seek_Bar, int progress,boolean fromUser)
	                                                                        
	        	{
                    	int x1=progress;
                        Integer.toString(x1);
						String	message = "$XPORT=DIONL1"+x1;
						c1.Control(IP_Address, Port, message);
                        valueLamp1.setText("ระดับความสว่าง : "+progress+"%");
                        if(progress>0)
                        {
                            imLamp1.setImageResource(R.drawable.onlamp);
                        }
                        else
                        {
                            imLamp1.setImageResource(R.drawable.offlamp);
                        }
	        	}

	        	public void onStartTrackingTouch(SeekBar seek_Bar)
	        	{
	        		// TODO Auto-generated method stub
	        		
	        	}

	        	public void onStopTrackingTouch(SeekBar seek_Bar)
	        	{
	        		// TODO Auto-generated method stub
	        		
	        	}
	        });
        Lamp2.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
            {
            public void onProgressChanged(SeekBar seek_Bar, int progress,boolean fromUser)

            {
                int x2=progress;
                Integer.toString(x2);
                String	message = "$XPORT=DIONL2"+x2;
                c1.Control(IP_Address, Port, message);
                valueLamp2.setText("ระดับความสว่าง : "+progress+"%");
                if(progress>0)
                {
                    imLamp2.setImageResource(R.drawable.onlamp);
                }
                else
                {
                    imLamp2.setImageResource(R.drawable.offlamp);
                }
            }

            public void onStartTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }
        });
        //

        //ห้องน้ำ
        Lamp3.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar seek_Bar, int progress,boolean fromUser)

            {
                int x3=progress;
                Integer.toString(x3);
                String	message = "$XPORT=DIONL3"+x3;
                c1.Control(IP_Address, Port, message);
                valueLamp3.setText("ระดับความสว่าง : "+progress+"%");
                if(progress>0)
                {
                    imLamp3.setImageResource(R.drawable.onlamp);
                }
                else
                {
                    imLamp3.setImageResource(R.drawable.offlamp);
                }
            }

            public void onStartTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }
        });
        //

        //ห้องรับแขก
        Lamp4.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar seek_Bar, int progress,boolean fromUser)

            {
                int x4=progress;
                Integer.toString(x4);
                String	message = "$XPORT=DIONL4"+x4;
                c1.Control(IP_Address, Port, message);
                valueLamp4.setText("ระดับความสว่าง : "+progress+"%");
                if(progress>0)
                {
                    imLamp4.setImageResource(R.drawable.onlamp);
                }
                else
                {
                    imLamp4.setImageResource(R.drawable.offlamp);
                }
            }

            public void onStartTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }
        });

        Lamp6.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar seek_Bar, int progress,boolean fromUser)

            {
                int x6=progress;
                Integer.toString(x6);
                String	message = "$XPORT=DIONL6"+x6;
                c1.Control(IP_Address, Port, message);
                valueLamp6.setText("ระดับความสว่าง : "+progress+"%");
                if(progress>0)
                {
                    imLamp6.setImageResource(R.drawable.onlamp);
                }
                else
                {
                    imLamp6.setImageResource(R.drawable.offlamp);
                }
            }

            public void onStartTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }
        });
        Lamp7.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar seek_Bar, int progress,boolean fromUser)

            {
                int x7=progress;
                Integer.toString(x7);
                String	message = "$XPORT=DIONL7"+x7;
                c1.Control(IP_Address, Port, message);
                valueLamp7.setText("ระดับความสว่าง : "+progress+"%");
                if(progress>0)
                {
                    imLamp7.setImageResource(R.drawable.onlamp);
                }
                else
                {
                    imLamp7.setImageResource(R.drawable.offlamp);
                }
            }

            public void onStartTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }
        });

        //

        //ห้องทำงาน
        Lamp9.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar seek_Bar, int progress,boolean fromUser)

            {
                int x9=progress;
                Integer.toString(x9);
                String	message = "$XPORT=DIONL9"+x9;
                c1.Control(IP_Address, Port, message);
                valueLamp9.setText("ระดับความสว่าง : "+progress+"%");
                if(progress>0)
                {
                    imLamp9.setImageResource(R.drawable.onlamp);
                }
                else
                {
                    imLamp9.setImageResource(R.drawable.offlamp);
                }
            }

            public void onStartTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }
        });
        Lamp10.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar seek_Bar, int progress,boolean fromUser)

            {
                int x10=progress;
                Integer.toString(x10);
                String	message = "$XPORT=DIONLA"+x10;
                c1.Control(IP_Address, Port, message);
                valueLamp10.setText("ระดับความสว่าง : "+progress+"%");
                if(progress>0)
                {
                    imLamp10.setImageResource(R.drawable.onlamp);
                }
                else
                {
                    imLamp10.setImageResource(R.drawable.offlamp);
                }
            }

            public void onStartTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }
        });
        //

        //ห้องครัว
        Lamp11.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar seek_Bar, int progress,boolean fromUser)

            {
                int x11=progress;
                Integer.toString(x11);
                String	message = "$XPORT=DIONLB"+x11;
                c1.Control(IP_Address, Port, message);
                valueLamp11.setText("ระดับความสว่าง : "+progress+"%");
                if(progress>0)
                {
                    imLamp11.setImageResource(R.drawable.onlamp);
                }
                else
                {
                    imLamp11.setImageResource(R.drawable.offlamp);
                }
            }

            public void onStartTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }
        });

        //ที่จอดรถ
        Lamp13.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar seek_Bar, int progress,boolean fromUser)

            {
                int x13=progress;
                Integer.toString(x13);
                String	message = "$XPORT=DIONLG"+x13;
                c1.Control(IP_Address, Port, message);
                valueLamp13.setText("ระดับความสว่าง : "+progress+"%");
                if(progress>0)
                {
                    imLamp13.setImageResource(R.drawable.onlamp);
                }
                else
                {
                    imLamp13.setImageResource(R.drawable.offlamp);
                }
            }

            public void onStartTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seek_Bar)
            {
                // TODO Auto-generated method stub

            }
        });
        //

	}//End onCreate

    //BackPressed
    public void onBackPressed() {
        final AlertDialog.Builder da1 = new AlertDialog.Builder(this.getParent(),AlertDialog.THEME_HOLO_DARK);
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

}//End Class DimmerMode_page
