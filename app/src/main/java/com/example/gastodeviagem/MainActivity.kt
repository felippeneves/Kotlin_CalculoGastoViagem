package com.example.gastodeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener
{


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializaEventos()
    }

    private fun inicializaEventos()
    {
        btCalcular.setOnClickListener(this)
    }

    override fun onClick(view: View)
    {
        if(view.id == R.id.btCalcular)
        {
            calculate()
        }
    }

    private fun calculate()
    {
        if (validationOK())
        {
            if(validationAutonomyValid())
            {
                try
                {
                    val distance = etDistance.text.toString().toFloat()
                    val price = etPrice.text.toString().toFloat()
                    val autonomy = etAutonomy.text.toString().toFloat()

                    val totalValue = (distance * price) / autonomy

                    tvValor.text = "R$ ${"%.2f".format(totalValue)}"
                }
                catch (nfe : NumberFormatException)
                {
                    Toast.makeText(applicationContext, getString(R.string.informe_valores_validos), Toast.LENGTH_SHORT).show()
                }
            }
            else
                Toast.makeText(applicationContext, getString(R.string.autonomia_diferente_zero), Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(applicationContext, getString(R.string.informe_todos_campos), Toast.LENGTH_SHORT).show()
    }

    private fun validationAutonomyValid(): Boolean = etAutonomy.text.toString() != "0"

    private fun validationOK() : Boolean = etDistance.text.toString().isNotBlank()
                                           && etPrice.text.toString().isNotBlank()
                                           && etAutonomy.text.toString().isNotBlank()

}