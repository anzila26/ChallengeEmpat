package anzila.binar.challengeempat

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import anzila.binar.challengeempat.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    lateinit var sharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        binding.btnReg.setOnClickListener {
            val nama = binding.etNameReg.text.toString()
            val uName = binding.etUnameReg.text.toString()
            val pass = binding.etPassReg.text.toString()
            val rePass = binding.etRepass.text.toString()
                if(pass == rePass){
                    val rPref = sharedPref.edit()
                    rPref.putString("nama", nama)
                    rPref.putString("username", uName)
                    rPref.putString("password", pass)
                    rPref.apply()
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    Toast.makeText(context,"Berhasil Regist", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context,"Password harus sama", Toast.LENGTH_LONG).show()
                }
        }
    }
}