package mozdevz.org.devfestmaputo.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mozdevz.org.devfestmaputo.Adapters.AdapterSocial;
import mozdevz.org.devfestmaputo.Adapters.AdapterSpeaker;
import mozdevz.org.devfestmaputo.Adapters.AdapterSponsor;
import mozdevz.org.devfestmaputo.Adapters.AdapterTeam;
import mozdevz.org.devfestmaputo.Config.Config;
import mozdevz.org.devfestmaputo.Config.Constantes;
import mozdevz.org.devfestmaputo.Model.Parceiros;
import mozdevz.org.devfestmaputo.Model.Socials;
import mozdevz.org.devfestmaputo.Model.Speaker;
import mozdevz.org.devfestmaputo.Model.Team;
import mozdevz.org.gdgmaputo.R;


public class TeamActivity extends Config {
    private RecyclerView mRecyclerView, mRecyclerView2;
    private DatabaseReference mRootRef;
    private FirebaseRecyclerAdapter<Team, AdapterTeam> mFirebaseAdapter, mFirebaseAdapter2;
    private LinearLayoutManager mLinearLayoutManager, mLinearLayoutManager2;


    private float scale;
    private int width, height, roundPixels;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        scale = getResources().getDisplayMetrics().density;
        width = getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
        height = (width / 16) * 9;

        roundPixels = (int) (2 * scale + 0.5f);

