package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView total,deaths,total_deaths,active,infected,recovered,critical,today_cases;
    PieChart pieChart;
    ScrollView scrollView;
    SimpleArcLoader arc;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total=findViewById(R.id.cases_count);
        deaths=findViewById(R.id.death_count_today);
        total_deaths=findViewById(R.id.death_count);
        active=findViewById(R.id.active_count);
        infected=findViewById(R.id.affected_count_today);
        recovered=findViewById(R.id.recovery_count);
        pieChart=findViewById(R.id.piechart);
        scrollView=findViewById(R.id.scroll);
        arc=findViewById(R.id.loader);
        critical=findViewById(R.id.critical_count);
        today_cases=findViewById(R.id.cases_today);
        btn=findViewById(R.id.button);
        fetch_data();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),affectedcountries.class));
            }
        });
    }

    private void fetch_data() {
   String url="https://disease.sh/v3/covid-19/all/";
     arc.start();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response.toString());
                    total.setText(jsonObject.getString("cases"));
                    deaths.setText(jsonObject.getString("todayDeaths"));
                    total_deaths.setText(jsonObject.getString("deaths"));
                    active.setText(jsonObject.getString("active"));
                    infected.setText(jsonObject.getString("affectedCountries"));
                    recovered.setText(jsonObject.getString("recovered"));
                    critical.setText(jsonObject.getString("critical"));
                    today_cases.setText(jsonObject.getString("todayCases"));
                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(total.getText().toString()), Color.parseColor("#FE6DA8")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(recovered.getText().toString()), Color.parseColor("#56B7F1")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(active.getText().toString()), Color.parseColor("#FED70E")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(total_deaths.getText().toString()), Color.parseColor("#CDA67F")));
                    pieChart.startAnimation();
                    arc.stop();
                    arc.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    arc.stop();
                    arc.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"SOMETHING WENT WRONG",Toast.LENGTH_SHORT).show();
                arc.stop();
                arc.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
            }

        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}