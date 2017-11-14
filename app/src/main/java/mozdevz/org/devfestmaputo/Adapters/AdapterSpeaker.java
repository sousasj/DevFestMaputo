package mozdevz.org.devfestmaputo.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import mozdevz.org.gdgmaputo.R;


/**
 * Created by SJ on 12/4/2016.
 */

public class AdapterSpeaker extends  RecyclerView.ViewHolder {
    public TextView txtName;
    public TextView txtCountry;
    public SimpleDraweeView imgFr;

    public AdapterSpeaker(View v) {
        super(v);
        imgFr = (SimpleDraweeView) v.findViewById(R.id.iv_img);
        txtName = (TextView) v.findViewById(R.id.tvName);
        txtCountry = (TextView) v.findViewById(R.id.tvCountry);
    }
}
