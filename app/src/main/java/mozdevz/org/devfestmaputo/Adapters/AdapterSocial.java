package mozdevz.org.devfestmaputo.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import mozdevz.org.gdgmaputo.R;


/**
 * Created by SJ on 12/4/2016.
 */

public class AdapterSocial extends  RecyclerView.ViewHolder {
    public ImageButton imageButton;

    public AdapterSocial(View v) {
        super(v);
        imageButton = (ImageButton) v.findViewById(R.id.ibSocial);
    }
}
