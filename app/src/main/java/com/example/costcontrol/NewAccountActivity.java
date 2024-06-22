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

import com.example.costcontrol.API.API;
import com.example.costcontrol.Utils.ExtraActivity;
import com.example.costcontrol.Utils.SharedPreferences;
import com.example.costcontrol.Utils.SweetAlert;
import com.example.costcontrol.persistance.SQLiteManager;
import com.example.costcontrol.persistance.models.User;

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

        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);

        emailInput = findViewById(R.id.emailInput);
        repeatEmailInput = findViewById(R.id.repeatEmailInput);
        passwordInput = findViewById(R.id.passwordInput);
        repeatPasswordInput = findViewById(R.id.repeatPasswordInput);
        createAccountBtn = findViewById(R.id.createAccountBtn);

        //Listener no botão para criar conta
        //Leva para Trips caso a criação da conta retorne sucesso
        createAccountBtn.setOnClickListener(v -> {
            try {
                if (emailInput.getText().toString().equals(repeatEmailInput.getText().toString())) {
                    if (passwordInput.getText().toString().equals(repeatPasswordInput.getText().toString())) {
                        String email = emailInput.getText().toString();
                        String password = passwordInput.getText().toString();

                        User user = new User(email, password);
                        sqLiteManager.addUserToDatabase(user);

                        //O usuário precisa ter um id que não esteja na lista de viagens
                        //
                        //API.getTripsByAccount();
                        //ou
                        //UUID.randomUUID()?

                        ExtraActivity.start(this, () -> {
                            Intent intent = new Intent(this, LoginActivity.class);
                            return ExtraActivity.setUserEmail(intent, user.getEmail());
                        });
                    } else {
                        SweetAlert.showErrorDialog(this, "As senhas são diferentes.");
                    }
                } else {
                    SweetAlert.showErrorDialog(this, "Os emails são diferentes.");
                }
            } catch (Error | Exception e) {
                SweetAlert.showErrorDialog(this, e.getMessage());
            }
        });
    }


}
