package com.example.foodrecipe.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipe.adapter.RecipeAdapter
import com.example.foodrecipe.databinding.FragmentRecyclerListBinding
import com.example.foodrecipe.model.RecyclerList
import com.example.foodrecipe.viewmodel.RecipeListViewModel






class RecipeListFragment : Fragment() {
      private lateinit  var recyclerAdapter: RecipeAdapter

    lateinit var binding: FragmentRecyclerListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRecyclerListBinding.inflate(inflater,container,false)


        initViewModel(binding)
        initViewModel()
        return binding.root
    }


//initialize the viewmodel, adapter
    private fun initViewModel(view: FragmentRecyclerListBinding){
        val recyclerView= binding.recyclerView
        recyclerView.layoutManager= LinearLayoutManager(activity)
        val decoration= DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerAdapter = RecipeAdapter(activity)
        recyclerView.adapter= recyclerAdapter
    }
    // check recycler list is not null then only set data to adapter else show message using toast
    private fun initViewModel(){

        val viewModel= ViewModelProvider(this).get(RecipeListViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<RecyclerList> {
            if(it !=null){
                recyclerAdapter.setUpdatedData(it.results)
                Log.d("Hello",""+it.results)

            }else{
                Toast.makeText(activity,"Error getting data",Toast.LENGTH_SHORT).show()
            }

        })
        viewModel.makeAPiCall()
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            RecipeListFragment()
    }
}