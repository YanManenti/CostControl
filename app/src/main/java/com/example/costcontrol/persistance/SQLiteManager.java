package com.example.costcontrol.persistance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.costcontrol.persistance.models.Trip;
import com.example.costcontrol.persistance.models.User;

import java.util.ArrayList;
import java.util.List;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;
    public static final String DATABASE_NAME = "CostControll.db";

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static SQLiteManager instanceOfDatabase(Context context) {
        if (sqLiteManager == null)
            sqLiteManager = new SQLiteManager(context);

        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder tripSb = new StringBuilder();
        tripSb.append("CREATE TABLE Trip (")
                .append("id INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append("destino TEXT, ")
                .append("numeroViajantes INTEGER, ")
                .append("duracaoDias INTEGER, ")
                .append("combustivel BOOLEAN, ")
                .append("totalEstimadoQuilometros REAL, ")
                .append("mediaQuilometrosLitro REAL, ")
                .append("custoMedioLitro REAL, ")
                .append("totalVeiculos INTEGER, ")
                .append("custoMedioNoite REAL, ")
                .append("totalNoites INTEGER, ")
                .append("totalQuartos INTEGER, ")
                .append("userId INTEGER, ")
                .append("tarifaAerea BOOLEAN, ")
                .append("custoEstimadoPessoa REAL, ")
                .append("aluguelVeiculo REAL, ")
                .append("refeicoes BOOLEAN, ")
                .append("custoEstimadoRefeicao REAL, ")
                .append("refeicoesDia INTEGER, ")
                .append("hospedagem BOOLEAN")
                .append(");");

        String createTripTableQuery = tripSb.toString();

        StringBuilder userSb = new StringBuilder();
        userSb.append("CREATE TABLE User (")
                .append("id INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append("email TEXT, ")
                .append("password TEXT")
                .append(");");

        String createUserTableQuery = userSb.toString();

        db.execSQL(createTripTableQuery);
        db.execSQL(createUserTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade if needed
    }

    public void addTripToDatabase(Trip trip) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("destino", trip.getDestino());
        contentValues.put("numeroViajantes", trip.getNumeroViajantes());
        contentValues.put("duracaoDias", trip.getDuracaoDias());
        contentValues.put("combustivel", trip.getCombustivel());
        contentValues.put("totalEstimadoQuilometros", trip.getTotalEstimadoQuilometros());
        contentValues.put("mediaQuilometrosLitro", trip.getMediaQuilometrosLitro());
        contentValues.put("custoMedioLitro", trip.getCustoMedioLitro());
        contentValues.put("totalVeiculos", trip.getTotalVeiculos());
        contentValues.put("custoMedioNoite", trip.getCustoMedioNoite());
        contentValues.put("totalNoites", trip.getTotalNoites());
        contentValues.put("totalQuartos", trip.getTotalQuartos());
        contentValues.put("userId", trip.getUserId());
        contentValues.put("tarifaAerea", trip.getTarifaAerea());
        contentValues.put("custoEstimadoPessoa", trip.getCustoEstimadoPessoa());
        contentValues.put("aluguelVeiculo", trip.getAluguelVeiculo());
        contentValues.put("refeicoes", trip.getRefeicoes());
        contentValues.put("custoEstimadoRefeicao", trip.getCustoEstimadoRefeicao());
        contentValues.put("refeicoesDia", trip.getRefeicoesDia());
        contentValues.put("hospedagem", trip.getHospedagem());

        sqLiteDatabase.insert("Trip", null, contentValues);
    }

    public void updateTrip(Trip trip) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("destino", trip.getDestino());
        contentValues.put("numeroViajantes", trip.getNumeroViajantes());
        contentValues.put("duracaoDias", trip.getDuracaoDias());
        contentValues.put("combustivel", trip.getCombustivel());
        contentValues.put("totalEstimadoQuilometros", trip.getTotalEstimadoQuilometros());
        contentValues.put("mediaQuilometrosLitro", trip.getMediaQuilometrosLitro());
        contentValues.put("custoMedioLitro", trip.getCustoMedioLitro());
        contentValues.put("totalVeiculos", trip.getTotalVeiculos());
        contentValues.put("custoMedioNoite", trip.getCustoMedioNoite());
        contentValues.put("totalNoites", trip.getTotalNoites());
        contentValues.put("totalQuartos", trip.getTotalQuartos());
        contentValues.put("userId", trip.getUserId());
        contentValues.put("tarifaAerea", trip.getTarifaAerea());
        contentValues.put("custoEstimadoPessoa", trip.getCustoEstimadoPessoa());
        contentValues.put("aluguelVeiculo", trip.getAluguelVeiculo());
        contentValues.put("refeicoes", trip.getRefeicoes());
        contentValues.put("custoEstimadoRefeicao", trip.getCustoEstimadoRefeicao());
        contentValues.put("refeicoesDia", trip.getRefeicoesDia());
        contentValues.put("hospedagem", trip.getHospedagem());

        sqLiteDatabase.update("Trip", contentValues, "id = ?", new String[]{String.valueOf(trip.getId())});
    }

    public void addUserToDatabase(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("email", user.getEmail());
        contentValues.put("password", user.getPassword());

        sqLiteDatabase.insert("User", null, contentValues);
    }

    public Trip getTripById(Integer id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String[] columns = {
                "id", "destino", "numeroViajantes", "duracaoDias", "combustivel",
                "totalEstimadoQuilometros", "mediaQuilometrosLitro", "custoMedioLitro",
                "totalVeiculos", "custoMedioNoite", "totalNoites", "totalQuartos",
                "userId", "tarifaAerea", "custoEstimadoPessoa", "aluguelVeiculo",
                "refeicoes", "custoEstimadoRefeicao", "refeicoesDia", "hospedagem"
        };

        String selection = "id = ?";
        String[] selectionArgs = {id.toString()};

        Cursor cursor = sqLiteDatabase.query(
                "Trip",
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null) {
            cursor.moveToFirst();

            Trip trip = new Trip();
            trip.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            trip.setDestino(cursor.getString(cursor.getColumnIndexOrThrow("destino")));
            trip.setNumeroViajantes(cursor.getInt(cursor.getColumnIndexOrThrow("numeroViajantes")));
            trip.setDuracaoDias(cursor.getInt(cursor.getColumnIndexOrThrow("duracaoDias")));
            trip.setCombustivel(cursor.getInt(cursor.getColumnIndexOrThrow("combustivel")) == 1);
            trip.setTotalEstimadoQuilometros(cursor.getFloat(cursor.getColumnIndexOrThrow("totalEstimadoQuilometros")));
            trip.setMediaQuilometrosLitro(cursor.getFloat(cursor.getColumnIndexOrThrow("mediaQuilometrosLitro")));
            trip.setCustoMedioLitro(cursor.getFloat(cursor.getColumnIndexOrThrow("custoMedioLitro")));
            trip.setTotalVeiculos(cursor.getInt(cursor.getColumnIndexOrThrow("totalVeiculos")));
            trip.setCustoMedioNoite(cursor.getFloat(cursor.getColumnIndexOrThrow("custoMedioNoite")));
            trip.setTotalNoites(cursor.getInt(cursor.getColumnIndexOrThrow("totalNoites")));
            trip.setTotalQuartos(cursor.getInt(cursor.getColumnIndexOrThrow("totalQuartos")));
            trip.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow("userId")));
            trip.setTarifaAerea(cursor.getInt(cursor.getColumnIndexOrThrow("tarifaAerea")) == 1);
            trip.setCustoEstimadoPessoa(cursor.getFloat(cursor.getColumnIndexOrThrow("custoEstimadoPessoa")));
            trip.setAluguelVeiculo(cursor.getFloat(cursor.getColumnIndexOrThrow("aluguelVeiculo")));
            trip.setRefeicoes(cursor.getInt(cursor.getColumnIndexOrThrow("refeicoes")) == 1);
            trip.setCustoEstimadoRefeicao(cursor.getFloat(cursor.getColumnIndexOrThrow("custoEstimadoRefeicao")));
            trip.setRefeicoesDia(cursor.getInt(cursor.getColumnIndexOrThrow("refeicoesDia")));
            trip.setHospedagem(cursor.getInt(cursor.getColumnIndexOrThrow("hospedagem")) == 1);

            cursor.close();
            return trip;
        } else {
            return null;
        }
    }

    public List<Trip> getTripsByUserId(Integer userId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String[] columns = {
                "id", "destino", "numeroViajantes", "duracaoDias", "combustivel",
                "totalEstimadoQuilometros", "mediaQuilometrosLitro", "custoMedioLitro",
                "totalVeiculos", "custoMedioNoite", "totalNoites", "totalQuartos",
                "userId", "tarifaAerea", "custoEstimadoPessoa", "aluguelVeiculo",
                "refeicoes", "custoEstimadoRefeicao", "refeicoesDia", "hospedagem"
        };

        String selection = "userId = ?";
        String[] selectionArgs = {userId.toString()};

        Cursor cursor = sqLiteDatabase.query(
                "Trip",
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List<Trip> trips = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Trip trip = new Trip();
                trip.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                trip.setDestino(cursor.getString(cursor.getColumnIndexOrThrow("destino")));
                trip.setNumeroViajantes(cursor.getInt(cursor.getColumnIndexOrThrow("numeroViajantes")));
                trip.setDuracaoDias(cursor.getInt(cursor.getColumnIndexOrThrow("duracaoDias")));
                trip.setCombustivel(cursor.getInt(cursor.getColumnIndexOrThrow("combustivel")) == 1);
                trip.setTotalEstimadoQuilometros(cursor.getFloat(cursor.getColumnIndexOrThrow("totalEstimadoQuilometros")));
                trip.setMediaQuilometrosLitro(cursor.getFloat(cursor.getColumnIndexOrThrow("mediaQuilometrosLitro")));
                trip.setCustoMedioLitro(cursor.getFloat(cursor.getColumnIndexOrThrow("custoMedioLitro")));
                trip.setTotalVeiculos(cursor.getInt(cursor.getColumnIndexOrThrow("totalVeiculos")));
                trip.setCustoMedioNoite(cursor.getFloat(cursor.getColumnIndexOrThrow("custoMedioNoite")));
                trip.setTotalNoites(cursor.getInt(cursor.getColumnIndexOrThrow("totalNoites")));
                trip.setTotalQuartos(cursor.getInt(cursor.getColumnIndexOrThrow("totalQuartos")));
                trip.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow("userId")));
                trip.setTarifaAerea(cursor.getInt(cursor.getColumnIndexOrThrow("tarifaAerea")) == 1);
                trip.setCustoEstimadoPessoa(cursor.getFloat(cursor.getColumnIndexOrThrow("custoEstimadoPessoa")));
                trip.setAluguelVeiculo(cursor.getFloat(cursor.getColumnIndexOrThrow("aluguelVeiculo")));
                trip.setRefeicoes(cursor.getInt(cursor.getColumnIndexOrThrow("refeicoes")) == 1);
                trip.setCustoEstimadoRefeicao(cursor.getFloat(cursor.getColumnIndexOrThrow("custoEstimadoRefeicao")));
                trip.setRefeicoesDia(cursor.getInt(cursor.getColumnIndexOrThrow("refeicoesDia")));
                trip.setHospedagem(cursor.getInt(cursor.getColumnIndexOrThrow("hospedagem")) == 1);
                trips.add(trip);
            }
            cursor.close();
        }
        return trips;
    }


    public User getUserEmail(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String[] columns = {
                "id", "email", "password"
        };

        String selection = "email = ?";
        String[] selectionArgs = {email};

        Cursor cursor = sqLiteDatabase.query(
                "User",
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("password")));

            cursor.close();
            return user;
        } else {
            if (cursor != null) {
                cursor.close();
            }
            return null;
        }
    }

}
