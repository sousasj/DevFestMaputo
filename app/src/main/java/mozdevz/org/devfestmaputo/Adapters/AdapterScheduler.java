package mozdevz.org.devfestmaputo.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import mozdevz.org.gdgmaputo.R;


/**
 * Created by SJ on 12/4/2016.
 */

public class AdapterScheduler extends  RecyclerView.ViewHolder {
    public TextView txtTitle;
    public TextView txtTime;
    public TextView txtType;
    public SimpleDraweeView imgFr;

    public AdapterScheduler(View v) {
        super(v);
        imgFr = (SimpleDraweeView) v.findViewById(R.id.iv_img);
        txtTitle = (TextView) v.findViewById(R.id.tvTitle);
        txtType = (TextView) v.findViewById(R.id.tvType);
        txtTime = (TextView) v.findViewById(R.id.tvTime);
    }
}
