package com.example.Galeria2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends Activity {
    public ArrayList<String> itemList = new ArrayList<String>();
    ImageView imageView;
    GridView gridview;
    ImageAdapter myImageAdapter;
    String ExternalStorageDirectoryPath, targetPath;
    File[] files;
    File targetDirector;
    Intent imageIntent;
    Bitmap[] bitmaps;


    public class ImageAdapter extends BaseAdapter {

        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        void add(String path){
            itemList.add(path);
        }

        void clear() {
            itemList.clear();
        }

        void remove(int index){
            itemList.remove(index);
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(220, 220));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }



            Bitmap bm = decodeSampledBitmapFromUri(itemList.get(position), 220, 220);

            imageView.setImageBitmap(bm);
            return imageView;
        }

        public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {

            Bitmap bm = null;
            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            bm = BitmapFactory.decodeFile(path, options);

            return bm;
        }

        public int calculateInSampleSize(

                BitmapFactory.Options options, int reqWidth, int reqHeight) {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {
                if (width > height) {
                    inSampleSize = Math.round((float)height / (float)reqHeight);
                } else {
                    inSampleSize = Math.round((float)width / (float)reqWidth);
                }
            }

            return inSampleSize;
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        gridview = (GridView) findViewById(R.id.gridview);
        myImageAdapter = new ImageAdapter(this);
        gridview.setAdapter(myImageAdapter);

        ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();
        targetPath = ExternalStorageDirectoryPath + "/DCIM/Test/";

        //Toast.makeText(getApplicationContext(), targetPath, Toast.LENGTH_LONG).show();
        targetDirector = new File(targetPath);

        try {
            files = targetDirector.listFiles();
            for (File file : files){
                myImageAdapter.add(file.getAbsolutePath());
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        bitmaps = new Bitmap[gridview.getChildCount()];

        gridview.setOnItemClickListener(myOnItemClickListener);


        imageIntent = new Intent(this, imageDisplay.class);
    }

    AdapterView.OnItemClickListener myOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    /*
                    * We're passing the URI to prevent it from exceeding the binder transaction buffer
                    **/
                    String imageURI = itemList.get(position);
                    imageIntent.putExtra("ImageURI", imageURI);

                    File f = new File(itemList.get(position));
                    Long last_modified = f.lastModified();
                    imageIntent.putExtra("last_modified", last_modified);

                    String fileName = f.getName();
                    imageIntent.putExtra("fileName", fileName);

                    startActivityForResult(imageIntent, 0);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, imageIntent);

        if (resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case 0:
                {
                    //do something
                }
                break;
            }
        }
    }

}