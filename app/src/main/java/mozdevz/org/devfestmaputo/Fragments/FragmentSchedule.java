package mozdevz.org.devfestmaputo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mozdevz.org.devfestmaputo.Activities.SessionActivity;
import mozdevz.org.devfestmaputo.Adapters.AdapterScheduler;
import mozdevz.org.devfestmaputo.Config.Constantes;
import mozdevz.org.devfestmaputo.Model.Session;
import mozdevz.org.gdgmaputo.R;

import static mozdevz.org.devfestmaputo.Config.Config.listener;

/**
 * Created by SJ on 11/13/2017.
 */

public class FragmentSchedule extends Fragment {
    private RecyclerView mRecyclerView;
    private DatabaseReference mRootRef;
    private FirebaseRecyclerAdapter<Session, AdapterScheduler> mFirebaseAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private float scale;
    private int width, height, roundPixels;
    Context c;

    public FragmentSchedule() {
        // Required empty public constructor
    }

    public static FragmentSchedule newInstance() {
        FragmentSchedule fragment = new FragmentSchedule();
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
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        c = getContext();
        scale = c.getResources().getDisplayMetrics().density;
        width = c.getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
        height = (width / 16) * 9;

        roundPixels = (int) (2 * scale + 0.5f);

        final ProgressBar simpleProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar2);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_ll);
        mRecyclerView.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mRootRef = FirebaseDatabase.getInstance().getReference();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Session, AdapterScheduler>(
                Session.class,
                R.layout.item_schedule,
                AdapterScheduler.class,
                mRootRef.child(Constantes.FIREBASE_SCHEDULE)) {
            @Override
            protected void populateViewHolder(AdapterScheduler holder, Session model, int position) {
                final DatabaseReference postRef = getRef(position);
                final String postKey = postRef.getKey();

                final String i = Constantes.FIREBASE_DEV_FEST_MAPUTO_IMAGE;
                final String title = model.getTitle();
                final String type = model.getComplexity();
                final String language = model.getLanguage();
                final String presentation = model.getTitle();
                final String description = model.getDescription();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getActivity(), SessionActivity.class);
                        intent.putExtra(Constantes.BUNDLE_ID, postKey);
                        intent.putExtra("title", title);
                        intent.putExtra("complexity", type);
                        intent.putExtra("language", language);
                        intent.putExtra("presentation", presentation);
                        intent.putExtra("description", description);

                        startActivity(intent);

                    }
                });

                holder.txtTitle.setText(title);
                holder.txtTime.setText(language);
                holder.txtType.setText(type);

                Uri uri = Uri.parse(i);
                DraweeController dc = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setTapToRetryEnabled(true)
                        .setControllerListener(listener)
                        .setOldController(holder.imgFr.getController())
                        .build();

                RoundingParams rp = RoundingParams.fromCornersRadii(roundPixels, roundPixels, 0, 0);
                rp.setRoundAsCircle(true);
                holder.imgFr.setController(dc);
                holder.imgFr.getHierarchy().setRoundingParams(rp);
            }
        };

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mFirebaseAdapter);
        simpleProgressBar.setVisibility(View.INVISIBLE);
        return rootView;
    }
}
