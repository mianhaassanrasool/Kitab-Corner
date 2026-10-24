package com.kitaab.corner.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kitaab.corner.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputLayout tilUser = findViewById(R.id.tilPhoneOrEmail);
        TextInputLayout tilPassword = findViewById(R.id.tilPassword);
        TextInputEditText etUser = findViewById(R.id.etPhoneOrEmail);
        TextInputEditText etPassword = findViewById(R.id.etPassword);
        MaterialButton btnLogIn = findViewById(R.id.btnLogIn);

        btnLogIn.setOnClickListener(v -> {
            boolean userOk = Form.require(tilUser, etUser, R.string.error_enter_phone_or_email);
            boolean passOk = Form.require(tilPassword, etPassword, R.string.error_enter_password);
            if (userOk && passOk) {
                startActivity(new Intent(this, HomeActivity.class));
            }
        });
    }
}
