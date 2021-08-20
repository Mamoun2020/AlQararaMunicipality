package alqarara.municipality.alqararamunicipality.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

        sp_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sp_area.getSelectedItem().equals("منطقة 86")){
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("مسجد التوحيد");
                    arrayList.add("مسجد الرضوان");
                    arrayList.add("مسجد بلال بن رباح");
                    arrayList.add("مدرسة المعري");
                    arrayList.add("روضة الأشبال النموذجية");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, arrayList);
                    sp_near.setAdapter(arrayAdapter);
                }
                else if(sp_area.getSelectedItem().equals("السريج")){
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("مسجد الرحمن");
                    arrayList.add("نادي القرارة الرياضي");
                    arrayList.add("مدرسة كامل الأغا");
                    arrayList.add("مدرسة النور");
                    arrayList.add("روضة المروة");
                    arrayList.add("مسجد عمر بن الخطاب");
                    arrayList.add("مسجد أبو بكر الصديق");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, arrayList);
                    sp_near.setAdapter(arrayAdapter);
                }
                else if(sp_area.getSelectedItem().equals("أل عبد الغفور و فياض")){
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("عيادة الزنة");
                    arrayList.add("مدرسة محمد بن صالح ابن عثمين");
                    arrayList.add("مسجد أسامة بن زيد");
                    arrayList.add("مجلس العلمي للدعوة السلفية");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, arrayList);
                    sp_near.setAdapter(arrayAdapter);
                }
                else if(sp_area.getSelectedItem().equals("منطقة العبادلة")){
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("مسجد كامل الأسطل");
                    arrayList.add("روضة العلماء الصغار");
                    arrayList.add("مسجد الهدى");
                    arrayList.add("مسجد المحسنين");
                    arrayList.add("مسجد الاستقامة");
                    arrayList.add("روضة الكوثر");
                    arrayList.add("جمعية الانسان التنموية");
                    arrayList.add("مدرسة القرارة");
                    arrayList.add("مسجد الصحابة");
                    arrayList.add("جمعية دار الكتاب و السنة");
                    arrayList.add("مسجد خالد بن الوليد");
                    arrayList.add("مسجد السلام");
                    arrayList.add("عيادة القرارة");
                    arrayList.add("مصلى عمر بن الخطاب");
                    arrayList.add("مدرسة عيلبون");
                    arrayList.add("روضة القدس النموذجية");
                    arrayList.add("عيادة مسقط");
                    arrayList.add("مدرسة مسقط");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, arrayList);
                    sp_near.setAdapter(arrayAdapter);
                }
                else if(sp_area.getSelectedItem().equals("منطقة الغربية")){
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("مدرسة الوكالة الإبتدائية");
                    arrayList.add("مركز شرطة القرارة");
                    arrayList.add("مدرسة الوكالة الإعدادية");
                    arrayList.add("المطاحن الفلسطينية");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, arrayList);
                    sp_near.setAdapter(arrayAdapter);
                }
                else{
                    ArrayList<String> arrayList = new ArrayList<>();
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, arrayList);
                    sp_near.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp_category.getSelectedItem().equals("قسم المياه والصرف الصحي")) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("وصلات غير شرعية");
                    arrayList.add("اهدار مياه");
                    arrayList.add("استفسار مياه");
                    arrayList.add("انفجار خط مياه");
                    arrayList.add("ملوحة مياه");
                    arrayList.add("سرقة مياه");
                    arrayList.add("انقطاع مياه");
                    arrayList.add("منهل مفتوح");
                    arrayList.add("توصيل خطوط الصرف الصحي");
                    arrayList.add("مصافي مياه أمطار");
                    arrayList.add("مشاريع صرف صحي");
                    arrayList.add("طفح صرف صحي");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, arrayList);
                    sp_type.setAdapter(arrayAdapter);
                }
                else if(sp_category.getSelectedItem().equals("قسم التنظيم والتخطيط")){
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("ترميم مباني");
                    arrayList.add("حرفة بدون ترخيص");
                    arrayList.add("اشغال رصيف");
                    arrayList.add("بناء بدون ترخيص");
                    arrayList.add("رسوم تنظيم");
                    arrayList.add("بناء لإيل للسقوط");
                    arrayList.add("التسجيل لحصة أسمنت");
                    arrayList.add("البسطات العشوائية");
                    arrayList.add("تعديات");
                    arrayList.add("ازعاج مكبرات صوت");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item,arrayList);
                    sp_type.setAdapter(arrayAdapter);
                }
                else if(sp_category.getSelectedItem().equals("قسم المالية")){
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("فواتير خدمات");
                    arrayList.add("إشكالات مالية");
                    arrayList.add("فواتير عامة");
                    arrayList.add("تركيب يافظة");
                    arrayList.add("قراءة عداد مياه");
                    arrayList.add("مواقف البلدية للسيارات");
                    arrayList.add("استفسار عن اليافطات");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item,arrayList);
                    sp_type.setAdapter(arrayAdapter);
                }
                else if(sp_category.getSelectedItem().equals("الشؤون الإدارية")){
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("طلب تطوع");
                    arrayList.add("طلب توظيف");
                    arrayList.add("مخالفات موظفين");
                    arrayList.add("استفسار عن وظيفة");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item,arrayList);
                    sp_type.setAdapter(arrayAdapter);
                }
                else if(sp_category.getSelectedItem().equals("قسم الصيانة")){
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("عمل مطبات");
                    arrayList.add("خلع بلاط");
                    arrayList.add("رصف شارع");
                    arrayList.add("حفر طرق");
                    arrayList.add("استفسار عن مشاريع طرق");
                    arrayList.add("حفرة شارع");
                    arrayList.add("انارة شوارع");
                    arrayList.add("اصلاح فانوس");
                    arrayList.add("تركيب فانوس جديد");
                    arrayList.add("كلاب ضالة");
                    arrayList.add("موالدات كهربائية");
                    arrayList.add("طلب حاويات");
                    arrayList.add("عما نظافة تصنيع");
                    arrayList.add("انتشار بعوض");
                    arrayList.add("انتشار قوارص");
                    arrayList.add("قطع أشجار");
                    arrayList.add("تشجير");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item,arrayList);
                    sp_type.setAdapter(arrayAdapter);
                }
                else if(sp_category.getSelectedItem().equals("شكوى عامة")){
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("طلب الغاء معاملة");
                    arrayList.add("طلب افادة");
                    arrayList.add("مقترحات تطويرية");
                    arrayList.add("طلب تعويضات");
                    arrayList.add("استفسار عن قوانين و أنظمة");
                    arrayList.add("استفسار عن حجز قاعة");
                    arrayList.add("استفسار عن ايجار مكان");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item,arrayList);
                    sp_type.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                            if( TextUtils.isEmpty(id))
                            {
                                et_id.setError( "id is required!" );
                            }
                            else if( TextUtils.isEmpty(phone)){
                                et_phone.setError( "phone is required!" );
                            }
                            else if(TextUtils.isEmpty(title)){
                                et_title.setError( "title is required!" );
                            }
                            else if(TextUtils.isEmpty(near)){
                                ((TextView)sp_near.getSelectedView()).setError("near is required!");
                            }
                            else if(TextUtils.isEmpty(area)){
                                ((TextView)sp_area.getSelectedView()).setError("area is required!");
                            }
                            else if(TextUtils.isEmpty(description)){
                                et_description.setError( "description is required!" );
                            }
                            else if(TextUtils.isEmpty(category) ){
                                ((TextView)sp_category.getSelectedView()).setError("category is required!");
                            }
                            else if(TextUtils.isEmpty(type) ){
                                ((TextView)sp_type.getSelectedView()).setError("type is required!");
                            }
                            else if(TextUtils.isEmpty(text)){
                                et_text.setError( "text is required!" );
                            }
                            else{
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
                            }
