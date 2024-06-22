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

import com.example.costcontrol.Utils.ExtraActivity;
import com.example.costcontrol.Utils.SharedPreferences;
import com.example.costcontrol.Utils.SweetAlert;
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

        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);


        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginBtn = findViewById(R.id.loginBtn);
        newAccountBtn = findViewById(R.id.newAccountBtn);
        resetPasswordBtn = findViewById(R.id.resetPasswordBtn);
        remmemberLoginSwitch = findViewById(R.id.remmemberLoginSwitch);

        try {
            //If it comes from NewAccountActivity
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String email = extras.getString("userEmail");
                User user = sqLiteManager.getUserByEmail(email);
                sp.SPWrite(user.getEmail(), user.getPassword(), this);
            }
            //Natural flow
            List<String> stored = sp.SPRead(this);
            if (stored.get(0) != null) {
                User user = Login(stored.get(0), stored.get(1));
                ExtraActivity.start(this, () -> {
                    Intent intent = new Intent(this, Trips.class);
                    return intent.putExtra("userId", user.getId());
                });
            }

        } catch (Exception e) {
            SweetAlert.showErrorDialog(this,e.getMessage());
        }


        loginBtn.setOnClickListener(v -> {
            try {
                User user = Login(emailInput.getText().toString(), passwordInput.getText().toString());
                if(user==null){
                    SweetAlert.showErrorDialog(this,"Usuário não encontrado.");
                    return;
                }
                if (remmemberLoginSwitch.isChecked()) {
                    sp.SPWrite(user.getEmail(), user.getPassword(), this);
                }else{
                    sp.SPWrite(null, null, this);
                }
                ExtraActivity.start(this, () -> {
                    Intent intent = new Intent(this, Trips.class);
                    return intent.putExtra("userId", user.getId());
                });
            } catch (Exception e) {
                SweetAlert.showErrorDialog(this,"Erro ao fazer login.");
            }
        });

        newAccountBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, NewAccountActivity.class));
        });

        resetPasswordBtn.setOnClickListener(v -> {
            SweetAlert.showErrorDialog(this,"Não implementado.");
        });

    }

    public User Login(String email, String password){
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        User user;
        user = sqLiteManager.getUserByEmail(email);
        if(!user.getPassword().equals(password)){
            SweetAlert.showErrorDialog(this,"Senha ou email errados.");
            passwordInput.setText("");
            return null;
        }
        return user;
    }

}