package pl.lodz.apzumi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.lodz.apzumi.navigation.getNavigator

class ProjectActivity : AppCompatActivity() {

    private val navigation by lazy {
        this.getNavigator()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean = navigation.navigateBack()
}