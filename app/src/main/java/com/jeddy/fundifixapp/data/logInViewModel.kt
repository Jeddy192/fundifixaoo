package com.jeddy.fundifixapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class logInViewModel : ViewModel() {

    var isLoading = mutableStateOf(false)
    var loginError = mutableStateOf<String?>(null)
    var loggedInAsFundi = mutableStateOf(false)
    var loggedInAsUser = mutableStateOf(false)
    var loggedInId = mutableStateOf<String?>(null)

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun login(email: String, password: String) {
        isLoading.value = true
        loginError.value = null

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid

                if (uid != null) {
                    // Check if it's a fundi
                    db.collection("businesses").document(uid).get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                loggedInAsFundi.value = true
                                loggedInId.value = uid
                            } else {
                                // Not fundi? Check if user
                                db.collection("users").document(uid).get()
                                    .addOnSuccessListener { userDoc ->
                                        if (userDoc.exists()) {
                                            loggedInAsUser.value = true
                                            loggedInId.value = uid
                                        } else {
                                            loginError.value = "Account not found"
                                        }
                                    }
                                    .addOnFailureListener {
                                        loginError.value = it.localizedMessage
                                    }
                            }
                            isLoading.value = false
                        }
                        .addOnFailureListener {
                            loginError.value = it.localizedMessage
                            isLoading.value = false
                        }
                } else {
                    loginError.value = "User ID not found"
                    isLoading.value = false
                }
            }
            .addOnFailureListener { e ->
                loginError.value = e.localizedMessage
                isLoading.value = false
            }
    }
}
