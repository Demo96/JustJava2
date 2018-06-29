package com.example.android.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p/>
 * package com.example.android.justjava;
 */

import java.text.NumberFormat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    public int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox creamView = (CheckBox) findViewById(R.id.creamCheckoBox);
        boolean cream = creamView.isChecked();
        CheckBox chocolateView = (CheckBox) findViewById(R.id.chocolateCheckBox);
        boolean chocolate = chocolateView.isChecked();
        int price = calculatePrice(x, cream, chocolate);
        EditText nameView = (EditText) findViewById(R.id.nameEditTextView);
        String name = nameView.getText().toString();
        String priceMessage = createOrderSummary(name, x, price, cream, chocolate);
        displayMessage(priceMessage);
    }

    public void increment(View view) {
        if (x < 100)
            x++;
        displayQuantity(x);
    }

    public void decrement(View view) {
        if (x > 0)
            x--;
        displayQuantity(x);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayMessage(String mes) {
        TextView widok = (TextView) findViewById(R.id.summary_text_view);
        widok.setText(mes);
    }

    private int calculatePrice(int quantity, boolean c, boolean ch) {
        int price;
        int licznik = 5;
        if (c)
            licznik += 1;
        if (ch)
            licznik += 2;
        price = quantity * licznik;
        return price;
    }

    private String createOrderSummary(String name, int q, int p, boolean c, boolean ch) {
        String summary;
        summary = "Name: " + name;
        if (c)
            summary = summary + "\nAdd whipped cream";
        if (ch)
            summary = summary + "\nAdd chocolate";
        summary = summary + "\nQuantity: " + q;
        summary = summary + "\nTotal: " + p + "$";
        summary = summary + "\nThank you!";
        return summary;
    }

    public void sendMail(View view) {
        String name = ((EditText) findViewById(R.id.nameEditTextView)).getText().toString();
        String bodytext = ((TextView) findViewById(R.id.summary_text_view)).getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, bodytext);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }
}