package anzila.binar.challengeempat

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import anzila.binar.challengeempat.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding
    lateinit var sharedPref : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtNoReg.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        data()
    }

    fun data(){
        binding.btnLogin.setOnClickListener {
            val uName = binding.etUnameLog.text.toString()
            val pass = binding.etPassLog.text.toString()
            val dataUname = sharedPref.getString("username", "")
            val dataPass = sharedPref.getString("password", "")
            if(uName == dataUname && pass == dataPass){
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }else{
                Toast.makeText(context,"Password atau Username Salah", Toast.LENGTH_LONG).show()
            }
        }
    }
}