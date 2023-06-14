
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kt.startkit.ui.res.IconResId

@Composable
fun BerryDetailScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
//    viewModel: BerryDetailScreenViewModel = hiltViewModel(),
) {
    Column {
        BerryDetailAppBar(onBackClick = onBackClick)
//        BerryDetailContentView()
    }
}

@Composable
fun BerryDetailAppBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
    ) {
        IconButton(
            onClick = { onBackClick() }
        ) {
            Icon(
                imageVector = IconResId.ArrowBack,
                contentDescription = null,
            )
        }
        Text("berry datail")

    }
}

@Composable
private fun BerryDetailContentView() {

}