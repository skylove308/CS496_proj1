package com.example.myapplication;

/**
 * Created by q on 2017-07-04.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ListCViewAdapter extends BaseAdapter  {
    // 버튼 클릭 이벤트를 위한 Listener 인터페이스 정의.
    public interface ListBtnClickListener {
        void onListBtnClick(int position) ;
    }

    // 생성자로부터 전달된 resource id 값을 저장.
    int resourceId ;
    // 생성자로부터 전달된 ListBtnClickListener  저장.
    private ListBtnClickListener listBtnClickListener ;

    private ArrayList<String> ListViewItemList = new ArrayList() ;
    private ArrayList<String> ListViewItemList2 = new ArrayList() ;


    // ListViewBtnAdapter 생성자. 마지막에 ListBtnClickListener 추가.
    public ListCViewAdapter(ArrayList list, ArrayList list2) {
        ListViewItemList = list;
        ListViewItemList2 = list2;
    }

    public int getCount() {
        return ListViewItemList.size() ;
    }

    // 새롭게 만든 Layout을 위한 View를 생성하는 코드
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position ;
        final Context context = parent.getContext();

        // 생성자로부터 저장된 resourceId(listview_btn_item)에 해당하는 Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.word_listview, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)로부터 위젯에 대한 참조 획득
        final TextView textTextView = (TextView) convertView.findViewById(R.id.textView1);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final String listViewItem = ListViewItemList.get(position);
        final String listViewItem2 = ListViewItemList2.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        textTextView.setText(listViewItem);

        // button1 클릭 시 TextView(textView1)의 내용 변경.
        Button button1 = (Button) convertView.findViewById(R.id.button1);
        button1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                if (textTextView.getText().toString() == listViewItem){
                        textTextView.setText(listViewItem2);
                }
                else{
                        textTextView.setText(listViewItem);
                }
            }
        });


        return convertView;
    }

    // button2가 눌려졌을 때 실행되는 onClick함수.


    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return ListViewItemList.get(position) ;
    }

    public void deleteItem (int position) {
        ListViewItemList.remove(position);
        // remove(int) does not exist for arrays, you would have to write that method yourself or use a List instead of an array
        notifyDataSetChanged();
    }
}
