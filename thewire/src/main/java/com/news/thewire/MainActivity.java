package com.news.thewire;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.news.thewire.client.ApiClient;
import com.news.thewire.client.ApiInterface;
import com.news.thewire.fragments.CultureFragment;
import com.news.thewire.fragments.LawFragment;
import com.news.thewire.fragments.OnNewsItemClick;
import com.news.thewire.fragments.OpinionFragment;
import com.news.thewire.fragments.PoliticsFragment;
import com.news.thewire.fragments.EconomicsFragment;
import com.news.thewire.fragments.ExternalAffairsFragment;
import com.news.thewire.fragments.ScienceFragment;
import com.news.thewire.fragments.SecurityFragment;
import com.news.thewire.fragments.SocietyFragment;
import com.news.thewire.model.HomeParams;
import com.news.thewire.model.HomeResponse;
import com.news.thewire.model.ModelCategory;
import com.news.thewire.model.ModelNews;
import com.news.thewire.model.ModelNewsDetails;
import com.news.thewire.model.ModelNewsPaged;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnNewsItemClick {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

//        getCategories();
//        getHome();
        getPoliticsPaged();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PoliticsFragment(), "Politics");
        adapter.addFragment(new EconomicsFragment(), "Economics");
        adapter.addFragment(new ExternalAffairsFragment(), "External Affairs");
        adapter.addFragment(new SecurityFragment(), "Security");
        adapter.addFragment(new LawFragment(), "Law");
        adapter.addFragment(new ScienceFragment(), "Science");
        adapter.addFragment(new SocietyFragment(), "Society");
        adapter.addFragment(new CultureFragment(), "Culture");
        adapter.addFragment(new OpinionFragment(), "Opinion");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onNewsItemClicked(String url) {
        Log.e(TAG, "onNewsItemClicked: URL : "+url );
        Intent intent = new Intent(this,ActivityDN.class);
        intent.putExtra("URL",url);

        startActivity(intent);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void getCategories() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ModelCategory[]> call = apiInterface.getCategories();
        call.enqueue(new Callback<ModelCategory[]>() {
            @Override
            public void onResponse(Call<ModelCategory[]>call, Response<ModelCategory[]> response) {
                ModelCategory[] modelCategories = response.body();
                for (ModelCategory category : modelCategories) {
                    Log.e(TAG, "onResponse: "+category.getTitle() );
                }

                ModelCategory.setModelCategories(modelCategories);
            }

            @Override
            public void onFailure(Call<ModelCategory[]>call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }

    private void getHome() {
        Log.e(TAG, "getHome: " );
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        JSONArray jsonArray = new JSONArray();

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("genre","fixed");
            jsonObject.put("name","trending");
            jsonObject.put("limit","10");
            jsonArray.put(jsonObject);

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("genre","type");
            jsonObject1.put("name","highlights");
            jsonObject1.put("limit","10");
            jsonArray.put(jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ModelNews> call = apiInterface.getPolitics(56,50,jsonArray);
        call.enqueue(new Callback<ModelNews>() {
            @Override
            public void onResponse(Call<ModelNews>call, Response<ModelNews> response) {
                ModelNews modelNews = response.body();
                ModelNews.setModelNews(modelNews);
                Log.e(TAG, "onResponse: response body  -------------- "+response.body() );
                Log.e(TAG, "onResponse: request  -------------- "+response.raw().request().url() );
                Log.e(TAG, "onResponse: json  -------------- "+new Gson().toJson(response.body()) );
                Log.e(TAG, "onResponse: response -------------- "+response );
                Log.e(TAG, "onResponse: -------------- "+modelNews );
                Log.e(TAG, "onResponse: -------------- "+modelNews.toString() );
                try {
                    for (ModelNews.Generic generic : modelNews.getGeneric()) {
                        Log.e(TAG, "onResponse 1 : "+generic.getPostTitle());
                        Log.e(TAG, "onResponse 2 : "+generic.getPostAuthor());
                        for (ModelNews.PostAuthorName postAuthorName : generic.getPostAuthorName()) {
                            Log.e(TAG, "onResponse 1 : "+postAuthorName.getAuthorName());
                            Log.e(TAG, "onResponse 2 : "+postAuthorName.getAuthorAvatar());
                            Log.e(TAG, "onResponse 2 : "+postAuthorName.getAuthorSlug());
                            Log.e(TAG, "onResponse 2 : -------------------------");
                        }
                    }
                    setupViewPager(viewPager);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "onResponse: EXCEPTION > "+e.getMessage() );
                }

            }

            @Override
            public void onFailure(Call<ModelNews>call, Throwable t) {
                Log.e(TAG, "onFailure: "+call.request().url() );
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }
    private void getPoliticsPaged() {
        Log.e(TAG, "getPoliticsPaged: " );
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ModelNewsPaged[]> call = apiInterface.getPolitics(10,1);
        call.enqueue(new Callback<ModelNewsPaged[]>() {
            @Override
            public void onResponse(Call<ModelNewsPaged[]>call, Response<ModelNewsPaged[]> response) {
                ModelNewsPaged[] modelNews = response.body();

                ModelNewsPaged.setModelNews(modelNews);
                setupViewPager(viewPager);
            }

            @Override
            public void onFailure(Call<ModelNewsPaged[]>call, Throwable t) {
                Log.e(TAG, "onFailure: "+call.request().url() );
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }
}
