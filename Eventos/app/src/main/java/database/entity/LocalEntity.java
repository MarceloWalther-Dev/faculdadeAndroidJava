package database.entity;

import android.provider.BaseColumns;

public class LocalEntity  implements BaseColumns {

    private LocalEntity() {
    }

    public static final String TABLE_NAME = "locais";
    public static final String COLUMN_NAME_NOME = "clubName";
    public static final String COLUMN_NAME_DISTRICT = "bairro";
    public static final String COLUMN_NAME_CITY = "cidade";
    public static final String COLUMN_NAME_CAPACIDADE_PUBLICO = "capacidadeDePublico";

}
