package com.example.presentation.screen.concept

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.ConceptItem
import com.example.domain.model.StudioConcepts
import com.example.domain.rule.Concept
import com.example.presentation.R
import com.example.presentation.component.ConceptCard
import com.example.presentation.screen.concept.vm.HomeConceptViewModel
import com.example.presentation.screen.concept.vm.model.HomeConceptState
import com.example.presentation.theme.primary02

@Composable
fun ConceptScreen(
    onClickConcept: (Concept) -> Unit,
    viewModel: HomeConceptViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.load()
    }
    ConceptScreen(uiState){ concept ->
        onClickConcept(concept)
    }
}

@Composable
fun ConceptScreen(
    uiState: HomeConceptState,
    onSelectConcept: (Concept) -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = primary02)
            .padding(16.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(color = primary02)
        ) {
            item {
                Spacer(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .background(color = primary02)
                )
            }
            item(span = { GridItemSpan(2) }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_logo_main),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }

            uiState.homeConcept.data.forEach {
                item {
                    ConceptCard(
                        imgUrl = it.mainUrl,
                        title = it.name,
                        onClick = {
                            onSelectConcept(
                                it.id
                            )
                        },
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun ConceptScreenPreview() {
    ConceptScreen(
        HomeConceptState(
            homeConcept = StudioConcepts(
                listOf(
                    ConceptItem(Concept.Actor, "샘플1", "https://picsum.photos/200"),
                    ConceptItem(Concept.Actor, "샘플2", "https://picsum.photos/199"),
                    ConceptItem(Concept.Actor, "샘플3", "https://picsum.photos/198"),
                    ConceptItem(Concept.Actor, "샘플4", "https://picsum.photos/197"),
                    ConceptItem(Concept.Actor, "샘플5", "https://picsum.photos/196"),
                    ConceptItem(Concept.Actor, "샘플6", "https://picsum.photos/195"),
                )
            )
        )
    )
}
