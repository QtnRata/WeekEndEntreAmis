package fr.chalon.weekendentreamis.helpers;

import android.widget.EditText;

public class FormHelper {

    public static boolean isEmpty(EditText editText){
        String input = editText.getText().toString().trim();
        return input.length() == 0;
    }

    public static void setError (EditText editText, String errorString){
        editText.setError(errorString);
    }

    public static void clearError(EditText editText){
        editText.setError(null);
    }
}
