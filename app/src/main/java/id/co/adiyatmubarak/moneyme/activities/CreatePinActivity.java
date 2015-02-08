package id.co.adiyatmubarak.moneyme.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.co.adiyatmubarak.moneyme.R;
import id.co.adiyatmubarak.moneyme.utilities.CommonHelper;
import id.co.adiyatmubarak.moneyme.utilities.DBHelper;
import id.co.adiyatmubarak.moneyme.utilities.FontEnum;

/**
 * Created by Administrator on 2015-02-08.
 */
public class CreatePinActivity extends Activity implements View.OnClickListener, TextWatcher{

    private EditText textCreatePin, textConfirmPin;
    private Button buttonCreatePin;
    private boolean isMatchedFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpin);

        initWidgets();

        // Make the activity not finish when click outside the activity
        setFinishOnTouchOutside(false);
    }

    private void initWidgets() {
        // Initialize widgets
        if (textCreatePin == null || textConfirmPin == null || buttonCreatePin == null) {
            textCreatePin = (EditText) findViewById(R.id.etCreatePin);
            textConfirmPin = (EditText) findViewById(R.id.etConfirmPin);
            buttonCreatePin = (Button) findViewById(R.id.btCreatePin);
        }

        // Set widget typeface
        textCreatePin.setTypeface(CommonHelper.getTypeFace(this, FontEnum.NORMAL));
        textConfirmPin.setTypeface(CommonHelper.getTypeFace(this, FontEnum.NORMAL));
        buttonCreatePin.setTypeface(CommonHelper.getTypeFace(this, FontEnum.NORMAL));

        // Set widget action listener
        textConfirmPin.addTextChangedListener(this);
        buttonCreatePin.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {}

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btCreatePin) {
            // Get filled user pin
            String pin = textCreatePin.getText().toString().trim();

            // Check if the fields is not empty
            if (!pin.isEmpty() && !textConfirmPin.getText().toString().trim().isEmpty()) {
                if (isMatchedFlag) {
                    DBHelper db = DBHelper.getInstance(this);
                    db.createPin(Integer.parseInt(pin));
                    finish();
                }
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Make the textfields font into green if confirm pin matches with the created pin
        // and make it red if not then rollback to black if comfirm field empty.
        if (textConfirmPin.getText().toString().isEmpty()) {
            textCreatePin.setTextColor(getResources().getColor(android.R.color.background_dark));
            textConfirmPin.setTextColor(getResources().getColor(android.R.color.background_dark));
        } else if (textConfirmPin.getText().toString().trim().equals(textCreatePin.getText().toString().trim())) {
            textCreatePin.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            textConfirmPin.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            // update flat to true
            isMatchedFlag = true;
        } else {
            textCreatePin.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            textConfirmPin.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {}

}
