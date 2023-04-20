package anzila.binar.challengeempat

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import anzila.binar.challengeempat.databinding.FragmentHomeBinding
import anzila.binar.challengeempat.room.NoteData

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var sharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_inputFragment)
        }
        val getNama = sharedPref.getString("nama", "")
        binding.txtWel.text = "Halo, $getNama"
        binding.rvNote.layoutManager = LinearLayoutManager(requireContext())
        val noteVM = ViewModelProvider(requireActivity())[NoteVM::class.java]
        val adapter = NoteAdapter(emptyList(),object:NoteAdapter.NoteAdapterListener{
            override fun editClick(note: NoteData) {
                val bundle = Bundle()
                bundle.putSerializable("note",note)
                findNavController().navigate(R.id.action_homeFragment_to_editFragment,bundle)
            }
            override fun deleteClick(note: NoteData) {
                val bundle = Bundle()
                bundle.putSerializable("note",note)
                findNavController().navigate(R.id.action_homeFragment_to_deleteFragment3,bundle)
            }
        });
        binding.rvNote.adapter = adapter

        noteVM.allNote.observe(requireActivity()) { newData ->
            adapter.setNewData(newData)
        }
    }
}