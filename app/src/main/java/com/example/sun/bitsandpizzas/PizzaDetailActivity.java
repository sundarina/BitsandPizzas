package com.example.sun.bitsandpizzas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

/**
 * PizzaDetailActivity создается прежде всего для вывода названия
 * и изображения пиццы, выбранной пользователем. Для этого мы извлекаем
 * идентификатор выбранной пиццы из интента, запустившего активность,
 * и передаем его PizzaDetailActivity из PizzaMaterialFragment,
 * когда пользователь выбирает один из видов пиццы в RecyclerView.
 */

public class PizzaDetailActivity extends Activity {
    ShareActionProvider shareActionProvider;
    //Константа будет использоваться для передачи идентификатора пиццы в дополнительной информации интента.
    public static final String EXTRA_PIZZANO = "pizzaNo";
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        //Включение кнопки Вверх
        getActionBar().setDisplayHomeAsUpEnabled(true);


        //Вывод подробной информации о пицце - Получить пиццу, выбранную пользователем, из интента.
        int pizzaNo = getIntent().getIntExtra(EXTRA_PIZZANO, 0);
        textView = (TextView) findViewById(R.id.pizza_text);
        textView.setText(Pizza.pizzas[pizzaNo].getName());

        imageView = (ImageView) findViewById(R.id.pizza_image);
        imageView.setImageDrawable(getResources().getDrawable(
                Pizza.pizzas[pizzaNo].getImageResourceId()));
        imageView.setContentDescription(Pizza.pizzas[pizzaNo].getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //Использование названия пиццы в действии Share
        TextView textView = (TextView) findViewById(R.id.pizza_text);
        CharSequence pizzaName = textView.getText();
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, pizzaName);
        //Назначить текст по умолчанию для действия Share.
        shareActionProvider.setShareIntent(intent);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_order:
                //Запустить OrderActivity, когда пользователь выбирает элемент на панели действий
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
