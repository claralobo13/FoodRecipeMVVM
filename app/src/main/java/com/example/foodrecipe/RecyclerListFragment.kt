package com.example.foodrecipe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipe.adapter.RecipeAdapter
import com.example.foodrecipe.model.RecyclerList
import com.example.foodrecipe.viewmodel.RecipeListViewModel




/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerListFragment : Fragment() {
      private lateinit  var recyclerAdapter: RecipeAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      val view= inflater.inflate(R.layout.fragment_recycler_list, container, false)
        initViewModel(view)
        initViewModel()
        return view
    }

    //need to initialize recyclwerview

    private fun initViewModel(view: View){
       val recyclerView= view.findViewById<RecyclerView>(R.id.recyclerView)
// Linear layout manager
        recyclerView.layoutManager= LinearLayoutManager(activity)
        val decoration= DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerAdapter = RecipeAdapter()
        recyclerView.adapter= RecipeAdapter()
    }

    private fun initViewModel(){

        //initialize the viewmodel, call all functions of viewmodel, set observer
        val viewmodel= ViewModelProvider(this).get(RecipeListViewModel::class.java)
        viewmodel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<RecyclerList> {
            // check recycler list not null
            if(it !=null){
                recyclerAdapter.setUpdatedData(it.results)

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