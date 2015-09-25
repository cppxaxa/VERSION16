package com.sample.version16;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import org.w3c.dom.Text;


public class Profile extends ActionBarActivity {

    Button btnBack;
    Button btnSave;
    EditText etName;
    EditText etEmail;
    EditText etMobile;
    RadioButton rbAccommodationYes;
    RadioButton rbAccommodationNo;
    RadioButton rbFirstTimeYes;
    RadioButton rbFirstTimeNo;

    Bundle toReturn = new Bundle();
    Intent returnToMain = new Intent();

    Bundle userDetail = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userDetail = getIntent().getBundleExtra("userDetail");

        btnBack = (Button)findViewById(R.id.btnBack);
        btnSave = (Button)findViewById(R.id.btnSave);
        etName = (EditText)findViewById(R.id.etName);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etMobile = (EditText)findViewById(R.id.etMobile);
        rbAccommodationYes = (RadioButton)findViewById(R.id.rbAccommodationYes);
        rbAccommodationNo = (RadioButton)findViewById(R.id.rbAccommodationNo);
        rbFirstTimeYes = (RadioButton)findViewById(R.id.rbFirstTimeYes);
        rbFirstTimeNo = (RadioButton)findViewById(R.id.rbFirstTimeNo);

        try{
            etName.setText(userDetail.getString("name"));
            etMobile.setText(userDetail.getString("mobile"));
            etEmail.setText(userDetail.getString("email"));
            if(userDetail.getBoolean("accommodation") == true)
                rbAccommodationYes.setChecked(true);
            else
                rbAccommodationNo.setChecked(true);
            if(userDetail.getBoolean("firstTime") == true)
                rbFirstTimeYes.setChecked(true);
            else
                rbFirstTimeNo.setChecked(true);


        }catch (Exception e){}

        toReturn.putString("action", "profile");
        toReturn.putBoolean("change", false);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toReturn.putString("message", "message");
                toReturn.putString("messageContent", "No changes made to your Profile");

                returnToMain.putExtra("TASK_STATUS", toReturn);
                setResult(RESULT_OK,  returnToMain);
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean accommodation = (rbAccommodationYes.isChecked())?true:false;
                Boolean firstTime = (rbFirstTimeYes.isChecked())?true:false;
                toReturn.putString("message", "message");
                toReturn.putString("messageContent", "Save clicked. Your details are acc:"+accommodation.toString()+", firstTime:"+firstTime.toString()
                                    +", name:"+etName.getText().toString()+", email:"+etEmail.getText().toString()+", mobile:"+etMobile.getText().toString());
                toReturn.putBoolean("accommodation", accommodation);
                toReturn.putBoolean("firstTime", firstTime);
                toReturn.putString("name", etName.getText().toString());
                toReturn.putString("email", etEmail.getText().toString());
                toReturn.putString("mobile", etMobile.getText().toString());

                toReturn.putBoolean("change", true);

                returnToMain.putExtra("TASK_STATUS", toReturn);
                setResult(RESULT_OK,  returnToMain);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
