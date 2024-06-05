package kr.co.onboard

import androidx.compose.runtime.Composable

@Composable
internal fun OnBoardRoute(
    onKakaoClick: () -> Unit,
    onNaverClick: () -> Unit,
    onGoogleClick: () -> Unit,
) {
   OnBoardScreen(
       onKakaoClick = onKakaoClick,
       onNaverClick = onNaverClick,
       onGoogleClick = onGoogleClick
   )
}

@Composable
private fun OnBoardScreen(
    onKakaoClick: () -> Unit,
    onNaverClick: () -> Unit,
    onGoogleClick: () -> Unit
) {


}
