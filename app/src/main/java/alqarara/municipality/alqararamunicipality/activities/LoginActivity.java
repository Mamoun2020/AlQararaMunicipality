package alqarara.municipality.alqararamunicipality.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import alqarara.municipality.alqararamunicipality.R;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText et_id,et_password;
    Button btn_login;
    private FirebaseAuth mAuth;
    TextView tv_register;
    String id, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_id=findViewById(R.id.login_et_userid);
        et_password=findViewById(R.id.login_et_password);
        btn_login=findViewById(R.id.login_btn_login);
        tv_register=findViewById(R.id.login_tv_newuser);
//        mAuth=FirebaseAuth.getInstance();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                signInUser(et_id.getText().toString().trim(),et_password.getText().toString());
                id = et_id.getText().toString();
                pass = et_password.getText().toString();

                if(id.equals("")){
                    et_id.setError("can't be blank");
                }
                else if(pass.equals("")){
                    et_password.setError("can't be blank");
                }
                else{
                    String url = "https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/users.json";
                    final ProgressDialog pd = new ProgressDialog(LoginActivity.this,R.style.MyAlertDialogStyle);
                    pd.setMessage("Loading...");
                    pd.show();

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            if(s.equals("null")){
                                Toast.makeText(LoginActivity.this, "user not found", Toast.LENGTH_LONG).show();
                            }
                            else{
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if(!obj.has(id)){
                                        Toast.makeText(LoginActivity.this, "user not found", Toast.LENGTH_LONG).show();
                                    }
                                    else if(obj.getJSONObject(id).getString("password").equals(pass)){
                                        FormDetails.id =id;
                                        FormDetails.password = pass;
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this, "incorrect password", Toast.LENGTH_LONG).show();
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
                            System.out.println("" + volleyError);
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
                    rQueue.add(request);
                }

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