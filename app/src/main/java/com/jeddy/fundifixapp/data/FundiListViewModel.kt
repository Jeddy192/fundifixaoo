import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.jeddy.fundifixapp.model.Fundi

class FundiListViewModel : ViewModel() {

    var fundiList by mutableStateOf<List<Fundi>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    private var listenerRegistration: ListenerRegistration? = null

    init {
        subscribeToFundis()
    }

    private fun subscribeToFundis() {
        isLoading = true
        listenerRegistration = FirebaseFirestore.getInstance()
            .collection("businesses")
            .addSnapshotListener { snapshots, error ->
                if (error != null) {
                    errorMessage = error.localizedMessage
                    isLoading = false
                    return@addSnapshotListener
                }

                if (snapshots != null) {
                    fundiList = snapshots.documents.mapNotNull {
                        it.toObject(Fundi::class.java)?.copy(id = it.id)
                    }
                }
                isLoading = false
            }
    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove() // Clean up the listener to avoid memory leaks
    }
}
