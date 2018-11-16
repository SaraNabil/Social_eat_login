package com.sara_nabil.social_eat_login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText name_et, email_et, pass_et, phone_et, address_et;
    RadioButton male_rb, female_rb;
    RadioGroup gender_rg;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name_et = findViewById(R.id.name_signup_et);
        email_et = findViewById(R.id.email_signup_et);
        pass_et = findViewById(R.id.pass_signup_et);
        phone_et = findViewById(R.id.phone_signup_et);
        address_et = findViewById(R.id.address_signup_et);
        male_rb = findViewById(R.id.male_radioButton);
        female_rb = findViewById(R.id.female_radioButton);
        gender_rg = findViewById(R.id.gender_radioGroup);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

    }

    public void createNewAccount(View view) {
        final String email = email_et.getText().toString();
        final String password = pass_et.getText().toString();
        final String name = name_et.getText().toString();
        final String phone = phone_et.getText().toString();
        final String address = address_et.getText().toString();
        final String gender;

        if (gender_rg.getCheckedRadioButtonId() == R.id.male_radioButton) {
            gender = "male";
        } else {
            gender = "female";
        }


        if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(name)
                || !TextUtils.isEmpty(phone) || !TextUtils.isEmpty(address) || male_rb.isChecked() || female_rb.isChecked()) {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.e("create", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                String id = user.getUid();
                                addUser(id,email, name, Integer.parseInt(phone), address, gender);
//                                startActivity(new Intent(CreateNewAccountActivity.this, MainActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.e("create failed", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignupActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(SignupActivity.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();

        }
    }

    private void addUser(String id ,String email, String name,
                         int phone, String address, String gender) {
        UserDetailes userDetailes = new UserDetailes(name, address, email, phone, gender);

        myRef.child(id).setValue(userDetailes);

    }
}
