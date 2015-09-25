package com.sample.version16;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class Account extends ActionBarActivity {

    Bundle taskStatus;
    Boolean loggedInStatus = true;

    Button btnNotification;
    Button btnApplicationStatus;
    Button btnInbox;
    Button btnSendMail;
    Button btnNewPost;
    Button btnFollowup;
    Button btnProfile;
    Button btnLogout;

    TextView tvWelcome, tvFirstInstruction;

    ImageView ivArrow;

    Intent returnToStart = new Intent();
    Bundle userLog = new Bundle();
    Bundle userDetail = new Bundle();

    void setMode(int mode){
        switch(mode){
            case 1:
                btnNotification.setVisibility(View.VISIBLE);
                btnApplicationStatus.setVisibility(View.VISIBLE);
                btnInbox.setVisibility(View.VISIBLE);
                btnSendMail.setVisibility(View.VISIBLE);
                btnNewPost.setVisibility(View.VISIBLE);
                btnFollowup.setVisibility(View.VISIBLE);
                btnProfile.setVisibility(View.VISIBLE);
                btnLogout.setVisibility(View.VISIBLE);
                tvFirstInstruction.setVisibility(View.INVISIBLE);
                ivArrow.setVisibility(View.INVISIBLE);
                break;
            case 2:
                btnNotification.setVisibility(View.INVISIBLE);
                btnApplicationStatus.setVisibility(View.INVISIBLE);
                btnInbox.setVisibility(View.INVISIBLE);
                btnSendMail.setVisibility(View.INVISIBLE);
                btnNewPost.setVisibility(View.INVISIBLE);
                btnFollowup.setVisibility(View.INVISIBLE);
                btnProfile.setVisibility(View.VISIBLE);
                btnLogout.setVisibility(View.VISIBLE);
                tvFirstInstruction.setVisibility(View.VISIBLE);
                ivArrow.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        btnNotification = (Button)findViewById(R.id.btnNotification);
        btnApplicationStatus = (Button)findViewById(R.id.btnApplicationStatus);
        btnInbox = (Button)findViewById(R.id.btnInbox);
        btnSendMail = (Button)findViewById(R.id.btnSendMail);
        btnNewPost = (Button)findViewById(R.id.btnNewPost);
        btnFollowup = (Button)findViewById(R.id.btnFollowup);
        btnProfile = (Button)findViewById(R.id.btnProfile);
        btnLogout = (Button)findViewById(R.id.btnLogout);

        tvWelcome = (TextView)findViewById(R.id.tvWelcome);
        tvFirstInstruction = (TextView)findViewById(R.id.tvFirstInstruction);

        ivArrow = (ImageView)findViewById(R.id.ivArrow);

        setMode(1);

        try {
            userDetail = getIntent().getBundleExtra("userDetail").getBundle("userDetail");
            userLog.putAll(userDetail);
        }catch (Exception e){
            userDetail = getIntent().getBundleExtra("userDetail");
            userLog.putAll(userDetail);
            setMode(2);
            Toast.makeText(getApplicationContext(), "Congratulations for making an account.", Toast.LENGTH_LONG).show();
        }

        /*
        try{
            Toast.makeText(getApplicationContext(), Boolean.toString(userDetail.getBundle("userDetail").getBoolean("accommodation")), Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Recursion does not exist", Toast.LENGTH_SHORT).show();
        }*/

        tvWelcome.setText("Welcome "+userLog.getString("user"));

        // read old userLog here

        ///*
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLog.putString("action", "logout");

                userDetail.putAll(userLog);
                returnToStart.putExtra("userDetail", userDetail);
                setResult(RESULT_OK, returnToStart);
                finish();
            }
        });

        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account.this, NewMail.class);
                intent.putExtra("userDetail", userDetail);
                startActivityForResult(intent, 1);


            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account.this, Profile.class);
                intent.putExtra("userDetail", userDetail);
                startActivityForResult(intent, 1);
            }
        });

        //*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        taskStatus = data.getBundleExtra("TASK_STATUS");


        try {
            if (taskStatus.getString("message").equals("message")) {
                Toast.makeText(getApplicationContext(), taskStatus.getString("messageContent"), Toast.LENGTH_LONG).show();
            }
            //else
                //Toast.makeText(getApplicationContext(), "No Message for you", Toast.LENGTH_SHORT).show();

            //Toast.makeText(getApplicationContext(), taskStatus.getString("action"), Toast.LENGTH_SHORT).show();

            if(taskStatus.getString("action").equals("sendMail")){

                if(taskStatus.getBoolean("messageSent")){
                    //Toast.makeText(getApplicationContext(), "")
                }

            }else if(taskStatus.getString("action").equals("profile")){

                if(taskStatus.getBoolean("change")){
                    userLog.putBoolean("accommodation", taskStatus.getBoolean("accommodation"));
                    userLog.putBoolean("firstTime", taskStatus.getBoolean("firstTime"));
                    userLog.putString("name", taskStatus.getString("name"));
                    userLog.putString("email", taskStatus.getString("email"));
                    userLog.putString("mobile", taskStatus.getString("mobile"));

                    userDetail.putAll(userLog);

                    setMode(1);
                }

            }

        }catch (Exception e){  }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
