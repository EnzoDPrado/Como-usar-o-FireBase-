package com.example.testefirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testefirebase.ui.theme.TesteFireBaseTheme
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        Log.i("DS3M", "${auth.currentUser!!.uid}" )

        if(auth.currentUser != null){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        setContent {
            TesteFireBaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Login("Android")
                }
            }
        }
    }
}

@Composable
fun Login(name: String) {

    var emailState by remember{
        mutableStateOf("")
    }

    var passwordState by remember{
        mutableStateOf("")
    }

    Column() {

        OutlinedTextField(
            value = emailState,
            onValueChange = {emailState = it},
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "insira")
            }
        )

        OutlinedTextField(
            value = passwordState,
            onValueChange = {passwordState = it},
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "insira")
            }
        )

        Button(
            onClick = {
                authenticate(emailState, passwordState, context)
            }

        ) {
            Text(text = "Entrar")

        }




    }



}

fun authenticate(email: String, password: String, context: Context) {
    //Obter a instancia do firebaseAuth
    val auth = FirebaseAuth.getInstance()

    //Autenticação
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            Log.i("DS3M", "${it.isSuccessful}")
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    TesteFireBaseTheme {
        Login("Android")
    }
}