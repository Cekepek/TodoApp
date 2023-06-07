package com.cekepek.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cekepek.todoapp.R
import com.cekepek.todoapp.databinding.FragmentEditTodoBinding
import com.cekepek.todoapp.model.Todo
import com.cekepek.todoapp.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment(), FragmentEditTodoLayoutInterface {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding:FragmentEditTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater,
            R.layout.fragment_edit_todo,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.todo
        dataBinding.radiolistener = this
        dataBinding.savelistener = this

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        observeViewModel()
        /*view.findViewById<TextView>(R.id.txtJudulTodo).text = "Edit Todo"

        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.text = "Save Changes"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        btnAdd.setOnClickListener{
            var radioGroupPriority = view. findViewById<RadioGroup>(R.id.radioGroupPriority)
            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)

            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt(), uuid)
            Toast.makeText(context, "Todo Updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }*/
    }

    fun observeViewModel() {
        viewModel.todoLd.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
            /*view?.findViewById<TextView>(R.id.txtTitle)?.setText(it.title)
            view?.findViewById<TextView>(R.id.txtNotes)?.setText(it.notes)

            val radioLow = view?.findViewById<RadioButton>(R.id.radioLow)
            val radioMedium = view?.findViewById<RadioButton>(R.id.radioMedium)
            val radioHigh = view?.findViewById<RadioButton>(R.id.radioHigh)

            when(it.priority){
                1 -> radioLow?.isChecked = true
                2 -> radioMedium?.isChecked = true
                3 -> radioHigh?.isChecked = true
            }*/
        })
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onTodoSave(v: View, obj: Todo) {
//        viewModel.update(obj.title,obj.notes,obj.priority,obj.uuid)
        viewModel.updateTodo(obj)
        Toast.makeText(context, "Todo Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }

}