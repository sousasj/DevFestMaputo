package mozdevz.org.devfestmaputo.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mozdevz.org.devfestmaputo.Adapters.AdapterSocial;
import mozdevz.org.devfestmaputo.Config.Config;
import mozdevz.org.devfestmaputo.Config.Constantes;
import mozdevz.org.devfestmaputo.Model.Socials;
import mozdevz.org.gdgmaputo.R;


public class SpeakerActivity extends Config {

    private TextView txtName;
    private TextView txtCompany;
    private TextView txtBio;
    private TextView txtCountry;
    private SimpleDraweeView  simpleDraweeViewSpeaker;

    LinearLayout mLnearLayout;

    private RecyclerView mRecyclerView;
    private DatabaseReference  mRootRefTag, mRootRefSocials;
    private FirebaseRecyclerAdapter<Socials, AdapterSocial> mFirebaseAdapter;
    private GridLayoutManager mGridLayoutManager;
    private String name;
    private String country;
    private String company;
    private String bio;
    private String image;
    private String mListID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_speaker);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = this.getIntent();

        name = intent.getStringExtra("name");
        country = intent.getStringExtra("country");
        company = intent.getStringExtra("company");
        bio = intent.getStringExtra("bio");
        image = intent.getStringExtra("image");
        mListID = intent.getStringExtra(Constantes.BUNDLE_ID);

        simpleDraweeViewSpeaker = (SimpleDraweeView) findViewById(R.id.iv_img);

        txtName = (TextView) findViewById(R.id.tvName);
        txtCompany = (TextView) findViewById(R.id.tvCompany);
        txtBio = (TextView) findViewById(R.id.tvBio);
        txtCountry = (TextView) findViewById(R.id.tvCountry);

        mLnearLayout = (LinearLayout) findViewById(R.id.ll);

        txtName.setText(name);
        txtCompany.setText(company);
        txtBio.setText(bio);
        txtCountry.setText(country);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_ll);
        mRecyclerView.setHasFixedSize( true );

        mGridLayoutManager = new GridLayoutManager(this, 5);

        Uri uri = Uri.parse(Constantes.FIREBASE_CONSTANTE  + image);
        simpleDraweeViewSpeaker.setImageURI(uri);

        mRootRefTag = FirebaseDatabase.getInstance().getReference().child(Constantes.FIREBASE_SPEAKERS).child(mListID).child(Constantes.FIREBASE_TAGS);

        mRootRefTag.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    String tag = child.getValue().toString();

                    CardView card = new CardView(getApplicationContext());

                    // Set the CardView layoutParams
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(15, 15, 15, 15);
                    card.setLayoutParams(params);

                    // Set CardView corner radius
                    card.setRadius(9);

                    final float scale = getResources().getDisplayMetrics().density;
                    int padding_20dp = (int) (20 * scale + 0.5f);
                    // Set cardView content padding
                    card.setContentPadding(15, 15, 15, 15);

                    // Set a background color for CardView
                    card.setCardBackgroundColor(Color.GRAY);

                    // Set the CardView maximum elevation
                    card.setMaxCardElevation(3);

                    // Set CardView elevation
                    card.setCardElevation(3);

                    // Initialize a new TextView to put in CardView
                    TextView tv = new TextView(getApplicationContext());
                    tv.setLayoutParams(params);
                    tv.setText(tag);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11);
                    tv.setTextColor(Color.WHITE);

                    // Put the TextView in CardView
                    card.addView(tv);

                    // Finally, add the CardView in root layout
                    mLnearLayout.addView(card);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRootRefSocials = FirebaseDatabase.getInstance().getReference().child(Constantes.FIREBASE_SPEAKERS)
                .child(mListID);

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Socials, AdapterSocial>(
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
                Toast.makeText(SpeakerActivity.this, name, Toast.LENGTH_SHORT).show();

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
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

              /*  if(name.equalsIgnoreCase("Facebook")){
                    holder.imageButton.setImageResource(R.drawable.facebook);

                    Intent intent = null;
                    try {
                        url.replace("https://twitter.com/","");
                        // get the Twitter app if possible
                        getPackageManager().getPackageInfo("com.twitter.android", 0);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/"+url));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    } catch (Exception e) {
                        // no Twitter app, revert to browser
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    }
                    startActivity(intent);


                } else if(name.equalsIgnoreCase("Twitter")){
                    holder.imageButton.setImageResource(R.drawable.twitter);

                    Intent intent = null;
                    try {
                        url.replace("https://twitter.com/","");
                        // get the Twitter app if possible
                        getPackageManager().getPackageInfo("com.twitter.android", 0);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id="+url));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    } catch (Exception e) {
                        // no Twitter app, revert to browser
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    }
                    startActivity(intent);

                } else if(name.equalsIgnoreCase("Medium")){
                    holder.imageButton.setImageResource(R.drawable.web);

                    Intent intent = null;
                    try {
                        url.replace("https://medium.com/","");
                        // get the Twitter app if possible
                        getPackageManager().getPackageInfo("com.twitter.android", 0);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id="+url));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    } catch (Exception e) {
                        // no Twitter app, revert to browser
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"+url));
                    }
                    startActivity(intent);

                } else if(name.equalsIgnoreCase("LinkedIn")){
                    holder.imageButton.setImageResource(R.drawable.linkedin);

                    Intent intent = null;
                    try {
                        url.replace("https://twitter.com/","");
                        // get the Twitter app if possible
                        getPackageManager().getPackageInfo("com.twitter.android", 0);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id="+url));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    } catch (Exception e) {
                        // no Twitter app, revert to browser
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    }
                    startActivity(intent);

                }else if(name.equalsIgnoreCase("GitHub")){
                    holder.imageButton.setImageResource(R.drawable.github_circle);

                    Intent intent = null;
                    try {
                        url.replace("https://github.com/","");
                        // get the Twitter app if possible
                        getPackageManager().getPackageInfo("com.twitter.android", 0);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id="+url));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    } catch (Exception e) {
                        // no Twitter app, revert to browser
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    }
                    startActivity(intent);

                }else if(name.equalsIgnoreCase("Google+")){
                    holder.imageButton.setImageResource(R.drawable.google);

                    Intent intent = null;
                    try {
                        url.replace("https://plus.google.com/","");
                        // get the Twitter app if possible
                        intent.setClassName("com.google.android.apps.plus",
                                "com.google.android.apps.plus.phone.UrlGatewayActivity");
                        intent.putExtra("customAppUri", "FAN_PAGE_ID");
                        intent.setPackage("com.google.android.apps.plus");
                    } catch (Exception e) {
                        // no Twitter app, revert to browser
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    }
                    startActivity(intent);

                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });*/

            }
        };
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mFirebaseAdapter);

    }

}
