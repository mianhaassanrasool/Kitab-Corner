package com.kitaab.corner.ui;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

final class Form {

    private Form() {
    }

    static boolean require(TextInputLayout layout, EditText field, int errorRes) {
        boolean empty = field.getText().toString().trim().isEmpty();
        layout.setError(empty ? layout.getContext().getString(errorRes) : null);
        return !empty;
    }
}
