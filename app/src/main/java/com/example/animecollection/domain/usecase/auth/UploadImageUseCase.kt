package com.example.animecollection.domain.usecase.auth

import android.net.Uri
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    suspend operator fun invoke(uri: Uri): Flow<UIState<Boolean>> =
        repository.uploadImage(uri)
}