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





class RecyclerListFragment : Fragment() {
      private lateinit  var recyclerAdapter: RecipeAdapter // adapter instance

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

    //need to initialize recyclwerview

    private fun initViewModel(view: FragmentRecyclerListBinding){
        val recyclerview= binding.recyclerView
// Linear layout manager
        recyclerview.layoutManager= LinearLayoutManager(activity)
        val decoration= DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerview.addItemDecoration(decoration)
        recyclerAdapter = RecipeAdapter()
        recyclerview.adapter= recyclerAdapter // set adpater instance to recyclerview
    }

    private fun initViewModel(){

        //initialize the viewmodel, call all functions of viewmodel, set observer
        val viewmodel= ViewModelProvider(this).get(RecipeListViewModel::class.java)
        viewmodel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<RecyclerList> {
            // check recycler list is not null then only set data to adapter else show message using toast
            if(it !=null){
                recyclerAdapter.setUpdatedData(it.results)
                Log.d("Hello",""+it.results)

            }else{
                Toast.makeText(activity,"Error getting data",Toast.LENGTH_SHORT).show()
            }

        })
        viewmodel.makeAPiCall()
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()
    }
}