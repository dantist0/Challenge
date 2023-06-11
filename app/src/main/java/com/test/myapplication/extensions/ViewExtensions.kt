package com.test.myapplication.extensions

import android.view.LayoutInflater
import android.view.View

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */

/**
 * Get inflater from view
 */
val View.inflater get() = LayoutInflater.from(context)