package com.example.silence.reviews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Silence on 03-Nov-16.
 */
public class ReviewsAdapter extends RecyclerView .Adapter<ReviewsAdapter.MyViewHolder> {
    private List<ReviewModel> moviesList;
    private static MainActivity mobject;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title_tv, comment_tv,usefulness_tv, reviewer_name_tv;
        public RatingBar ratingBar,packaging_bar,offer_bar,delivery_bar;
        public LinearLayout show_more;
        public Button show_more_button,show_less_button;

        private View v;

        public MyViewHolder(View view) {
            super(view);
            v=view;
            view.setOnClickListener(this);
            title_tv = (TextView) view.findViewById(R.id.title_tv);
            comment_tv = (TextView) view.findViewById(R.id.comment_tv);
            usefulness_tv = (TextView) view.findViewById(R.id.usefulness_tv);
            reviewer_name_tv = (TextView) view.findViewById(R.id.name_tv);
            ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);
            delivery_bar=(RatingBar) view.findViewById(R.id.delivery_time_bar);
            offer_bar = (RatingBar) view.findViewById(R.id.discounts_and_offers_bar);
            packaging_bar = (RatingBar) view.findViewById(R.id.packaging_bar);
            show_more = (LinearLayout)view.findViewById(R.id.show_rl);
            show_more_button = (Button)view.findViewById(R.id.show_more_butt);
            show_less_button = (Button)view.findViewById(R.id.show_less_butt);
            show_more_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_more.setVisibility(View.VISIBLE);
                    show_more_button.setVisibility(View.GONE);
                    show_less_button.setVisibility(View.VISIBLE);

                }
            });

            show_less_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_more.setVisibility(View.GONE);
                    show_less_button.setVisibility(View.GONE);
                    show_more_button.setVisibility(View.VISIBLE);

                }
            });


        }

        @Override
        public void onClick(View v) {
            Context context = itemView.getContext();
            ReviewModel movie= moviesList.get(getPosition());
//            Intent intent = new Intent(context, MovieDetailsActivity.class);
//            intent.putExtra("movie_id",movie.id);
//            context.startActivity(intent);

        }
    }


    public ReviewsAdapter(List<ReviewModel> reviewsList,MainActivity object) {
        this.moviesList = reviewsList;
        mobject=object;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reviews_list_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ReviewModel movie = moviesList.get(position);
        holder.title_tv.setText(movie.getTitle());
        holder.comment_tv.setText(movie.getComment());
        if(movie.reviewer_name.equals("false")) {
            holder.reviewer_name_tv.setText(" ");
        } else
        {
            holder.reviewer_name_tv.setText(movie.getReviewer_name());
        }
        holder.usefulness_tv.setText(movie.getUsefulness());
//        holder.ratingBar.setIsIndicator(true);
        holder.ratingBar.setVisibility(View.VISIBLE);
        holder.ratingBar.setNumStars(5);
        holder.ratingBar.setStepSize(1);
        holder.ratingBar.setRating(Float.parseFloat(movie.getRatings()));
        holder.delivery_bar.setVisibility(View.VISIBLE);
        holder.delivery_bar.setNumStars(5);
        holder.delivery_bar.setStepSize(1);
        holder.delivery_bar.setRating(Float.parseFloat(movie.getDelivery_time()));
        holder.offer_bar.setVisibility(View.VISIBLE);
        holder.offer_bar.setNumStars(5);
        holder.offer_bar.setStepSize(1);
        holder.offer_bar.setRating(Float.parseFloat(movie.getDiscounts_and_offers()));
        holder.packaging_bar.setVisibility(View.VISIBLE);
        holder.packaging_bar.setNumStars(5);
        holder.packaging_bar.setStepSize(1);
        holder.packaging_bar.setRating(Float.parseFloat(movie.getPackaging()));
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
