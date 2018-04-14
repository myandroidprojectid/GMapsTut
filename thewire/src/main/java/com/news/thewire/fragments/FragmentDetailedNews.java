package com.news.thewire.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.news.thewire.R;
import com.news.thewire.client.ApiClient;
import com.news.thewire.client.ApiInterface;
import com.news.thewire.model.ModelNewsDetails;

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

        Call<ModelNewsDetails> call = apiInterface.getDetailedNews(url);
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
        });
    }
}