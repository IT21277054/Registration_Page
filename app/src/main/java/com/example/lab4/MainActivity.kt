package com.example.lab4

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.lab4.models.FormatDat
import com.example.lab4.models.FormatData
import com.example.lab4.models.validation.ValidationResult

lateinit var editStudentId:EditText
lateinit var spnYear:Spinner
lateinit var spnSemester:Spinner
lateinit var cbAgree:CheckBox
lateinit var submit:Button
private var count = 0;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editStudentId = findViewById(R.id.registerStudent)
        spnYear = findViewById(R.id.year)
        spnSemester = findViewById(R.id.semester)
        cbAgree = findViewById(R.id.agree)
        submit = findViewById(R.id.submit)
    }

    fun displayAlert(title:String, message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            // Do something when the "OK" button is clicked
        }
        val dialog = builder.create()
        dialog.show()}

    override fun onResume() {
        super.onResume()
        submit.setOnClickListener(View.OnClickListener {

            val myForm = FormatDat(
                editStudentId.text.toString(),
                spnYear.selectedItem.toString(),
                spnSemester.selectedItem.toString(),
                cbAgree.isChecked
            )

            val studentValidation = myForm.validationStudentID()
            val spnYearValidation = myForm.validateYear()
            val spnSemesterValidation = myForm.validateSemester()
            val cbAgreeValdation = myForm.validateAgreement()

            when(studentValidation){
                is ValidationResult.Valid -> {
                    count++
                }
                is ValidationResult.Invalid ->{
                    editStudentId.error = studentValidation.errorMessage
                }
                is ValidationResult.Empty ->{
                    editStudentId.error = studentValidation.errorMessage
                }
            }

            when(spnYearValidation){
                is ValidationResult.Valid -> {
                    count++
                }
                is ValidationResult.Invalid ->{
                    val tv:TextView = spnYear.selectedView as TextView
                    tv.error = ""
                    tv.text = spnYearValidation.errorMessage
                }
                is ValidationResult.Empty ->{
                    val tv:TextView = spnYear.selectedView as TextView
                    tv.error = ""
                    tv.text = spnYearValidation.errorMessage
                }
            }

            when(spnSemesterValidation){
                is ValidationResult.Valid -> {
                    count++
                }
                is ValidationResult.Invalid ->{
                    val tv:TextView = spnSemester.selectedView as TextView
                    tv.error = ""
                    tv.text = spnSemesterValidation.errorMessage
                }
                is ValidationResult.Empty ->{
                    val tv:TextView = spnSemester.selectedView as TextView
                    tv.error = ""
                    tv.text = spnSemesterValidation.errorMessage
                }
            }

            when(cbAgreeValdation){
                is ValidationResult.Valid -> {
                    count++
                }
                is ValidationResult.Invalid ->{
                    displayAlert("Error", cbAgreeValdation.errorMessage)
                }
                is ValidationResult.Empty ->{

                }
            }

            if(count==4){
                displayAlert("Success","Congratulation, you've registered an account nobody cares :)")
                val dataObject = FormatData(
                        editStudentId.text.toString(),
                        spnYear.selectedItem.toString(),
                        spnSemester.selectedItem.toString(),
                        cbAgree.isChecked
                )

            }



        })



    }
}