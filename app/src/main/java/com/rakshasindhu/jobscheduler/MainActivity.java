package com.rakshasindhu.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Button btnStart;
    private Button btnCancel;
    private JobScheduler jobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.start);
        btnCancel = (Button) findViewById(R.id.stop);

        jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

       /* btnStart.setOnClickListener(this);
        btnCancel.setOnClickListener(this);*/

       btnStart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(getPackageName(),MyExampleJob.class.getName()));

               //run job service after every 5 seconds
               builder.setPeriodic(5000);
               jobScheduler.schedule(builder.build());

           }
       });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobScheduler.cancelAll();
                Toast.makeText(MainActivity.this, "cancel job", Toast.LENGTH_SHORT).show();
            }
        });
    }

   @Override
    protected void onPause() {
        super.onPause();
        jobScheduler.cancelAll(); //remove job service onPause
    }


    /*@Override
    public void onClick(View view) {
        int id= view.getId();
        if(id==R.id.start)
        {
            JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(getPackageName(),
                    MyExampleJob.class.getName()));

            //run job service after every 5 seconds
            builder.setPeriodic(5000);
            jobScheduler.schedule(builder.build());


        }
        else if(id==R.id.stop){
            jobScheduler.cancelAll();
            Toast.makeText(MainActivity.this, "cancel job", Toast.LENGTH_SHORT).show();
        }
    }*/
}
