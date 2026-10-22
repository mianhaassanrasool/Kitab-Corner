package com.kitaab.corner;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// Dummy data so the list screens have something in them. No backend yet.
final class Sample {

    private Sample() {}

    // title, author, price, condition, area, posted
    private static final String[][] bookData = {
            {"Atomic Habits", "James Clear", "Rs 1,200", "LIKE NEW", "Gulberg, Lahore", "2 days ago"},
            {"The Alchemist", "Paulo Coelho", "Rs 650", "GOOD", "Clifton, Karachi", "5 hrs ago"},
            {"Introduction to Algorithms", "Cormen, Leiserson, Rivest, Stein", "Rs 3,400", "GOOD", "F-8, Islamabad", "1 day ago"},
            {"Pride and Prejudice", "Jane Austen", "Rs 450", "FAIR", "Satellite Town, Faisalabad", "3 days ago"},
            {"Thinking, Fast and Slow", "Daniel Kahneman", "Rs 1,500", "LIKE NEW", "DHA, Lahore", "Today"},
            {"Physics Part 1 (FSc)", "Punjab Textbook Board", "Rs 380", "GOOD", "Cantt, Multan", "4 days ago"},
            {"Rich Dad Poor Dad", "Robert Kiyosaki", "Rs 550", "GOOD", "Saddar, Rawalpindi", "1 week ago"},
            {"Dune", "Frank Herbert", "Rs 1,100", "VERY GOOD", "PECHS, Karachi", "2 hrs ago"},
            {"Peer-e-Kamil", "Umera Ahmed", "Rs 900", "GOOD", "Model Town, Lahore", "6 days ago"},
            {"Database System Concepts", "Silberschatz, Korth", "Rs 2,800", "GOOD", "University Town, Peshawar", "Yesterday"},
    };

    private static final int[] coverColors = {
            0xFF1F5C4D, 0xFFB5502F, 0xFF27374D, 0xFF8C2F39, 0xFF5B3A4A,
            0xFF4A5A63, 0xFFB08424, 0xFF6B6B2F, 0xFF2F5A3A, 0xFF3C3C5C,
    };

    // initials, name, book, last msg, time, unread
    private static final String[][] chatData = {
            {"AK", "Ayesha Khan", "Atomic Habits", "1,100 and it's yours. I'm near Liberty Market most evenings.", "4:12 pm", "2"},
            {"HT", "Hamza Tariq", "Introduction to Algorithms", "Is the 3rd edition? Mine needs the 4th.", "1:40 pm", "1"},
            {"SM", "Sana Malik", "Pride and Prejudice", "Thank you! Reached home, the book is lovely.", "Yesterday", ""},
            {"BA", "Bilal Ahmed", "Dune", "Can you do 1,000 if I pick it up today?", "Yesterday", ""},
            {"ZR", "Zainab Riaz", "Peer-e-Kamil", "Sure, Monday works for me.", "Mon", ""},
            {"UA", "Usman Ali", "Database System Concepts", "You: Is the CD still with the book?", "Sun", ""},
    };

    private static final int[] avatarColors = {
            0xFFC2552F, 0xFF27374D, 0xFF5B3A4A, 0xFF1F5C4D, 0xFFB08424, 0xFF4A5A63,
    };

    // type (0 offer, 1 message, 2 price drop, 3 approved), text, time, unread?
    private static final String[][] notifData = {
            {"0", "Ayesha countered your offer on Atomic Habits: Rs 1,100.", "12 min ago", "1"},
            {"2", "Thinking, Fast and Slow dropped from Rs 1,700 to Rs 1,500.", "1 hr ago", "1"},
            {"1", "Hamza sent you a message about Introduction to Algorithms.", "3 hrs ago", "0"},
            {"3", "Your ad for Dune is now live.", "Yesterday", "0"},
            {"0", "Bilal offered Rs 1,000 for Dune.", "Yesterday", "0"},
            {"3", "Your ad for Peer-e-Kamil was marked as sold. Nice one.", "Sun", "0"},
    };

