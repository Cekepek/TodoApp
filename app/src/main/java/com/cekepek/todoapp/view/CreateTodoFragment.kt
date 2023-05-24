package com.cekepek.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cekepek.todoapp.R
import com.cekepek.todoapp.model.Todo
import com.cekepek.todoapp.viewmodel.DetailTodoViewModel

class CreateTodoFragment : Fragment() {

    private lateinit var viewModel:DetailTodoViewModel
    //lateinit berarti memberi tahu kotlin bahwa variabel tidak mungkin null tapi pasti diisi nanti

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this)[DetailTodoViewModel::class.java]

        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
            val todo = Todo(txtTitle.text.toString(), txtNotes.text.toString())
            viewModel.addTodo(todo)
            Toast.makeText(view.context, "Todo added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()//untuk kembali ke layar sebelumnya
        }
    }
}