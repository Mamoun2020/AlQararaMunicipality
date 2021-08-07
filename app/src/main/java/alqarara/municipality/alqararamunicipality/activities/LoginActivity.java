package alqarara.municipality.alqararamunicipality.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import alqarara.municipality.alqararamunicipality.R;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText et_id,et_password;
    Button btn_login;
    private FirebaseAuth mAuth;
    TextView tv_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_id=findViewById(R.id.login_et_userid);
        et_password=findViewById(R.id.login_et_password);
        btn_login=findViewById(R.id.login_btn_login);
        tv_register=findViewById(R.id.login_tv_newuser);
        mAuth=FirebaseAuth.getInstance();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser(et_id.getText().toString().trim(),et_password.getText().toString());
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void signInUser(String id , String password){
        mAuth.signInWithEmailAndPassword(id,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getBaseContext(),"أهلاً بكم",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.d("login",task.getException().getMessage());
                }
            }
        });
    }
}