package com.example.minishopmanager

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

data class Product(val name: String, val icon: Int)

class ProductAdapter(context: Context, private val products: List<Product>) :
    ArrayAdapter<Product>(context, 0, products) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView
            ?: LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        val product = products[position]

        view.findViewById<ImageView>(R.id.ivProduct).setImageResource(product.icon)
        view.findViewById<TextView>(R.id.tvProductName).text = product.name

        view.findViewById<Button>(R.id.btnBuy).setOnClickListener {
            Toast.makeText(context, "Produit acheté : ${product.name}", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}

class CatalogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        val listView = findViewById<ListView>(R.id.listViewProducts)

        val products = listOf(
            Product("Téléphone", android.R.drawable.ic_menu_call),
            Product("Casque Bluetooth", android.R.drawable.ic_lock_silent_mode_off),
            Product("Montre connectée", android.R.drawable.ic_menu_compass),
            Product("Chargeur USB", android.R.drawable.ic_lock_idle_charging),
            Product("Accessoires", android.R.drawable.ic_menu_gallery),
            Product("Cosmétiques", android.R.drawable.ic_menu_edit)
        )

        listView.adapter = ProductAdapter(this, products)

        listView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this,
                "Produit sélectionné : ${products[position].name}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}