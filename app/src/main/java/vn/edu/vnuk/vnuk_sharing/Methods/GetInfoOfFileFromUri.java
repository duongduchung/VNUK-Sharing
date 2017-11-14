package vn.edu.vnuk.vnuk_sharing.Methods;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

/**
 * Created by ngocb on 10/25/2017.
 */

public class GetInfoOfFileFromUri {
    private String nameFile;
    private int sizeFile;

    public GetInfoOfFileFromUri(){}

    public GetInfoOfFileFromUri(String nameFile, int sizeFile){
        this.nameFile = nameFile;
        this.sizeFile = sizeFile;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public int getSizeFile() {
        return sizeFile;
    }

    public void setSizeFile(int sizeFile) {
        this.sizeFile = sizeFile;
    }

    private String getFileName(Context context, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private String getRealSize(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Audio.Media.SIZE };
            cursor = context.getContentResolver().query(uri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void get(Context context, Uri uri){
        this.nameFile = getFileName(context, uri);
        this.sizeFile = Integer.parseInt(getRealSize(context, uri));
    }

}
