package br.com.igorbag.meuprimeiroappdio

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import android.widget.Button
import android.widget.TextView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //O desafio sera criar uma valor dentro do string.xml
        // E trocar o texto do xml e tornar internacional (Ingles, Espanhol, etc...)

        val add: Button = findViewById(R.id.add)
        val remove: Button = findViewById(R.id.remove)
        val counter: TextView = findViewById(R.id.counter)
        val changeLanguage: Button = findViewById(R.id.change_language)

        add.setOnClickListener {
            var counterNumber = counter.text.toString().toIntOrNull()

            counterNumber?.let {
                counterNumber++
                counter.text = counterNumber.toString()
            }
        }

        remove.setOnClickListener {
            var counterNumber = counter.text.toString().toIntOrNull()

            counterNumber?.let {
                if (counterNumber > 0) {
                    counterNumber--
                    counter.text = counterNumber.toString()

                }
            }
        }

        changeLanguage.setOnClickListener {


            updateLocale(this)
        }
    }

    fun updateLocale(context: Context) {
        val currentLocale = getCurrentLocale(this)
        val currentLanguage = currentLocale.language

        val newLanguage = when (currentLanguage) {
            "pt" -> "en"
            "en" -> "es"
            else -> "pt"
        }

        val locale = Locale(newLanguage)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale)
            config.setLocales(LocaleList(locale))
        } else {
            @Suppress("DEPRECATION")
            config.setLocale(locale)
        }

        context.resources.updateConfiguration(config, context.resources.displayMetrics)

        recreate()
    }

    fun getCurrentLocale(context: Context): Locale {
        context.resources.configuration

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales[0]
        } else {
            @Suppress("DEPRECATION")
            context.resources.configuration.locale
        }
    }
}