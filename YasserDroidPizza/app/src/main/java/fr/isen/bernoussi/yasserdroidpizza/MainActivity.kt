package fr.isen.bernoussi.yasserdroidpizza

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.android.material.textfield.TextInputEditText
import fr.isen.bernoussi.yasserdroidpizza.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private val USER_PREFS = "user_prefs"
    private val USER_NAME = "nom"
    private val USER_SURNAME = "prenom"
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            this,
            R.array.pizzas,
            R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val nom = findViewById<TextInputEditText>(R.id.tietNom)
        nom.setText(sharedPreferences.getString(USER_NAME, ""))
        val prenom = findViewById<TextInputEditText>(R.id.tietPrenom)
        prenom.setText(sharedPreferences.getString(USER_SURNAME, ""))
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickTimePicker(view: View) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)
        val tpd =
            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener(function = { view, h, m ->
                val etHerure = binding.etHeure
                etHerure.text = "$h : $m"
            }), hour, minute, false)
        tpd.show()
    }

    fun verifyEntriesAndAct(view: View) {
        if (binding.tietNom.text.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "Veuillez renseigner votre nom  s'il vous plaît",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (binding.tietPrenom.text.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "Veuillez renseigner votre prénom s'il vous plaît",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (binding.tietAdresse.text.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "Veuillez renseigner votre adresse s'il vous plaît",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (binding.tietTelephone.text.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "Veuillez renseigner votre numéro de téléphone s'il vous plaît",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (binding.etHeure.text.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "Veuillez renseigner l'heure s'il vous plaît",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val edit = sharedPreferences.edit()
        edit.putString(USER_NAME, binding.tietNom.text.toString())
        edit.putString(USER_SURNAME, binding.tietPrenom.text.toString())
        edit.apply()

        val adresse = binding.tietAdresse.text.toString()
        val spinner = binding.spinner.selectedItem.toString()
        val heure = binding.etHeure.text.toString()
        val bundle = Bundle();
        bundle.putString("adresse", adresse);
        bundle.putString("spinner", spinner);
        bundle.putString("heure", heure);


        val intent = Intent(

            this,
            ConfirmationActivity::class.java
        )
        intent.putExtra("bundle", bundle)

        startActivity(intent)
    }


}




