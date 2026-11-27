package com.winlator.core;

import android.app.AlertDialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
import android.provider.Settings;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import java.io.File;

// shortcut: SEM desktop reflection may break on future OneUI; UiModeManager covers generic desktop mode.
import com.winlator.widget.LogView;

public abstract class DexUtils {
    // Bump on every release so startup.log identifies the running build.
    public static final String FORK_BUILD = "v0.1.0-iso3";
    private static final String SAMSUNG_DEX_FEATURE = "com.samsung.feature.samsung_dex";

    public static boolean isDesktopMode(Context context) {
        UiModeManager uiModeManager = (UiModeManager)context.getSystemService(Context.UI_MODE_SERVICE);
        if (uiModeManager != null && uiModeManager.getCurrentModeType() == Configuration.UI_MODE_TYPE_DESK) return true;
        return isSamsungDexMode(context);
    }

    public static boolean isSamsungDexMode(Context context) {
        try {
            if (context.getPackageManager().hasSystemFeature(SAMSUNG_DEX_FEATURE)) {
                Configuration config = context.getResources().getConfiguration();
                int enabled = Configuration.class.getField("SEM_DESKTOP_MODE_ENABLED").getInt(null);
                if (config.getClass().getField("semDesktopModeEnabled").getInt(config) == enabled) return true;
            }
        }
        catch (Exception ignored) {}
        return false;
    }

    public static boolean hasExternalDisplay(Context context) {
        try {
            DisplayManager displayManager = (DisplayManager)context.getSystemService(Context.DISPLAY_SERVICE);
            if (displayManager == null) return false;
            for (Display display : displayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION)) {
                if (display.getDisplayId() != Display.DEFAULT_DISPLAY) return true;
            }
        }
        catch (Exception ignored) {}
        return false;
    }

    // System pointer speed (-7..7) -> multiplier, so the DeX mouse feel carries into Wine.
    public static float getSystemPointerSpeedFactor(Context context) {
        int speed = 0;
        try {
            speed = Settings.System.getInt(context.getContentResolver(), "pointer_speed", 0);
        }
        catch (Exception ignored) {}
        return Math.max(0.25f, Math.min(2.0f, 1.0f + speed * 0.1f));
    }

    // On DeX, dropdown spinners are hard to scroll with a mouse. Show the
    // choices in a wheel/keyboard-friendly single-choice dialog instead.
    public static void enableDialogModeForAllSpinners(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)root;
            for (int i = 0; i < group.getChildCount(); i++) enableDialogModeForAllSpinners(group.getChildAt(i));
        }
        else if (root instanceof Spinner) {
            final Spinner spinner = (Spinner)root;
            spinner.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    showSpinnerDialog(spinner);
                    return true;
                }
                return false;
            });
            spinner.setOnKeyListener((v, keyCode, event) -> {
                if (event.getAction() == android.view.KeyEvent.ACTION_DOWN
                    && (keyCode == android.view.KeyEvent.KEYCODE_DPAD_CENTER || keyCode == android.view.KeyEvent.KEYCODE_ENTER)) {
                    showSpinnerDialog(spinner);
                    return true;
                }
                return false;
            });
        }
    }

    private static void showSpinnerDialog(final Spinner spinner) {
        final SpinnerAdapter adapter = spinner.getAdapter();
        if (adapter == null) return;
        final int count = adapter.getCount();
        final CharSequence[] items = new CharSequence[count];
        for (int i = 0; i < count; i++) items[i] = String.valueOf(adapter.getItem(i));
        new AlertDialog.Builder(spinner.getContext())
            .setSingleChoiceItems(items, spinner.getSelectedItemPosition(), (dialog, which) -> {
                spinner.setSelection(which);
                dialog.dismiss();
            })
            .show();
    }

    // DeX fork: startup milestones for hang diagnosis (logs.txt may stay empty
    // when the guest never emits output). Written to Documents/WinlatorDeX/.
    public static void appendMarker(Context context, String message) {
        try {
            File dir = LogView.getLogFile().getParentFile();
            if (dir != null && !dir.isDirectory() && !dir.mkdirs()) {
                File extDir = context.getExternalFilesDir(null);
                dir = extDir != null ? extDir : context.getFilesDir();
            }
            if (dir == null) return;
            try (java.io.FileWriter writer = new java.io.FileWriter(new java.io.File(dir, "startup.log"), true)) {
                writer.write(new java.util.Date().toString() + " " + message + "\n");
            }
        }
        catch (Exception ignored) {}
    }
}
