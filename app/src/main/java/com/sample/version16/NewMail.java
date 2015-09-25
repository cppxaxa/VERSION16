package com.sample.version16;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewMail extends ActionBarActivity {

    Button btnSend;
    Button btnBack;
    EditText etReceiverAddress;
    EditText etBody;

    Intent returnToMain = new Intent();
    Bundle toReturn = new Bundle();

    Bundle userDetail = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mail);

        userDetail = getIntent().getBundleExtra("userDetail");

        btnSend = (Button)findViewById(R.id.btnSend);
        btnBack = (Button)findViewById(R.id.btnBack);
        etReceiverAddress = (EditText)findViewById(R.id.etReceiverAddress);
        etBody = (EditText)findViewById(R.id.etBody);

        toReturn.putString("action", "sendMail");
        toReturn.putBoolean("messageSent", false);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toReturn.putBoolean("messageSent", true);
                toReturn.putString("message", "message");
                toReturn.putString("messageContent", "Mail is sent. To: "+etReceiverAddress.getText().toString()+", Message: "+etBody.getText().toString());
                toReturn.putString("receiverAddress", etReceiverAddress.getText().toString());
                toReturn.putString("body", etBody.getText().toString());
                returnToMain.putExtra("TASK_STATUS", toReturn);

                setResult(RESULT_OK, returnToMain);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toReturn.putString("message", "message");
                toReturn.putString("messageContent", "Mail was not sent");
                returnToMain.putExtra("TASK_STATUS", toReturn);

                setResult(RESULT_OK, returnToMain);
                finish();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_mail, menu);
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
