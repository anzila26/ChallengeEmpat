package anzila.binar.challengeempat

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import anzila.binar.challengeempat.databinding.FragmentInputBinding
import anzila.binar.challengeempat.room.NoteDatabase
import anzila.binar.challengeempat.room.NoteDatabaseDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class InputFragment : Fragment() {
    lateinit var binding : FragmentInputBinding
    var noteDB : NoteDatabase? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInputBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteDB = NoteDatabase.getInstance(this)

        binding.btnSave.setOnClickListener {
            addData()
        }
    }

    fun addData(){
        GlobalScope.async {
            var title = binding.etJudul.
            var content = binding.etIsi.text.toString()
            noteDB?.noteDatabaseDao()?.insertData(NoteDatabaseDao(0, title, content))
        }
        finish()
    }
}