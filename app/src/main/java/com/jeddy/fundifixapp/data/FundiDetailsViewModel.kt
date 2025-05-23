import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.jeddy.fundifixapp.model.Fundi

class FundiDetailsViewModel : ViewModel() {

    var selectedFundi by mutableStateOf<Fundi?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadFundiById(fundiId: String) {
        isLoading = true
        errorMessage = null

        // Use the correct collection: "businesses"
        FirebaseFirestore.getInstance().collection("businesses")
            .document(fundiId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    selectedFundi = document.toObject(Fundi::class.java)?.copy(id = document.id)
                    errorMessage = null
                } else {
                    errorMessage = "Fundi not found."
                }
                isLoading = false
            }
            .addOnFailureListener { exception ->
                errorMessage = exception.localizedMessage ?: "Error loading fundi."
                isLoading = false
            }
    }
}
