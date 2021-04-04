package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class affectedcountries extends AppCompatActivity {
    EditText edt;
    ListView listView;
    SimpleArcLoader simpleArcLoader;
   public static List<CountryModel>countryModelList=new ArrayList<>();
   CountryModel countryModel;
   MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affectedcountries);
        edt=findViewById(R.id.edit);
        listView=findViewById(R.id.list);
        simpleArcLoader=findViewById(R.id.arc);
        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        fetchData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(),detailed_activity.class).putExtra("position",i));
            }
        });
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                myAdapter.getFilter().filter(charSequence);
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {
        String url="https://disease.sh/v3/covid-19/countries/";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                   
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    String countryname=jsonObject.getString("country");
                        String active =jsonObject.getString("active");
                        String totalcases=jsonObject.getString("cases");
                        String todaycases=jsonObject.getString("todayCases");
                        String deaths=jsonObject.getString("deaths");
                        String todaydeaths=jsonObject.getString("todayDeaths");
                        String recovery=jsonObject.getString("recovered");
                        String critical=jsonObject.getString("critical");
                        JSONObject object=jsonObject.getJSONObject("countryInfo");
                        String flag=object.getString("flag");

                        countryModel=new CountryModel(active,flag,totalcases,todaycases,deaths,todaydeaths,critical,countryname,recovery);

                     countryModelList.add(countryModel);

                    }
                    myAdapter=new MyAdapter(affectedcountries.this,countryModelList);
                    listView.setAdapter(myAdapter);
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                Toast.makeText(affectedcountries.this,"SOMETHING WENT WRONG",Toast.LENGTH_SHORT).show();

            }

        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}