package mozdevz.org.devfestmaputo.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import mozdevz.org.gdgmaputo.R;


/**
 * Created by SJ on 12/4/2016.
 */

public class AdapterTeam extends  RecyclerView.ViewHolder {
    public TextView txtName;
    public TextView txtTitle;
    public SimpleDraweeView imgFr;
    public RecyclerView mRecyclerViewSocial;

    public AdapterTeam(View v) {
        super(v);
        imgFr = (SimpleDraweeView) v.findViewById(R.id.iv_img);
        txtName = (TextView) v.findViewById(R.id.tvName);
        txtTitle = (TextView) v.findViewById(R.id.tvTitle);
        mRecyclerViewSocial = (RecyclerView) v.findViewById(R.id.my_ll);
    }
}
