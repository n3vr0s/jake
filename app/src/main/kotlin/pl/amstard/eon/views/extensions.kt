package pl.amstard.eon.views

import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.TextView

fun TextInputEditText.showErrorOnTextInputLayout(charSequence: CharSequence?) {
    if(parent?.parent is TextInputLayout){
        (parent?.parent as TextInputLayout).error = charSequence
    }else{
        error = charSequence
    }
}

fun View.showValidationError(charSequence: CharSequence?){
    if(this is TextInputEditText){
        this.showErrorOnTextInputLayout(charSequence)
    }else if(this is TextView){
       this.error = charSequence
    }
}