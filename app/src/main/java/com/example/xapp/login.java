package com.example.xapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    Button r,l;
    EditText iemail,ipassword;
    FirebaseAuth Auth;
    ProgressDialog progressDialog;
    CheckBox saveLoginCheckBox;

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private String username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* if (Auth.getCurrentUser() != null) {
            startActivity(new Intent(login.this, MainActivity.class));
            finish();
        }*/
        setContentView(R.layout.activity_login);
        Auth = FirebaseAuth.getInstance();
        iemail = (EditText) findViewById(R.id.uname);
        ipassword = (EditText) findViewById(R.id.password);
        saveLoginCheckBox = (CheckBox)findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            iemail.setText(loginPreferences.getString("username", ""));
            ipassword.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }

        //Auth = FirebaseAuth.getInstance();

        addListenerOnButton();
    }
    public void addListenerOnButton() {
        progressDialog = new ProgressDialog(this);
        final Context context = this;
        r = (Button) findViewById(R.id.rbtn);
        l = (Button) findViewById(R.id.lbtn);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Register.class);
                startActivity(intent);
            }
        });
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = iemail.getText().toString();
                password = ipassword.getText().toString();

                if (saveLoginCheckBox.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", username);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }


                final String email = iemail.getText().toString();

                final String password = ipassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Logging in..");
                progressDialog.show();
                Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                ipassword.setError(getString(R.string.minimum_password));
                                progressDialog.dismiss();
                            } else {
                                Toast.makeText(login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                        else {
                            DatabaseReference databaseReference;
                            databaseReference = FirebaseDatabase.getInstance().getReference().child("Students");
                            Query query = databaseReference.orderByChild("email").equalTo(email);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        Intent intent= new Intent(context,Subject.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }progressDialog.dismiss();


                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }

                                ;
                            });


                        }

                    }

                    ;

                });
            }

            ;
        });
    }}
