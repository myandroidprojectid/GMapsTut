package com.selfdev.rxretrowire;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.selfdev.rxretrowire.client.ApiClient;
import com.selfdev.rxretrowire.client.ApiInterface;
import com.selfdev.rxretrowire.fragments.CultureFragment;
import com.selfdev.rxretrowire.fragments.EconomicsFragment;
import com.selfdev.rxretrowire.fragments.ExternalAffairsFragment;
import com.selfdev.rxretrowire.fragments.LawFragment;
import com.selfdev.rxretrowire.fragments.OnNewsItemClick;
import com.selfdev.rxretrowire.fragments.OpinionFragment;
import com.selfdev.rxretrowire.fragments.PoliticsFragment;
import com.selfdev.rxretrowire.fragments.ScienceFragment;
import com.selfdev.rxretrowire.fragments.SecurityFragment;
import com.selfdev.rxretrowire.fragments.SocietyFragment;
import com.selfdev.rxretrowire.model.ModelCategory;
import com.selfdev.rxretrowire.model.ModelNews;
import com.selfdev.rxretrowire.model.ModelNewsDetails;
import com.selfdev.rxretrowire.model.ModelNewsPaged;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function4;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

//RETROFIT
//https://www.androidhive.info/2016/05/android-working-with-retrofit-http-library/
//RETRO DEFS
//https://www.journaldev.com/13639/retrofit-android-example-tutorial

//RxJava
//http://www.zoftino.com/retrofit-rxjava-android-example
//RxJavaDefs
//https://mindorks.com/course/learn-rxjava/chapter/id/2/page/id/7

//https://github.com/codepath/android_guides/wiki/Consuming-APIs-with-Retrofit

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

        setupViewPager(viewPager);

//        getCategories();
//        getHome();
//        getPoliticsNews();
//        getPoliticsEconomyPaged();
//        flatMapPoliticsEconomics();
//        getPoliticsEconomyPaged();

//        flapMapEx();
//        flatMapTask();
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


    /*private void getCategories() {
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
    }*/

    private void getCategories() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Observable<ModelCategory[]> call = apiInterface.getCategories();

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModelCategory[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelCategory[] modelCategories) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getHome() {
        Log.e(TAG, "getHome: =================" );
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

        apiInterface.getPolitics(50,1,jsonArray)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModelNews>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelNews modelNews) {
                        ModelNews.setModelNews(modelNews);
                        setupViewPager(viewPager);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getPoliticsPaged() {
        Log.e(TAG, "getPoliticsPaged: " );
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Observable<ModelNewsPaged[]> call = apiInterface.getPolitics(10,1);

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModelNewsPaged[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelNewsPaged[] modelNewsPageds) {
                        ModelNewsPaged.setModelNews(modelNewsPageds);
                        setupViewPager(viewPager);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getPoliticsNews() {
        Log.e(TAG, "getPoliticsNews: " );
        final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        final Observable<ArrayList<ModelNewsPaged>> call = apiInterface.getPoliticsArrayListObservable(10,1);
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<ModelNewsPaged>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(ArrayList<ModelNewsPaged> modelNewsPageds) {
                        ModelNewsPaged.addModelNews(modelNewsPageds);
                        Log.e(TAG, "onNext ======== : "+ModelNewsPaged.getModelNewsPagedArrayList().size() );
                        setupViewPager(viewPager);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " );
                    }
                });
    }

    private void getPoliticsEconomyPaged() {

        Log.e(TAG, "getPoliticsEconomyPaged: " );
        final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        final Observable<ArrayList<ModelNewsPaged>> call1 = apiInterface.getPoliticsArrayListObservable(10,1);
        final Observable<ArrayList<ModelNewsPaged>> call2 = apiInterface.getEconomyArrayListObservable(10,1);


        Observable.concat(call1,call2)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<ModelNewsPaged>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(ArrayList<ModelNewsPaged> modelNewsPageds) {
                        ModelNewsPaged.addModelNews(modelNewsPageds);
                        Log.e(TAG, "onNext ======== : "+ModelNewsPaged.getModelNewsPagedArrayList().size() );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " );
                    }
                });
    }

    private void flatMapTask() {
        Log.e(TAG, "flatMapTask: ---------------------------------------------" );
        final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Observable.just(apiInterface)
                .subscribeOn(Schedulers.computation())
                .flatMap(s -> {
                    return s.getEconomyArrayListObservable(10,1)
                            .subscribeOn(Schedulers.io())
                            .map(res -> res.get(0).getPostName())
                            .flatMap( store -> s.getDetailedNews(store));
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModelNewsDetails>(){
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(ModelNewsDetails modelNewsDetails) {
                        Log.e(TAG, "onNext: "+modelNewsDetails.getPostDetails()[0].getPostContent() );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getLocalizedMessage() );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " );
                    }
                } );
    }

    private void flapMapEx() {
        Observable.just(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(Integer integer) {
                        Log.d("flatMap 1", integer + " * 2");
                        return multiplyInt(integer, 2);
                    }
                })
                .flatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(Integer integer) {
                        Log.d("flatMap 2", integer + " * 3");
                        return multiplyInt(integer, 3);
                    }
                })
                .flatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(Integer integer) {
                        Log.d("flatMap 3", integer + " * 4");
                        return multiplyInt(integer, 4);
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("flatMap", "onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d("flatMap", "onError");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("flatMap", "Final result: " + integer.toString());
                    }
                });

    }

    private Observable<Integer> multiplyInt(final Integer integer, final int mulplier) {
        //simulating a heavy duty computational expensive operation
        for (int i=0; i<1000000000; i++) {}
        return Observable.just(new Integer(integer * mulplier));
    }
}
