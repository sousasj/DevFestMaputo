package mozdevz.org.devfestmaputo.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;

import mozdevz.org.devfestmaputo.Config.Constantes;
import mozdevz.org.devfestmaputo.Fragments.FragmentHome;
import mozdevz.org.devfestmaputo.Fragments.FragmentSchedule;
import mozdevz.org.devfestmaputo.Fragments.FragmentSponsor;
import mozdevz.org.devfestmaputo.Fragments.FramentSpeaker;
import mozdevz.org.devfestmaputo.Fragments.TicketDialog;
import mozdevz.org.devfestmaputo.Tabs.SlidingTabLayout;
import mozdevz.org.gdgmaputo.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private String img;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                TicketDialog td = new TicketDialog();
                td.show(fm, "Ticket");
            }
        });

        mPager = (ViewPager) findViewById(R.id.pager);

        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);

        mTabs.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mTabs.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent));

        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);

        mPager.setAdapter(new MyPagerAdaper(getSupportFragmentManager()));
        mTabs.setViewPager(mPager);

        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    class MyPagerAdaper extends FragmentStatePagerAdapter {

        String[] tabText;

        public MyPagerAdaper(FragmentManager fm) {
            super(fm);
            tabText = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabText[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;

            switch (position) {
                case 0:
                    fragment = FragmentHome.newInstance();
                    break;
                case 1:
                    fragment = FragmentSchedule.newInstance();
                    break;
                case 2:
                    fragment = FramentSpeaker.newInstance();
                    break;
                case 3:
                    fragment = FragmentSponsor.newInstance();
                    break;
                default: {
                    fragment = FragmentHome.newInstance();
                    break;
                }

            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.aboutApp:
                i = new Intent(MainActivity.this, TeamActivity.class);
                startActivity(i);
                return true;
            case R.id.teamApp:
                i = new Intent(MainActivity.this, TeamActivity.class);
                startActivity(i);
                return true;
            case R.id.shareApp:
                i = new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app));
                i.setType("text/plain");
                startActivity(Intent.createChooser(i, "Partilhe a experiencia do DevFest"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
