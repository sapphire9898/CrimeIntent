package com.example.yueyangzou.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yueyangzou.criminalintent.database.CrimeBaseHelper;
import com.example.yueyangzou.criminalintent.database.CrimeCursorWrapper;
import com.example.yueyangzou.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by yueyangzou on 16/8/8.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
//    private List<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }
    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

//        mCrimes = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i%2 == 0);
//            mCrimes.add(crime);
//        }
    }
    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeTable.NAME, null, values);
//        mCrimes.add(c);
    }

//    public void deleteCrime(Crime c) {
//        if (mCrimes.size() == 0) {
//            return;
//        }
//        for (int i = 0; i < mCrimes.size(); i++) {
//            if (c.getUUID() == mCrimes.get(i).getUUID()) {
//                mCrimes.remove(i);
//            }
//        }
//    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return crimes;

    }

    public Crime getCrime(UUID id) {
//        for (Crime crime : mCrimes) {
//            if (crime.getUUID().equals(id)) {
//                return crime;
//            }
//        }

        CrimeCursorWrapper cursor = queryCrimes(CrimeTable.Cols.UUID + "= ?",
                new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        }
        finally {
            cursor.close();
        }
    }
    public void updateCrime(Crime crime) {
        String uuidString = crime.getUUID().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeTable.NAME, values,
                CrimeTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getUUID().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeTable.Cols.SUSPECT, crime.getSuspect());
//        values.put(CrimeTable.Cols.CONTACT_ID, crime.getContactId());
        return values;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }

    public void deleteCrime(Crime crime) {
        String uuidString = crime.getUUID().toString();

        mDatabase.delete(CrimeTable.NAME,
                CrimeTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

}
