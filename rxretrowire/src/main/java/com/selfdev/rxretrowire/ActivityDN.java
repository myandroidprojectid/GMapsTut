package com.selfdev.rxretrowire;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.selfdev.rxretrowire.client.ApiClient;
import com.selfdev.rxretrowire.client.ApiInterface;
import com.selfdev.rxretrowire.model.ModelNewsDetails;
import com.squareup.picasso.Picasso;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ActivityDN extends AppCompatActivity {

    private static final String TAG = ActivityDN.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dn);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String url = getIntent().getStringExtra("URL");

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Observable<ModelNewsDetails> call = apiInterface.getDetailedNews(url);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModelNewsDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelNewsDetails modelNewsDetails) {
                        ModelNewsDetails.setModelNewsDetails(modelNewsDetails);
                        Log.e(TAG, "MainActivity onNext: ~~~~~~~~~~~~~~~~~~~~~~~" );
                        setImage();
                        setText();
                        WebView webView = (WebView) findViewById(R.id.detailed_news);
                        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

                        webView.setWebChromeClient(new WebChromeClient());
                        webView.setWebViewClient(new WebViewClient());
                        webView.clearCache(true);
                        webView.clearHistory();
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

                        String html = "<body><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\"/>"
                                + ModelNewsDetails.getModelNewsDetails().getPostDetails()[0].getPostContent()
                                + "</body>";
                        webView.loadData(html,"","");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        /*Call<ModelNewsDetails> call = apiInterface.getDetailedNews(url);
        call.enqueue(new Callback<ModelNewsDetails>() {
            @Override
            public void onResponse(Call<ModelNewsDetails>call, Response<ModelNewsDetails> response) {
                ModelNewsDetails modelNewsDetails = response.body();

                ModelNewsDetails.setModelNewsDetails(modelNewsDetails);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setImage();
                        setText();
                        WebView webView = (WebView) findViewById(R.id.detailed_news);
                        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


                        webView.setWebChromeClient(new WebChromeClient());
                        webView.setWebViewClient(new WebViewClient());
                        webView.clearCache(true);
                        webView.clearHistory();
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);



                        String html = "<html><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\"/>"
                                + ModelNewsDetails.getModelNewsDetails().getPostDetails()[0].getPostContent()
                                + "</html>";
                        webView.loadData(html,"","");
                    }
                });
            }

            @Override
            public void onFailure(Call<ModelNewsDetails>call, Throwable t) {
                finish();
            }
        });*/

    }

    private void setImage() {
        Picasso.with(this)
                .load(ModelNewsDetails.getModelNewsDetails().getPostDetails()[0].getFeaturedImage()[0])
                .placeholder(R.drawable.the_wire)
                .fit()
                .into((ImageView) findViewById(R.id.htab_header));
    }

    private void setText() {
        LinearLayout headlineCard = (LinearLayout) findViewById(R.id.headline_card);
        TextView time = (TextView) findViewById(R.id.time);
        TextView category = (TextView) findViewById(R.id.category);
        TextView headline = (TextView) findViewById(R.id.headline);
        TextView excerpt = (TextView) findViewById(R.id.excerpt);

        headlineCard.setVisibility(View.VISIBLE);
        time.setText(ModelNewsDetails.getModelNewsDetails().getPostDetails()[0].getDateTimeDisplay());
        category.setText(ModelNewsDetails.getModelNewsDetails().getPostDetails()[0].getPrimeCategory()[0].getName());
        headline.setText(ModelNewsDetails.getModelNewsDetails().getPostDetails()[0].getPostTitle());
        excerpt.setText(ModelNewsDetails.getModelNewsDetails().getPostDetails()[0].getPostExcerpt());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