        mRecyclerView2 = (RecyclerView) findViewById(R.id.rv_suporte);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_organizacao);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager2 = new LinearLayoutManager(this);

        mRootRef = FirebaseDatabase.getInstance().getReference().child(Constantes.FIREBASE_TEAM);

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Team, AdapterTeam>(
                Team.class,
                R.layout.item_team,
                AdapterTeam.class,
                mRootRef.child("0").child(Constantes.FIREBASE_MEMBERS)) {
            @Override
            protected void populateViewHolder(AdapterTeam holder, Team model, int position) {
                final DatabaseReference postRef = getRef(position);
                final String postKey = postRef.getKey();

                final String i = model.getPhotoUrl();
                final String name = model.getName();
                final String title = model.getTitle();


                holder.txtName.setText(name);
                holder.txtTitle.setText(title);

                Uri uri = Uri.parse(Constantes.FIREBASE_CONSTANTE + i);
                DraweeController dc = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setTapToRetryEnabled(true)
                        .setControllerListener(listener)
                        .setOldController(holder.imgFr.getController())
                        .build();

                RoundingParams rp = RoundingParams.fromCornersRadius(5f);
                rp.setRoundAsCircle(true);
                rp.setBorder(android.R.color.transparent, 1);
                holder.imgFr.setController(dc);
                holder.imgFr.getHierarchy().setRoundingParams(rp);

                DatabaseReference mRootRefSocials = FirebaseDatabase.getInstance().getReference().child(Constantes.FIREBASE_TEAM)
                        .child("0").child(Constantes.FIREBASE_MEMBERS).child(postKey);

                GridLayoutManager mGridLayoutManager =  new GridLayoutManager(getApplicationContext(), 5);
                FirebaseRecyclerAdapter<Socials, AdapterSocial> mFirebaseAdapterSocial = new FirebaseRecyclerAdapter<Socials, AdapterSocial>(
                        Socials.class,
                        R.layout.item_social,
                        AdapterSocial.class,
                        mRootRefSocials.child(Constantes.FIREBASE_SOCIALS)) {
                    @Override
                    protected void populateViewHolder(AdapterSocial holder, Socials model, int position) {
                        final DatabaseReference postRef = getRef(position);
                        final String postKey = postRef.getKey();

                        final String url = model.getLink();
                        final String name = model.getName();
                        if(name.equalsIgnoreCase("Facebook")){
                            holder.imageButton.setImageResource(R.drawable.facebook);
                        } else if(name.equalsIgnoreCase("Twitter")){
                            holder.imageButton.setImageResource(R.drawable.twitter);
                        } else if(name.equalsIgnoreCase("Medium")){
                            holder.imageButton.setImageResource(R.drawable.web);
                        } else if(name.equalsIgnoreCase("LinkedIn")){
                            holder.imageButton.setImageResource(R.drawable.linkedin);
                        }else if(name.equalsIgnoreCase("GitHub")){
                            holder.imageButton.setImageResource(R.drawable.github_circle);
                        }else if(name.equalsIgnoreCase("Google+")){
                            holder.imageButton.setImageResource(R.drawable.google);
                        }

                        holder.imageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }
                };
                holder.mRecyclerViewSocial.setLayoutManager(mGridLayoutManager);
                holder.mRecyclerViewSocial.setAdapter(mFirebaseAdapterSocial);
            }
        };

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mFirebaseAdapter);


        mFirebaseAdapter2 = new FirebaseRecyclerAdapter<Team, AdapterTeam>(
                Team.class,
                R.layout.item_team,
                AdapterTeam.class,
                mRootRef.child("1").child(Constantes.FIREBASE_MEMBERS)) {
            @Override
            protected void populateViewHolder(AdapterTeam holder, Team model, int position) {
                final DatabaseReference postRef = getRef(position);
                final String postKey = postRef.getKey();

                final String i = model.getPhotoUrl();
                final String name = model.getName();
                final String title = model.getTitle();

                holder.txtName.setText(name);
                holder.txtTitle.setText(title);

                Uri uri = Uri.parse(Constantes.FIREBASE_CONSTANTE + i);
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

                DatabaseReference mRootRefSocials = FirebaseDatabase.getInstance().getReference().child(Constantes.FIREBASE_TEAM)
                        .child("1").child(Constantes.FIREBASE_MEMBERS).child(postKey);

                GridLayoutManager mGridLayoutManager =  new GridLayoutManager(getApplicationContext(), 5);
                FirebaseRecyclerAdapter<Socials, AdapterSocial> mFirebaseAdapterSocial = new FirebaseRecyclerAdapter<Socials, AdapterSocial>(
                        Socials.class,
                        R.layout.item_social,
                        AdapterSocial.class,
                        mRootRefSocials.child(Constantes.FIREBASE_SOCIALS)) {
                    @Override
                    protected void populateViewHolder(AdapterSocial holder, Socials model, int position) {
                        final DatabaseReference postRef = getRef(position);
                        final String postKey = postRef.getKey();

                        final String url = model.getLink();
                        final String name = model.getName();
                        Toast.makeText(TeamActivity.this, name, Toast.LENGTH_SHORT).show();
                        if(name.equalsIgnoreCase("Facebook")){
                            holder.imageButton.setImageResource(R.drawable.facebook);
                        } else if(name.equalsIgnoreCase("Twitter")){
                            holder.imageButton.setImageResource(R.drawable.twitter);
                        } else if(name.equalsIgnoreCase("Medium")){
                            holder.imageButton.setImageResource(R.drawable.web);
                        } else if(name.equalsIgnoreCase("LinkedIn")){
                            holder.imageButton.setImageResource(R.drawable.linkedin);
                        }else if(name.equalsIgnoreCase("GitHub")){
                            holder.imageButton.setImageResource(R.drawable.github_circle);
                        }else if(name.equalsIgnoreCase("Google+")){
                            holder.imageButton.setImageResource(R.drawable.google);
                        }

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                            }
                        });

                    }
                };
                holder.mRecyclerViewSocial.setLayoutManager(mGridLayoutManager);
                holder.mRecyclerViewSocial.setAdapter(mFirebaseAdapterSocial);
            }
        };

        mRecyclerView2.setLayoutManager(mLinearLayoutManager2);
        mRecyclerView2.setAdapter(mFirebaseAdapter2);
    }

}
