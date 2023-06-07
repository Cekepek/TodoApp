package com.cekepek.todoapp.view

import android.view.View
import android.webkit.WebSettings.RenderPriority
import android.widget.CompoundButton
import com.cekepek.todoapp.model.Todo

interface TodoItemLayoutInterface{ //interface hanya digunakan untuk menyimpan judul-judul function yang nanti digunakan di tempat lain
    fun onCheckedChange(cb:CompoundButton, isChecked:Boolean, obj:Todo)
    fun onTodoEditClick(v:View)
}

interface FragmentEditTodoLayoutInterface{
    fun onRadioClick(v:View, priority:Int, obj: Todo )
    fun onTodoSave(v:View, obj:Todo)
}