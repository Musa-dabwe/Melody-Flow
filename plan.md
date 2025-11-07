# Melody Flow - Android App Development

# Plan

**Project:** Melody Flow **Package Name:** com.musa.melodyflow **Target:** Native Android (Kotlin +
XML) **Minimum SDK:** 24 (Android 7.0 Nougat)

## 1. Introduction

This document outlines the complete plan for translating the 3-screen HTML mockup (Playlist,
Now Playing, Settings) into a functional, native Android application. It covers theming,
dependencies, file structure, and a detailed screen-by-screen translation guide for Kotlin and
XML.

## 2. Project Setup & Core Structure

The app will be a Single-Activity application.
● **MainActivity.kt** : The single entry point for the app.
● **activity_main.xml** : Will host a FragmentContainerView for navigation and a
com.google.android.material.bottomnavigation.BottomNavigationView to switch between
the main screens (e.g., Home, Playlists, Settings).
● **Navigation:** We will use the Android Navigation Component to manage Fragment
transactions.

## 3. Dependencies (build.gradle.kts)

Here are the essential dependencies required for this project:
// Core
implementation("androidx.core:core-ktx:1.13.1")
implementation("androidx.appcompat:appcompat:1.7.0")
implementation("androidx.constraintlayout:constraintlayout:2.1.4")
implementation("androidx.fragment:fragment-ktx:1.8.0")

// Material Design
implementation("com.google.android.material:material:1.12.0")

// Navigation Component
implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

// RecyclerView (for lists)
implementation("androidx.recyclerview:recyclerview:1.3.2")

// Preferences (for Settings screen)
implementation("androidx.preference:preference-ktx:1.2.1")


// Image Loading (e.g., Glide or Coil)
implementation("com.github.bumptech.glide:glide:4.16.0")

// Media Playback (Essential for a music player)
implementation("androidx.media3:media3-exoplayer:1.3.1")
implementation("androidx.media3:media3-ui:1.3.1")
implementation("androidx.media3:media3-session:1.3.1")

// For rounded image corners
implementation("com.google.android.material:material:1.12.0") //
(Already listed, but needed for ShapeableImageView)

## 4. Theming & Styling

Based on analysis of all mockups, we will consolidate the inconsistent themes into one unified
brand. The dark theme from playlist.png and now playing.png is the strongest.

### Font

```
● Font: Spline Sans
● Action:
```
1. Download Spline Sans (Regular, Medium, Bold) from Google Fonts.
2. Create res/font/ directory.
3. Add font files (e.g., spline_sans_regular.ttf).
4. Create res/font/spline_sans_font_family.xml:
    <?xml version="1.0" encoding="utf-8"?>
    <font-family
    xmlns:android="[http://schemas.android.com/apk/res/android](h
    ttp://schemas.android.com/apk/res/android)"

```
xmlns:app="[http://schemas.android.com/apk/res-auto](http://s
chemas.android.com/apk/res-auto)">
<font
android:font="@font/spline_sans_regular"
android:fontStyle="normal"
android:fontWeight="400"
app:font="@font/spline_sans_regular"
app:fontStyle="normal"
app:fontWeight="400" />
<font
android:font="@font/spline_sans_medium"
android:fontStyle="normal"
android:fontWeight="500"
app:font="@font/spline_sans_medium"
app:fontStyle="normal"
```

```
app:fontWeight="500" />
<font
android:font="@font/spline_sans_bold"
android:fontStyle="normal"
android:fontWeight="700"
app:font="@font/spline_sans_bold"
app:fontStyle="normal"
app:fontWeight="700" />
</font-family>
```
### Colors (res/values/colors.xml)

<?xml version="1.0" encoding="utf-8"?>
<resources>
<!-- Dark Theme (Primary) -->
<color name="md_dark_primary">#5B9DFF</color>
<color name="md_dark_onPrimary">#FFFFFF</color>
<color name="md_dark_background">#101322</color>
<color name="md_dark_onBackground">#E0E0FF</color>
<color name="md_dark_surface">#1A1D2B</color> <!-- Slightly
lighter for cards -->
<color name="md_dark_onSurface">#E0E0FF</color>
<color name="md_dark_textSecondary">#A0A0B0</color>
<color name="md_dark_icon">#E0E0FF</color>
<color name="md_dark_icon_subtle">#A0A0B0</color>

