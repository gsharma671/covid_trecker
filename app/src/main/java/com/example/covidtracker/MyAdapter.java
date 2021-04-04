package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyAdapter extends ArrayAdapter<CountryModel> {
    private Context context;
    private List<CountryModel>countryModelList;
    private  List<CountryModel>countryModelListFilter;
    public MyAdapter(@NonNull Context context, List<CountryModel>countryModelList) {
        super(context, R.layout.list_view,countryModelList);
        this.countryModelList=countryModelList;
        this.countryModelListFilter=countryModelList;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view,null,true);
        ImageView img=view.findViewById(R.id.image);
        TextView txt=view.findViewById(R.id.country_id);
        txt.setText(countryModelListFilter.get(position).getCountry());
        Glide.with(context).load(countryModelListFilter.get(position).getFlag()).into(img);
        return view;
    }

    @Override
    public int getCount() {
        return countryModelListFilter.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListFilter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countryModelList.size();
                    filterResults.values = countryModelList;

                }else{
                    List<CountryModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(CountryModel itemsModel:countryModelList){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countryModelListFilter = (List<CountryModel>) results.values;
                affectedcountries.countryModelList = (List<CountryModel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
