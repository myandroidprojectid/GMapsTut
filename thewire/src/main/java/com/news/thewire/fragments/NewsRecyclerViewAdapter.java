package com.news.thewire.fragments;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.thewire.R;
import com.news.thewire.model.ModelNewsPaged;
import com.squareup.picasso.Picasso;

/**
 * Created by Rahul on 07/04/18.
 */

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {
    private Activity activity;
    private ModelNewsPaged[] modelNews;
    private OnNewsItemClick onNewsItemClick;

    public NewsRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
        this.modelNews = ModelNewsPaged.getModelNews();
        this.onNewsItemClick = (OnNewsItemClick) activity;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_view, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.news_date.setText(modelNews[position].getPostDate());
        holder.news_headline.setText(modelNews[position].getPostTitle());
        holder.news_by.setText("News By: "+modelNews[position].getPostAuthorName()[0].getAuthorName());
        downloadImage(modelNews[position].getFeaturedImage()[0],holder.news_image);
    }

    @Override
    public int getItemCount() {
        return modelNews.length;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView news_image;
        private TextView  news_date;
        private TextView  news_headline;
        private TextView  news_by;

        public NewsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            news_image = (ImageView) itemView.findViewById(R.id.news_image);
            news_date = (TextView) itemView.findViewById(R.id.news_date);
            news_headline = (TextView) itemView.findViewById(R.id.news_headline);
            news_by = (TextView) itemView.findViewById(R.id.news_by);
        }

        @Override
        public void onClick(View view) {
            onNewsItemClick.onNewsItemClicked(modelNews[getAdapterPosition()].getPostName());
        }
    }

    private void downloadImage(String url, final ImageView imageView) {
        /*Log.e("XXXX", "downloadImage: downloadImage");
        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("XXXX", "onFailure: IMAGE");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("XXXX", "onResponse: ===========" );
                InputStream inputStream = response.body().byteStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
                Log.e("XXXX", "downloadImage: downloadImage 222222");
            }
        });*/
        Picasso.with(activity)
                .load(url)
                .placeholder(R.drawable.the_wire)
                .fit()
                .into(imageView);
    }
}
