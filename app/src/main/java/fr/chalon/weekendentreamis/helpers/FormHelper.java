package fr.chalon.weekendentreamis.helpers;

import android.content.Context;
import android.widget.EditText;

import fr.chalon.weekendentreamis.R;

public class FormHelper {

    public static boolean isEmpty(EditText editText){
        String input = editText.getText().toString().trim();
        return input.length() == 0;
    }

    public static boolean isEmpty(Context context, EditText editText, int idErrorString)
    {
        if (editText.getText().toString().trim().length() == 0)
        {
            editText.setError(context.getResources().getString(idErrorString));
            editText.requestFocus();
            return true;
        }
        return false;
    }

    public static void setError (EditText editText, String errorString){
        editText.setError(errorString);
    }

    public static void clearError(EditText editText){
        editText.setError(null);
    }
}
