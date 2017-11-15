package mozdevz.org.devfestmaputo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mozdevz.org.devfestmaputo.Adapters.AdapterSponsor;
import mozdevz.org.devfestmaputo.Config.Constantes;
import mozdevz.org.devfestmaputo.Model.Parceiros;
import mozdevz.org.gdgmaputo.R;

import static mozdevz.org.devfestmaputo.Config.Config.listener;

/**
 * Created by SJ on 11/13/2017.
 */

public class FragmentSponsor extends Fragment {

    private RecyclerView mRecyclerView, mRecyclerView2;
    private DatabaseReference mRootRef;
    private FirebaseRecyclerAdapter<Parceiros, AdapterSponsor> mFirebaseAdapter, mFirebaseAdapter2;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager gridLayoutManager;


    private float scale;
    private int width, height, roundPixels;
    Context c;

    public FragmentSponsor() {
        // Required empty public constructor
    }

    public static FragmentSponsor newInstance() {
        FragmentSponsor fragment = new FragmentSponsor();
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
        View rootView = inflater.inflate(R.layout.fragment_sponsor, container, false);

        c = getContext();
        scale = c.getResources().getDisplayMetrics().density;
        width = c.getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
        height = (width / 16) * 9;

        roundPixels = (int) (2 * scale + 0.5f);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_apoio);
        mRecyclerView2 = (RecyclerView) rootView.findViewById(R.id.rv_organizacao);


        mLinearLayoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        gridLayoutManager = new GridLayoutManager(getActivity(), 2)
        {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        mRootRef = FirebaseDatabase.getInstance().getReference().child(Constantes.FIREBASE_SPONSORS);

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Parceiros, AdapterSponsor>(
                Parceiros.class,
                R.layout.item_speaker,
                AdapterSponsor.class,
                mRootRef.child("0").child(Constantes.FIREBASE_LOGOS)) {
            @Override
            protected void populateViewHolder(AdapterSponsor holder, Parceiros model, int position) {
                final DatabaseReference postRef = getRef(position);
                final String postKey = postRef.getKey();

                final String i = model.getLogoUrl();
                final String name = model.getName();
                final String url = model.getUrl();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);

                    }
                });
                holder.txtName.setText(name);

                String auxImage="";
                if (name.equals("Google")) {
                    auxImage = i.replaceAll(i,"https://cdn.vox-cdn.com/uploads/chorus_asset/file/6466217/fixed-google-logo-font.png");

                } else if (name.equals("Muthiana Code")) {
                    auxImage = i.replaceAll(i,"https://media.licdn.com/mpr/mpr/shrink_200_200/AAEAAQAAAAAAAAwXAAAAJDY0NmJjYjlmLWNkYzktNGE4YS1iYjhkLTBjNTU0NmVjY2IzYg.png");

                } else if (name.equals("Mozdevz")) {
                    auxImage = i.replaceAll(i,"http://idear.io/wp-content/uploads/2017/07/MozDevz_muvaTech.png");

                }

                Uri uri = Uri.parse(auxImage);
                DraweeController dc = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setTapToRetryEnabled(true)
                        .setControllerListener(listener)
                        .setOldController(holder.imgFr.getController())
                        .build();

                RoundingParams rp = RoundingParams.fromCornersRadius(5f);
                rp.setBorder(android.R.color.transparent, 1);
                holder.imgFr.setController(dc);
                holder.imgFr.getHierarchy().setRoundingParams(rp);
            }
        };

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter2 = new FirebaseRecyclerAdapter<Parceiros, AdapterSponsor>(
                Parceiros.class,
                R.layout.item_sponsor,
                AdapterSponsor.class,
                mRootRef.child("1").child(Constantes.FIREBASE_LOGOS)) {
            @Override
            protected void populateViewHolder(AdapterSponsor holder, Parceiros model, int position) {
                final DatabaseReference postRef = getRef(position);
                final String postKey = postRef.getKey();

                final String i = model.getLogoUrl();
                final String name = model.getName();
                final String url = model.getUrl();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);

                    }
                });
                holder.txtName.setText(name);
                String auxImage="";
                if (name.equals("Standard Bank")) {
                    auxImage = i.replaceAll(i,"https://upload.wikimedia.org/wikipedia/en/d/d2/Standard_Bank_Logo.png");

                } else if (name.equals("CENFOSS")) {
                    auxImage = i.replaceAll(i,"http://www.cenfoss.co.mz/wp-content/uploads/2017/01/logo-cenfoss.png");

                }

                //auxImage = i.replace("..","");

                Uri uri = Uri.parse(auxImage);
                DraweeController dc = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setTapToRetryEnabled(true)
                        .setControllerListener(listener)
                        .setOldController(holder.imgFr.getController())
                        .build();

                RoundingParams rp = RoundingParams.fromCornersRadii(roundPixels, roundPixels, 0, 0);
                holder.imgFr.setController(dc);
                holder.imgFr.getHierarchy().setRoundingParams(rp);
            }
        };

        mRecyclerView2.setLayoutManager(gridLayoutManager);
        mRecyclerView2.setAdapter(mFirebaseAdapter2);
        return rootView;
    }
}
