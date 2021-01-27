package io.github.caoshen.androidadvance.jetpack.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.github.caoshen.androidadvance.jetpack.databinding.FragmentMainBinding

/**
 * @author caoshen
 * @date 2021/1/27
 */
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val word = "IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII"
//        val word = "Hello world"
        binding.text.text = "正在翻译..."

        // observe result change
        viewModel.translateResult.observe(viewLifecycleOwner) {
            binding.text.text = "原词:$word\n翻译:$it"
        }

        // start translate
        viewModel.translate(word)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}