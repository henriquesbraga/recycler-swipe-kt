package com.henriquesbraga.recycleswipeexample.view

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.henriquesbraga.recycleswipeexample.R
import com.henriquesbraga.recycleswipeexample.databinding.ActivityMainBinding
import com.henriquesbraga.recycleswipeexample.model.Person
import com.henriquesbraga.recycleswipeexample.view.adapter.MainAdapter
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recycler: RecyclerView
    private lateinit var adapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // 1 - Get Recycler View
        recycler = binding.recycler

        // 2 - Set layout
        recycler.layoutManager = LinearLayoutManager(this)

        //3 - Set adapter with data
        var list:ArrayList<Person> = arrayListOf(
            Person("Value 1", "SubValue 1", "Attribute 1"),
            Person("Value 2", "SubValue 2", "Attribute 2"),
            Person("Value 3", "SubValue 3", "Attribute 3"),
            Person("Value 4", "SubValue 4", "Attribute 4"),
            Person("Value 5", "SubValue 5", "Attribute 5"),
            Person("Value 6", "SubValue 6", "Attribute 6"),
            Person("Value 7", "SubValue 7", "Attribute 7"),
            Person("Value 8", "SubValue 8", "Attribute 8"),
            Person("Value 9", "SubValue 9", "Attribute 9"),
            Person("Value 10", "SubValue 10", "Attribute 10")
        )

        adapter = MainAdapter(this, recycler)
        adapter.setData(list)
        recycler.adapter = adapter

        val itemTouchHelperCallback =
            object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                    val position = viewHolder.adapterPosition
                    Log.d("test", "from onSwiped: $position")

                    when(direction){
                        ItemTouchHelper.LEFT -> {
                            adapter.archieveData(position)
                        }
                        ItemTouchHelper.RIGHT -> {
                            adapter.deleteData(position)
                        }
                        else -> {
                            Toast.makeText(applicationContext, "Default!", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {

                    RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(applicationContext, R.color.purple_700))
                        .addSwipeLeftActionIcon(R.drawable.ic_baseline_archive_24)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(applicationContext, R.color.teal_700))
                        .addSwipeRightActionIcon(R.drawable.ic_baseline_delete_24)
                        .create()
                        .decorate()

                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }

            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recycler)
    }
}