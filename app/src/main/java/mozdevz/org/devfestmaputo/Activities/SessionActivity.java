package mozdevz.org.devfestmaputo.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mozdevz.org.devfestmaputo.Adapters.AdapterSpeaker;
import mozdevz.org.devfestmaputo.Config.Config;
import mozdevz.org.devfestmaputo.Config.Constantes;
import mozdevz.org.devfestmaputo.Model.Speaker;
import mozdevz.org.gdgmaputo.R;


public class SessionActivity extends Config {

    private TextView txtTitle;
    private TextView txtComplexity;
    private TextView txtLannguage;
    private TextView txtDescription;
    private SimpleDraweeView simpleDraweeView;

    private ImageButton ibPresentation;

    private DatabaseReference mRootRef, mRootRefTag, mRootRefSpeaker;

    private float scale;
    private int width, height, roundPixels;

    private String mListID;
    private String title;
    private String complexity;
    private String language;
    private String description;
    private String presentation;
    String speakerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_session);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = this.getIntent();

        title = intent.getStringExtra("title");
        complexity = intent.getStringExtra("complexity");
        language = intent.getStringExtra("langauge");
        description = intent.getStringExtra("description");
        presentation = intent.getStringExtra("presentation");
        mListID = intent.getStringExtra(Constantes.BUNDLE_ID);

        scale = getResources().getDisplayMetrics().density;
        width = getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
        height = (width / 16) * 9;

        roundPixels = (int) (2 * scale + 0.5f);
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.iv_img);


        txtTitle = (TextView) findViewById(R.id.tvTitle);
        txtComplexity = (TextView) findViewById(R.id.tvComplexity);
        txtLannguage = (TextView) findViewById(R.id.tvLanguage);
        txtDescription = (TextView) findViewById(R.id.tvDescription);

        txtTitle.setText(title);
        txtComplexity.setText(complexity);
        txtLannguage.setText(language);
        txtDescription.setText(description);


        Uri uri = Uri.parse(Constantes.FIREBASE_DEV_FEST_MAPUTO_IMAGE);
        simpleDraweeView.setImageURI(uri);

        mRootRefTag = FirebaseDatabase.getInstance().getReference().child(Constantes.FIREBASE_SCHEDULE).child(mListID).child(Constantes.FIREBASE_TAGS);

        mRootRefTag.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    String tag = child.getValue().toString();
                    TextView txtTag = (TextView) findViewById(R.id.tvTagHHK);
                    txtTag.setText(tag);
                    //addMessageBox( tag);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mRootRefTag.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    String tag = child.getValue().toString();
                    Toast.makeText(SessionActivity.this, tag, Toast.LENGTH_SHORT).show();
                    TextView txtTag = (TextView) findViewById(R.id.tvTagHHK);
                    txtTag.setText(tag);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRootRefSpeaker = FirebaseDatabase.getInstance().getReference().child(Constantes.FIREBASE_SCHEDULE).child(mListID).child(Constantes.FIREBASE_SPEAKERS);

        mRootRefSpeaker.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    speakerID = child.getValue().toString();

                    mRootRef = FirebaseDatabase.getInstance().getReference().child(Constantes.FIREBASE_SPEAKERS).child(speakerID);

                    mRootRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Speaker speaker = dataSnapshot.getValue(Speaker.class);

                            TextView txtName;
                            TextView txtCountry;
                            SimpleDraweeView imgFr;

                            imgFr = (SimpleDraweeView) findViewById(R.id.iv_img_speaker);
                            txtName = (TextView) findViewById(R.id.tvName);
                            txtCountry = (TextView) findViewById(R.id.tvCountry);

                            txtName.setText(speaker.getName());
                            txtCountry.setText(speaker.getCountry());

                            Uri uri = Uri.parse(Constantes.FIREBASE_CONSTANTE + speaker.getPhotoUrl());
                            DraweeController dc = Fresco.newDraweeControllerBuilder()
                                    .setUri( uri )
                                    .setTapToRetryEnabled(true)
                                    .setControllerListener( listener )
                                    .setOldController( imgFr.getController() )
                                    .build();

                            RoundingParams rp = RoundingParams.fromCornersRadii(roundPixels, roundPixels, 0, 0);
                            rp.setRoundAsCircle(true);
                            imgFr.setController(dc);
                            imgFr.getHierarchy().setRoundingParams(rp);

                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
