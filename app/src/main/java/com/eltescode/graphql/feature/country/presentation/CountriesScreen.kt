package com.eltescode.graphql.feature.country.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.eltescode.graphql.feature.country.domain.model.DetailedCountry
import com.eltescode.graphql.feature.country.domain.model.SimpleCountry

@Composable
fun CountriesScreen(viewModel: CountryViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()
    if (state.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            items(state.value.countries) {
                CountryItem(country = it, modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { viewModel.getCountry(it.code) })
            }
        }
    }
    state.value.selectedCountry?.let {
        CountryDialog(it) { viewModel.dismissDialog() }
    }

}

@Composable
fun CountryItem(
    country: SimpleCountry,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = country.emoji, fontSize = 30.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1F)) {
            Text(text = country.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = country.capital.toString())
        }
    }
}

@Composable
fun CountryDialog(country: DetailedCountry, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val header = country.emoji + " " + country.name
                Text(text = header, fontSize = 30.sp, textAlign = TextAlign.Center)
                DetailedRow(
                    description = "Capital:",
                    text = country.capital.toString(),
                    modifier = Modifier.fillMaxWidth()
                )
                DetailedRow(
                    description = "Currency:",
                    text = country.currency,
                    modifier = Modifier.fillMaxWidth()
                )
                DetailedRow(
                    description = "Languages:",
                    text = country.languages.toString(),
                    modifier = Modifier.fillMaxWidth()
                )
                DetailedRow(
                    description = "Continent:",
                    text = country.continent,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun DetailedRow(description: String, text: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
      verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = description, textAlign = TextAlign.Start, modifier = Modifier.weight(1F))
        Text(text = text,textAlign = TextAlign.Center,modifier = Modifier.weight(1F))
    }
}

