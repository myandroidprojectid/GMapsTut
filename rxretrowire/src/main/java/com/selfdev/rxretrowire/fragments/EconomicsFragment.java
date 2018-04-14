package com.selfdev.rxretrowire.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.selfdev.rxretrowire.R;
import com.selfdev.rxretrowire.client.ApiClient;
import com.selfdev.rxretrowire.client.ApiInterface;
import com.selfdev.rxretrowire.model.ModelNewsPaged;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Rahul on 07/04/18.
 */

public class EconomicsFragment extends Fragment {

    private static final String TAG = EconomicsFragment.class.getSimpleName();
    private RecyclerView recyclerView;

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
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.news_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        getEconomicsNews();
    }

    private void getEconomicsNews() {
        Log.e(TAG, "getPoliticsNews: " );
        final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        final Observable<ArrayList<ModelNewsPaged>> call = apiInterface.getEconomyArrayListObservable(10,1);
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<ModelNewsPaged>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(ArrayList<ModelNewsPaged> modelNewsPageds) {
                        ModelNewsPaged.addEconomicsNews(modelNewsPageds);

                        NewsRecyclerViewAdapter newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getActivity());
                        recyclerView.setItemViewCacheSize(30);
                        recyclerView.setAdapter(newsRecyclerViewAdapter);
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
}
