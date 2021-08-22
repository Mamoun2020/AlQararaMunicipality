package alqarara.municipality.alqararamunicipality.activities;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static java.util.Calendar.DAY_OF_YEAR;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import alqarara.municipality.alqararamunicipality.R;

public class ChatActivity extends AppCompatActivity {
    LinearLayout layout;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;
    Date time2;
    TextView tv_hint;
    long time22;
    static  final String SHARED_PREF_NAME ="sharedpreferance";
    static  final String STORAGE_MINUTE ="sharedtime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        layout = (LinearLayout)findViewById(R.id.layout1);
        sendButton = (ImageView)findViewById(R.id.sendButton);
        messageArea = (EditText)findViewById(R.id.messageArea);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        tv_hint=findViewById(R.id.tv_message);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/messages/" + FormDetails.id + "_" + FormDetails.chatWith);
        reference2 = new Firebase("https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/messages/" + FormDetails.chatWith + "_" + FormDetails.id );
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        String id = intent.getStringExtra("id");
        String id2 = intent.getStringExtra("id");
//        Long time =System.currentTimeMillis();
        Map<String, String> map = new HashMap<String, String>();
//                    map.put("message", messageText);
        map.put("message",text);
        map.put("id",id);
        Date time=Calendar.getInstance().getTime();
        messageArea.setVisibility(View.GONE);
        long currenttime=time.getTime();
//        long currenttime= System.currentTimeMillis();
        Log.d("current time: ",currenttime+"");
        long a=1800000; // 30 minute
//        long a=120000;
        long newtime=currenttime+a;

        time2=Calendar.getInstance().getTime();
        time22=time2.getTime();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(time22<=newtime){
                    if (newtime==time22){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_hint.setVisibility(View.GONE);
                                messageArea.setVisibility(View.VISIBLE);
                                sendButton.setVisibility(View.VISIBLE);

                            }
                        });
                    }
                    Log.d("time",time22+"  ***  "+newtime);
                    time2=Calendar.getInstance().getTime();
                    time22=time2.getTime();
                }
            }
        }).start();




//        String targetTime=currenttime+30;

//        Calendar calendar = Calendar.getInstance();
//        calendar.add(SECOND, 5);
//        messageArea.setVisibility(View.GONE);
//        Toast.makeText(getBaseContext(),"time start",Toast.LENGTH_SHORT).show();
//        boolean dataStored = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).getBoolean("dataStored", false);
//        if (!dataStored) {
//            getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).edit().putLong(STORAGE_MINUTE,calendar.getTimeInMillis()).apply();
//            getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).edit().putBoolean("dataStored", true).apply();
//        }
        reference1.child(id2).push().setValue(map);
        reference2.push().setValue(map);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//           *     try {
//                    Thread.sleep(6000);
//                    if (System.currentTimeMillis() >= getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).getLong(STORAGE_MINUTE, System.currentTimeMillis() * 2)) {
//                        Toast.makeText(getBaseContext(),"time over",Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, "run: ");
//                        messageArea.setVisibility(View.VISIBLE);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();

                if(!messageText.equals("")){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("id", id);
                    reference1.child(id2).push().setValue(map);
                    reference2.push().setValue(map);
                }
            }
        });

        reference1.child(id2).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                boolean dataStored = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).getBoolean("dataStored", false);
                if (!dataStored) {
                    getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).edit().putBoolean("dataStored", true).apply();
                    String message = map.get("message").toString();
                    String id = map.get("id").toString();
                    if(id.equals(id)){
                        addMessageBox("You :\n" + message, 1);
                    }
                    else{
                        addMessageBox(FormDetails.chatWith + " :\n" + message, 2);
                    }
                }
                        String id;
                        String text = map.get("message").toString();
                        id = map.get("id").toString();
                        if (id.equals(id)) {
                            addMessageBox("you:\n" + text, 1);
                        }




            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    public void addMessageBox(String message, int type){
        TextView textView = new TextView(ChatActivity.this);
        textView.setText(message);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 10);
        textView.setLayoutParams(lp);

        if(type == 1) {
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
            textView.setBackgroundResource(R.drawable.rounded_corner1);
        }
        else{
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
            textView.setBackgroundResource(R.drawable.rounded_corner2);
        }

        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}