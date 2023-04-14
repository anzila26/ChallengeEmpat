package anzila.binar.challengeempat.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteData(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val content : String
)
