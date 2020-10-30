package database.entity;

import android.provider.BaseColumns;

import model.LocalEvento;

public class EventoEntity  implements BaseColumns {

    private EventoEntity(){}

    public static final String TABLE_NAME = "eventos";
    public static final String COLUMN_NAME_NOME = "nome";
    public static final String COLUMN_NAME_DATA = "data";
    public static final String COLUMN_NAME_ID_LOCAL = "idLocal";
}
