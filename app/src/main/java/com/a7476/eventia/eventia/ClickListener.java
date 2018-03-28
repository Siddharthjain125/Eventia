package com.a7476.eventia.eventia;

import android.view.View;

/**
 * Created by HP on 3/28/2018.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
