package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

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
        options.inSampleSize = 8;

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
        final ArrayList<Uri> uris = intent.getParcelableArrayListExtra("uris");

        final Gallery gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this, images, uris));

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ImageView imageView = (ImageView) findViewById(R.id.image);
                PhotoViewAttacher photoAttacher = new PhotoViewAttacher(imageView);
                photoAttacher.setScaleType(ImageView.ScaleType.FIT_CENTER);

                if (position < images.length){
                    imageView.setImageResource(images[position]);
                } else {
                    try{
                        Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), uris.get(position-images.length));
                        imageView.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }

                gallery.setSelection(position);
            }
        });

        gallery.performItemClick(gallery, position, gallery.getAdapter().getItemId(position));
    }

    public class ImageAdapter extends BaseAdapter{
        private Context context;
        private int[] images;
        private ArrayList<Uri> uris;

        public ImageAdapter(Context c, int[] getimages, ArrayList<Uri> geturis){
            context = c;
            images = getimages;
            uris = geturis;
        }

        public int getCount() {
            return images.length + uris.size();
        }

        public Integer getItem(int position) {
            return images[position];
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);

            if (position < images.length){
                imageView.setImageBitmap(decodeSampledBitmapFromResource(imageView.getResources(), images[position], 100,100));
            } else {
                try{
                    AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(uris.get(position-images.length), "r");
                    BitmapFactory.Options opt = new BitmapFactory.Options();
                    opt.inSampleSize = 4;

                    Bitmap bm = BitmapFactory.decodeFileDescriptor(afd.getFileDescriptor(), null, opt);
                    imageView.setImageBitmap(bm);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            imageView.setLayoutParams(new Gallery.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            return imageView;
        }
    }
}
