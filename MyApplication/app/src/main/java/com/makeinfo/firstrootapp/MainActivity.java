package com.makeinfo.firstrootapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import eu.chainfire.libsuperuser.Shell;

//Created by themakeinfo.com,Promote us !!!

public class MainActivity extends Activity {

    Button reboot,recv,shut,sysui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        reboot = (Button) findViewById(R.id.btn_reb);

        recv = (Button) findViewById(R.id.btn_rec);
        shut = (Button) findViewById(R.id.shut);
        sysui = (Button) findViewById(R.id.SysUi);
        reboot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                (new StartUp()).setContext(v.getContext()).execute("reboot");


            }
        });
        recv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                (new StartUp()).setContext(v.getContext()).execute("recov");

            }
        });
        shut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                (new StartUp()).setContext(v.getContext()).execute("shutdown");

            }
        });
        //Created by themakeinfo.com,Promote us !!!
        sysui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                (new StartUp()).setContext(v.getContext()).execute("sysui");


            }
        });

          }

    private class StartUp extends AsyncTask<String,Void,Void> {


        private Context context = null;
        boolean suAvailable = false;
        //Created by themakeinfo.com,Promote us !!!
        public StartUp setContext(Context context) {
            this.context = context;
            return this;
        }

        @Override
        protected Void doInBackground(String... params) {
            suAvailable = Shell.SU.available();
            if (suAvailable) {

               // suResult = Shell.SU.run(new String[] {"cd data; ls"}); Shell.SU.run("reboot");
               switch (params[0]){
                   case "reboot"  : Shell.SU.run("reboot");break;
                   case "recov"   : Shell.SU.run("reboot recovery");break;
                   case "shutdown": Shell.SU.run("reboot -p");break;
                   //case "sysui"   : Shell.SU.run("am startservice -n com.android.systemui/.SystemUIService");break;
                   case "sysui"   : Shell.SU.run("pkill com.android.systemui");break;
               }
            }
            else{
                Toast.makeText(getApplicationContext(),"Phone not Rooted",Toast.LENGTH_SHORT).show();
            }

           return null;
        }




    }



}
