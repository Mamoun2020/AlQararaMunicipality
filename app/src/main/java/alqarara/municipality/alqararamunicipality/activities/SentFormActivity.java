package alqarara.municipality.alqararamunicipality.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import alqarara.municipality.alqararamunicipality.R;

public class SentFormActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText et_id,et_phone,et_title,et_description,et_text;
    Spinner sp_near,sp_area,sp_category,sp_type;
    Button btn_sent;
    String id,phone,title,description,text,near,area,category,type;

//    ListView usersList;
//    TextView noUsersText;
    ArrayList<String> al = new ArrayList<>();
    int totalUsers = 0;
//    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_form);
        inflate();
        configureToolbar();
        Firebase.setAndroidContext(this);
        btn_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id= et_id.getText().toString();
                phone=et_phone.getText().toString();
                title=et_title.getText().toString();
                description=et_description.getText().toString();
                text=et_text.getText().toString();
                near=sp_near.getSelectedItem().toString();
                area=sp_area.getSelectedItem().toString();
                category=sp_category.getSelectedItem().toString();
                type=sp_type.getSelectedItem().toString();
                final ProgressDialog pd = new ProgressDialog(SentFormActivity.this,R.style.MyAlertDialogStyle);
                pd.setMessage("Loading...");
                pd.show();

                String url = "https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/forms.json";

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        Firebase reference = new Firebase("https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/forms");
                        int random = new Random().nextInt(9000);
                        if(s.equals("null")) {
                            reference.child(String.valueOf(random)).child("id").setValue(id);
                            reference.child(String.valueOf(random)).child("phone").setValue(phone);
                            reference.child(String.valueOf(random)).child("title").setValue(title);
                            reference.child(String.valueOf(random)).child("milepost").setValue(near);
                            reference.child(String.valueOf(random)).child("area").setValue(area);
                            reference.child(String.valueOf(random)).child("description").setValue(description);
                            reference.child(String.valueOf(random)).child("category").setValue(category);
                            reference.child(String.valueOf(random)).child("type").setValue(type);
                            reference.child(String.valueOf(random)).child("text").setValue(text);
                            chat();
//                            startActivity(new Intent(SentFormActivity.this, ChatActivity.class));
                        }
                        else {
                            try {
                                JSONObject obj = new JSONObject(s);
                                    reference.child(String.valueOf(random)).child("id").setValue(id);
                                    reference.child(String.valueOf(random)).child("phone").setValue(phone);
                                    reference.child(String.valueOf(random)).child("title").setValue(title);
                                    reference.child(String.valueOf(random)).child("milepost").setValue(near);
                                    reference.child(String.valueOf(random)).child("area").setValue(area);
                                    reference.child(String.valueOf(random)).child("description").setValue(description);
                                    reference.child(String.valueOf(random)).child("category").setValue(category);
                                    reference.child(String.valueOf(random)).child("type").setValue(type);
                                    reference.child(String.valueOf(random)).child("text").setValue(text);
                                chat();
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

                RequestQueue rQueue = Volley.newRequestQueue(SentFormActivity.this);
                rQueue.add(request);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        backArrow();
    }

    private void backArrow(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                finish();
            }
        });
    }

    private void inflate(){
        toolbar=findViewById(R.id.sent_toolbar);

        et_id=findViewById(R.id.sent_et_id);
        et_phone=findViewById(R.id.sent_et_phone);
        et_title=findViewById(R.id.sent_et_title);
        et_description=findViewById(R.id.sent_et_description);
        et_text=findViewById(R.id.sent_et_text);

        sp_near=findViewById(R.id.sent_sp_near);
        sp_area=findViewById(R.id.sent_sp_area);
        sp_category=findViewById(R.id.sent_sp_category);
        sp_type=findViewById(R.id.sent_sp_type);

        btn_sent=findViewById(R.id.sent_btn_sent);
    }
    private void configureToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
    private void chat(){
//        pd = new ProgressDialog(SentFormActivity.this);
//        pd.setMessage("Loading...");
//        pd.show();

        String url = "https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(SentFormActivity.this);
        rQueue.add(request);
        FormDetails.chatWith = "Admin";
        Intent intent =new Intent(SentFormActivity.this, ChatActivity.class)
                .putExtra("text",et_text.getText().toString());
        startActivity(intent);

//        if(UserDetails.username.equals("mamoun")){
//            usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    UserDetails.chatWith = al.get(position);
//                    startActivity(new Intent(Users.this, Chat.class));
//                }
//            });
//        }
//        else{

//        }
    }

    public void doOnSuccess(String s){
        try {
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            while(i.hasNext()){
                key = i.next().toString();

                if(!key.equals(FormDetails.id)) {
                    al.add(key);
                }

                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        if(totalUsers <=1){
//            noUsersText.setVisibility(View.VISIBLE);
//            usersList.setVisibility(View.GONE);
//        }
//        else{
//            noUsersText.setVisibility(View.GONE);
//            usersList.setVisibility(View.VISIBLE);
//            usersList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
//        }
//
//        pd.dismiss();
    }
}