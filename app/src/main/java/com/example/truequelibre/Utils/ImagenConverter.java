package com.example.truequelibre.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ImagenConverter {
    public static byte[] convertImgViewToArray(ImageView img1){
        Bitmap bitmap = ((BitmapDrawable) img1.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap convertByteToBitmap(String bytesBase64){
        if(bytesBase64==null) return null;
        byte[] byteArray =  Base64.decode(bytesBase64, Base64.DEFAULT);
        ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        return theImage;

    }

}
