package mindtech.adam.beers.Modules.Details.Views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mindtech.adam.beers.Data.Models.Beer
import mindtech.adam.beers.Modules.MainViewModel

@Composable
fun DetailInfoView(info: String, divider: Boolean = true) {

    Text(
        modifier = Modifier
            .padding(top = 24.dp, bottom = 12.dp),
        text = info
    )

    if (divider) {
        Divider(modifier = Modifier
            .padding(end = 12.dp))
    }

}