package alqarara.municipality.alqararamunicipality.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import alqarara.municipality.alqararamunicipality.R;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText et_id,et_password;
    Button btn_register;
    String id, pass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_id=findViewById(R.id.register_et_userid);
        et_password=findViewById(R.id.register_et_password);
        btn_register=findViewById(R.id.register_btn_login);
        Firebase.setAndroidContext(this);
//        mAuth=FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                signupUser(et_id.getText().toString().trim(),et_password.getText().toString());
                id = et_id.getText().toString();
                pass = et_password.getText().toString();

                if(id.equals("")){
                    et_id.setError("can't be blank");
                }
                else if(pass.equals("")){
                    et_password.setError("can't be blank");
                }
                else if(!id.matches("[0-9]+")){
                    et_id.setError("only alphabet or number allowed");
                }
                else if(id.length()<9){
                    et_id.setError("at least 9 numbers");
                }
                else if(pass.length()<5){
                    et_password.setError("at least 5 characters long");
                }
                else {
                    final ProgressDialog pd = new ProgressDialog(RegisterActivity.this,R.style.MyAlertDialogStyle);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/users.json";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/users");

                            if(s.equals("null")) {
                                reference.child(id).child("password").setValue(pass);
                                Toast.makeText(RegisterActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(id)) {
                                        reference.child(id).child("password").setValue(pass);
                                        Toast.makeText(RegisterActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "username already exists", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            pd.dismiss();
                        }

                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError );
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(RegisterActivity.this);
                    rQueue.add(request);
                }
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