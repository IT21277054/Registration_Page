package com.example.lab4.models;

import com.example.lab4.models.validation.ValidationResult

public class FormatDat(
    private var studentID:String,
    private var year:String,
    private var semester:String,
    private var agree:Boolean

) {

    fun validationStudentID(): ValidationResult {
        return if(studentID.isEmpty())
        {
            ValidationResult.Empty("Student ID is empty")
        } else if(studentID.length!=10){
            ValidationResult.Invalid("ID should be 10 digits")
        } else if(!studentID.startsWith("IT",true)){
            ValidationResult.Invalid("ID should start with IT")
        } else{
            ValidationResult.Valid
        }
    }

    fun validateYear(): ValidationResult {
        return if(year.isEmpty()){
            ValidationResult.Empty("Year is empty")
        }else{
            ValidationResult.Valid
        }
    }

    fun validateSemester(): ValidationResult {
        return if(semester.isEmpty()){
            ValidationResult.Empty("Semester is empty")
        }else{
            ValidationResult.Valid
        }
    }

    fun validateAgreement(): ValidationResult {
        return if(!agree){
            ValidationResult.Invalid("You need to agree to terms and conditions")
        }else{
            ValidationResult.Valid
        }
    }

}
