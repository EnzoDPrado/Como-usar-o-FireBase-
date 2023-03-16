package com.example.testefirebase

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesteFireBaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

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
            onClick = { accountCreate(emailState, passwordState) }

        ) {
            Text(text = "Clique")

        }




    }



}


@Composable
fun DefaultPreview() {
    TesteFireBaseTheme {
        Greeting("Android")
    }
}

fun accountCreate(email: String, password:String){

    //Obter uma instancia do firebaseAuth
    val auth = FirebaseAuth.getInstance()

    auth.createUserWithEmailAndPassword(email, password).
    addOnSuccessListener {
        Log.i("DS3M", "${it.user!!.uid}")
    }

        .addOnFailureListener {
           try{
               throw it

           }catch (e: FirebaseAuthUserCollisionException){
               Log.i("DS3M", "${e.message}")
               Log.i("DS3M", e.errorCode)
           }catch (e: FirebaseAuthWeakPasswordException){
               Log.i("DS3M", "A senha esta fraca")
               Log.i("DS3M", "${e.message}")
           }
        }

}