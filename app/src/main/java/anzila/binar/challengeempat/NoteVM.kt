package anzila.binar.challengeempat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import anzila.binar.challengeempat.room.NoteData
import anzila.binar.challengeempat.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteVM(app : Application):AndroidViewModel(app) {

    var allNote : MutableLiveData<List<NoteData>> = MutableLiveData()

    init {
        getAllNote()
    }

    fun getAllNote(){
        GlobalScope.launch {
            val userDao = NoteDatabase.getInstance(getApplication())!!.noteDatabaseDao()
            val listnote = userDao.getData()
            allNote.postValue(listnote)
        }
    }

    fun insertNote(entity: NoteData){
        val noteDao = NoteDatabase.getInstance(getApplication())?.noteDatabaseDao()
        noteDao!!.insertData(entity)
        getAllNote()
    }

    fun deleteNote(entity: NoteData){
        val userDao = NoteDatabase.getInstance(getApplication())!!.noteDatabaseDao()
        userDao?.deleteData(entity)
        getAllNote()
    }

    fun updateNote(entity: NoteData){
        val userDao = NoteDatabase.getInstance(getApplication())!!.noteDatabaseDao()
        userDao?.updateData(entity)
        getAllNote()
    }
}