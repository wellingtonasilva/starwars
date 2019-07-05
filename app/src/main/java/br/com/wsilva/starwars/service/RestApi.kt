package br.com.wsilva.starwars.service

import br.com.wsilva.starwars.model.dto.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET("api/people/")
    fun getAllPeople(@Query("page") page: Int = 1): Call<GenericDTO<PeopleDTO>>

    @GET("api/people/{id}")
    fun getPeople(@Path("id") id: Long): Observable<PeopleDTO>

    @GET("api/films/{id}")
    fun getFilm(@Path("id") id: Long): Observable<FilmsDTO>

    @GET("api/species/{id}")
    fun getSpecies(@Path("id") id: Long): Observable<SpeciesDTO>

    @GET("api/vehicles/{id}")
    fun getVehicles(@Path("id") id: Long): Observable<VehiclesDTO>

    @GET("api/starships/{id}")
    fun getStarships(@Path("id") id: Long): Observable<StarshipsDTO>
}