<!-- Light Theme -->
<color name="md_light_primary">#5B9DFF</color>
<color name="md_light_onPrimary">#FFFFFF</color>
<color name="md_light_background">#F8F8F8</color>
<color name="md_light_onBackground">#101322</color>
<color name="md_light_surface">#FFFFFF</color>
<color name="md_light_onSurface">#101322</color>
<color name="md_light_textSecondary">#505060</color>
<color name="md_light_icon">#101322</color>
<color name="md_light_icon_subtle">#505060</color>

<!-- Settings Screen Specific (Overrides) -->
<color name="settings_accent_red">#E63946</color>
<color name="settings_container_bg_dark">#1D1D1D</color>
<color name="settings_icon_bg_dark">#333333</color>
</resources>

### Themes (res/values/themes.xml)

<resources


xmlns:tools="[http://schemas.android.com/tools](http://schemas.android
.com/tools)">
<!-- Base application theme. -->
<style name="Theme.MelodyFlow"
parent="Theme.Material3.DayNight.NoActionBar">

<!-- Font -->
<item
name="android:fontFamily">@font/spline_sans_font_family</item>
<item name="fontFamily">@font/spline_sans_font_family</item>

<!-- Dark Theme Colors -->
<item name="colorPrimary">@color/md_dark_primary</item>
<item name="colorOnPrimary">@color/md_dark_onPrimary</item>
<item
name="android:colorBackground">@color/md_dark_background</item>
<item
name="colorOnBackground">@color/md_dark_onBackground</item>
<item name="colorSurface">@color/md_dark_surface</item>
<item name="colorOnSurface">@color/md_dark_onSurface</item>
<item
name="android:textColorSecondary">@color/md_dark_textSecondary</item>

<!-- Status Bar -->
<item
name="android:statusBarColor">@color/md_dark_background</item>
<item name="android:windowLightStatusBar"
tools:targetApi="M">false</item>
</style>

<!-- Specific theme for Settings to match mockup -->
<style name="Theme.MelodyFlow.Settings" parent="Theme.MelodyFlow">
<item name="android:colorBackground">#111111</item>
<item
name="colorSurface">@color/settings_container_bg_dark</item>
<item name="colorPrimary">@color/md_dark_onBackground</item>
<!-- White text -->
<item name="colorAccent">@color/settings_accent_red</item>
<item
name="android:textColorSecondary">@color/md_dark_textSecondary</item>
</style>
</resources>

## 5. Icons (Material Symbols to Vector Assets)

We will use the Android Studio "Vector Asset" tool to import these Material Symbols.


```
HTML Icon Name Vector Asset Name Usage
search ic_search Playlist search bar
more_vert ic_more_vert Playlist item menu
add ic_add Playlist FAB
expand_more ic_expand_more Now Playing close
queue_music ic_queue_music Now Playing queue
favorite ic_favorite Now Playing (filled)
skip_previous ic_skip_previous Now Playing control
pause ic_pause Now Playing control
skip_next ic_skip_next Now Playing control
shuffle ic_shuffle Now Playing control
share ic_share Now Playing control
playlist_add ic_playlist_add Now Playing control
repeat ic_repeat Now Playing control
arrow_back ic_arrow_back Settings back
person ic_person Settings
workspace_premium ic_workspace_premium Settings
equalizer ic_equalizer Settings
high_quality ic_high_quality Settings
sync_alt ic_sync_alt Settings
Youtubeed_for ic_search (or similar) Settings (Scan)
help ic_help Settings
privacy_tip ic_privacy_tip Settings
gavel ic_gavel Settings
chevron_right ic_chevron_right Settings item arrow
```
## 6. Screen 1: Playlist Screen

```
● Fragment: PlaylistFragment.kt
● Layout: fragment_playlist.xml
● Item Layout: list_item_playlist.xml
```
### fragment_playlist.xml (Layout Translation)

This translates the HTML structure into a ConstraintLayout with a Toolbar, RecyclerView, and
FloatingActionButton.
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout

xmlns:android="[http://schemas.android.com/apk/res/android](http://sch
emas.android.com/apk/res/android)"

xmlns:app="[http://schemas.android.com/apk/res-auto](http://schemas.an
droid.com/apk/res-auto)"

xmlns:tools="[http://schemas.android.com/tools](http://schemas.android
.com/tools)"


