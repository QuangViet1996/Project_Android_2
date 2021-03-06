package activity;


import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.vnexpress.R;

import variables.variables;


public class MainActivity extends AppCompatActivity
        implements ObservableScrollViewCallbacks, NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {
    ObservableListView listview;
    AsyncTask_ReadRSS asyncTask_readRSS;
    AsyncTask_VideoHTML asyncTask_video;
    variables val;
    View tempView;
    ImageView imageViewItem;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView txtTitle;
    int position;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabCalculator = (FloatingActionButton) findViewById(R.id.fabCalculator);
        fabCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActionButton_Calculator.class);
                startActivity(intent);
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        addControl();
        addEvent();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void addControl() {
        listview = (ObservableListView) findViewById(R.id.listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

    }

    private void addEvent() {
        listview.setScrollViewCallbacks(this);
        listview.setOnItemClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (asyncTask_readRSS.url != null) {
                    swipeRefreshLayout.setRefreshing(true);
                    AsyncTask(asyncTask_readRSS.url);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    swipeRefreshLayout.setRefreshing(true);
                    AsyncTask_Video();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        Log.d("test", "url: " + val.URL.length);
        AsyncTask(val.URL[0]);


        //AsyncTask_Video();


    }

    public void AsyncTask(String url) {
//        Fragment fragment = new fragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment,fragment).commit();

        asyncTask_readRSS = new AsyncTask_ReadRSS(MainActivity.this);
        asyncTask_readRSS.execute(url);
    }

    public void AsyncTask_Video() {
//        Fragment fragment = new fragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment,fragment).commit();

        asyncTask_video = new AsyncTask_VideoHTML(MainActivity.this);
        asyncTask_video.execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.trangchu:
                getSupportActionBar().setTitle("Trang chủ");
                AsyncTask(val.URL[0]);
                break;
            case R.id.thoisu:
                getSupportActionBar().setTitle("Thời sự");
                AsyncTask(val.URL[1]);
                break;
            case R.id.thegioi:
                getSupportActionBar().setTitle("Thế giới");
                AsyncTask(val.URL[2]);
                break;
            case R.id.kinhdoanh:
                getSupportActionBar().setTitle("Kinh doanh");
                AsyncTask(val.URL[3]);
                break;
            case R.id.giaitri:
                getSupportActionBar().setTitle("Giải trí");
                AsyncTask(val.URL[4]);
                break;
            case R.id.thethao:
                getSupportActionBar().setTitle("Thể thao");
                AsyncTask(val.URL[5]);
                break;
            case R.id.phapluat:
                getSupportActionBar().setTitle("Pháp luật");
                AsyncTask(val.URL[6]);
                break;
            case R.id.giaoduc:
                getSupportActionBar().setTitle("Giáo dục");
                AsyncTask(val.URL[7]);
                break;
            case R.id.suckhoe:
                getSupportActionBar().setTitle("Sức khỏe");
                AsyncTask(val.URL[8]);
                break;
            case R.id.giadinh:
                getSupportActionBar().setTitle("Gia đình");
                AsyncTask(val.URL[9]);
                break;
            case R.id.dulich:
                getSupportActionBar().setTitle("Du lịch");
                AsyncTask(val.URL[10]);
                break;
            case R.id.khoahoc:
                getSupportActionBar().setTitle("Khoa học");
                AsyncTask(val.URL[11]);
                break;
            case R.id.sohoa:
                getSupportActionBar().setTitle("Số hóa");
                AsyncTask(val.URL[12]);
                break;
            case R.id.xe:
                getSupportActionBar().setTitle("Xe");
                AsyncTask(val.URL[13]);
                break;
            case R.id.congdong:
                getSupportActionBar().setTitle("Cộng đồng");
                AsyncTask(val.URL[14]);
                break;
            case R.id.tamsu:
                getSupportActionBar().setTitle("Tâm sự");
                AsyncTask(val.URL[15]);
                break;
            case R.id.cuoi:
                getSupportActionBar().setTitle("Cười");
                AsyncTask(val.URL[16]);
                break;
            case R.id.video:
                getSupportActionBar().setTitle("Video");
                AsyncTask_Video();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar ab = getSupportActionBar();
        if (ab == null) {
            return;
        }
        if (scrollState == scrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == scrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        position = i;
        try {
            if (asyncTask_video.arrayList_Video != null) {
                if (tempView != null) {
                    if (asyncTask_video.arrayList_Video.get(position).getVideo() != null) {
                        tempView.findViewById(R.id.imageViewItemPlayer).setVisibility(View.VISIBLE);
                        tempView.findViewById(R.id.imageViewItem).setVisibility(View.VISIBLE);
                        VideoView videoView = (VideoView) tempView.findViewById(R.id.videoViewItem);
                        videoView.pause();
                        videoView.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(MainActivity.this, "Lỗi!!!", Toast.LENGTH_LONG).show();
                    }
                }
                if (asyncTask_video.arrayList_Video.get(position).getLink() != null) {
                    txtTitle = (TextView) view.findViewById(R.id.videoTitle);
                    Bundle bundle = new Bundle();
                    try {
                        if (asyncTask_video.arrayList_Video.get(position).getLink() != null) {
                            bundle.putString("link", asyncTask_video.arrayList_Video.get(position).getLink());
                            Intent intent = new Intent(MainActivity.this, WebView_Details.class);
                            intent.putExtra("data", bundle);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Lỗi!!!", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        // bundle.putString("link", asyncTask_video.arrayList_Video.get(i).getLink());
                    }
                } else {
                    tempView = view;
                    final VideoView video = (VideoView) view.findViewById(R.id.videoViewItem);
                    imageViewItem = (ImageView) view.findViewById(R.id.imageViewItemPlayer);
                    view.findViewById(R.id.imageViewItem).setVisibility(View.GONE);
                    imageViewItem.setVisibility(View.GONE);
                    video.setVisibility(View.VISIBLE);
                    video.setVideoPath(asyncTask_video.arrayList_Video.get(position).getVideo());
                    video.start();
                }

            }
            else{
                Bundle bundle = new Bundle();
                bundle.putString("link", asyncTask_readRSS.arrayList_News.get(position).getLink());
                Intent intent = new Intent(MainActivity.this, WebView_Details.class);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        }
        catch(Exception ex){
            Toast.makeText(MainActivity.this, "Lỗi!!! Không thể tải tin....", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


}
