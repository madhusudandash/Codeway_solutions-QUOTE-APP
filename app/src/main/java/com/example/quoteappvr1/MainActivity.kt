import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quoteapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var editTextQuote: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextQuote = findViewById(R.id.editTextQuote)
        val btnAddQuote = findViewById<Button>(R.id.btnAddQuote)
        val btnShareQuote = findViewById<Button>(R.id.btnShareQuote)
        val btnDeleteQuote = findViewById<Button>(R.id.btnDeleteQuote)
        val btnRefresh = findViewById<Button>(R.id.btnRefresh)
        val btnShare = findViewById<Button>(R.id.btnShare)
        val btnExit = findViewById<Button>(R.id.btnExit)

        val quotes = mutableListOf<String>()

        btnAddQuote.setOnClickListener {
            val quote = editTextQuote.text.toString().trim()
            if (quote.isNotEmpty()) {
                quotes.add(quote)
                Toast.makeText(this, "Quote added", Toast.LENGTH_SHORT).show()
                editTextQuote.text.clear()
            } else {
                Toast.makeText(this, "Please enter a quote", Toast.LENGTH_SHORT).show()
            }
        }

        btnShareQuote.setOnClickListener {
            val quote = editTextQuote.text.toString().trim()
            if (quote.isNotEmpty()) {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, quote)
                intent.type = "text/plain"
                startActivity(Intent.createChooser(intent, "Share Quote"))
            } else {
                Toast.makeText(this, "Please enter a quote to share", Toast.LENGTH_SHORT).show()
            }
        }

        btnDeleteQuote.setOnClickListener {
            val quote = editTextQuote.text.toString().trim()
            if (quote.isNotEmpty() && quotes.contains(quote)) {
                quotes.remove(quote)
                Toast.makeText(this, "Quote deleted", Toast.LENGTH_SHORT).show()
                editTextQuote.text.clear()
            } else {
                Toast.makeText(this, "Quote not found", Toast.LENGTH_SHORT).show()
            }
        }

        btnRefresh.setOnClickListener {
            quotes.clear()
            Toast.makeText(this, "Quotes refreshed", Toast.LENGTH_SHORT).show()
        }

        btnShare.setOnClickListener {
            val allQuotes = quotes.joinToString(separator = "\n")
            if (allQuotes.isNotEmpty()) {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, allQuotes)
                intent.type = "text/plain"
                startActivity(Intent.createChooser(intent, "Share All Quotes"))
            } else {
                Toast.makeText(this, "No quotes to share", Toast.LENGTH_SHORT).show()
            }
        }

        btnExit.setOnClickListener {
            finish()
        }
    }
}
