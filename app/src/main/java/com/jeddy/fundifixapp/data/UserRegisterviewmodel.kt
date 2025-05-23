package com.yourpackage.fundifix.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jeddy.fundifixapp.model.User
import kotlin.jvm.java

class UserRegisterviewmodel: ViewModel() {

    var userData by mutableStateOf<User?>(null)
        private set

    fun fetchUser() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        FirebaseFirestore.getInstance().collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                userData = document.toObject(User::class.java)
            }
    }

    fun saveUser(user: User) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        FirebaseFirestore.getInstance().collection("users").document(userId)
            .set(user.copy(id = userId))
    }
}
