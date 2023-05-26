package com.example.fifteen.export;

import android.net.Uri;

public interface Exporter {

    String MIME_TYPE = "text/csv";

    char DELIMITER = ';';

    void export(Uri uri, Callback callback);

    void importData(Uri uri, Callback callback);

    String defaultFilename();

    interface Callback {

        void onSuccess(int count);

        void onError();
    }
}