    // name, stars, text, when
    private static final String[][] reviewData = {
            {"Hamza T.", "★★★★★", "Book was exactly as described. Met at Emporium Mall, very easy to deal with.", "2 weeks ago"},
            {"Sana M.", "★★★★★", "Replied fast and even threw in a bookmark. Would buy from again.", "1 month ago"},
            {"Usman A.", "★★★★☆", "Cover had a small crease that wasn't in the photos, but she knocked off a hundred rupees.", "2 months ago"},
    };

    // shelf name, ad count
    private static final String[][] shelfData = {
            {"Textbooks", "1,240 ads"}, {"Novels", "860 ads"}, {"Urdu adab", "412 ads"},
            {"Self-help", "390 ads"}, {"University", "725 ads"}, {"Islamic", "301 ads"},
            {"Kids", "268 ads"}, {"Comics", "119 ads"},
    };

    private static final int[] shelfBg = {
            R.drawable.bg_cat_green, R.drawable.bg_cat_rust, R.drawable.bg_cat_mustard, R.drawable.bg_cat_grey,
    };

    // finds every container tagged "fill:..." and puts items in it
    static void fill(View v) {
        if (v.getTag() instanceof String && ((String) v.getTag()).startsWith("fill:")
                && v instanceof ViewGroup) {
            addItems((ViewGroup) v, ((String) v.getTag()).substring(5));
        }
        if (v instanceof ViewGroup) {
            ViewGroup g = (ViewGroup) v;
            for (int i = 0; i < g.getChildCount(); i++) fill(g.getChildAt(i));
        }
    }

