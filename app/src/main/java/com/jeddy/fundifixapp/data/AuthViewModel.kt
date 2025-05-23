import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jeddy.fundifixapp.model.User
import com.jeddy.fundifixapp.navigation.ROUTE_FINDFUNDI
import com.jeddy.fundifixapp.navigation.ROUTE_LOGIN
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel : ViewModel() {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage
    private val firestore = FirebaseFirestore.getInstance()

    fun signup(
        name: String, phone: String, email: String, password: String,
        navController: NavController, context: Context
    ) {
        if (name.isBlank() || phone.isBlank() || email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_LONG).show()
            return
        }

        _isLoading.value = true

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    val userId = mAuth.currentUser?.uid ?: return@addOnCompleteListener
                    val userData = User(
                        id = userId,
                        name = name,
                        phone = phone,
                        email = email
                    )
                    saveUserToFirestore(userData, navController, context)
                } else {
                    _errorMessage.value = task.exception?.message
                    Toast.makeText(context, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun saveUserToFirestore(user: User, navController: NavController, context: Context) {
        firestore.collection("users")
            .document(user.id)
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(context, "User Successfully Registered", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN)
            }
            .addOnFailureListener {
                _errorMessage.value = it.message
                Toast.makeText(context, "Firestore error: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }

    fun login(email: String, password: String, navController: NavController, context: Context) {
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Email and password required", Toast.LENGTH_LONG).show()
            return
        }

        _isLoading.value = true

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    val uid = mAuth.currentUser?.uid
                    if (uid != null) {
                        firestore.collection("fundis").document(uid).get()
                            .addOnSuccessListener { document ->
                                if (document != null && document.exists()) {
                                    val name = document.getString("name") ?: "Fundi"
                                    Toast.makeText(context, "Welcome back, $name!", Toast.LENGTH_LONG).show()

                                    // Navigate to personalized home
                                    navController.navigate("fundi_home/$uid/$name") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                } else {
                                    Toast.makeText(context, "Fundi profile not found", Toast.LENGTH_LONG).show()
                                }
                            }
                            .addOnFailureListener {
                                Toast.makeText(context, "Error fetching profile: ${it.message}", Toast.LENGTH_LONG).show()
                            }
                    }
                } else {
                    _errorMessage.value = task.exception?.message
                    Toast.makeText(context, "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

}
