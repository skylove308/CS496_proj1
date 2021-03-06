package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<String> listViewItemList = new ArrayList() ;
    private ArrayList<String> listViewItemList2 = new ArrayList() ;
    private ArrayList<String> listViewItemList3 = new ArrayList() ;

    // ListViewAdapter의 생성자
    public ListAViewAdapter(ArrayList list, ArrayList list2, ArrayList list3) {
        listViewItemList = list;
        listViewItemList2 = list2;
        listViewItemList3 = list3;
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contact_listview, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView1) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.name) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.phonenumber) ;
        ImageView message = (ImageView) convertView.findViewById(R.id.mail_image);
        ImageView call = (ImageView) convertView.findViewById(R.id.call_image);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득

        String listViewItem = listViewItemList.get(position);
        final String listViewItem2 = listViewItemList2.get(position);
        String listViewItem3 = listViewItemList3.get(position);



        // 아이템 내 각 위젯에 데이터 반영
        if(context.getResources().getIdentifier(listViewItem3, "drawable", context.getApplicationContext().getPackageName()) != 0){
            iconImageView.setImageResource(context.getResources().getIdentifier(listViewItem3, "drawable", context.getApplicationContext().getPackageName()));
        }else {
            iconImageView.setImageResource(R.drawable.ic_person_black);
        }
        titleTextView.setText(listViewItem);
        descTextView.setText(listViewItem2);

        call.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                String phoneNumber = listViewItem2;
                String tel = "tel:" + phoneNumber;
                Intent myIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(tel));
                context.startActivity(myIntent);
            }
        });

        call.setFocusable(false);

        message.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                String phonenumber = listViewItem2;
                String tel = "tel:" + phonenumber;
                Intent myintent = new Intent(Intent.ACTION_VIEW, Uri.parse(tel));
                context.startActivity(myintent);
            }
        });

        message.setFocusable(false);

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    }

