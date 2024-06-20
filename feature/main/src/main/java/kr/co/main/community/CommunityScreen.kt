package kr.co.main.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.co.domain.entity.BulletinEntity

@Composable
internal fun CommunityRoute(
    navigateToWriting: () -> Unit,
    navigateToNotification: () -> Unit,
    navigateToBulletinDetail: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CommunityViewModel = hiltViewModel(),
) {
    val bulletinEntities by viewModel.bulletinEntities.collectAsStateWithLifecycle()
    val searchInput by viewModel.searchInput.collectAsStateWithLifecycle()
    CommunityScreen(
        modifier = modifier,
        navigateToWriting = navigateToWriting,
        navigateToNotification = navigateToNotification,
        navigateToBulletinDetail = navigateToBulletinDetail,
        bulletinEntities = bulletinEntities,
        searchInput = searchInput,
        onSearchInputChanged = viewModel::onSearchInputChanged,
        onFreeCategoryClick = viewModel::onFreeCategoryClick,
        onBulletinClick = viewModel::onBulletinClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CommunityScreen(
    modifier: Modifier = Modifier,
    navigateToWriting: () -> Unit = {},
    navigateToNotification: () -> Unit = {},
    navigateToBulletinDetail: () -> Unit = {},
    bulletinEntities: List<BulletinEntity> = emptyList(),
    searchInput: String = "",
    onSearchInputChanged: (String) -> Unit = {},
    onFreeCategoryClick: () -> Unit = {},
    onBulletinClick: (Long) -> Unit = {},
) {
//    var tempTextFieldValue by remember {
//        mutableStateOf(TextFieldValue())
//    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToWriting) {
                Icon(
                    imageVector = Icons.Filled.Create,
                    contentDescription = "Writing floating action button",
                )
            }
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                CenterAlignedTopAppBar(title = { Text("감자 게시판") })
                // 나중에 알림버튼 추가
            }
            item {
                Card(
                    modifier = Modifier
                        .height(40.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = CardColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.Black,
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        TextButton(onClick = onFreeCategoryClick) {
                            Text("자유 주제")
                        }
                        // TODO: 디바이더에도 여백이 붙는데...
                        VerticalDivider(
                            thickness = 1.dp,
                            color = Color.Red,
                        )
                        Text("질문")
                        VerticalDivider(
                            thickness = 1.dp,
                            color = Color.Red,
                        )
                        Text("병해충")
                    }
                }
            }
            item {
                TextField(
                    value = searchInput,
                    onValueChange = {
                        if ("\n" !in it) onSearchInputChanged(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("검색어를 입력하세요") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = kr.co.nbdream.core.ui.R.drawable.baseline_search_24),
                            contentDescription = "serach icon",
                        )
                    },
                )
            }
            if (bulletinEntities.isEmpty()) {
                item {
                    Text("게시물이 없습니다.")
                }
            }
            itemsIndexed(
                bulletinEntities
            ) { idx, bulletin ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            onBulletinClick(bulletin.bulletinId)
                            navigateToBulletinDetail()
                        },
                    colors = CardDefaults.cardColors(),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 4.dp,
                    ),  // ?
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = kr.co.nbdream.core.ui.R.drawable.ic_person_32),
                            contentDescription = "프로필 사진",
                            modifier = Modifier
                                .background(
                                    color = Color.Gray,
                                    shape = CircleShape,
                                )
                                .padding(4.dp),
                        )
                        Column {
                            Text("${bulletin.authorId}의닉네임")
                            Text(bulletin.createdTime)
                        }
                        Icon(
                            painter = painterResource(id = kr.co.nbdream.core.ui.R.drawable.baseline_bookmark_24),
                            contentDescription = "북마크 채워진 아이콘",
                        )
                        Icon(
                            painter = painterResource(id = kr.co.nbdream.core.ui.R.drawable.baseline_bookmark_border_24),
                            contentDescription = "북마크 빈 아이콘",
                        )
                        Text("${bulletin.bookmarkedCount}")
                    }
                    Text(
                        bulletin.content,
                        modifier = Modifier.height(80.dp),
                    )
                    Text(
                        "사진들 $idx",
                        modifier = Modifier.height(240.dp),
                    )
                    Row {
                        Icon(
                            painter = painterResource(id = kr.co.nbdream.core.ui.R.drawable.baseline_comment_24),
                            contentDescription = "댓글 아이콘",
                        )
                        Text("${bulletin.comments.size}")
                    }
                    if (bulletin.comments.isNotEmpty()) {
                        // 여기에 넣어야하는데 일단 표시용으로 밖에.
                    }
                    Row {
                        Image(
                            painter = painterResource(id = kr.co.nbdream.core.ui.R.drawable.ic_person_32),
                            contentDescription = "댓글 프사",
                            modifier = Modifier
                                .background(
                                    color = Color.Gray,
                                    shape = CircleShape,
                                )
                                .padding(4.dp),
                        )
                        Text("댓글닉네임$idx")
                    }
                    Text("댓글 내용 $idx")
                }
            }
        }
    }
}

@Preview(heightDp = 1200)
@Composable
private fun CommunityScreenPreview() {
    MaterialTheme {
        Surface {
            CommunityScreen(
                bulletinEntities = List(10) { i -> BulletinEntity.dummy(i) },
            )
        }
    }
}
