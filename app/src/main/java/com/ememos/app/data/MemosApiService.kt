package com.ememos.app.data

import retrofit2.Response
import retrofit2.http.*

interface MemosApiService {
    @GET("/api/v1/memos")
    suspend fun getMemos(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<List<Memo>>

    @POST("/api/v1/memos")
    suspend fun createMemo(@Body memo: Memo): Response<Memo>

    @PUT("/api/v1/memos/{id}")
    suspend fun updateMemo(
        @Path("id") id: String,
        @Body memo: Memo
    ): Response<Memo>

    @DELETE("/api/v1/memos/{id}")
    suspend fun deleteMemo(@Path("id") id: String): Response<Unit>

    @POST("/api/v1/auth/signin")
    suspend fun signIn(@Body credentials: SignInRequest): Response<SignInResponse>
}