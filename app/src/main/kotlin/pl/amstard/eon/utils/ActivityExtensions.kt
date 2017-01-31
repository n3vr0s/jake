package pl.amstard.eon.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.io.Serializable

inline fun <reified T : Activity> Activity.navigateTo() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

inline fun <reified T : Activity> Context.navigateTo() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

inline fun <reified T : Activity> Context.navigateTo(serializable: Serializable) {
    val intent = Intent(this, T::class.java)
    intent.putExtra("argument", serializable)
    startActivity(intent)
}

inline fun <reified T : Activity> Activity.getIntent(context: Context): Intent = Intent(context, T::class.java)

inline fun <reified T : Activity> Context.getIntent(context: Context): Intent = Intent(context, T::class.java)
