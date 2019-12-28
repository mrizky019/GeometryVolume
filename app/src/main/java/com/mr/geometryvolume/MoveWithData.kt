package com.mr.geometryvolume

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MoveWithData : AppCompatActivity() {

    companion object {
        const val EXTRA_RESULT = "extra_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_data)

        val dataReceive: TextView = findViewById(R.id.resultIntent)

        val result = intent.getStringExtra(EXTRA_RESULT)
        dataReceive.text = result
    }
}