android:layout_width="match_parent"
android:layout_height="match_parent"
android:background="?android:colorBackground"
tools:context=".ui.playlist.PlaylistFragment">

<!-- Top App Bar -->
<com.google.android.material.appbar.MaterialToolbar
android:id="@+id/toolbar"
android:layout_width="0dp"
android:layout_height="wrap_content"
app:layout_constraintTop_toTopOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:title="Playlists"
app:titleCentered="true" />

<!-- Search Bar -->
<com.google.android.material.textfield.TextInputLayout
android:id="@+id/search_bar_layout"
style="@style/Widget.Material3.TextInputLayout.FilledBox"
android:layout_width="0dp"
android:layout_height="wrap_content"
android:layout_marginStart="16dp"
android:layout_marginEnd="16dp"
android:layout_marginTop="8dp"
android:hint="Find a playlist..."
app:boxBackgroundColor="?colorSurface"
app:boxCornerRadiusTopStart="28dp"
app:boxCornerRadiusTopEnd="28dp"
app:boxCornerRadiusBottomStart="28dp"
app:boxCornerRadiusBottomEnd="28dp"
app:boxStrokeWidth="0dp"
app:boxStrokeWidthFocused="0dp"
app:startIconDrawable="@drawable/ic_search"
app:layout_constraintTop_toBottomOf="@id/toolbar"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent">

<com.google.android.material.textfield.TextInputEditText
android:id="@+id/search_bar_edit_text"
android:layout_width="match_parent"
android:layout_height="wrap_content" />
</com.google.android.material.textfield.TextInputLayout>

<!-- Playlist List -->
<androidx.recyclerview.widget.RecyclerView
android:id="@+id/recycler_view_playlists"
android:layout_width="0dp"


android:layout_height="0dp"
android:layout_marginTop="16dp"
android:paddingHorizontal="14dp"

app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
app:layout_constraintTop_toBottomOf="@id/search_bar_layout"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent"
tools:listitem="@layout/list_item_playlist" />

<!-- Floating Action Button -->

<com.google.android.material.floatingactionbutton.FloatingActionButton
android:id="@+id/fab_add_playlist"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_margin="16dp"
android:src="@drawable/ic_add"
android:contentDescription="Add new playlist"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>

### list_item_playlist.xml (Item Layout)

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout

xmlns:android="[http://schemas.android.com/apk/res/android](http://sch
emas.android.com/apk/res/android)"

xmlns:app="[http://schemas.android.com/apk/res-auto](http://schemas.an
droid.com/apk/res-auto)"

xmlns:tools="[http://schemas.android.com/tools](http://schemas.android
.com/tools)"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:padding="8dp">

<!-- Rounded Album Art -->
<com.google.android.material.imageview.ShapeableImageView
android:id="@+id/image_view_album_art"
android:layout_width="56dp"
android:layout_height="56dp"


android:scaleType="centerCrop"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent"
app:layout_constraintBottom_toBottomOf="parent"

app:shapeAppearanceOverlay="@style/ShapeAppearance.Material3.Corner.Me
dium"
tools:src="@tools:sample/backgrounds/scenic" />

<TextView
android:id="@+id/text_view_playlist_name"
android:layout_width="0dp"
android:layout_height="wrap_content"
android:layout_marginStart="16dp"
android:layout_marginEnd="8dp"

android:textAppearance="@style/TextAppearance.Material3.BodyLarge"
android:textColor="?colorOnBackground"
android:maxLines="1"
android:ellipsize="end"
app:layout_constraintStart_toEndOf="@id/image_view_album_art"
app:layout_constraintEnd_toStartOf="@id/button_more"
app:layout_constraintTop_toTopOf="@id/image_view_album_art"
app:layout_constraintBottom_toTopOf="@id/text_view_song_count"
tools:text="Late Night Drives" />

<TextView
android:id="@+id/text_view_song_count"
android:layout_width="0dp"
android:layout_height="wrap_content"

android:textAppearance="@style/TextAppearance.Material3.BodyMedium"
android:textColor="?android:textColorSecondary"

app:layout_constraintStart_toStartOf="@id/text_view_playlist_name"
app:layout_constraintEnd_toEndOf="@id/text_view_playlist_name"

app:layout_constraintTop_toBottomOf="@id/text_view_playlist_name"

