package com.example.costcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewAccountActivity extends AppCompatActivity {

    EditText emailInput, repeatEmailInput, passwordInput, repeatPasswordInput;

    AppCompatButton createAccountBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newaccount);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailInput = findViewById(R.id.emailInput);
        repeatEmailInput = findViewById(R.id.repeatEmailInput);
        passwordInput = findViewById(R.id.passwordInput);
        repeatPasswordInput = findViewById(R.id.repeatPasswordInput);
        createAccountBtn = findViewById(R.id.createAccountBtn);

        //Listener no botão para criar conta
        //Leva para Trips caso a criação da conta retorne sucesso
        createAccountBtn.setOnClickListener(v -> {
            try {
                if (emailInput.getText().equals(repeatEmailInput.getText())) {
                    if (passwordInput.getText().equals(repeatPasswordInput.getText())) {
                        //Chechar dados com o banco
                        startActivity(new Intent(this, Trips.class));
                    } else {
                        Toast.makeText(getBaseContext(), "As senhas são diferentes.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Os emails são diferentes.", Toast.LENGTH_LONG).show();
                }
            } catch (Error e) {
                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
