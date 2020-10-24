package com.honnalmanja.androidkotlinmvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.honnalmanja.androidkotlinmvvm.databinding.FragmentTaskAddBinding


class AddTaskFragment : Fragment() {

    lateinit var binding: FragmentTaskAddBinding
    companion object {
        @JvmStatic
        fun newInstance() =
            AddTaskFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_add, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addTaskBtn.setOnClickListener {
            findNavController().navigate(R.id.action_AddTaskFragment_to_TaskListFragment)
        }


    }
}