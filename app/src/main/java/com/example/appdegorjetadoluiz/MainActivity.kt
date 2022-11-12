package com.example.appdegorjetadoluiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appdegorjetadoluiz.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calcular.setOnClickListener { calcularGorjeta() }
    }

    private fun calcularGorjeta() {
        val valorEmString = binding.valorDaDespesa.text.toString()
        val valor = valorEmString.toDoubleOrNull()
        if (valor == null) {
            binding.resultadoFinal.text = ""
            return
        }
        val gorjetaEscolhida = binding.tipOptions.checkedRadioButtonId

        val porcentagem = when (gorjetaEscolhida) {

            R.id.quinzepct -> 0.15
            R.id.dezpct -> 0.10
            R.id.cincopct -> 0.5
            else -> 0.0
        }
        var gorjeta = porcentagem * valor
        val arredondamento = binding.arredondamento.isChecked
        if (arredondamento) {
            gorjeta = kotlin.math.ceil(gorjeta)
        }

        val gorjetaFormatada = NumberFormat.getCurrencyInstance().format(gorjeta)
        binding.resultadoFinal.text = getString(R.string.valor_da_gorjeta, gorjetaFormatada)

    }


}