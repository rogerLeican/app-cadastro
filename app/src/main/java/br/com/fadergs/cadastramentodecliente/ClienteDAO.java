package br.com.fadergs.cadastramentodecliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static final String TABLE_CLIENTES = "clientes";

    private ClienteDAO(){
    }
    public static void inserir(Context context, Cliente cliente) {

        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());
        values.put("estado", cliente.getEstado());

        SQLiteDatabase db;
        Banco connectionDb = new Banco(context);
            db = connectionDb.getWritableDatabase();

        db.insert(TABLE_CLIENTES, null, values);
    }

    public static void editar(Context context, Cliente cliente) {

        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());
        values.put("estado", cliente.getEstado());

        SQLiteDatabase db;
        Banco connectionDb = new Banco(context);
            db = connectionDb.getWritableDatabase();


        db.update(TABLE_CLIENTES, values, "id = " + cliente.getId(), null);
    }

    public static void excluir(Context context, int id) {

        SQLiteDatabase db;
        Banco connectionDb = new Banco(context);
            db = connectionDb.getWritableDatabase();

        db.delete(TABLE_CLIENTES, "id = " + id, null);
    }

    public static List<Cliente> listarTodos(Context context) {
        List<Cliente> lista = new ArrayList<>();
        SQLiteDatabase db;
        Banco connectionDb = new Banco(context);
            db = connectionDb.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM clientes ORDER BY nome",
                null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Cliente cliente = new Cliente();
                    cliente.setId(cursor.getInt(0));
                    cliente.setNome(cursor.getString(1));
                    cliente.setTelefone(cursor.getString(2));
                    cliente.setEstado(cursor.getString(3));
                    lista.add(cliente);

                } while (cursor.moveToNext());
            }

        return lista;
    }

    public static Cliente buscaClientePorId(Context context, int idCliente) {

        SQLiteDatabase db;
        Banco connectionDb = new Banco(context);
            db = connectionDb.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM clientes WHERE id = " + idCliente,
                null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                Cliente cliente = new Cliente();
                cliente.setId(cursor.getInt(0));
                cliente.setNome(cursor.getString(1));
                cliente.setTelefone(cursor.getString(2));
                cliente.setEstado(cursor.getString(3));
                return cliente;
            }

        return null;
    }
}