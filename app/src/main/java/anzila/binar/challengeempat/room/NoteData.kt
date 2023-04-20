package anzila.binar.challengeempat.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class NoteData(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var title : String,
    var content : String
):Serializable{
    constructor(title: String, content: String) : this(0, title, content)
}
