package id.co.adiyatmubarak.moneyme.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.co.adiyatmubarak.moneyme.R;
import id.co.adiyatmubarak.moneyme.utilities.CommonHelper;
import id.co.adiyatmubarak.moneyme.utilities.DBHelper;
import id.co.adiyatmubarak.moneyme.utilities.FontEnum;

public class AuthActivity extends Activity implements View.OnClickListener {

    private TextView labelTitle;
    private Button buttonSignIn;
    private EditText textPin;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        initWidget();

        // Create DBHelper instance
        db = DBHelper.getInstance(this);

        // Check if no user exist in database, then display the create pin activity
        if (!db.isExists()) {
            Intent intent = new Intent(this, CreatePinActivity.class);
            startActivity(intent);
        }
    }

    private void initWidget() {
        // initialize widget
        if (labelTitle == null || buttonSignIn == null || textPin == null) {
            labelTitle = (TextView) findViewById(R.id.idTitle);
            buttonSignIn = (Button) findViewById(R.id.bt_signin);
            textPin = (EditText) findViewById(R.id.etPin);
        }

        // set label font
        labelTitle.setTypeface(CommonHelper.getTypeFace(this, FontEnum.NORMAL));
        buttonSignIn.setTypeface(CommonHelper.getTypeFace(this, FontEnum.NORMAL));
        textPin.setTypeface(CommonHelper.getTypeFace(this, FontEnum.NORMAL));

        // initialize widget action listener
        buttonSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_signin) {
            // Get user input
            String pin = textPin.getText().toString();

            // Check if user input is not empty
            if (!pin.isEmpty()) {
                // Check if user pin less than 6 char
                if (pin.length() < 6) {
                    Toast.makeText(this, "Pin kurang dari 6 karakter.", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if pin valid
                    if (db.isAuthenticated(pin)) {
                        Toast.makeText(this, "BENAR.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "PIN Anda Salah.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