//                            startActivity(new Intent(SentFormActivity.this, ChatActivity.class));
                        }
                        else {
                            try {
                                JSONObject obj = new JSONObject(s);
                                if( TextUtils.isEmpty(id))
                                {
                                    et_id.setError( "id is required!" );
                                }
                                else if( TextUtils.isEmpty(phone)){
                                    et_phone.setError( "phone is required!" );
                                }
                                else if(TextUtils.isEmpty(title)){
                                    et_title.setError( "title is required!" );
                                }
                                else if(TextUtils.isEmpty(near)){
                                    ((TextView)sp_near.getSelectedView()).setError("near is required!");
                                }
                                else if(TextUtils.isEmpty(area)){
                                    ((TextView)sp_area.getSelectedView()).setError("area is required!");
                                }
                                else if(TextUtils.isEmpty(description)){
                                    et_description.setError( "description is required!" );
                                }
                                else if(TextUtils.isEmpty(category) ){
                                    ((TextView)sp_category.getSelectedView()).setError("category is required!");
                                }
                                else if(TextUtils.isEmpty(type) ){
                                    ((TextView)sp_type.getSelectedView()).setError("type is required!");
                                }
                                else if(TextUtils.isEmpty(text)){
                                    et_text.setError( "text is required!" );
                                }
                                else{
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