app:layout_constraintBottom_toBottomOf="@id/image_view_album_art"
tools:text="32 songs" />

<ImageButton
android:id="@+id/button_more"
android:layout_width="48dp"
android:layout_height="48dp"
android:src="@drawable/ic_more_vert"
android:background="?attr/selectableItemBackgroundBorderless"


android:tint="?android:textColorSecondary"
android:contentDescription="More options"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintTop_toTopOf="parent"
app:layout_constraintBottom_toBottomOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>

### PlaylistFragment.kt (Kotlin Logic)

package com.musa.melodyflow.ui.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.musa.melodyflow.databinding.FragmentPlaylistBinding

class PlaylistFragment : Fragment() {

private var _binding: FragmentPlaylistBinding? = null
private val binding get() = _binding!!

// private lateinit var playlistAdapter: PlaylistAdapter

override fun onCreateView(
inflater: LayoutInflater, container: ViewGroup?,
savedInstanceState: Bundle?
): View {
_binding = FragmentPlaylistBinding.inflate(inflater,
container, false)
return binding.root
}

override fun onViewCreated(view: View, savedInstanceState:
Bundle?) {
super.onViewCreated(view, savedInstanceState)

setupRecyclerView()

binding.fabAddPlaylist.setOnClickListener {
// Handle new playlist creation
}
}

private fun setupRecyclerView() {


// playlistAdapter = PlaylistAdapter()
// binding.recyclerViewPlaylists.adapter = playlistAdapter
// Load data into adapter
// (e.g., viewModel.playlists.observe(viewLifecycleOwner) {
playlists ->
// playlistAdapter.submitList(playlists)
// })
}

override fun onDestroyView() {
super.onDestroyView()
_binding = null
}
}

## 7. Screen 2: Now Playing Screen

```
● Fragment: NowPlayingFragment.kt (Likely presented as a full-screen dialog or navigated
to).
● Layout: fragment_now_playing.xml
● Note: Full media playback requires a MediaBrowserService running in the background.
This plan focuses on the UI translation.
```
### fragment_now_playing.xml (Layout Translation)

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout

xmlns:android="[http://schemas.android.com/apk/res/android](http://sch
emas.android.com/apk/res/android)"

xmlns:app="[http://schemas.android.com/apk/res-auto](http://schemas.an
droid.com/apk/res-auto)"

xmlns:tools="[http://schemas.android.com/tools](http://schemas.android
.com/tools)"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:background="?android:colorBackground">

<!-- Top App Bar -->
<com.google.android.material.appbar.MaterialToolbar
android:id="@+id/toolbar"
android:layout_width="0dp"
android:layout_height="wrap_content"
app:layout_constraintTop_toTopOf="parent"


app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:navigationIcon="@drawable/ic_expand_more"
app:title="NOW PLAYING"
app:titleCentered="true"
app:menu="@menu/now_playing_menu" /> <!-- Menu file with
ic_queue_music -->

<!-- Album Art -->
<com.google.android.material.imageview.ShapeableImageView
android:id="@+id/image_view_album_art"
android:layout_width="0dp"
android:layout_height="0dp"
android:layout_margin="32dp"
android:scaleType="centerCrop"
app:layout_constraintDimensionRatio="1:1"
app:layout_constraintTop_toBottomOf="@id/toolbar"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintBottom_toTopOf="@id/guideline_controls"

app:shapeAppearanceOverlay="@style/ShapeAppearance.Material3.Corner.La
rge"
tools:src="@tools:sample/backgrounds/scenic" />

<!-- Controls Guideline -->
<androidx.constraintlayout.widget.Guideline
android:id="@+id/guideline_controls"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:orientation="horizontal"
app:layout_constraintGuide_percent="0.65" />

<!-- Track Title -->
<TextView
android:id="@+id/text_view_title"
android:layout_width="0dp"
android:layout_height="wrap_content"
android:layout_marginStart="24dp"
android:layout_marginEnd="8dp"

android:textAppearance="@style/TextAppearance.Material3.HeadlineSmall"
android:textColor="?colorOnBackground"
android:fontFamily="@font/spline_sans_bold"
app:layout_constraintTop_toTopOf="@id/guideline_controls"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toStartOf="@id/button_favorite"
tools:text="Bohemian Rhapsody" />


<!-- Artist Name -->
<TextView
android:id="@+id/text_view_artist"
android:layout_width="0dp"
android:layout_height="wrap_content"

