package com.example.costcontrol;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.costcontrol.Utils.SharedPreferences;
import com.example.costcontrol.persistance.SQLiteManager;
import com.example.costcontrol.persistance.models.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton loginBtn, newAccountBtn, resetPasswordBtn;
    EditText emailInput, passwordInput;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch remmemberLoginSwitch;
    SharedPreferences sp = new SharedPreferences();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginBtn = findViewById(R.id.loginBtn);
        newAccountBtn = findViewById(R.id.newAccountBtn);
        resetPasswordBtn = findViewById(R.id.resetPasswordBtn);
        remmemberLoginSwitch = findViewById(R.id.remmemberLoginSwitch);

        try {
            List<String> stored = sp.SPRead(this);
            if (stored.get(0) != null) {
                User user = Login(stored.get(0), stored.get(1));
                Intent intent = new Intent(this, Trips.class);
                intent.putExtra("userId", user.getId());
                startActivity(intent);
            }

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


        loginBtn.setOnClickListener(v -> {
            try {
                User user = Login(emailInput.getText().toString(), passwordInput.getText().toString());
                if(user==null){
                    Toast.makeText(getBaseContext(), "Usuário não encontrado.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (remmemberLoginSwitch.isChecked()) {
                    sp.SPWrite(user.getEmail(), user.getPassword(), this);
                }
                startActivity(new Intent(this, Trips.class));
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });

        newAccountBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, NewAccountActivity.class));
        });

        resetPasswordBtn.setOnClickListener(v -> {
            Toast.makeText(getBaseContext(), "NÃO IMPLEMENTADO!", Toast.LENGTH_LONG).show();
        });

    }

    public User Login(String email, String password){
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        User user;
        user = sqLiteManager.getUserByEmail(email);
        if(!user.getPassword().equals(password)){
            Toast.makeText(getBaseContext(), "Senha ou email errado.", Toast.LENGTH_LONG).show();
            passwordInput.setText("");
            return null;
        }
        return user;
    }

}