import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapiconsume.android.core.error.Failure
import com.example.composeapiconsume.android.features.auth.data.datasources.AuthRemoteDataSource
import com.example.composeapiconsume.android.features.auth.data.models.LoginResponse
import com.example.composeapiconsume.android.utils.helper.Either
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : ViewModel() {

    private val _loginResult = MutableStateFlow<Either<Failure, LoginResponse>?>(null)
    val loginResult: StateFlow<Either<Failure, LoginResponse>?> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val params = mapOf(
                "username" to username,
                "password" to password
            )

            _loginResult.value = authRemoteDataSource.login(params)
        }
    }
}
