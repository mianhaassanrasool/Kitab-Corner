package com.kitaab.corner.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.kitaab.corner.R;

final class NavBar {

    private NavBar() {
    }

    static void bind(Activity from) {
        go(from, R.id.nav_home, HomeActivity.class);
        go(from, R.id.nav_search, SearchActivity.class);
        go(from, R.id.nav_sell, SellBookActivity.class);
        go(from, R.id.nav_chats, ChatsActivity.class);
        go(from, R.id.nav_profile, ProfileActivity.class);
    }

    private static void go(Activity from, int id, Class<? extends Activity> to) {
        View tab = from.findViewById(id);
        if (tab == null || from.getClass() == to) {
            return;
        }
        tab.setOnClickListener(v -> from.startActivity(new Intent(from, to)));
    }
}
