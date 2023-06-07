package com.cekepek.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cekepek.todoapp.R
import com.cekepek.todoapp.model.Todo
import com.cekepek.todoapp.util.buildDb
import com.cekepek.todoapp.viewmodel.DetailTodoViewModel
import com.cekepek.todoapp.viewmodel.ListTodoViewModel

class TodoListAdapter(val todos:ArrayList<Todo>,val todoOnClick:(Todo) -> Unit)
    : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        return TodoViewHolder(view)
    }

    //OnBindViewHolder digunakan untuk menampilkan data dan dijalankan sebanyak data yang ada
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var checkTask = holder.view.findViewById<CheckBox>(R.id.checkTask)
        checkTask.text = todos[position].title
        checkTask.isChecked = false

        checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked) {
                todoOnClick(todos[position])
            }
        }

        var imgEdit = holder.view.findViewById<ImageView>(R.id.imgEdit)
        imgEdit.setOnClickListener {
            val action = TodoListFragmentDirections.actionEditTodoFragment(todos[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun updateTodoList(newtodos:List<Todo>){
        todos.clear()
        todos.addAll(newtodos)
        notifyDataSetChanged()
    }
}