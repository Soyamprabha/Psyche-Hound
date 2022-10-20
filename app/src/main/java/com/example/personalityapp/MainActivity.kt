package com.example.personalityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.personalityapp.ui.theme.*
import com.example.personalityapp.ui.helpers.PersonalityType
import com.example.personalityapp.ui.helpers.PersonalityTypeList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonalityAppTheme()
            {
                FinalApp()
            }
        }
    }
}


@Composable
fun TitleBar(title: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .padding(
                start = 15.dp,
                end = 15.dp,
                top = 15.dp
            )
    ) {
        Text(
            text = stringResource(R.string.app_title_bar_text),
            fontFamily = fontBold,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onSurface
        )
    }
}


@Composable
fun SearchBox(
    searchText: String,
    onValueChange: (String) -> Unit,
) {

    OutlinedTextField(
        value = searchText, onValueChange = onValueChange,
        placeholder = {
            Text(
                text = "Search",
                fontFamily = fontLight,
                color = Black,
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search Icon",
                tint = Black
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, start = 15.dp, end = 15.dp)
            .background(color = Grey, shape = RoundedCornerShape(8.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.surface,
            unfocusedBorderColor = MaterialTheme.colors.onSurface,
            cursorColor = MaterialTheme.colors.secondary,
            trailingIconColor = MaterialTheme.colors.onSurface,
            textColor = Black
        )
    )
}


@Composable
fun PersonalityTypesComponent(
    searchText: String = ""
) {
    val personalityOptions = PersonalityTypeList.filter {
        it.description.lowercase().contains(searchText.lowercase())
    }


    LazyColumn(
        Modifier
            .padding(15.dp)
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(personalityOptions.size) {
            PersonalityCard(
                personalityTypes = personalityOptions[it]
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PersonalityCard(personalityTypes: PersonalityType) {
    Card(
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
    ) {
        var expanded by remember {
            mutableStateOf(false)
        }
        Card(
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxSize(),
            backgroundColor = personalityTypes.backgroundColor,
            onClick = { expanded = !expanded },
            indication = null
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = tween(400)
                    )
                    .padding(20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Card(
                        backgroundColor = personalityTypes.timeBackgroundColor
                    ) {
                        Text(
                            text = personalityTypes.keyword1,
                            fontFamily = fontMedium,
                            fontSize = 12.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Card(
                        backgroundColor = Color.White
                    ) {
                        Text(
                            text = personalityTypes.keyword2,
                            fontFamily = fontMedium,
                            fontSize = 12.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Card(
                        backgroundColor = personalityTypes.timeBackgroundColor
                    ) {
                        Text(
                            text = personalityTypes.keyword3,
                            fontFamily = fontMedium,
                            fontSize = 12.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    ExpandItemButton(
                        expanded = expanded,
                        color = personalityTypes.contentColor,
                        onClick = { expanded = !expanded }
                    )
                }

                Text(
                    text = personalityTypes.title,
                    fontFamily = fontBold,
                    fontSize = 18.sp,
                    color = personalityTypes.contentColor,
                    textAlign = TextAlign.Start
                )

                AnimatedVisibility(
                    visible = expanded,
                    enter = fadeIn(initialAlpha = 0.1f),
                    exit = fadeOut(
                        animationSpec = tween(durationMillis = 100),
                        targetAlpha = 0.3f)
                ) {
                    Text(
                        text = personalityTypes.description,
                        fontFamily = fontLight,
                        fontSize = 16.sp,
                        color = personalityTypes.contentColor,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}


@Composable
private fun ExpandItemButton(
    expanded: Boolean,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = color,
            contentDescription = ""
        )
    }
}


@Preview
@Composable
fun PersonalityCardPreview() {
    PersonalityAppTheme {
        FinalApp()
    }
}


@Preview
@Composable
fun PersonalityCardDarkPreview() {
    PersonalityAppTheme(darkTheme = true) {
        FinalApp()
    }
}

@Composable
fun FinalApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            var search by remember {
                mutableStateOf("")
            }

            TitleBar(title = "Personality App")
            SearchBox(
                searchText = search,
                onValueChange = { search = it }
            )
            PersonalityTypesComponent(
                searchText = search
            )
        }
    }
}