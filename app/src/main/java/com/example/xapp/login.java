package com.example.xapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
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
    TextView f;
    EditText iemail,ipassword;
    FirebaseAuth Auth;
    ProgressDialog progressDialog;
    CheckBox saveLoginCheckBox;
    SharedPreferences sharedpreferences;

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private String username,password,email;
    public static final String mypreferences = "myprefer";

    public static final String Email = "emailKey";
    public static final String ID = "sid";
    String sid;

    int lock=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* if (Auth.getCurrentUser() != null) {
            startActivity(new Intent(login.this, MainActivity.class));
            finish();
        }*/
        setContentView(R.layout.activity_login);
        f=(TextView) findViewById(R.id.forgotpassword);
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
        sharedpreferences = getSharedPreferences(mypreferences,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Email)) {
            iemail.setText(sharedpreferences.getString(Email, ""));
        }

        //Auth = FirebaseAuth.getInstance();

        addListenerOnButton();
    }
    public void Save()
    {
      //  String n = iname.getText().toString();
        String e = iemail.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Email, e);
        // editor.putString(Email, e);
        editor.commit();
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


               email = iemail.getText().toString();

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

                final Query userQuery = FirebaseDatabase.getInstance().getReference().child("Students").orderByChild("email");
                //  Log.i("tevin",userQuery+"");
                userQuery.equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.i("tevin",dataSnapshot+"");
                         sid = "";
                        for(DataSnapshot studentsnapshot : dataSnapshot.getChildren())
                        {
                            sid=studentsnapshot.getKey();
                            Log.i("safwaaa",sid);
                        }
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(ID, sid);
                        lock=1;
                        // editor.putString(Email, e);
                        editor.commit();
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("safw", databaseError.getMessage());

                    }
                });


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
                                        Save();
                                        while(lock==0);
                                        Intent intent= new Intent(context,Profile.class);
                                        intent.putExtra("MY_KEY",sid );
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
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, PasswordActivity.class));
            }
        });
    }}
