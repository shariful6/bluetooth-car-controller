package com.shariful.bt_car_controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button i1,left,right,back,hornBtn;
    ToggleButton toggleButton,tog1,tog2,tog3,tog4;
    TextView t1;
    SeekBar seekBar;
    TextView seekTV;

    EditText ip;
    ToggleButton viewBtn;
    WebView webView;
    LinearLayout linearLayout;

    String address = null , name=null;

    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    Set<BluetoothDevice> pairedDevices;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Arduino BT Car Controller");

        seekBar=findViewById(R.id.seekbarID);
        seekTV=findViewById(R.id.valueOfSeekbarID);

        webView=findViewById(R.id.webviewID);
        ip=findViewById(R.id.ip_addressETID);
        viewBtn=findViewById(R.id.viewBtnID);
        linearLayout=findViewById(R.id.webLayoutID);



           viewBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   if (viewBtn.isChecked())
                   {

                       String address=ip.getText().toString();
                       if(address.isEmpty())
                       {
                           Toast.makeText(MainActivity.this, ""+"Enter IP Adress !!", Toast.LENGTH_LONG).show();
                       }
                       else
                       {
                           webView.setWebViewClient(new WebViewClient());
                           WebSettings webSettings = webView.getSettings();
                           webSettings.setJavaScriptEnabled(true);
                           linearLayout.setVisibility(View.VISIBLE);
                           webView.loadUrl(address);
                       }

                   }
                   else
                   {

                       linearLayout.setVisibility(View.GONE);

                   }
               }
           });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekTV.setText(progress+"/6");
                led_on_off(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        try {setw();} catch (Exception e) {}

    }

    private void setw() throws IOException
    {
        t1=(TextView)findViewById(R.id.textView1);
        bluetooth_connect_device();



        i1=(Button)findViewById(R.id.button1);
        left=findViewById(R.id.leftID);
        right=findViewById(R.id.rightID);
        back=findViewById(R.id.backwardID);
        hornBtn=findViewById(R.id.hornID);

        toggleButton=findViewById(R.id.toggleBtnID);
        tog1=findViewById(R.id.tog1ID);
        tog2=findViewById(R.id.tog2ID);
        tog3=findViewById(R.id.tog3ID);
        tog4=findViewById(R.id.tog4ID);



        i1.setOnTouchListener(new View.OnTouchListener()
        {   @Override
        public boolean onTouch(View v, MotionEvent event){
            if(event.getAction() == MotionEvent.ACTION_DOWN) {led_on_off("a");}
            if(event.getAction() == MotionEvent.ACTION_UP){led_on_off("s");}
            return true;}
        });

        back.setOnTouchListener(new View.OnTouchListener()
        {   @Override
        public boolean onTouch(View v, MotionEvent event){
            if(event.getAction() == MotionEvent.ACTION_DOWN) {led_on_off("b");}
            if(event.getAction() == MotionEvent.ACTION_UP){led_on_off("s");}
            return true;}
        });

        left.setOnTouchListener(new View.OnTouchListener()
        {   @Override
        public boolean onTouch(View v, MotionEvent event){
            if(event.getAction() == MotionEvent.ACTION_DOWN) {led_on_off("c");}
            if(event.getAction() == MotionEvent.ACTION_UP){led_on_off("s");}
            return true;}
        });

        right.setOnTouchListener(new View.OnTouchListener()
        {   @Override
        public boolean onTouch(View v, MotionEvent event){
            if(event.getAction() == MotionEvent.ACTION_DOWN) {led_on_off("d");}
            if(event.getAction() == MotionEvent.ACTION_UP){led_on_off("s");}
            return true;}
        });

        hornBtn.setOnTouchListener(new View.OnTouchListener()
        {   @Override
        public boolean onTouch(View v, MotionEvent event){
            if(event.getAction() == MotionEvent.ACTION_DOWN) {led_on_off("g");}
            if(event.getAction() == MotionEvent.ACTION_UP){led_on_off("h");}
            return true;}
        });

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked())
                {
                    led_on_off("e");
                }
                else
                {
                    led_on_off("f");
                }
            }
        });

        tog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tog1.isChecked())
                {
                    led_on_off("i");
                }
                else
                {
                    led_on_off("j");
                }
            }
        });

        tog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tog2.isChecked())
                {
                    led_on_off("k");
                }
                else
                {
                    led_on_off("l");
                }
            }
        });

        tog3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tog3.isChecked())
                {
                    led_on_off("m");
                }
                else
                {
                    led_on_off("n");
                }
            }
        });
        tog4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tog4.isChecked())
                {
                    led_on_off("o");
                }
                else
                {
                    led_on_off("p");
                }
            }
        });

    }
    private void bluetooth_connect_device() throws IOException
    {
        try
        {
            myBluetooth = BluetoothAdapter.getDefaultAdapter();
            address = myBluetooth.getAddress();
            pairedDevices = myBluetooth.getBondedDevices();
            if (pairedDevices.size()>0)
            {
                for(BluetoothDevice bt : pairedDevices)
                {
                    address=bt.getAddress().toString();name = bt.getName().toString();
                    Toast.makeText(getApplicationContext(),"Connected", Toast.LENGTH_SHORT).show();

                }
            }

        }
        catch(Exception we){}
        myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
        BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
        btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
        btSocket.connect();
        try {
            t1.setText("BT Name: "+name+"\nBT Address: "+address);
             }
        catch(Exception e){}
    }

    @Override
    public void onClick(View v) {

        try
        {

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    private void led_on_off(String i)
    {
        try
        {
            if (btSocket!=null)
            {

                btSocket.getOutputStream().write(i.toString().getBytes());
            }

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.manualID)
        {
            Intent intent = new Intent(MainActivity.this,UserManualActivity.class);
            startActivity(intent);
        }

        if (item.getItemId()==R.id.aboutID)
        {
            Intent intent = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent);
        }

        if (item.getItemId()==R.id.shareID)
        {
            Intent myIntent=new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String body="http://play.google.com/store/apps/details?id=com.shariful.bt_car_controller";
            myIntent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(myIntent,"Share Using"));
        }

        if (item.getItemId()==R.id.privacyID)
        {
            Intent intent = new Intent(MainActivity.this,PolicyActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
