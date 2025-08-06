package id.hanifalfaqih.greenbin_fit2025.util

import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.BAKLAVA)
fun setFormattedDateTime(textView: TextView, date: Date?) {
    date?.let {
        val locale = Locale.of("id", "ID")
        val formatter = SimpleDateFormat("dd MMMM yyyy, HH:mm", locale)
        textView.text = formatter.format(date)
    }
}