package com.cekepek.todoapp.view

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.cekepek.todoapp.R
import com.cekepek.todoapp.databinding.FragmentCreateTodoBinding
import com.cekepek.todoapp.model.Todo
import com.cekepek.todoapp.util.NotificationHelper
import com.cekepek.todoapp.util.TodoWorker
import com.cekepek.todoapp.viewmodel.DetailTodoViewModel
import java.util.concurrent.TimeUnit

class CreateTodoFragment : Fragment(), FragmentCreateTodoLayoutInterface {

    private lateinit var viewModel:DetailTodoViewModel
    //lateinit berarti memberi tahu kotlin bahwa variabel tidak mungkin null tapi pasti diisi nanti
    private lateinit var dataBinding:FragmentCreateTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_todo, container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.todo = Todo("","", 3,0)
        dataBinding.radiolistener = this
        dataBinding.buttonlistener = this

        viewModel= ViewModelProvider(this)[DetailTodoViewModel::class.java]

        /*val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
                .setInitialDelay(30,TimeUnit.SECONDS)
                .setInputData(workDataOf(
                    "title" to "Todo Created",
                    "message" to "A new todo has been created! stay focus!"
                ))
                .build()
            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)

//            NotificationHelper(view.context).createNotification("Todo Created",
//            "A new todo has been created! stay focus!")

            var radioGroupPriority = view. findViewById<RadioGroup>(R.id.radioGroupPriority)
            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId) //mendapatkan radio button yang terpilih saja
            val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
            val todo = Todo(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt(), 0)
            viewModel.addTodo(todo)
            Toast.makeText(view.context, "Todo added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()//untuk kembali ke layar sebelumnya
        }*/

    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {

    }

    override fun onButtonAddTodo(v: View) {
        TODO("Not yet implemented")
    }
}