package org.linus.base.util.extension

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val KEY_USER_ID = stringPreferencesKey("key_user_id")

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Demo-DataStore")