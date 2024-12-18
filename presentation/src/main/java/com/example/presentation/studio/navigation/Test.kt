package com.example.presentation.studio.navigation


//fun NavHostController.getId(): String =
//    this.currentBackStackEntry?.savedStateHandle?.get<String>(ID) ?: DEFAULT_STRING
//
//fun NavHostController.getPassword(): String =
//    this.currentBackStackEntry?.savedStateHandle?.get<String>(PASSWORD) ?: DEFAULT_STRING
//
//fun NavHostController.setIdPassword(
//    id: String,
//    password: String
//){
//    this.previousBackStackEntry?.savedStateHandle?.apply {
//        set(ID, id)
//        set(PASSWORD, password)
//    }
//}

//
//composable<Route.SignUp> {
//    SignUpScreen(
//        navigationToSignIn = { id, password ->
//            with(navController) {
//                setIdPassword(id, password)
//                navigateUp()
//            }
//        },
//        onCloseButtonClick = {
//            navController.navigateUp()
//        },
//        modifier = topBarModifier
//    )
//}