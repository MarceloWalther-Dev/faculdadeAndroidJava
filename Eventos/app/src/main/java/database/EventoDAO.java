package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import database.entity.EventoEntity;
import database.entity.LocalEntity;
import model.Eventos;
import model.LocalEvento;

public class EventoDAO {


    private final String SQL_LISTAR_TODOS = "SELECT evento._id, nomeDoEvento , dataDoEvento, idLocal, nomeClub, bairro,city, capacidadePublico FROM " +
            EventoEntity.TABLE_NAME + " INNER JOIN " + LocalEntity.TABLE_NAME +
            " ON " + EventoEntity.COLUMN_NAME_ID_LOCAL + " = " + LocalEntity.TABLE_NAME + "." + LocalEntity._ID;

    private  DBGateway dbGateway;

    public EventoDAO(Context context){

        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar (Eventos eventos){

        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, eventos.getNomeDoEvento());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA, eventos.getDataDoEvento());

        contentValues.put(EventoEntity.COLUMN_NAME_ID_LOCAL, eventos.getLocalDoEvento().getId());

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
            int idLocal = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_LOCAL));
            String nomeClub = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_DISTRICT));
            String city = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CITY));
            int capacidadePublico = cursor.getInt(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE_PUBLICO));
            LocalEvento localEvento = new LocalEvento(idLocal,nomeClub,bairro,city,capacidadePublico);
            eventos.add(new Eventos(id,nome,data,localEvento));
        }
        cursor.close();
        return eventos;
    }

    public boolean excluir(int id){
        return dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME, EventoEntity._ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

}