android:textAppearance="@style/TextAppearance.Material3.BodyLarge"
android:textColor="?android:textColorSecondary"
app:layout_constraintTop_toBottomOf="@id/text_view_title"
app:layout_constraintStart_toStartOf="@id/text_view_title"
app:layout_constraintEnd_toEndOf="@id/text_view_title"
tools:text="Queen" />

<!-- Favorite Button -->
<ImageButton
android:id="@+id/button_favorite"
android:layout_width="48dp"
android:layout_height="48dp"
android:layout_marginEnd="24dp"
android:background="?attr/selectableItemBackgroundBorderless"
android:src="@drawable/ic_favorite"
app:tint="?colorPrimary"
android:contentDescription="Favorite"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintTop_toTopOf="@id/text_view_title"
app:layout_constraintBottom_toBottomOf="@id/text_view_artist"
/>

<!-- Progress Slider -->
<com.google.android.material.slider.Slider
android:id="@+id/slider_progress"
android:layout_width="0dp"
android:layout_height="wrap_content"
android:layout_marginTop="16dp"
android:layout_marginHorizontal="24dp"
app:layout_constraintTop_toBottomOf="@id/text_view_artist"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent" />

<!-- Timestamps -->
<TextView
android:id="@+id/text_view_current_time"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textColor="?android:textColorSecondary"

android:textAppearance="@style/TextAppearance.Material3.BodySmall"


app:layout_constraintTop_toBottomOf="@id/slider_progress"
app:layout_constraintStart_toStartOf="@id/slider_progress"
tools:text="1:47" />

<TextView
android:id="@+id/text_view_total_time"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textColor="?android:textColorSecondary"

android:textAppearance="@style/TextAppearance.Material3.BodySmall"
app:layout_constraintTop_toBottomOf="@id/slider_progress"
app:layout_constraintEnd_toEndOf="@id/slider_progress"
tools:text="5:55" />

<!-- Primary Controls -->
<ImageButton
android:id="@+id/button_play_pause"
android:layout_width="80dp"
android:layout_height="80dp"
android:background="@drawable/background_play_button"
android:src="@drawable/ic_pause"
app:tint="?colorOnPrimary"
android:scaleType="fitXY"
android:contentDescription="Play/Pause"

app:layout_constraintTop_toBottomOf="@id/text_view_current_time"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintBottom_toTopOf="@id/button_share"
app:layout_constraintVertical_bias="0.4" />

<ImageButton
android:id="@+id/button_previous"
android:layout_width="64dp"
android:layout_height="64dp"
android:src="@drawable/ic_skip_previous"
android:background="?attr/selectableItemBackgroundBorderless"
android:contentDescription="Previous"
app:tint="?colorOnBackground"
app:layout_constraintEnd_toStartOf="@id/button_play_pause"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="@id/button_play_pause"
app:layout_constraintBottom_toBottomOf="@id/button_play_pause"
/>

<ImageButton
android:id="@+id/button_next"


android:layout_width="64dp"
android:layout_height="64dp"
android:src="@drawable/ic_skip_next"
android:background="?attr/selectableItemBackgroundBorderless"
android:contentDescription="Next"
app:tint="?colorOnBackground"
app:layout_constraintStart_toEndOf="@id/button_play_pause"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintTop_toTopOf="@id/button_play_pause"
app:layout_constraintBottom_toBottomOf="@id/button_play_pause"
/>

<!-- Secondary Controls -->
<ImageButton
android:id="@+id/button_shuffle"
android:layout_width="48dp"
android:layout_height="48dp"
android:src="@drawable/ic_shuffle"
android:background="?attr/selectableItemBackgroundBorderless"
android:contentDescription="Shuffle"
app:tint="?colorPrimary"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintEnd_toStartOf="@id/button_share"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintHorizontal_chainStyle="spread_inside"
android:layout_marginBottom="16dp"
android:layout_marginStart="24dp" />

<ImageButton
android:id="@+id/button_share"
android:layout_width="48dp"
android:layout_height="48dp"
android:src="@drawable/ic_share"
android:background="?attr/selectableItemBackgroundBorderless"
android:contentDescription="Share"
app:tint="?android:textColorSecondary"
app:layout_constraintBottom_toBottomOf="@id/button_shuffle"
app:layout_constraintTop_toTopOf="@id/button_shuffle"
app:layout_constraintStart_toEndOf="@id/button_shuffle"

