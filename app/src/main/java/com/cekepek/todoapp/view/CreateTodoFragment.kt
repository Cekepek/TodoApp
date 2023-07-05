package com.cekepek.todoapp.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
import java.util.Calendar
import java.util.concurrent.TimeUnit

class CreateTodoFragment : Fragment(), FragmentCreateTodoLayoutInterface,
    DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var viewModel:DetailTodoViewModel
    //lateinit berarti memberi tahu kotlin bahwa variabel tidak mungkin null tapi pasti diisi nanti
    private lateinit var dataBinding:FragmentCreateTodoBinding
    var year = 0
    var month = 0
    var day = 0
    var hour = 0
    var minute = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_todo, container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.todo = Todo("","", 3,0, 0)
        dataBinding.radiolistener = this
        dataBinding.buttonlistener = this
        dataBinding.dateListener = this
        dataBinding.timeListener = this

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
        }*/

    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {

    }

    override fun onButtonAddTodo(v: View) {
        val today = Calendar.getInstance().timeInMillis/1000L
        val c = Calendar.getInstance()
        c.set(year, month, day, hour, minute, 0)
        val tododate = c.timeInMillis/1000L
        dataBinding.todo!!.todo_date = tododate.toInt()
        val diff = tododate-today

        val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
            .setInitialDelay(diff, TimeUnit.SECONDS)
            .setInputData(
                workDataOf(
                    "title" to "Todo is due",
                    "message" to "${dataBinding.todo!!.title} due date is approaching"
                )
            ).build()
        viewModel.addTodo(dataBinding.todo!!)
        Toast.makeText(v.context, "Todo added", Toast.LENGTH_LONG).show()
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
        Navigation.findNavController(v).popBackStack()//untuk kembali ke layar sebelumnya
    }

    override fun onDateClick(v: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        activity?.let {
            DatePickerDialog(it, this, year, month, day).show()
        }
    }

    override fun onTimeClick(v: View) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity)).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month
        this.day = day

        dataBinding.txtDate.setText(
            "${this.day.toString().padStart(2,'0')}"+"/"+
                    "${this.month.toString().padStart(2,'0')}"+"/"+
                    this.year
        )
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        this.hour = hour
        this.minute = minute

        dataBinding.txtTime.setText(
            "${this.hour.toString().padStart(2,'0')}"+":"+
                    "${this.minute.toString().padStart(2,'0')}"
        )
    }
}