package com.kitaab.corner.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kitaab.corner.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextInputLayout tilName = findViewById(R.id.tilFullName);
        TextInputLayout tilMobile = findViewById(R.id.tilMobileNumber);
        TextInputLayout tilPassword = findViewById(R.id.tilPassword);
        TextInputEditText etName = findViewById(R.id.etFullName);
        TextInputEditText etMobile = findViewById(R.id.etMobileNumber);
        TextInputEditText etPassword = findViewById(R.id.etPassword);
        MaterialButton btnCreate = findViewById(R.id.btnCreateAccount);

        btnCreate.setOnClickListener(v -> {
            boolean nameOk = Form.require(tilName, etName, R.string.error_enter_name);
            boolean mobileOk = Form.require(tilMobile, etMobile, R.string.error_enter_mobile);
            boolean passOk = Form.require(tilPassword, etPassword, R.string.error_enter_password);
            if (nameOk && mobileOk && passOk) {
                startActivity(new Intent(this, HomeActivity.class));
            }
        });
    }
}
