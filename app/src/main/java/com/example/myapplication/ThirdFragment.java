package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {


    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        String json = parseJSON();

        try {
            JSONArray jarray = new JSONArray(json);  // JSONArray 생성
            final ArrayList<String> Korean = new ArrayList<String>(jarray.length());
            final ArrayList<String> English = new ArrayList<String>(jarray.length());
            for(int i=0; i < jarray.length(); i++){
                Korean.add(jarray.getJSONObject(i).getString("Korean")); //Korean 리스트 생성
                English.add(jarray.getJSONObject(i).getString("English")); //English 리스트 생성
            }

            // Inflate the layout for this fragment
            final View view = inflater.inflate(R.layout.fragment_third, container, false);
            final ListCViewAdapter Adapter = new ListCViewAdapter(English, Korean);

            final ListView listView = (ListView) view.findViewById(R.id.list1);
            listView.setAdapter(Adapter);

            final CheckBox checkbox = (CheckBox)view.findViewById(R.id.checkBox);

            checkbox.setOnClickListener(new CheckBox.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(checkbox.isChecked()) {

                        SwipeDismissListViewTouchListener touchListener =
                                new SwipeDismissListViewTouchListener(
                                        listView,
                                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                                            @Override
                                            public boolean canDismiss(int position) {
                                                return true;
                                            }

                                            @Override
                                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                                for (int position : reverseSortedPositions) {
                                                    Adapter.deleteItem(position);
                                                }
                                                Adapter.notifyDataSetChanged();
                                            }
                                        });
                        listView.setOnTouchListener(touchListener);
                        // Setting this scroll listener is required to ensure that during ListView scrolling,
                        // we don't look for swipes.
                        listView.setOnScrollListener(touchListener.makeScrollListener());
                    }
                }
            });
            return view;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String parseJSON() {
        String json = null;
        try {
            InputStream is = getActivity().getApplicationContext().getAssets().open("word.json"); // word.json file에서 pasring
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }


}


