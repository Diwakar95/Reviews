package com.example.silence.reviews;

/**
 * Created by Silence on 09-Nov-16.
 */
public class Filter {

    public static int SORT = -1;


    public static void clearFilter(){
        SORT = -1;

    }

    public static boolean isFilter(){
        if(SORT == -1){
            return false;
        }
        return true;
    }

}
