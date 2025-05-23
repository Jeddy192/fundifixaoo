package com.jeddy.fundifixapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jeddy.fundifixapp.model.Fundi

class FundiRegistrationViewModel : ViewModel() {

    var isLoading = mutableStateOf(false)
        private set

    var registrationSuccess = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    var registeredFundi = mutableStateOf<Fundi?>(null)
        private set

    fun registerFundi(fundi: Fundi) {
        if (
            fundi.name.isBlank() ||
            fundi.email.isBlank() ||
            fundi.phone.isBlank() ||
            fundi.password.isBlank() ||
            fundi.location.isBlank() ||
            fundi.service.isBlank()
        ) {
            errorMessage.value = "All fields are required"
            return
        }

        isLoading.value = true

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(fundi.email, fundi.password)
            .addOnSuccessListener { authResult ->
                val uid = authResult.user?.uid ?: ""
                val db = FirebaseFirestore.getInstance()
                val doc = db.collection("businesses").document(uid)
                val finalFundi = fundi.copy(id = uid)

                doc.set(finalFundi)
                    .addOnSuccessListener {
                        registeredFundi.value = finalFundi
                        registrationSuccess.value = true
                        isLoading.value = false
                    }
                    .addOnFailureListener { e ->
                        errorMessage.value = e.localizedMessage
                        isLoading.value = false
                    }
            }
            .addOnFailureListener { e ->
                errorMessage.value = e.localizedMessage
                isLoading.value = false
            }
    }
}
