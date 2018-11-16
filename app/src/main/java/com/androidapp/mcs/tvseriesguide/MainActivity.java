package com.androidapp.mcs.tvseriesguide;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Looper;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidapp.mcs.tvseriesguide.adapter.SeasonAdapter;
import com.androidapp.mcs.tvseriesguide.adapter.SeriesAdapter;
import com.androidapp.mcs.tvseriesguide.database.AppDatabase;
import com.androidapp.mcs.tvseriesguide.event.SerieListEvent;
import com.androidapp.mcs.tvseriesguide.model.Series;
import com.androidapp.mcs.tvseriesguide.service.WebService;
import com.androidapp.mcs.tvseriesguide.utils.MyNetworkHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidapp.mcs.tvseriesguide.DetailsActivity.MY_GLOBAL_PREFS;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "main_activity";
    ProgressDialog pDialog;

    Series mSeries;
    RecyclerView recyclerView;

    private boolean networkOk;  //Internet Permission Check

    private AppDatabase db;  //Database
    public List<Series> seriesList;

    private Executor executor = Executors.newSingleThreadExecutor();    //Executor can manage a pool of threads //Make sure I'm running one database operation at a time

    //This executor object can be reused multiple times and each operation will be executed one at a time.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.main_rv);

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);

        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        networkOk = MyNetworkHelper.hasNetworkStatus(this);
        Toast.makeText(this, "Network Ok:\t" + networkOk, Toast.LENGTH_LONG).show();

        if (networkOk == true) {
            WebService webService = WebService.retrofit.create(WebService.class);
            Call<Series> call = webService.getSeries();
            getData(call);

        } else {
            Toast.makeText(this, "No network connection, fetching data from database!", Toast.LENGTH_LONG).show();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    seriesList = db.seriesDao().getAllSeries(); //Get data from database
                    EventBus.getDefault().post(new SerieListEvent(seriesList));//To dispatch an event using event bus. Pass the data to an event bus to get the data to the UI thread(Main thread)

                }
            });

            displayData((Series) seriesList);

        }

    }

    //------------------------------------------------
    private void getData(Call<Series> call) {
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(@NonNull Call<Series> call, @NonNull Response<Series> response) {

                pDialog.dismiss();
                if (response.isSuccessful()) {

                    mSeries = response.body();
                    displayData(mSeries);
                } else {
                    Toast.makeText(MainActivity.this, "Json response unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Series> call, @NonNull Throwable t) {
                Log.d("onFailure: ", t.getMessage());
            }
        });
    }
    //------------------------------------------------

    private void displayData(Series mSeries) {
        if (this.mSeries != null) {

            if (Looper.myLooper() == Looper.getMainLooper()) {         //This line checks if the current thread is Main thread and returns true if it is.
                SeriesAdapter seriesAdapter = new SeriesAdapter(this.mSeries, this);
                recyclerView.setAdapter(seriesAdapter);
            } else {
                insertDataIntoDatabase(mSeries);
                EventBus.getDefault().register(this);
            }
        }


    }

//        @Subscribe(threadMode = ThreadMode.MAIN)  //Receive data main thread
//    public void seriesItemEventHandler(SerieListEvent serieListEvent){
//            SeriesAdapter seriesAdapter = new SeriesAdapter(this.mSeries, this);
//            recyclerView.setAdapter(seriesAdapter);
//    }


    //----------------------------------------------------
    public void insertDataIntoDatabase(final Series mSeries) {
        //Initialize Database
        db = AppDatabase.getInstance(this);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                int itemCount = db.seriesDao().countItems();
                if (itemCount == 0) {
                    seriesList = mSeries.getSearch();
                    db.seriesDao().insertAllSeries(seriesList);
                    Log.i(TAG, "Data Inserted!");
                } else {
                    Log.i(TAG, "Data already exists ");
                }
            }
        });


    }


    //------------------------------------------------

    public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

        private int mItemOffset;

        public ItemOffsetDecoration(int itemOffset) {
            mItemOffset = itemOffset;
        }

        public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
            this(context.getResources().getDimensionPixelSize(itemOffsetId));
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                                   @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
        }
    }


    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

    //------------------------------------------------


//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.i(TAG, "onPause: Saving persistent state");
//        // Use only getpreferences instead of shared to save data btw activity lifecycle methods
//        //warning is displayed because I specified .edit()
//        SharedPreferences preferences = getPreferences(Activity.MODE_PRIVATE); //Get of reference of preferences
//        SharedPreferences.Editor editor = preferences.edit(); //Use the reference to edit or save values in shared preferences
//        editor.putString(SeriesAdapter.TITLEID, );
//        editor.apply();
//    }
}
