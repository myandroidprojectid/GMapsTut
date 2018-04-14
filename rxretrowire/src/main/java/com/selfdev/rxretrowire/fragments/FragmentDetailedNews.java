package com.selfdev.rxretrowire.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.selfdev.rxretrowire.R;
import com.selfdev.rxretrowire.client.ApiClient;
import com.selfdev.rxretrowire.client.ApiInterface;
import com.selfdev.rxretrowire.model.ModelNewsDetails;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rahul on 07/04/18.
 */

public class FragmentDetailedNews extends Fragment {

    private static final String TAG = FragmentDetailedNews.class.getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailed_news,container,false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        String url = bundle.getString("URL");
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
                        WebView webView = (WebView) view.findViewById(R.id.detailed_news);
                        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                        webView.loadData(ModelNewsDetails.getModelNewsDetails().getPostDetails()[0].getPostContent(),"","");
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        WebView webView = (WebView) view.findViewById(R.id.detailed_news);
                        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                        webView.loadData(ModelNewsDetails.getModelNewsDetails().getPostDetails()[0].getPostContent(),"","");
                    }
                });
            }

            @Override
            public void onFailure(Call<ModelNewsDetails>call, Throwable t) {
            }
        });*/
    }
}