package com.honnalmanja.androidkotlinmvvm.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.honnalmanja.androidkotlinmvvm.R
import com.honnalmanja.androidkotlinmvvm.databinding.ActivityTaskBinding


class TaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task)
        setSupportActionBar(binding.toolbar)

    }

    fun pageTitle(title: String = getString(R.string.app_name)){
        supportActionBar?.title = title
    }

    fun toggleTaskBar(hide: Boolean = false) {
        binding.toolbar.isVisible = !hide
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}