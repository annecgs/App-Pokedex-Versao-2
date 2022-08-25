package com.example.frontend.ui.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.frontend.R
import com.example.frontend.databinding.FragmentErrorBinding
import com.example.frontend.ui.home.HomeFragment
import com.example.frontend.utils.Helpers
import com.example.frontend.ui.viewModel.MainViewModel

class ErrorFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels() { Helpers.getMainViewModelFactory() }
    private var _binding: FragmentErrorBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentErrorBinding.inflate(inflater, container, false)
        mensagemErro()
        binding.btnTryAgain.setOnClickListener {
            replaceFragment(HomeFragment())
        }

        val view = binding.root
        return view
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.nav_fragment, fragment)
        fragmentTransaction?.commit()
    }

    private fun mensagemErro() {
        val mensagem = viewModel.mensagem
        if (mensagem.contains("400")) {
            binding.mensagemError.text = "Instabilidade no seu computador ou na sua conexão de Internet"
            Glide.with(requireContext())
                .load(R.drawable.error_400)
                .centerCrop()
                .into(binding.imagemError)
        } else if (mensagem.contains("401")) {
            binding.mensagemError.text = "O site que você está tentando acessar se encontra protegido e requer autorização ou autenticação"
            Glide.with(requireContext())
                .load(R.drawable.error_401)
                .centerCrop()
                .into(binding.imagemError)
        } else if (mensagem.contains("403")) {
            binding.mensagemError.text = "Negação por parte do proprietário, que não permite que a página receba visitas"
            Glide.with(requireContext())
                .load(R.drawable.error_403)
                .centerCrop()
                .into(binding.imagemError)
        } else if (mensagem.contains("404")) {
            binding.mensagemError.text = "URL não localizada"
            Glide.with(requireContext())
                .load(R.drawable.error_404)
                .centerCrop()
                .into(binding.imagemError)
        } else if (mensagem.contains("410")) {
            binding.mensagemError.text = "URL excluída permanentemente"
            Glide.with(requireContext())
                .load(R.drawable.error_410_6)
                .centerCrop()
                .into(binding.imagemError)
        } else if (mensagem.contains("429")) {
            binding.mensagemError.text = "Você excedeu o limite de requisições"
            Glide.with(requireContext())
                .load(R.drawable.error_429)
                .centerCrop()
                .into(binding.imagemError)
        } else if (mensagem.contains("500")) {
            binding.mensagemError.text = "O servidor não pode atender sua solicitação neste momento"
            Glide.with(requireContext())
                .load(R.drawable.error_500)
                .centerCrop()
                .into(binding.imagemError)
        } else if (mensagem.contains("502")) {
            binding.mensagemError.text = "Falha de comunicação entre os servidores"
            Glide.with(requireContext())
                .load(R.drawable.error_502_2)
                .centerCrop()
                .into(binding.imagemError)
        } else if (mensagem.contains("503")) {
            binding.mensagemError.text = "Serviço temporariamente indisponível"
            Glide.with(requireContext())
                .load(R.drawable.error_503_2)
                .centerCrop()
                .into(binding.imagemError)
        } else if (mensagem.contains("504")) {
            binding.mensagemError.text = "Esperando por muito tempo para receber a resposta do servidor"
            Glide.with(requireContext())
                .load(R.drawable.error_504)
                .centerCrop()
                .into(binding.imagemError)
        } else if (mensagem.contains("505")) {
            binding.mensagemError.text = "Versão HTTP não suportada"
            Glide.with(requireContext())
                .load(R.drawable.error_505_5)
                .centerCrop()
                .into(binding.imagemError)
        }
        else{
            binding.mensagemError.text = "Ocorreu um erro por favor tente mais tarde"
            Glide.with(requireContext())
                .load(R.drawable.error_404)
                .centerCrop()
                .into(binding.imagemError)
        }
    }
}

private fun Button.setOnClickListener(replaceFragment: Unit) {
}
