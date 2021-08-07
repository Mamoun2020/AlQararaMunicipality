package alqarara.municipality.alqararamunicipality.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import alqarara.municipality.alqararamunicipality.R;

public class SentFormActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_form);
        inflate();
        configureToolbar();
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
    }
    private void configureToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}