package com.orost.covid19.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 *
 * Provides some useful methods as extensions to [FragmentManager]
 *
 */

internal fun FragmentManager.replaceWithBackStack(
    @IdRes containerId: Int,
    fragment: Fragment,
    fragmentTag: String? = null,
    backStackName: String? = null
) {
    beginTransaction().apply {
        replace(containerId, fragment, fragmentTag)
        addToBackStack(backStackName)
        commit()
    }
}

internal fun FragmentManager.addToBackStack(
    @IdRes containerId: Int,
    fragment: Fragment,
    fragmentTag: String? = null
) {
    beginTransaction().apply {
        add(containerId, fragment, fragmentTag)
        commit()
    }
}
