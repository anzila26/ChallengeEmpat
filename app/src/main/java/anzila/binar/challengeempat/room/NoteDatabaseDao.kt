package anzila.binar.challengeempat.room

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface NoteDatabaseDao {

    @Insert
    fun insertData(noteData: NoteData)
}