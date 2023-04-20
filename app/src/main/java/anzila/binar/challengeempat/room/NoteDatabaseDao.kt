package anzila.binar.challengeempat.room

import androidx.room.*

@Dao
interface NoteDatabaseDao {

    @Insert
    fun insertData(noteData: NoteData)

    @Query("SELECT * FROM NoteData ORDER BY id DESC")
    fun getData() : List<NoteData>

    @Delete
    fun deleteData(noteData: NoteData)

    @Update
    fun updateData(noteData: NoteData)
}