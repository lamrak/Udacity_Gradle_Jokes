package net.validcat.showjokelibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public static String KEY_JOKE = "key_joke";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = null;
        if (getIntent() != null)
            joke = getIntent().getStringExtra(KEY_JOKE);

        if (!TextUtils.isEmpty(joke))
            ((TextView) findViewById(R.id.tv_joke)).setText(joke);

    }
}
