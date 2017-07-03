package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by q on 2017-07-03.
 */

public class PhotoDetail extends AppCompatActivity {

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = 4;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.photo_detail);

        Intent intent = getIntent();
        final int[] images = intent.getIntArrayExtra("images");
        int position = intent.getIntExtra("position", 0);

        final Gallery gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this, images));

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ImageView imageView = (ImageView) findViewById(R.id.image);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setImageResource(images[position]);
                gallery.setSelection(position);
            }
        });

        gallery.performItemClick(gallery, position, gallery.getAdapter().getItemId(position));
    }

    public class ImageAdapter extends BaseAdapter{
        private Context context;
        private int[] images;

        public ImageAdapter(Context c, int[] getimages){
            context = c;
            images = getimages;
        }

        public int getCount() {
            return images.length;
        }

        public Integer getItem(int position) {
            return images[position];
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);

            imageView.setImageBitmap(decodeSampledBitmapFromResource(imageView.getResources(), images[position], 100,100));
            imageView.setLayoutParams(new Gallery.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            return imageView;
        }
    }
}