    // tag looks like  type:count:offset  (count and offset are optional)
    private static void addItems(ViewGroup box, String spec) {
        String[] p = spec.split(":");
        int count = p.length > 1 ? Integer.parseInt(p[1]) : 4;
        int offset = p.length > 2 ? Integer.parseInt(p[2]) : 0;
        LayoutInflater in = LayoutInflater.from(box.getContext());
        Context c = box.getContext();

        // xml has a few <include>s only for the Design preview, remove them
        if (!p[0].equals("cover")) box.removeAllViews();

        switch (p[0]) {
            case "cover":
                showBook(box, offset);
                break;

            case "book_grid":
                for (int i = 0; i < count; i += 2) {
                    LinearLayout row = new LinearLayout(c);
                    row.setOrientation(LinearLayout.HORIZONTAL);
                    for (int j = i; j < Math.min(i + 2, count); j++) {
                        View card = in.inflate(R.layout.item_book_card, row, false);
                        showBook(card, offset + j);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
                                ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                        lp.setMargins(dp(c, 6), dp(c, 6), dp(c, 6), dp(c, 14));
                        row.addView(card, lp);
                    }
                    box.addView(row);
                }
                break;

            case "book_row":
                for (int i = 0; i < count; i++) {
                    View card = in.inflate(R.layout.item_book_card, box, false);
                    showBook(card, offset + i);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dp(c, 140),
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp.setMarginEnd(dp(c, 14));
                    box.addView(card, lp);
                }
                break;

            case "book_list":
            case "saved":
                for (int i = 0; i < count; i++) {
                    View row = in.inflate(R.layout.item_book_row, box, false);
                    showBook(row, offset + i);
                    if (p[0].equals("saved")) row.findViewById(R.id.heart).setSelected(true);
                    box.addView(row);
                }
                break;

            case "my_ads":
                String[] stats = {"128 views  ·  9 chats", "64 views  ·  3 chats", "301 views  ·  14 chats"};
                for (int i = 0; i < count; i++) {
                    View row = in.inflate(R.layout.item_my_ad, box, false);
                    showBook(row, offset + i);
                    text(row, R.id.stats, stats[i % stats.length]);
                    text(row, R.id.status, "ACTIVE");
                    box.addView(row);
                }
                break;

            case "chat_list":
                for (int i = 0; i < Math.min(count, chatData.length); i++) {
                    String[] d = chatData[i];
                    View row = in.inflate(R.layout.item_chat, box, false);
                    text(row, R.id.avatar, d[0]);
                    row.findViewById(R.id.avatar).setBackgroundTintList(ColorStateList.valueOf(avatarColors[i]));
                    text(row, R.id.name, d[1]);
                    text(row, R.id.book, "re: " + d[2]);
                    text(row, R.id.last, d[3]);
                    text(row, R.id.time, d[4]);
                    TextView unread = row.findViewById(R.id.unread);
                    if (d[5].isEmpty()) unread.setVisibility(View.GONE);
                    else unread.setText(d[5]);
                    box.addView(row);
                }
                break;

            case "notif_list":
                int[] icons = {R.drawable.ic_tag, R.drawable.ic_chat, R.drawable.ic_tag, R.drawable.ic_check_circle};
                for (int i = 0; i < Math.min(count, notifData.length); i++) {
                    String[] d = notifData[i];
                    View row = in.inflate(R.layout.item_notification, box, false);
                    ((ImageView) row.findViewById(R.id.icon)).setImageResource(icons[Integer.parseInt(d[0])]);
                    text(row, R.id.text, d[1]);
                    text(row, R.id.time, d[2]);
                    if (d[3].equals("0")) row.findViewById(R.id.dot).setVisibility(View.INVISIBLE);
                    box.addView(row);
                }
                break;

            case "review_list":
                for (int i = 0; i < Math.min(count, reviewData.length); i++) {
                    String[] d = reviewData[i];
                    View row = in.inflate(R.layout.item_review, box, false);
                    text(row, R.id.name, d[0]);
                    text(row, R.id.stars, d[1]);
                    text(row, R.id.text, d[2]);
                    text(row, R.id.time, d[3]);
                    box.addView(row);
                }
                break;

            case "category_row":
                for (String[] s : shelfData) {
                    TextView pill = (TextView) in.inflate(R.layout.item_category_pill, box, false);
                    pill.setText(s[0]);
                    box.addView(pill);
                }
                break;

            case "category_grid":
                for (int i = 0; i < shelfData.length; i += 2) {
                    LinearLayout row = new LinearLayout(c);
                    for (int j = i; j < i + 2; j++) {
                        View card = in.inflate(R.layout.item_category, row, false);
                        card.setBackgroundResource(shelfBg[(j + j / 2) % shelfBg.length]);
                        text(card, R.id.name, shelfData[j][0]);
                        text(card, R.id.count, shelfData[j][1]);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
                                dp(c, 96), 1f);
                        lp.setMargins(dp(c, 6), dp(c, 6), dp(c, 6), dp(c, 6));
                        row.addView(card, lp);
                    }
                    box.addView(row);
                }
                break;

            default:
                break;
        }
    }

    private static void showBook(View item, int index) {
        int i = index % bookData.length;
        String[] b = bookData[i];
        text(item, R.id.title, b[0]);
        text(item, R.id.author, b[1]);
        text(item, R.id.price, b[2]);
        text(item, R.id.condition, b[3]);
        text(item, R.id.city, b[4] + "  ·  " + b[5]);
        text(item, R.id.cover_title, b[0]);
        text(item, R.id.cover_author, b[1].toUpperCase());
        View cover = item.findViewById(R.id.cover);
        if (cover != null) cover.setBackgroundTintList(ColorStateList.valueOf(coverColors[i]));
    }

    private static void text(View root, int id, String s) {
        View v = root.findViewById(id);
        if (v instanceof TextView) ((TextView) v).setText(s);
    }

    private static int dp(Context c, int v) {
        return Math.round(v * c.getResources().getDisplayMetrics().density);
    }
}
