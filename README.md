# Kitaab Corner: book marketplace (Task 1: UI and layouts)

Buy and sell pre-loved books locally. Task 1 is the complete look of every screen in XML.

## Run it
1. Android Studio > **Open** > select this `KitaabCorner` folder.
2. Let Gradle sync (internet needed the first time). Gradle 9.4.1, Android Gradle Plugin 9.2.1, JDK from Android Studio.
3. Run on an emulator or phone (Android 8.0+). The app opens on the Login screen.

## What is here
- `res/layout/activity_*.xml`: 20 screens. `item_*.xml`: list rows. `layout_nav_*.xml`: bottom bar (one per tab). `dialog_*.xml` and `bottom_sheet_sort.xml`: dialogs and sheet.
- `res/values`: `strings.xml` (all visible text), `colors.xml` and `values-night/colors.xml` (day and dark palettes), `dimens.xml`, `styles.xml`, `themes.xml`.
- `res/menu`: 6 toolbar menus. `res/drawable`: vector icons and shape drawables.
- `java/.../ui`: one Activity per screen. They only call `setContentView()`; MainActivity opens the Login screen.

## Task 1 limits
Buttons do nothing yet and lists have no adapters. Lists show sample rows only in the Android Studio Layout Editor (`tools:` data). Navigation, adapters and validation are planned for Task 2.
