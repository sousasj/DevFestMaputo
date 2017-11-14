package mozdevz.org.devfestmaputo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import mozdevz.org.devfestmaputo.Config.Constantes;
import mozdevz.org.gdgmaputo.R;

/**
 * Created by SJ on 11/13/2017.
 */

public class FragmentHome extends Fragment {

    private SimpleDraweeView simpleDraweeView, simpleDraweeViewBanner;
    private TextView txtLocal;
    private TextView txtDate;
    private TextView txtSubtitle;
    private TextView txtGDG;

    public FragmentHome() {
        // Required empty public constructor
    }

    public static FragmentHome newInstance() {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        simpleDraweeView = (SimpleDraweeView) rootView.findViewById(R.id.iv_img);
        txtDate = (TextView) rootView.findViewById(R.id.txtDate);
        txtLocal = (TextView) rootView.findViewById(R.id.txtLocal);
        txtSubtitle = (TextView) rootView.findViewById(R.id.tvSubtitle);

        Uri uri = Uri.parse(Constantes.FIREBASE_DEV_FEST_MAPUTO_IMAGE);
        simpleDraweeView.setImageURI(uri);

        txtDate.setText(Constantes.BUNDLE_DATE);
        txtLocal.setText(Constantes.BUNDLE_LOCAL);
        txtSubtitle.setText(Constantes.BUNDLE_DEV_FEST_MAPUTO_QUOTE + getResources().getString(R.string.buy_ticket));


        return rootView;
    }
}
