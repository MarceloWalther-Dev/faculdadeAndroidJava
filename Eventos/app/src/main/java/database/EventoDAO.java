package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import database.entity.EventoEntity;
import model.Eventos;

public class EventoDAO {


    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + EventoEntity.TABLE_NAME;
    private  DBGateway dbGateway;

    public EventoDAO(Context context){

        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar (Eventos eventos){

        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, eventos.getNomeDoEvento());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA, eventos.getDataDoEvento());
        contentValues.put(EventoEntity.COLUMN_NAME_LOCAL, eventos.getLocalDoEvento());

        if(eventos.getId() > 0){
            return dbGateway.getDatabase().update(EventoEntity.TABLE_NAME, contentValues, EventoEntity._ID + "=?" , new String[]{String.valueOf(eventos.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME, null, contentValues)> 0;
    }

    public List<Eventos> listar(){

        List<Eventos> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            String local = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_LOCAL));
            eventos.add(new Eventos(id,nome,data,local));
        }
        cursor.close();
        return eventos;
    }

    public boolean excluir(int id){
        return dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME, EventoEntity._ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

}
