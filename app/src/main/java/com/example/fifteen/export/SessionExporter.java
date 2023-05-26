package com.example.fifteen.export;

import static java.nio.charset.StandardCharsets.UTF_8;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;

import com.example.fifteen.Constants;
import com.example.fifteen.Logger;
import com.example.metalslug.R;
import com.example.fifteen.Tools;
import com.example.fifteen.statistics.StatisticsEntry;
import com.example.fifteen.statistics.StatisticsKey;
import com.example.fifteen.statistics.StatisticsManager;

import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SessionExporter implements Exporter {

    private static final String DEFAULT_FILENAME_FORMAT = "15-puzzle-session-%s.csv";

    private final Context context;
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Handler handler;

    public SessionExporter(Context context) {
        this.context = context;
        this.handler = new Handler(context.getMainLooper());
    }

    @Override
    public void export(Uri uri, Callback callback) {
        executor.execute(new ExportTask(uri, callback));
    }

    @Override
    public void importData(Uri uri, Callback callback) {
        throw new UnsupportedOperationException("Import is not supported for " + SessionExporter.class.getSimpleName());
    }

    @Override
    public String defaultFilename() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault());
        return String.format(DEFAULT_FILENAME_FORMAT, dateFormat.format(new Date()));
    }

    private class ExportTask implements Runnable {
        private final Uri uri;
        private final Callback callback;

        ExportTask(Uri uri, Callback callback) {
            this.uri = uri;
            this.callback = callback;
        }

        @Override
        public void run() {
            try {
                ContentResolver resolver = context.getContentResolver();
                try (OutputStreamWriter w = new OutputStreamWriter(resolver.openOutputStream(uri), UTF_8)) {
                    w.write(context.getString(R.string.export_type));
                    w.write(DELIMITER);

                    w.write(context.getString(R.string.export_hard));
                    w.write(DELIMITER);

                    w.write(context.getString(R.string.export_width));
                    w.write(DELIMITER);

                    w.write(context.getString(R.string.export_height));
                    w.write(DELIMITER);

                    w.write(context.getString(R.string.export_time));
                    w.write(DELIMITER);

                    w.write(context.getString(R.string.export_moves));
                    w.write(DELIMITER);

                    w.write(context.getString(R.string.export_tps));
                    w.write('\n');

                    String[] types = context.getResources().getStringArray(R.array.game_types);

                    Map<StatisticsKey, List<StatisticsEntry>> all = StatisticsManager.getInstance(context).getAll();
                    int exportedCount = 0;
                    for (Map.Entry<StatisticsKey, List<StatisticsEntry>> mapEntry : all.entrySet()) {
                        StatisticsKey key = mapEntry.getKey();
                        for (StatisticsEntry entry : mapEntry.getValue()) {
                            w.write(types[key.type]);
                            w.write(DELIMITER);

                            w.write(String.valueOf(key.hard ? 1 : 0));
                            w.write(DELIMITER);

                            w.write(String.valueOf(key.width));
                            w.write(DELIMITER);

                            w.write(String.valueOf(key.height));
                            w.write(DELIMITER);

                            w.write(Tools.timeToString(Constants.TIME_FORMAT_SEC_MS_LONG, entry.time));
                            w.write(DELIMITER);

                            w.write(String.valueOf(entry.moves));
                            w.write(DELIMITER);

                            w.write(Tools.formatFloat(entry.tps));
                            w.write('\n');
                            exportedCount++;
                        }
                    }
                    int total = exportedCount;
                    handler.post(() -> callback.onSuccess(total));
                }
            } catch (Exception e) {
                Logger.e(e, "SessionExporter error:");
                handler.post(callback::onError);
            }
        }
    }
}
