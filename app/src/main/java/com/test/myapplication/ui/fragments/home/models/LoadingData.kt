package com.test.myapplication.ui.fragments.home.models

import android.view.View

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
data class LoadingData(
    val isLoading: Boolean,
    val error: Throwable?
) {
    val errorVisibility = if(error != null) View.VISIBLE else View.GONE
    val loadingVisibility = if(isLoading) View.VISIBLE else View.GONE
}