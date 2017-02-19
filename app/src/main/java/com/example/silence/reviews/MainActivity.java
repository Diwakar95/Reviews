package com.example.silence.reviews;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static com.example.silence.reviews.Filter.*;

public class MainActivity extends AppCompatActivity {
    private String TAG="MainActivity";
    private List<ReviewModel> reviewsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReviewsAdapter mAdapter;
    public static TextView no_movies_tonight;
    public static LinearLayout linlaHeaderProgress,show_more_ll;
    ProgressBar progressBar;
    public static LinearLayout moviesList;
    public static RelativeLayout no_movies_rl;
    public static Button retry_movies;
    public static ImageView no_internet_img;
    private ToggleButton tg_rating,tg_uesefull,tg_connection;
    private SelectSingleToggleButtonLayout sortTypeSSTBL;
    private Button applyFilter,showMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
        mAdapter = new ReviewsAdapter(reviewsList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        fetchMoviesList();
        applyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchMoviesList();

            }
        });

    }


    public void initviews()
    {
//        showMore = (Button) findViewById(R.id.show_more_butt);
        tg_rating = (ToggleButton) findViewById(R.id.rating_tb);
        tg_connection = (ToggleButton) findViewById(R.id.conn_tb);
        tg_uesefull = (ToggleButton) findViewById(R.id.usefulnes_tb);
        sortTypeSSTBL = new SelectSingleToggleButtonLayout();
        sortTypeSSTBL.addToggleButton(tg_rating,tg_connection,tg_uesefull);
        sortTypeSSTBL.selectToggleButton(SORT);
        applyFilter = (Button) findViewById(R.id.filter_butt);
        recyclerView = (RecyclerView) findViewById(R.id.movies_rv);
        no_movies_tonight = (TextView) findViewById(R.id.txt_no_internet);
        linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
        progressBar = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#3F51B5"), PorterDuff.Mode.SRC_ATOP);
        linlaHeaderProgress.setVisibility(View.VISIBLE);
        no_internet_img = (ImageView) findViewById(R.id.no_internet_img);
        moviesList = (LinearLayout)findViewById(R.id.movie_list_ll);
        retry_movies = (Button) findViewById(R.id.retry_movies);
        no_movies_rl = (RelativeLayout) findViewById(R.id.no_movies_rl);
//        show_more_ll = (LinearLayout) findViewById(R.id.show_rl);

    }
    private void fetchMoviesList() {

        String url = URLConstant.BASE_URL + "reviews" + "/1/3";
        JsonObjectRequest req = new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                linlaHeaderProgress.setVisibility(View.GONE);
                reviewsList.clear();
                try {

                    JSONArray jArray =response.getJSONArray("reviews");

                    for (int i = 0; i < jArray.length(); i++) {

                        JSONObject jObject = jArray.getJSONObject(i);
                        ReviewModel movie = new ReviewModel(
                                jObject.getString("title"),
                                jObject.getString("comment"),
                                jObject.getString("usefulness"),
                                jObject.getJSONObject("reviewer").getString("name"),
                                jObject.getJSONObject("ratings").getString("Overall"),
                                jObject.getJSONObject("ratings").getString("delivery_time"),
                                jObject.getJSONObject("ratings").getString("discounts_and_offers"),
                                jObject.getJSONObject("ratings").getString("packaging"),
                                jObject.getJSONObject("reviewer").getString("connection_level")
                        );
                        Log.e("Title" ,jObject.getJSONObject("ratings").getString("Overall"));
                        reviewsList.add(movie);
                        moviesList.setVisibility(View.VISIBLE);
                        mAdapter.notifyDataSetChanged();
                    }
                    SORT =getIndex(sortTypeSSTBL.getSelectedToggleButton(), getResources().getStringArray(R.array.sort));
                    Log.e("Sort_inex=", String.valueOf(SORT));
                    if(isFilter()) {
                        if (SORT == 0) {
                            Collections.sort(reviewsList, new Comparator<ReviewModel>() {
                                @Override
                                public int compare(ReviewModel o1, ReviewModel o2) {
                                    return Integer.valueOf(o2.getRatings()).compareTo(Integer.valueOf(o1.getRatings()));
                                }
                            });
                        }
                        else if (SORT == 1) {
                            Collections.sort(reviewsList, new Comparator<ReviewModel>() {
                                @Override
                                public int compare(ReviewModel o1, ReviewModel o2) {
                                    return Integer.valueOf(o2.getConnection_level()).compareTo(Integer.valueOf(o1.getConnection_level()));
                                }
                            });
                        }else if (SORT == 2) {
                            Collections.sort(reviewsList, new Comparator<ReviewModel>() {
                                @Override
                                public int compare(ReviewModel o1, ReviewModel o2) {
                                    return Integer.valueOf(o2.getUsefulness()).compareTo(Integer.valueOf(o1.getUsefulness()));
                                }
                            });
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(req);
    }

    private Integer getIndex(ToggleButton tb, String[] array) {
        if (tb != null)
            for (int i = 0; i < array.length; i++) {
                if (tb.getText().equals(array[i])) {
                    return i;
                }
            }
        return -1;
    }

}

