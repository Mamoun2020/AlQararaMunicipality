package alqarara.municipality.alqararamunicipality.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import alqarara.municipality.alqararamunicipality.R;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText et_id,et_password;
    Button btn_register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_id=findViewById(R.id.register_et_userid);
        et_password=findViewById(R.id.register_et_password);
        btn_register=findViewById(R.id.register_btn_login);
        mAuth=FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupUser(et_id.getText().toString().trim(),et_password.getText().toString());
            }
        });
    }
    private void signupUser(String id , String password){
        mAuth.createUserWithEmailAndPassword(id,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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