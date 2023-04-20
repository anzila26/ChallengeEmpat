package anzila.binar.challengeempat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import anzila.binar.challengeempat.databinding.FragmentDeleteBinding
import anzila.binar.challengeempat.room.NoteData
import anzila.binar.challengeempat.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class DeleteFragment : Fragment() {

    lateinit var binding : FragmentDeleteBinding
    var noteDB: NoteDatabase? = null
    lateinit var note:NoteData
    lateinit var noteVM: NoteVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeleteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteDB = NoteDatabase.getInstance(requireContext())

        //ambil data dari adapter
        note = arguments?.getSerializable("note") as NoteData
        noteVM = ViewModelProvider(requireActivity())[NoteVM::class.java]
        binding.btnDelete.setOnClickListener {
            deleteData()
        }

        binding.btnDelete.setOnClickListener {
            deleteData()
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigate(R.id.action_deleteFragment3_to_homeFragment)
        }
    }

    fun deleteData() {
        GlobalScope.async {
            noteVM.deleteNote(note)
            findNavController().navigate(R.id.action_deleteFragment3_to_homeFragment)
        }
    }
}