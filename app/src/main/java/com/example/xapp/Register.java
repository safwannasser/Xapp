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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity implements OnItemSelectedListener {
    Button r,l;
    FirebaseAuth auth;
    SharedPreferences sharedpreferences;
    ProgressDialog progressDialog;

    EditText iemail,ipassword,iphno,icpassword,iname;
  //  public static final String mypreference = "mypref";
   public static final String Name = "nameKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();
        iemail = (EditText)findViewById(R.id.editText2);
        ipassword = (EditText)findViewById(R.id.pword);
        iphno = (EditText)findViewById(R.id.phno);
        icpassword = (EditText)findViewById(R.id.cpword) ;
        iname = (EditText)findViewById(R.id.name);
        Spinner spinner = (Spinner) findViewById(R.id.std);
       // sharedpreferences = getSharedPreferences(mypreference,
       //         Context.MODE_PRIVATE);
      if (sharedpreferences.contains(Name)) {
          iname.setText(sharedpreferences.getString(Name, ""));


        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("CLASS");
        categories.add("I");
        categories.add("II");
        categories.add("III");
        categories.add("IV");
        categories.add("V");
        categories.add("VI");
        categories.add("VII");
        categories.add("IX");
        categories.add("X");
        categories.add("XI");
        categories.add("V");
        categories.add("XII");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        spinner.setAdapter(dataAdapter);

        addListenerOnButton();
    }}
    public void save() {
        String n = iname.getText().toString();
        String e = iemail.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
       // editor.putString(Email, e);
        editor.commit();
    }
    public void addListenerOnButton() {
        progressDialog = new ProgressDialog(this);
        final Context context= this;
        r= (Button) findViewById(R.id.rbtn);
        l= (Button) findViewById(R.id.r2lbtn);
          /* r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, login.class);
                startActivity(intent);
            }
        });*/
          r.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String email=iemail.getText().toString().trim();
                  String password=ipassword.getText().toString().trim();
                  String cpassword=icpassword.getText().toString().trim();
                  String name=iname.getText().toString().trim();
                  String number=iphno.getText().toString().trim();

                  if(TextUtils.isEmpty(name))
                  {
                      Toast.makeText(getApplicationContext(), "Enter Name!", Toast.LENGTH_SHORT).show();
                      return;
                  }
                  if(TextUtils.isEmpty(email))
                  {
                      Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                      return;
                  }
                  if(TextUtils.isEmpty(password))
                  {
                      Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
                      return;
                  }
                  if(TextUtils.isEmpty(cpassword))
                  {
                      Toast.makeText(getApplicationContext(), "Enter confirm password!", Toast.LENGTH_SHORT).show();
                      return;
                  }
                  if(TextUtils.isEmpty(number))
                  {
                      Toast.makeText(getApplicationContext(), "Enter phone number!", Toast.LENGTH_SHORT).show();
                      return;
                  }
                  if(password.length()<6)
                  {
                      Toast.makeText(getApplicationContext(), "Password too short!Enter minimum 6 characters", Toast.LENGTH_SHORT).show();
                      return;
                  }
                  if(!password.equals(cpassword))
                  {
                      Toast.makeText(getApplicationContext(), "passwords do not match!", Toast.LENGTH_SHORT).show();
                      return;
                  }
                  progressDialog.setMessage("Registering User Statement..");
                  progressDialog.show();
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {

                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                save();
                                DatabaseReference users = FirebaseDatabase.getInstance().getReference();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String key = database.getReference("Students").push().getKey();
                                DatabaseReference userRef =  users.child("Students").child(key);
                                userRef.child("name").setValue(name);
                                userRef.child("number").setValue(number);
                                userRef.child("email").setValue(email);

                                startActivity(new Intent(Register.this, login.class));
                                finish();
                            }progressDialog.dismiss();

                        }
                    });




              }
          });
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, login.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
