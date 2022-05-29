package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUser extends AppCompatActivity {

    EditText email,pass;
    ProgressBar pBar;
    Button register;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        pBar = findViewById(R.id.pBar);
        register = findViewById(R.id.btn_submit);
        auth = FirebaseAuth.getInstance();



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String uEmail = email.getText().toString();
                String uPassword = pass.getText().toString();

                pBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                        .addOnCompleteListener(LoginUser.this,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){

                                    pBar.setVisibility(view.INVISIBLE);
                                    email.setText("");
                                    pass.setText("");
                                    Toast.makeText(LoginUser.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginUser.this,MainActivity.class));


                                }else {
                                    pBar.setVisibility(view.INVISIBLE);
                                    email.setText("");
                                    pass.setText("");
                                    Toast.makeText(LoginUser.this, "Something went Wrong", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });

            }
        });


    }
}