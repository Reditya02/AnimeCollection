package com.example.animecollection.data.remote.firebase

import com.example.animecollection.core.UIState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class RemoteFirebaseDatasources {
    fun user() = Firebase.auth.currentUser
    fun getUid(): String = Firebase.auth.currentUser?.uid ?: ""
    fun logout() = Firebase.auth.signOut()

    fun login(email: String, password: String) = flow {
        emit(UIState.Loading())
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
        emit(UIState.Success(true))
    }.catch {
        emit(UIState.Error(it.message))
    }.flowOn(Dispatchers.IO)

    fun register(username: String, email: String, password: String) = flow {
        emit(UIState.Loading())
        Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        user()?.apply {
            setUserData(username, email, uid)
        }
        emit(UIState.Success(true))
    }.catch {
        emit(UIState.Error(it.message))
    }.flowOn(Dispatchers.IO)

    fun setUserData(username: String, email: String, id: String) {
        FirebaseFirestore
            .getInstance()
            .collection("user_data")
            .document(id)
            .apply {
                set(hashMapOf(
                    "username" to username,
                    "email" to email
                ))
            }
    }

}