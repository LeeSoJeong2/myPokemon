
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.kt.startkit.domain.entity.berry.BerryDetail
import com.kt.startkit.ui.features.main.berry.detail.BerryDetailScreenViewModel
import com.kt.startkit.ui.features.main.berry.detail.BerryDetailState
import com.kt.startkit.ui.res.IconResId

@Composable
fun BerryDetailScreen(
    berryName : String?,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BerryDetailScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    when (state) {
        is BerryDetailState.Initial -> {
            if (berryName != null) {
                viewModel.fetchInitialData(berryName)
            }
        }

        is BerryDetailState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is BerryDetailState.Data -> {
            BerryDetailContentView(
                berryDetail = (state as BerryDetailState.Data).berryDetail,
                onBackClick = onBackClick
            )
        }

        is BerryDetailState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material.Text(
                    (state as BerryDetailState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }


}

@Composable
private fun BerryDetailContentView(
    berryDetail: BerryDetail,
    onBackClick: () -> Unit,
) {
    Column {
        BerryDetailAppBar(
            berryName = berryDetail.name,
            onBackClick = onBackClick,
        )

        Box(
            modifier = Modifier
                .padding(
                    vertical = 18.dp,
                    horizontal = 3.dp
                )
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 32.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {

                AsyncImage(
                    model = berryDetail.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f))
                )

                Spacer(modifier = Modifier.width(32.dp))

                Text(
                    text = berryDetail.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit.Unspecified
                )

                Spacer(modifier = Modifier.width(32.dp))

                Text(
                    modifier = Modifier
                        .padding(all = 48.dp),
                    text = berryDetail.effect
                )


            }
        }
    }
}

@Composable
fun BerryDetailAppBar(
    berryName: String,
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
        Text(berryName)
    }
}