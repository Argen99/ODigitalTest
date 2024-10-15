package com.example.odigitaltest.core.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.safeNavigation(
    direction: NavDirections,
) {
    currentDestination?.getAction(direction.actionId)?.let { navigate(direction) }
}