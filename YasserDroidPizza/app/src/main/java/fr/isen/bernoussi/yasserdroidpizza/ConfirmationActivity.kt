package fr.isen.bernoussi.yasserdroidpizza

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.bernoussi.yasserdroidpizza.databinding.ActivityConfirmationBinding

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationBinding
    private lateinit var sharedPref:SharedPreferences
    private val USER_PREFS = "user_prefs"
    private val USER_NAME = "nom"
    private val USER_SURNAME = "prenom"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)
        binding.textView4.text = sharedPref.getString("nom", "Something went wrong")
        binding.tvPrenomUti.text = sharedPref.getString("prenom", "Something went wrong")

        val bundle = intent.getBundleExtra("bundle")
        if (bundle != null) {
            binding.tvNomAdresse.text = bundle.getString("adresse", "Something went wrong")
            binding.tvPizzaChoisie.text = bundle.getString("spinner", "Something went wrong")
            binding.tvHeureLivree.text = bundle.getString("heure", "Something went wrong")

        }
    }
}