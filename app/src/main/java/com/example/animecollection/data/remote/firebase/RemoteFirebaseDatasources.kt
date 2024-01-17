package com.example.animecollection.data.remote.firebase

import com.example.animecollection.core.UIState
import com.google.firebase.auth.ktx.auth
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
        emit(UIState.Success(true))
    }.catch {
        emit(UIState.Error(it.message))
    }.flowOn(Dispatchers.IO)

}