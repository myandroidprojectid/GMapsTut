package com.selfdev.rxretrowire.fragments;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfdev.rxretrowire.R;
import com.selfdev.rxretrowire.client.ApiClient;
import com.selfdev.rxretrowire.client.ApiInterface;
import com.selfdev.rxretrowire.model.ModelNewsPaged;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Rahul on 07/04/18.
 */

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {
    private static final String TAG = NewsRecyclerViewAdapter.class.getSimpleName();
    private Activity activity;
    private OnNewsItemClick onNewsItemClick;
    private static int pageCounter = 1;
    private static final int LOADER = 1;
    private static final int NEWS = 2;

    public NewsRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
        this.onNewsItemClick = (OnNewsItemClick) activity;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==NEWS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_view, parent, false);
            return new NewsViewHolder(view,viewType);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_loader, parent, false);
            return new NewsViewHolder(view,viewType);
        }
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        if (holder.viewType == NEWS) {
            holder.news_date.setText(ModelNewsPaged.getModelNewsPagedArrayList().get(position).getPostDate());
            holder.news_headline.setText(ModelNewsPaged.getModelNewsPagedArrayList().get(position).getPostTitle());
            holder.news_by.setText("News By: "+ModelNewsPaged.getModelNewsPagedArrayList().get(position).getPostAuthorName()[0].getAuthorName());
            downloadImage(ModelNewsPaged.getModelNewsPagedArrayList().get(position).getFeaturedImage()[0],holder.news_image);
        } else {
            pageCounter++;
            getPoliticsNews();
        }
    }

    @Override
    public int getItemCount() {
        return ModelNewsPaged.getModelNewsPagedArrayList().size()+LOADER;
    }

    @Override
    public int getItemViewType(int position) {
        if (position>=ModelNewsPaged.getModelNewsPagedArrayList().size()) {
            return LOADER;
        } else {
            return NEWS;
        }
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int viewType;
        private ImageView news_image;
        private TextView  news_date;
        private TextView  news_headline;
        private TextView  news_by;

        public NewsViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            if (viewType==NEWS) {
                itemView.setOnClickListener(this);
                news_image = (ImageView) itemView.findViewById(R.id.news_image);
                news_date = (TextView) itemView.findViewById(R.id.news_date);
                news_headline = (TextView) itemView.findViewById(R.id.news_headline);
                news_by = (TextView) itemView.findViewById(R.id.news_by);
            } else {

            }
        }

        @Override
        public void onClick(View view) {
            onNewsItemClick.onNewsItemClicked(ModelNewsPaged.getModelNewsPagedArrayList().get(getAdapterPosition()).getPostName());
        }
    }

    private void downloadImage(String url, final ImageView imageView) {
        Picasso.with(activity)
                .load(url)
                .placeholder(R.drawable.the_wire)
                .fit()
                .into(imageView);
    }

    private void getPoliticsNews() {
        Log.e(TAG, "getPoliticsEconomyPaged: " );
        final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        final Observable<ArrayList<ModelNewsPaged>> call = apiInterface.getPoliticsArrayListObservable(10,pageCounter);
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
                        notifyDataSetChanged();
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
