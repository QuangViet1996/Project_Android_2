package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.vnexpress.R;

import fragment.FragTrangChu;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView lvhienthi;
    AsyncTask_ReadRSS asyncTask_readRSS;

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
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_fragment, new FragTrangChu()).commit();

        addControl();
        addEvent();





    }

    private void addEvent() {
        asyncTask_readRSS=new AsyncTask_ReadRSS(this,lvhienthi);
        asyncTask_readRSS.execute("http://vnexpress.net/rss/tin-moi-nhat.rss");


    }

    private void addControl() {
        lvhienthi= (ListView) findViewById(R.id.lvhienthi);

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
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.thoisu:
               getSupportActionBar().setTitle("Thời sự");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.thegioi:
               getSupportActionBar().setTitle("Thế giới");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.kinhdoanh:
               getSupportActionBar().setTitle("Kinh doanh");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.giaitri:
               getSupportActionBar().setTitle("Giải trí");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.thethao:
               getSupportActionBar().setTitle("Thể thao");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.phapluat:
               getSupportActionBar().setTitle("Pháp luật");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.giaoduc:
               getSupportActionBar().setTitle("Giáo dục");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.suckhoe:
               getSupportActionBar().setTitle("Sức khỏe");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.giadinh:
               getSupportActionBar().setTitle("Gia đình");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.dulich:
               getSupportActionBar().setTitle("Du lịch");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.khoahoc:
               getSupportActionBar().setTitle("Khoa học");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.sohoa:
               getSupportActionBar().setTitle("Số hóa");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.xe:
               getSupportActionBar().setTitle("Xe");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.congdong:
               getSupportActionBar().setTitle("Cộng đồng");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.tamsu:
               getSupportActionBar().setTitle("Tâm sự");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.cuoi:
               getSupportActionBar().setTitle("Cười");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.video:
               getSupportActionBar().setTitle("Video");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;
           case R.id.raovat:
               getSupportActionBar().setTitle("Rao vặt");
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id.content_fragment, new FragTrangChu()).commit();
               break;

       }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
