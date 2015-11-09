package com.example.user.vetguide;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by itlab on 10/6/15.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setPadding(10, 10, 10, 10);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.iconocanino, R.drawable.iconofelino,
            R.drawable.iconoave, R.drawable.iconoconejo,
            R.drawable.iconoiguana, R.drawable.iconomono,
            R.drawable.iconoserviente, R.drawable.iconoroedor,
            R.drawable.iconoaracnido, R.drawable.iconopez,
            R.drawable.iconotortugs, R.drawable.iconoauquenido,
            R.drawable.iconochanchito, R.drawable.iconoerizo,
    };

    public Integer[] getmThumbIds() {
        return mThumbIds;
    }
}
