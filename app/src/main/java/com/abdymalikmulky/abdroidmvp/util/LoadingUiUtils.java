package com.abdymalikmulky.abdroidmvp.util;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by abdymalikmulky on 1/17/17.
 */

public class LoadingUiUtils {
    public static void hideShowLoading(ProgressBar loading,boolean show){
        if(show){

            loading.setVisibility(View.VISIBLE);
        }else {
            loading.setVisibility(View.GONE);
        }
    }
    public static void hideShowLoading(ProgressBar loading, TextView loadingText,boolean show){
        if(show){
            loadingText.setVisibility(View.VISIBLE);
            loading.setVisibility(View.VISIBLE);
        }else {
            loading.setVisibility(View.GONE);
            loadingText.setVisibility(View.GONE);
        }
    }
}
