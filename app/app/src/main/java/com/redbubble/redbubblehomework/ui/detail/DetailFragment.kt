package com.redbubble.redbubblehomework.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.redbubble.redbubblehomework.R
import com.redbubble.redbubblehomework.adapter.HomeAdapter
import com.redbubble.redbubblehomework.databinding.DetailFragmentBinding
import com.redbubble.redbubblehomework.model.DetailModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DetailFragment : Fragment(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(DetailFragmentViewModel::class.java)
    }

    lateinit var workDetails: DetailModel

    private var homeModelAdapter = HomeAdapter()

    private lateinit var binding: DetailFragmentBinding

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate<DetailFragmentBinding>(
            layoutInflater,
            R.layout.detail_fragment,
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
        fetchData(if (args.type == "PRODUCT") args.id.substringBefore("_") else args.id)
    }

    private fun fetchData(id: String) {
        job = launch {
            try {
                val data = withContext(Dispatchers.IO) {

                    viewModel.fetchData(id)
                }
                viewModel.parseResponse(data)
                homeModelAdapter.list = viewModel.getAvailableProducts()
                viewModel.getWorkDetails()?.apply {
                    workDetails = this
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}