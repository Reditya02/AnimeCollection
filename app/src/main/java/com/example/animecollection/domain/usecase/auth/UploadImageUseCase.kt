package com.example.animecollection.domain.usecase.auth

import android.net.Uri
import com.example.animecollection.domain.repository.IUserRepository
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    suspend operator fun invoke(uri: Uri) =
        repository.uploadImage(uri)
}