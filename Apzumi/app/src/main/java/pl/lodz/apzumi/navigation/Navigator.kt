package pl.lodz.apzumi.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

class Navigator(private val navController: NavController): Navigation {

    override fun navigate(destinanation: NavDirections) {
        navController.navigate(destinanation)
    }

    override fun navigateBack(): Boolean = navController.popBackStack()
}