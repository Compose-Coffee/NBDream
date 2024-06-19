package kr.co.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.co.main.MainBottomRoute
import kr.co.main.MainRoute
import kr.co.main.accountbook.content.AccountBookContentScreen
import kr.co.main.accountbook.main.AccountBookRoute
import kr.co.main.accountbook.register.AccountBookRegister
import kr.co.main.community.BulletinDetailScreen
import kr.co.main.community.BulletinWritingRoute
import kr.co.main.community.CommunityRoute
import kr.co.main.home.HomeRoute
import kr.co.main.home.chat.ChatRoute
import kr.co.main.my.MyPageRoute
import kr.co.main.my.profile.MyPageProfileEditRoute
import kr.co.main.my.setting.MyPageSettingRoute
import kr.co.main.my.setting.delete.MyPageSettingDeleteAccountRoute
import timber.log.Timber

const val MAIN_ROUTE = "mainRoute"
internal const val CHAT_ROUTE = "chatRoute"
internal const val NOTIFICATION_ROUTE = "notificationRoute"
internal const val ACCOUNT_BOOK_ROUTE = "accountBookRoute"
internal const val ACCOUNT_BOOK_CONTENT_ROUTE = "accountBookContentRoute"

internal const val MY_PAGE_EDIT_ROUTE = "myPageProfileEditRoute"
internal const val MY_PAGE_SETTING_ROUTE = "myPageSettingRoute"
internal const val MY_PAGE_SETTING_NOTIFICATION_ROUTE = "myPageSettingNotificationRoute"
internal const val MY_PAGE_SETTING_PRIVACY_POLICY_ROUTE = "myPageSettingPrivacyPolicyRoute"
internal const val MY_PAGE_SETTING_LOGOUT_ROUTE = "myPageSettingLogoutRoute"
internal const val MY_PAGE_SETTING_APP_INFO_ROUTE = "myPageSettingAppInfoRoute"
internal const val MY_PAGE_SETTING_DELETE_ACCOUNT_ROUTE = "myPageSettingDeleteAccountRoute"

internal const val COMMUNITY_WRITING_ROUTE = "community_writing_route"
internal const val COMMUNITY_BULLETIN_DETAIL_ROUTE = "community_bulletin_detail_route"

fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {
    composable(route = MAIN_ROUTE) {
        MainRoute(
            mainBuilder = {
                composable(
                    route = MainBottomRoute.HOME.route
                ) {
                    HomeRoute(
                        navigateToChat = {
                            navController.navigate(CHAT_ROUTE)
                        },
                        navigateToNotification = {
                            navController.navigate(NOTIFICATION_ROUTE)
                        }
                    )
                }

                composable(
                    route = MainBottomRoute.CALENDAR.route
                ) {

                }

                composable(
                    route = MainBottomRoute.ACCOUNT.route
                ) {
                    AccountBookRoute(
                        navigationToRegister = {
                            navController.navigate(ACCOUNT_BOOK_ROUTE)
                        },
                        navigationToContent = { id ->
                            navController.navigate("$ACCOUNT_BOOK_CONTENT_ROUTE/$id")
                        }
                    )
                }

                composable(
                    route = MainBottomRoute.COMMUNITY.route
                ) {
                    CommunityRoute(
                        navigateToWriting = { navController.navigate(COMMUNITY_WRITING_ROUTE) },
                        navigateToNotification = {},
                        navigateToBulletinDetail = {
                            navController.navigate(COMMUNITY_BULLETIN_DETAIL_ROUTE)
                        },
                    )
                }

                composable(
                    route = MainBottomRoute.MY_PAGE.route
                ) {
                    MyPageRoute(
                        navigateToProfileEdit = {
                            navController.navigate(MY_PAGE_EDIT_ROUTE)
                        }
                    )
                }
            }
        )
    }

    composable(
        route = CHAT_ROUTE
    ) {
        ChatRoute(
            popBackStack = navController::popBackStack
        )
    }

    composable(
        route = NOTIFICATION_ROUTE
    ) {

    }

    composable(
        route = ACCOUNT_BOOK_ROUTE
    ) {
        AccountBookRegister(
            popBackStack = navController::popBackStack
        )
    }

    composable(
        route = "$ACCOUNT_BOOK_CONTENT_ROUTE/{id}"
    ) { backStackEntry ->
        val idString = backStackEntry.arguments?.getString("id")
        val id = idString?.toLongOrNull()

        if (id != null) {
            AccountBookContentScreen(
                popBackStack = navController::popBackStack,
                id = id
            )
        } else {
            Timber.e("Invalid id")
        }
    }

    composable(
        route = MY_PAGE_EDIT_ROUTE
    ) {
        MyPageProfileEditRoute(
            popBackStack = navController::popBackStack
        )
    }

    composable(
        route = MY_PAGE_SETTING_ROUTE
    ) {
        MyPageSettingRoute(
            popBackStack = navController::popBackStack,
            navigateTo = { route ->
                navController.navigate(route)
            }
        )
    }

    composable(
        route = MY_PAGE_SETTING_NOTIFICATION_ROUTE
    ) {

    }

    composable(
        route = MY_PAGE_SETTING_PRIVACY_POLICY_ROUTE
    ) {

    }

    composable(
        route = MY_PAGE_SETTING_LOGOUT_ROUTE
    ) {

    }

    composable(
        route = MY_PAGE_SETTING_APP_INFO_ROUTE
    ) {

    }

    composable(
        route = MY_PAGE_SETTING_DELETE_ACCOUNT_ROUTE
    ) {
        MyPageSettingDeleteAccountRoute(
            popBackStack = navController::popBackStack
        )
    }

    composable(
        route = COMMUNITY_WRITING_ROUTE
    ) {
        BulletinWritingRoute(popBackStack = navController::popBackStack)
    }

    composable(
        route = COMMUNITY_BULLETIN_DETAIL_ROUTE
    ) {
        BulletinDetailScreen(popBackStack = navController::popBackStack)
    }

}
