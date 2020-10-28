package database.contract;


import database.entity.LocalEntity;

public final class LocalContract {

    private LocalContract() {}


    public static final String criarTabela(){
        return "CREATE TABLE " + LocalEntity.TABLE_NAME + " (" +
                LocalEntity._ID + " INTEGER PRIMARY KEY," +
                LocalEntity.COLUMN_NAME_NOME + " TEXT, " +
                LocalEntity.COLUMN_NAME_DISTRICT + " TEXT, " +
                LocalEntity.COLUMN_NAME_CITY + " TEXT, " +
                LocalEntity.COLUMN_NAME_CAPACIDADE_PUBLICO + " INT)";
    }


    public static final String removerTabela(){
        return "DROP TABLE IF EXISTS " + LocalEntity.TABLE_NAME;
    }

}
