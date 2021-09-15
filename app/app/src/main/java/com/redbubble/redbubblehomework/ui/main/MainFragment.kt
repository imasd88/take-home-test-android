package com.redbubble.redbubblehomework.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.redbubble.redbubblehomework.R
import com.redbubble.redbubblehomework.adapter.HomeAdapter
import com.redbubble.redbubblehomework.databinding.MainFragmentBinding
import com.redbubble.redbubblehomework.model.HomeModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainFragment : Fragment(), CoroutineScope {

    lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainFragmentViewModel::class.java)
    }

    lateinit var list: List<HomeModel>

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    private val homeModelAdapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate<MainFragmentBinding>(
            inflater,
            R.layout.main_fragment,
            container,
            false
        ).also {
            it.lifecycleOwner = activity
        }
        binding.rvHome.apply {
            adapter =
                homeModelAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
    }

    private fun fetchData() {
        job = launch {
            try {
                list = withContext(Dispatchers.IO) {
                    viewModel.fetchData()
                }
                homeModelAdapter.list = list
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

