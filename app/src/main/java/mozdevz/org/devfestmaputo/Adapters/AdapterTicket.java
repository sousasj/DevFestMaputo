package mozdevz.org.devfestmaputo.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mozdevz.org.gdgmaputo.R;


/**
 * Created by SJ on 12/4/2016.
 */

public class AdapterTicket extends  RecyclerView.ViewHolder {
    public TextView txtName;
    public TextView txtPrice;
    public TextView txtTime;
    public TextView txtInfo;

    public AdapterTicket(View v) {
        super(v);
        txtName = (TextView) v.findViewById(R.id.tvName);
        txtPrice = (TextView) v.findViewById(R.id.tvPrice);
        txtTime = (TextView) v.findViewById(R.id.tvTime);
        txtInfo = (TextView) v.findViewById(R.id.tvInfo);
    }
}
