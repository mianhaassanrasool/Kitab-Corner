package com.kitaab.corner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import java.util.HashMap;
import java.util.Map;

// One activity for every screen. We just pick which screen_*.xml to show.
// Buttons are wired using the android:tag written in the xml:
//   go:x    -> open screen_x
//   root:x  -> open screen_x and drop the back stack (tabs, login)
//   back    -> close this screen
//   toggle  -> flip selected (hearts, filter chips)
//   pick:on / toggle:on -> same, but starts out selected
//   pick    -> select this one, unselect the others next to it
//   fill:.. -> Sample.java puts dummy items in this container
public class ScreenActivity extends AppCompatActivity {

    static final String EXTRA_SCREEN = "screen";

    // which tab in the bottom bar should be highlighted on which screen
    private static final Map<String, String> tabs = new HashMap<>();

    static {
        tabs.put("home", "home");
        tabs.put("categories", "search");
        tabs.put("search", "search");
        tabs.put("chats", "chats");
        tabs.put("saved", "profile");
        tabs.put("profile", "profile");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String screen = getIntent().getStringExtra(EXTRA_SCREEN);
        if (screen == null) screen = "splash";

        int layoutId = getResources().getIdentifier("screen_" + screen, "layout", getPackageName());
        if (layoutId == 0) {
            screen = "splash";
            layoutId = R.layout.screen_splash;
        }
        setContentView(layoutId);

        View root = findViewById(android.R.id.content);
        Sample.fill(root);
        setClicks(root);
        highlightTab(screen);

        if (screen.equals("splash")) {
            getWindow().setStatusBarColor(getColor(R.color.green));
            getWindow().setNavigationBarColor(getColor(R.color.green));
            WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView())
                    .setAppearanceLightStatusBars(false);
            root.postDelayed(() -> open("login", true), 1600);
        }
    }

    private void highlightTab(String screen) {
        String tab = tabs.get(screen);
        if (tab == null) return;
        int id = getResources().getIdentifier("nav_" + tab, "id", getPackageName());
        View item = findViewById(id);
        if (item != null) item.setSelected(true);
    }

    private void open(String screen, boolean clearStack) {
        Intent intent = new Intent(this, ScreenActivity.class);
        intent.putExtra(EXTRA_SCREEN, screen);
        if (clearStack) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        startActivity(intent);
        if (clearStack) overridePendingTransition(0, 0);
    }

    // goes through every view and attaches the click for its tag
    private void setClicks(View view) {
        if (view.getTag() instanceof String) {
            String tag = (String) view.getTag();
            if (tag.endsWith(":on")) {   // starts out selected
                view.setSelected(true);
                tag = tag.substring(0, tag.length() - 3);
            }
            final String action = tag;
            if (tag.equals("back")) {
                view.setOnClickListener(v -> finish());
            } else if (tag.equals("toggle")) {
                view.setOnClickListener(v -> v.setSelected(!v.isSelected()));
            } else if (tag.equals("pick")) {
                view.setOnClickListener(v -> {
                    ViewGroup row = (ViewGroup) v.getParent();
                    for (int i = 0; i < row.getChildCount(); i++) {
                        row.getChildAt(i).setSelected(false);
                    }
                    v.setSelected(true);
                });
            } else if (tag.startsWith("go:")) {
                view.setOnClickListener(v -> open(action.substring(3), false));
            } else if (tag.startsWith("root:")) {
                view.setOnClickListener(v -> open(action.substring(5), true));
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                setClicks(group.getChildAt(i));
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
