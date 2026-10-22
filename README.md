# Kitaab Corner: book marketplace (UI/UX)

Buy and sell pre-loved books locally, in the spirit of OLX. UI is 100% XML.

## Run it
1. Android Studio > **Open** > select this `KitaabCorner` folder.
2. Let Gradle sync (needs internet the first time). It uses Gradle 9.4.1 and Android Gradle Plugin 9.2.1, and runs on the JDK that comes with Android Studio.
3. Pick any emulator or phone (Android 8.0+) and press Run.

## Screens (res/layout/screen_*.xml)
| Flow | Screens |
|---|---|
| Start | splash, login, signup |
| Discover | home, categories, search, filters |
| Buy | book_detail, seller, make_offer, chat, chats, saved |
| Sell | sell, sell_success, my_ads |
| Account | profile, edit_profile, notifications, settings |

Reusable pieces: `view_cover` (typographic book cover), `view_bottom_nav`, and
`item_*` row/card layouts. Colours, text styles and button styles live in `res/values`.

## How navigation works without much code
Layouts carry `android:tag` values that `ScreenActivity.java` reads:
`go:x` opens screen_x, `root:x` opens it with a cleared back stack, `back` closes,
`toggle`/`pick` switch chip and heart states, `fill:...` asks `Sample.java` to
inflate demo books/chats. There is no backend; this submission is the UI/UX layer.
