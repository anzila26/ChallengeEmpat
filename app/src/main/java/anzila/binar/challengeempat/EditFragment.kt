package anzila.binar.challengeempat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import anzila.binar.challengeempat.databinding.FragmentEditBinding
import anzila.binar.challengeempat.room.NoteData
import anzila.binar.challengeempat.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditFragment : Fragment() {

    lateinit var binding : FragmentEditBinding
    var noteDB: NoteDatabase? = null
    lateinit var note:NoteData
    lateinit var noteVM: NoteVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteDB = NoteDatabase.getInstance(requireContext())
        noteVM = ViewModelProvider(requireActivity())[NoteVM::class.java]

        //ambil data yg dikirim dari adapter
        note = arguments?.getSerializable("note") as NoteData
        //set data yang dikirim ke dalam editText
        binding.etJudulEdit.setText(note.title)
        binding.etIsiEdit.setText(note.content)

        binding.btnEdit.setOnClickListener {
            editNote()
        }
    }

    fun editNote() {
        note.title = binding.etJudulEdit.text.toString()
        note.content = binding.etIsiEdit.text.toString()
        GlobalScope.async {
            noteVM.updateNote(note)
        }
    }
}