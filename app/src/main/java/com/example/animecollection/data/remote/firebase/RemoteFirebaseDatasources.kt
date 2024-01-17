package com.example.animecollection.data.remote.firebase

import android.util.Log
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.domain.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await

class RemoteFirebaseDatasources {
    fun user() = Firebase.auth.currentUser
    fun getUid(): String = Firebase.auth.currentUser?.uid ?: ""
    fun logout() = Firebase.auth.signOut()

    fun getUserDataInstance() =
        FirebaseFirestore
            .getInstance()
            .collection("user_data")

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

    fun getUserData() = callbackFlow {
        trySend(UIState.Loading())

        val listener =
            EventListener<DocumentSnapshot> { value, error ->
                if (value != null && value.exists()) {
                    val result = value.toObject(User::class.java)
                    trySend(UIState.Success(result))
                } else {
                    trySend(UIState.Error(error?.message ?: "error"))
                }
            }

        val firebase = getUserDataInstance()
            .document(getUid())
            .addSnapshotListener(listener)

        awaitClose { firebase.remove() }
    }

    private fun setUserData(username: String, email: String, id: String) {
        FirebaseFirestore
            .getInstance()
            .collection("user_data")
            .document(id)
            .apply {
                set(hashMapOf(
                    "username" to username,
                    "email" to email,
                    "photo" to "profile_image/profile_image_placeholder.jpg"
                ))
                    .addOnSuccessListener {
                        Log.d("Firestore", "User data successfully set for ID: $id")
                    }
                    .addOnFailureListener { e ->
                        Log.d("Firestore", "Error setting user data for ID: $id $e")
                    }
            }
    }

    fun getALlFavorite() = callbackFlow {
        trySend(UIState.Loading())

        val listener = EventListener<QuerySnapshot> { value, error ->
            if (value != null) {
                val result = value.toObjects(Anime::class.java)
                trySend(UIState.Success(result))
            } else {
                trySend(UIState.Error(error?.message ?: "error"))
            }
        }

        val firebase = getUserDataInstance()
            .document(getUid())
            .collection("favorite")
            .addSnapshotListener(listener)

        awaitClose { firebase.remove() }
    }

    fun checkIsFavorite(id: String) = callbackFlow {
        trySend(UIState.Loading())

        val listener = EventListener<QuerySnapshot> { value, error ->
            if (value != null) {
                trySend(UIState.Success(true))
            } else {
                trySend(UIState.Success(false))
            }
        }

        val firebase = getUserDataInstance()
            .document(getUid())
            .collection("favorite")
            .whereEqualTo("id", id)
            .addSnapshotListener(listener)

        awaitClose { firebase.remove() }
    }

    fun addFavorite(anime: Anime) {
        getUserDataInstance()
            .document(getUid())
            .collection("favorite")
            .document(anime.id)
            .set(anime)
    }

}

/*
hashMapOf(
                "id" to anime.id,
                "posterImage" to anime.posterImage,
                "coverImage" to anime.coverImage,
                "titleEn" to anime.titleEn,
                "titleJp" to anime.titleJp,
                "rating" to anime.rating,
                "synopsis" to anime.synopsis,
            )
 */