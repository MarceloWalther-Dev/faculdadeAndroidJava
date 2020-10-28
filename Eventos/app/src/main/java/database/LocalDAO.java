package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import database.entity.EventoEntity;
import database.entity.LocalEntity;
import model.LocalEvento;

public class LocalDAO {

    private final String SQL_LISTAR_TODOS = " SELECT * FROM " + LocalEntity.TABLE_NAME;
    private DBGateway dbGateway;

    public LocalDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(LocalEvento localEvento){
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalEntity.COLUMN_NAME_NOME, localEvento.getNome());
        contentValues.put(LocalEntity.COLUMN_NAME_DISTRICT, localEvento.getBairro());
        contentValues.put(LocalEntity.COLUMN_NAME_CITY, localEvento.getCidade());
        contentValues.put(LocalEntity.COLUMN_NAME_CAPACIDADE_PUBLICO,
                localEvento.getCapacidadePublico());

        if(localEvento.getId() > 0){
            return dbGateway.getDatabase().update(LocalEntity.TABLE_NAME, contentValues, LocalEntity._ID +
                    "=?" , new String[]{String.valueOf(localEvento.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(LocalEntity.TABLE_NAME, null , contentValues) > 0;
    }

    public List<LocalEvento> listar(){
        List<LocalEvento> locais = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nomeClub = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_DISTRICT));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CITY));
            int capacidadePublico = cursor.getInt(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE_PUBLICO));
            locais.add(new LocalEvento(id,nomeClub,bairro,cidade,capacidadePublico));
        }
        cursor.close();
        return locais;
    }
}
