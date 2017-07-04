package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }

    ArrayList<String> listContents;
    ArrayList<String> listContents2;
    ListView listview;

    final int REQUEST_GET_CONTACT = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String json = parseJSON();

        try {
            JSONArray jarray = new JSONArray(json);  // JSONArray 생성
            listContents = new ArrayList<String>(jarray.length());
            listContents2 = new ArrayList<String>(jarray.length());
            for(int i=0; i < jarray.length(); i++){
                listContents.add(jarray.getJSONObject(i).getString("name")); //name 리스트 생성
                listContents2.add(jarray.getJSONObject(i).getString("number")); //number 리스트 생성
            }

            View view = inflater.inflate(R.layout.fragment_first, container, false);
            ListAViewAdapter Adapter = new ListAViewAdapter(listContents, listContents2);

            listview = (ListView) view.findViewById(R.id.contact_list);
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
                    try {
                        intent.putExtra("picture", getResources().getIdentifier(
                                listContents.get(position).toLowerCase(), "drawable", getActivity().getApplicationContext().getPackageName()));
                    }catch (NullPointerException e){
                        intent.putExtra("picture", R.drawable.ic_person_black);
                    }
                    startActivity(intent);
                }
            });

            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab1);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newContractIntent = new Intent(
                            getActivity().getApplicationContext(),
                            AppendContact.class
                    );

                    startActivityForResult(newContractIntent, REQUEST_GET_CONTACT);
                }
            });
            return view;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GET_CONTACT && resultCode == RESULT_OK){
            String name = data.getStringExtra("name");
            String number = data.getStringExtra("phoneNumber");
            String email = data.getStringExtra("email");

            listContents.add(name);
            listContents2.add(number);

            listview.invalidateViews();
        }
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
