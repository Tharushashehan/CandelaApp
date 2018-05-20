package lk.candelalearning.candelalearning;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import static android.support.v7.widget.TintTypedArray.obtainStyledAttributes;

/**
 * Created by tharu on 5/19/2018.
 */

public class ImageGalleryAdaptor extends BaseAdapter {
    private Context context;
    private Integer[] images={R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six, R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten};
    private int itemBackground;
    int imageBackground;

    public ImageGalleryAdaptor(Context context) {

        this.context = context;
        TypedArray a;
        a = context.obtainStyledAttributes(R.styleable.GalleryWhite);
        itemBackground = a.getResourceId(R.styleable.GalleryWhite_android_background, 0);
        a.recycle();
    }

    @Override
    public int getCount() {

        return images.length;
    }

    @Override
    public Object getItem(int arg0) {

        return arg0;
    }

    @Override
    public long getItemId(int arg0) {

        return arg0;
    }

//    @Override
//    public View getView(int arg0, View arg1, ViewGroup arg2) {
//
//        ImageView imageView = new ImageView(context);
//        imageView.setImageResource(images[arg0]);
//        return imageView;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new Gallery.LayoutParams(150, 120));
//        imageView.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.FILL_PARENT, 25));
//        imageView.setBackgroundResource(itemBackground);
        return imageView;
    }
}
