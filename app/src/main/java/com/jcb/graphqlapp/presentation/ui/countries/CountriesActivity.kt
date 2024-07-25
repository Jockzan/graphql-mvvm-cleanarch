package com.jcb.graphqlapp.presentation.ui.countries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jcb.graphqlapp.presentation.theme.GraphQLAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            GraphQLAppTheme {
                CountriesScreen()
            }
        }
    }
}
