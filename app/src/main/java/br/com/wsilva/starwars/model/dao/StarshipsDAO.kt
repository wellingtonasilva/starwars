package br.com.wsilva.starwars.model.dao

import androidx.room.*
import br.com.wsilva.starwars.core.BasicDAO
import br.com.wsilva.starwars.model.entity.StarshipsEntity
import io.reactivex.Flowable

@Dao
interface StarshipsDAO: BasicDAO<StarshipsEntity> {
    @Query("SELECT * FROM starships")
    override fun listAll(): Flowable<List<StarshipsEntity>>

    @Query("SELECT * FROM starships WHERE _id = :id")
    override fun get(id: Long): StarshipsEntity

    @Delete
    override fun delete(entity: StarshipsEntity): Int

    @Insert
    override fun insert(entity: StarshipsEntity): Long

    @Update
    override fun update(entity: StarshipsEntity): Int

    @Query("SELECT COUNT(1) FROM starships WHERE _id = :id")
    fun exist(id: Long) : Int
}