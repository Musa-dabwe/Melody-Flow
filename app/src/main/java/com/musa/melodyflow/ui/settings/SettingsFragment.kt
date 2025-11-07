package com.musa.melodyflow.ui.settings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.snackbar.Snackbar
import com.musa.melodyflow.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Apply the special settings theme
        context?.theme?.applyStyle(R.style.Theme_MelodyFlow_Settings, true)

        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        // Find and handle Log Out click
        val logoutPreference: Preference? = findPreference("pref_logout")
        logoutPreference?.setOnPreferenceClickListener {
            // Handle log out logic
            view?.let {
                Snackbar.make(it, "Logged Out", Snackbar.LENGTH_SHORT).show()
            }
            true // Click was handled
        }
    }
}