app:layout_constraintEnd_toStartOf="@id/button_add_to_playlist" />

<ImageButton
android:id="@+id/button_add_to_playlist"
android:layout_width="48dp"
android:layout_height="48dp"
android:src="@drawable/ic_playlist_add"
android:background="?attr/selectableItemBackgroundBorderless"


android:contentDescription="Add to playlist"
app:tint="?android:textColorSecondary"
app:layout_constraintBottom_toBottomOf="@id/button_shuffle"
app:layout_constraintTop_toTopOf="@id/button_shuffle"
app:layout_constraintStart_toEndOf="@id/button_share"
app:layout_constraintEnd_toStartOf="@id/button_repeat" />

<ImageButton
android:id="@+id/button_repeat"
android:layout_width="48dp"
android:layout_height="48dp"
android:src="@drawable/ic_repeat"
android:background="?attr/selectableItemBackgroundBorderless"
android:contentDescription="Repeat"
app:tint="?android:textColorSecondary"
app:layout_constraintBottom_toBottomOf="@id/button_shuffle"
app:layout_constraintTop_toTopOf="@id/button_shuffle"

app:layout_constraintStart_toEndOf="@id/button_add_to_playlist"
app:layout_constraintEnd_toEndOf="parent"
android:layout_marginEnd="24dp" />

</androidx.constraintlayout.widget.ConstraintLayout>

### NowPlayingFragment.kt (Kotlin Logic)

package com.musa.melodyflow.ui.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.musa.melodyflow.databinding.FragmentNowPlayingBinding

class NowPlayingFragment : Fragment() {

private var _binding: FragmentNowPlayingBinding? = null
private val binding get() = _binding!!

// private lateinit var mediaBrowser: MediaBrowserCompat
// private var mediaController: MediaControllerCompat? = null

override fun onCreateView(
inflater: LayoutInflater, container: ViewGroup?,
savedInstanceState: Bundle?
): View {


_binding = FragmentNowPlayingBinding.inflate(inflater,
container, false)
return binding.root
}

override fun onViewCreated(view: View, savedInstanceState:
Bundle?) {
super.onViewCreated(view, savedInstanceState)

setupClickListeners()
// connectToMediaService()
}

private fun setupClickListeners() {
binding.buttonPlayPause.setOnClickListener {
// mediaController?.transportControls?.play() or pause()
}

binding.buttonNext.setOnClickListener {
// mediaController?.transportControls?.skipToNext()
}

binding.buttonPrevious.setOnClickListener {
// mediaController?.transportControls?.skipToPrevious()
}
}

// private fun connectToMediaService() {
// // Logic to connect to MediaBrowserService
// }

// private fun updateUI(metadata: MediaMetadataCompat?,
playbackState: PlaybackStateCompat?) {
// // Update title, artist, album art, progress, and
play/pause button state
// }

override fun onDestroyView() {
super.onDestroyView()
_binding = null
}
}

## 8. Screen 3: Settings Screen

```
● Fragment: SettingsFragment.kt
```

```
● Strategy: Use PreferenceFragmentCompat for a native, easy-to-manage settings screen.
● Preference XML: res/xml/root_preferences.xml
```
### res/xml/root_preferences.xml (Settings Layout)

This file defines the entire settings layout, matching the mockup perfectly.
<?xml version="1.0" encoding="utf-8"?>
<PreferenceScreen
xmlns:app="[http://schemas.android.com/apk/res-auto](http://schemas.an
droid.com/apk/res-auto)"

xmlns:android="[http://schemas.android.com/apk/res/android](http://sch
emas.android.com/apk/res/android)">

<!-- Toolbar is handled by the Fragment's layout -->

<PreferenceCategory
app:title="Account"
app:iconSpaceReserved="false">

<Preference
app:key="pref_profile"
app:title="Profile Information"
app:icon="@drawable/ic_person" />
<Preference
app:key="pref_subscription"
app:title="Manage Subscription"
app:icon="@drawable/ic_workspace_premium" />
</PreferenceCategory>

<PreferenceCategory
app:title="Audio"
app:iconSpaceReserved="false">

<Preference
app:key="pref_equalizer"
app:title="Equalizer"
app:icon="@drawable/ic_equalizer" />

