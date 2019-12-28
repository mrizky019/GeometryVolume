package com.mr.geometryvolume

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtLength: EditText
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var btnMoveData: Button
    private lateinit var txtResult: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtLength = findViewById(R.id.editTextLength)
        edtWidth = findViewById(R.id.editTextWidth)
        edtHeight = findViewById(R.id.editTextHeight)
        btnCalculate = findViewById(R.id.btnCalculate)
        btnMoveData = findViewById(R.id.btnMoveIntentWithData)
        txtResult = findViewById(R.id.resultCalculate)

        btnCalculate.setOnClickListener(this)
        btnMoveIntentWithData.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            txtResult.text = result
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnCalculate -> {
                val inputLength = edtLength.text.toString().trim()
                val inputWidth = edtWidth.text.toString().trim()
                val inputHeight = edtHeight.text.toString().trim()

                var isEmptyFields = false
                var isInvalidDouble = false

                if (inputLength.isEmpty()) {
                    isEmptyFields = true
                    edtLength.error = getString(R.string.required_field)
                }

                if (inputWidth.isEmpty()) {
                    isEmptyFields = true
                    edtWidth.error = getString(R.string.required_field)
                }

                if (inputHeight.isEmpty()) {
                    isEmptyFields = true
                    edtHeight.error = getString(R.string.required_field)
                }

                val length = toDouble(inputLength)
                val width = toDouble(inputWidth)
                val height = toDouble(inputHeight)

                if (length == null) {
                    isInvalidDouble = true
                    edtLength.error = getString(R.string.required_number)
                }
                if (width == null) {
                    isInvalidDouble = true
                    edtWidth.error = getString(R.string.required_number)
                }
                if (height == null) {
                    isInvalidDouble = true
                    edtHeight.error = getString(R.string.required_number)
                }

                if (!isEmptyFields && !isInvalidDouble) {
                    val volume = length as Double * width as Double * height as Double
                    txtResult.text = volume.toString()
                }
            }

            R.id.btnMoveIntentWithData -> {
                var isNumber = true
                val moveResult = txtResult.text.toString()

                try {
                    java.lang.Double.parseDouble(moveResult)
                } catch (e: java.lang.NumberFormatException) {
                    isNumber = false
                }
                
                if (isNumber) {
                    startActivity(intentFor<MoveWithData>("extra_result" to moveResult))
                } else {
                    toast(getString(R.string.required_result))
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, txtResult.text.toString())
    }

    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }
}
