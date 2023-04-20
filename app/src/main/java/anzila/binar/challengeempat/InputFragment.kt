package anzila.binar.challengeempat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import anzila.binar.challengeempat.databinding.FragmentInputBinding
import anzila.binar.challengeempat.room.NoteData
import anzila.binar.challengeempat.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class InputFragment : Fragment() {
    lateinit var binding : FragmentInputBinding
    var noteDB : NoteDatabase? = null
    lateinit var noteVM: NoteVM
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

        noteDB = NoteDatabase.getInstance(requireContext())
        noteVM = ViewModelProvider(requireActivity())[NoteVM::class.java]

        binding.btnSave.setOnClickListener {
            addData()
        }
    }

    fun addData(){
        GlobalScope.async {
            var title = binding.etJudul.text.toString()
            var content = binding.etIsi.text.toString()
            noteVM.insertNote(NoteData(title=title,content=content))
            findNavController().navigate(R.id.action_inputFragment_to_homeFragment)
        }
    }
}
