package com.androidapp.mcs.tvseriesguide.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.androidapp.mcs.tvseriesguide.model.Details;
import com.androidapp.mcs.tvseriesguide.model.Episode;
import com.androidapp.mcs.tvseriesguide.model.EpisodeResponse;
import com.androidapp.mcs.tvseriesguide.model.Rating;
import com.androidapp.mcs.tvseriesguide.model.Seasons;
import com.androidapp.mcs.tvseriesguide.model.Series;

//  This class won't be instantiated directly. Instead I'll be adding annotations to the class definition
//that Room uses to generate code in the background. I'll create an implementing class that's what will be used at runtime
//This is a Singleton class

@Database(entities = {Series.class, Seasons.class, Rating.class, EpisodeResponse.class, Episode.class, Details.class}, version = 1)
//entities -> comma delimited list of entity classes
public abstract class AppDatabase extends RoomDatabase {                                                                            //version = 1 -> First version of database structure

    public static final String DATABASE_NAME = "AppDatabase.db";                             //Database name useful when testing (Not actually required)
    private static volatile AppDatabase instance;                                            //This is a singleton of the database. Votalatile so that it can only be referenced from Main memory
    private static final Object LOCK = new Object();                                         //Object to synchronize on

    //For each Database Access object create an abstract method
    public abstract SeriesDao seriesDao();                                                   //This will return an instance of the Dao Interface

    public abstract SeasonsDao seasonsDao();

    public abstract EpisodeResponseDao episodeResponseDao();

    public abstract RatingDao ratingDao();


    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {                                                               //Synchronise on lock object so that two parts of the application dont try to create database at the same time
                if (instance == null) {                                                         //Secondary check
                    instance = Room.databaseBuilder(context.getApplicationContext()                  //Use builder object that's available in the Room class
                            , AppDatabase.class, "app-database")                               // Should always use application context
                            .allowMainThreadQueries()
                            .build();                                                                // Next argument is a class that needs to be instantiated to define database itself
                    //3rd argument -> Name of the database as it will be stored in the persistent storage
                }
            }

        }
        return instance;
    }

    public static void destroyInstance()                                                          //This method will be used to dereference the database object within the singleton
    {                                                                                            //This helps to clean up the reference and avoid leaks
        instance = null;
    }                                                                                            //Private constructor not specified because Room requires a no arg constructor to function properly
}
