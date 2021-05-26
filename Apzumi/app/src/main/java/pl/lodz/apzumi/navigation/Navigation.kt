package pl.lodz.apzumi.navigation

import androidx.navigation.NavDirections

interface Navigation {
    fun navigate(destinanation: NavDirections)
    fun navigateBack(): Boolean
}