package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {



    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String json = parseJSON();

        try {
            JSONArray jarray = new JSONArray(json);  // JSONArray 생성
            final List<String> listContents = new ArrayList<String>(jarray.length());
            final List<String> listContents2 = new ArrayList<String>(jarray.length());
            for(int i=0; i < jarray.length(); i++){
                listContents.add(jarray.getJSONObject(i).getString("name")); //name 리스트 생성
                listContents2.add(jarray.getJSONObject(i).getString("number")); //number 리스트 생성
            }

            View view = inflater.inflate(R.layout.fragment_first, container, false);
            ArrayAdapter Adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listContents);

            ListView listview = (ListView) view.findViewById(R.id.contact_list);
            listview.setAdapter(Adapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id){
                    Intent intent = new Intent(
                            getActivity().getApplicationContext(),
                            ContactDetail.class
                    );
                    intent.putExtra("name", listContents.get(position));
                    intent.putExtra("number", listContents2.get(position));
                    intent.putExtra("picture", getResources().getIdentifier(listContents.get(position).toLowerCase(), "drawable", getActivity().getApplicationContext().getPackageName()));
                    startActivity(intent);
                }
            });

            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab1);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(
                            getActivity().getApplicationContext(),
                            AppendContact.class
                    );
                    startActivity(intent);

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
            InputStream is = getActivity().getApplicationContext().getAssets().open("contact.json"); // contact.json file에서 pasring
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
