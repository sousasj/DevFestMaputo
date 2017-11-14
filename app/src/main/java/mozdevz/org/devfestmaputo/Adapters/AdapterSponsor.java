package mozdevz.org.devfestmaputo.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import mozdevz.org.gdgmaputo.R;


/**
 * Created by SJ on 12/4/2016.
 */

public class AdapterSponsor extends  RecyclerView.ViewHolder {
    public TextView txtName;
    public SimpleDraweeView imgFr;

    public AdapterSponsor(View v) {
        super(v);
        imgFr = (SimpleDraweeView) v.findViewById(R.id.iv_img);
        txtName = (TextView) v.findViewById(R.id.tvName);
    }
}
