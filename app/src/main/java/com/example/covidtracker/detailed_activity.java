package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class detailed_activity extends AppCompatActivity {
private  int position_country;
TextView totalcases,todaycases,recovery,totaldeaths,todaydeaths,active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=getIntent();
        position_country=intent.getIntExtra("position",0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_activity);
        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        totalcases=findViewById(R.id.cases_count);
        todaycases=findViewById(R.id.today_count);
        recovery=findViewById(R.id.RECOVERY_count);
        totaldeaths=findViewById(R.id.totaldeaths);
        todaydeaths=findViewById(R.id.DEATHS);
        active=findViewById(R.id.ACTIVE);
        position_country=intent.getIntExtra("position",0);
        totalcases.setText(affectedcountries.countryModelList.get(position_country).getTotalcases());
        todaycases.setText(affectedcountries.countryModelList.get(position_country).getTodaycases());
        recovery.setText(affectedcountries.countryModelList.get(position_country).getRecovered());
        todaydeaths.setText(affectedcountries.countryModelList.get(position_country).getTodaydeaths());
        totaldeaths.setText(affectedcountries.countryModelList.get(position_country).getDeaths());
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);}
    }