<ListPreference
app:key="pref_playback_quality"
app:title="Playback Quality"
app:icon="@drawable/ic_high_quality"
app:summary="Automatic"
app:entries="@array/playback_quality_entries"
app:entryValues="@array/playback_quality_values"
app:defaultValue="auto" />


<SwitchPreferenceCompat
app:key="pref_crossfade"
app:title="Crossfade"
app:icon="@drawable/ic_sync_alt"
app:defaultValue="true" />

<SwitchPreferenceCompat
app:key="pref_gapless"
app:title="Gapless Playback"
app:icon="@drawable/ic_skip_next"
app:defaultValue="true" />
</PreferenceCategory>

<PreferenceCategory
app:title="Device"
app:iconSpaceReserved="false">

<Preference
app:key="pref_scan"
app:title="Scan Music"
app:icon="@drawable/ic_search" />
</PreferenceCategory>

<PreferenceCategory
app:title="About"
app:iconSpaceReserved="false">

<Preference
app:key="pref_help"
app:title="Help & Support"
app:icon="@drawable/ic_help" />

<Preference
app:key="pref_privacy"
app:title="Privacy Policy"
app:icon="@drawable/ic_privacy_tip" />

<Preference
app:key="pref_terms"
app:title="Terms of Service"
app:icon="@drawable/ic_gavel" />
</PreferenceCategory>

<!-- Log Out Button -->
<Preference
app:key="pref_logout"
app:title="Log Out"
app:layout="@layout/preference_logout_button" />


</PreferenceScreen>

_Note: preference_logout_button.xml would be a custom layout for the button, and
@array/playback_quality_entries would be defined in res/values/arrays.xml._

### SettingsFragment.kt (Kotlin Logic)

package com.musa.melodyflow.ui.settings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.snackbar.Snackbar
import com.musa.melodyflow.R

class SettingsFragment : PreferenceFragmentCompat() {

override fun onCreatePreferences(savedInstanceState: Bundle?,
rootKey: String?) {
// Apply the special settings theme
context?.theme?.applyStyle(R.style.Theme_MelodyFlow_Settings,
true)

setPreferencesFromResource(R.xml.root_preferences, rootKey)

// Find and handle Log Out click
val logoutPreference: Preference? =
findPreference("pref_logout")
logoutPreference?.setOnPreferenceClickListener {
// Handle log out logic
view?.let {
Snackbar.make(it, "Logged Out",
Snackbar.LENGTH_SHORT).show()
}
true // Click was handled
}
}
}

## 9. Extensive Link Tree (Resources)

Here are the official documentation links for all major components used in this plan.

● **App Architecture:**
○ Guide to App Architecture (https://developer.android.com/jetpack/guide)

○ Single-Activity Principle (https://developer.android.com/guide/navigation/navigation-principles#single-activity)


● **Views & Layouts:**

○ Material Design 3 Components (https://m3.material.io/?hl=en-US)

○ MaterialToolbar (https://developer.android.com/reference/com/google/android/material/appbar/MaterialToolbar)

○ ConstraintLayout (https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout)

○ RecyclerView (https://developer.android.com/develop/ui/views/layout/recyclerview)

○ FloatingActionButton (FAB) (https://developer.android.com/reference/com/google/android.material/floatingactionbutton/FloatingActionButton&hl=en-US)

○ ShapeableImageView (https://developer.android.com/reference/com/google/android/material/imageview/ShapeableImageView)

○ Material Slider (https://developer.android.com/reference/com/google/android/material/slider/Slider)


● **Navigation:**
○ Navigation Component (https://developer.android.com/guide/navigation)

○ BottomNavigationView (https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView)


● **Settings:**
○ Settings with PreferenceFragmentCompat (https://developer.android.com/develop/ui/views/components/settings)

● **Media Playback (The Hardest Part):**

○ Media3 (Modern MediaPlayback) (https://developer.android.com/media/media3)

○ Media3 MediaBrowserService (https://developer.android.com/media/media3)

○ Building a Media App (Codelab) (https://developer.android.com/media/media3/exoplayer)

● **Theming & Styling:**
○ Material Theming (Colors, Type, Shape) (https://developer.android.com/develop/ui/views/theming/themes)

○ Adding Fonts (XML & Downloadable) (https://developer.android.com/develop/ui/views/text-and-emoji/fonts-in-xml)
