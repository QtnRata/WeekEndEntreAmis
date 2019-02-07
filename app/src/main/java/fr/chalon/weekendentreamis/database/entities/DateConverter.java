package fr.chalon.weekendentreamis.database.entities;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date getDate(String value) {
        return value == null ? null : new Date(value);
    }


}
