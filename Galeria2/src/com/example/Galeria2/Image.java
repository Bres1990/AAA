package com.example.Galeria2;

/**
 * @author Adam Poterałowicz
 */

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * Class responsible for handling all actions connected with images' handling - saving, deleting, modifying, rating,
 * localizing
 *
 */
public class Image {
    private String imgName;
    private Date imgDate;

    public Image()
    {
        String imgName;
        Date imgDate;
    }

    /**
     * Method enabling user to rename an image
     * @param i -- image
     * @param name -- new name of an image
     */
    public void setName(Image i, String name)
    {
        i.imgName = name;
    }

    /**
     * Method returning an image name - if it's set
     * @param i -- image
     * @return image name
     */
    public String getName(Image i)
    {
        return i.imgName;
    }

    /*public Date getLastModifiedDate(Image i)
    {


        return i.imgDate;
    }*/

    /**
     * Method saving an image in a predefined directory
     * @param finalBitmap -- bitmap of an image to save
     * @param photo_directory -- directory for the photo to be saved at
     */
    public static void SaveImageAtDirectory(Bitmap finalBitmap, String photo_directory) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + photo_directory);
        myDir.mkdirs();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fname = "Image-"+ timeStamp +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        String photo_directory = "/DCIM/100ANDRO";
        File myDir = new File(root + photo_directory);
        myDir.mkdirs();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fname = "Image-"+ timeStamp +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
