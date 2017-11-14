package mozdevz.org.devfestmaputo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mozdevz.org.devfestmaputo.Activities.SpeakerActivity;
import mozdevz.org.devfestmaputo.Adapters.AdapterSpeaker;
import mozdevz.org.devfestmaputo.Config.Constantes;
import mozdevz.org.devfestmaputo.Model.Speaker;
import mozdevz.org.gdgmaputo.R;

import static mozdevz.org.devfestmaputo.Config.Config.listener;

/**
 * Created by SJ on 11/13/2017.
 */

public class FramentSpeaker extends Fragment {
    private RecyclerView mRecyclerView;
    private DatabaseReference mRootRef;
    private FirebaseRecyclerAdapter<Speaker, AdapterSpeaker> mFirebaseAdapter;
    private GridLayoutManager mGridLayoutManager;

    private float scale;
    private int width, height, roundPixels;
    Context c;
    public FramentSpeaker() {
        // Required empty public constructor
    }

    public static FramentSpeaker newInstance() {
        FramentSpeaker fragment = new FramentSpeaker();
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
        View rootView = inflater.inflate(R.layout.fragment_speaker, container, false);

        c=getContext();
        scale = c.getResources().getDisplayMetrics().density;
        width = c.getResources().getDisplayMetrics().widthPixels - (int)(14 * scale + 0.5f);
        height = (width / 16) * 9;

        roundPixels = (int)(2 * scale + 0.5f);

        final ProgressBar simpleProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar2);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_ll);
        mRecyclerView.setHasFixedSize( true );

        mGridLayoutManager = new GridLayoutManager(getActivity(), 2);

        mRootRef = FirebaseDatabase.getInstance().getReference();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Speaker, AdapterSpeaker>(
                Speaker.class,
                R.layout.item_speaker,
                AdapterSpeaker.class,
                mRootRef.child(Constantes.FIREBASE_SPEAKERS)) {
            @Override
            protected void populateViewHolder(AdapterSpeaker holder, Speaker model, int position) {
                final DatabaseReference postRef = getRef(position);
                final String postKey = postRef.getKey();

                final String i = model.getPhotoUrl();
                final String name = model.getName();
                final String country = model.getCountry();
                final String company = model.getCompany();
                final String bio = model.getBio();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch Activity
                        Intent intent = new Intent(getActivity(), SpeakerActivity.class);
                        intent.putExtra(Constantes.BUNDLE_ID, postKey);
                        intent.putExtra("name", name);
                        intent.putExtra("country", country);
                        intent.putExtra("company", company);
                        intent.putExtra("bio", bio);
                        intent.putExtra("image", i);

                        startActivity(intent);

                    }
                });


                holder.txtName.setText(name);
                holder.txtCountry.setText(country);

                Uri uri = Uri.parse(Constantes.FIREBASE_CONSTANTE + i);
                DraweeController dc = Fresco.newDraweeControllerBuilder()
                        .setUri( uri )
                        .setTapToRetryEnabled(true)
                        .setControllerListener( listener )
                        .setOldController( holder.imgFr.getController() )
                        .build();

                RoundingParams rp = RoundingParams.fromCornersRadii(roundPixels, roundPixels, 0, 0);
                holder.imgFr.setController(dc);
                holder.imgFr.getHierarchy().setRoundingParams(rp);
            }
        };

        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mFirebaseAdapter);
        simpleProgressBar.setVisibility(View.INVISIBLE);
        return rootView;
    }
}
