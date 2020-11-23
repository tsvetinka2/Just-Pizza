package com.example.justpizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView tvQuantity, textMessage;
    EditText names;
    RadioButton onlyPizza, pizzaAdds;
    LinearLayout addList;
    CheckBox mushrooms, corn, cucumber;
    int pizzaQuantity = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvQuantity = findViewById(R.id.tvQuantity);
        textMessage = findViewById(R.id.textMessage);
        names = findViewById(R.id.names);
        onlyPizza = findViewById(R.id.onlyPizza);
        pizzaAdds = findViewById(R.id.pizzaAdds);
        addList = findViewById(R.id.addsList);
        addList.setVisibility(View.GONE);
        mushrooms = findViewById(R.id.mushrooms);
        corn = findViewById(R.id.corn);
        cucumber = findViewById(R.id.cucumbers);

    }
    private void display(int number){
        tvQuantity.setText(" " + number);
    }

    public void submitOrder(View view){

        boolean hasMushrooms = mushrooms.isChecked();
        boolean hasCorn = corn.isChecked();
        boolean hasCucumber = cucumber.isChecked();
        String name = names.getText().toString().trim();
        int price = calculatePrice(hasMushrooms, hasCorn, hasCucumber);

        String priceMessage = createOrderSummary (name,hasMushrooms,hasCorn,hasCucumber,price);
        displayMessage(priceMessage);

    }

    private void displayMessage(String message){
        textMessage.setText(message);
    }

    public void increment(View view){
        if(pizzaQuantity == 100){
            Toast.makeText(this, "Не може да поръчате повече от 100 пици.", Toast.LENGTH_SHORT).show();
        }else{
            pizzaQuantity = pizzaQuantity + 1;
            display(pizzaQuantity);
        }
    }

    public void decrement(View view){
        if(pizzaQuantity<1){
            Toast.makeText(this, "Не може да поръчате по-малко от 1 пица.", Toast.LENGTH_SHORT).show();
        }
        else{
            pizzaQuantity = pizzaQuantity - 1;
            display(pizzaQuantity);
        }
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.onlyPizza:
                if(checked)
                    addList.setVisibility(View.GONE);
                    mushrooms.setChecked(false);
                    corn.setChecked(false);
                    cucumber.setChecked(false);
                break;
            case R.id.pizzaAdds:
                if(checked)
                    addList.setVisibility(View.VISIBLE);
                break;
        }
    }
    private int calculatePrice(boolean hasMashrooms, boolean hasCorn, boolean hasCucumbers){
        int basePrice = 4;

        if(hasMashrooms){
            basePrice = basePrice + 1;
        }

        if(hasCorn){
            basePrice = basePrice + 2;
        }

        if(hasCucumbers){
            basePrice = basePrice + 3;
        }
        return basePrice*pizzaQuantity;
    }

    private String createOrderSummary(String name, boolean hasMushrooms, boolean hasCorn, boolean hasCucumber, int price){
        String priceMessage = getString(R.string.theName) + " " + name;
        priceMessage += "\n" + getString(R.string.withMushrooms) + " " + hasMushrooms;
        priceMessage += "\n" + getString(R.string.withCorn) + " " + hasCorn;
        priceMessage += "\n" + getString(R.string.withCucumber) + " " + hasCucumber;
        priceMessage += "\n" + getString(R.string.quant) + " " + pizzaQuantity;
        priceMessage += "\n" + getString(R.string.value) + " " + NumberFormat.getCurrencyInstance().format(price);
        priceMessage += "\n" + getString(R.string.thank);

        return priceMessage;
    }

}