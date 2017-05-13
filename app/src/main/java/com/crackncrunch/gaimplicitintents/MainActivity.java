package com.crackncrunch.gaimplicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareEditText;

    private Button mOpenWebsiteButton;
    private Button mOpenLocationButton;
    private Button mShareTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = (EditText) findViewById(R.id.website_edittext);
        mOpenWebsiteButton = (Button) findViewById(R.id.open_website_button);
        mOpenWebsiteButton.setOnClickListener(this);
        mLocationEditText = (EditText) findViewById(R.id.location_edittext);
        mOpenLocationButton = (Button) findViewById(R.id.open_location_button);
        mOpenLocationButton.setOnClickListener(this);
        mShareEditText = (EditText) findViewById(R.id.share_edittext);
        mShareTextButton = (Button) findViewById(R.id.share_text_button);
        mShareTextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_website_button:
                openWebsite();
                break;
            case R.id.open_location_button:
                openLocation();
                break;
            case R.id.share_text_button:
                shareThis();
                break;
            default:
                break;
        }
    }

    public void openWebsite() {
        String url = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(this.getClass().getSimpleName(), "Can't handle this");
        }
    }

    public void openLocation() {
        String loc = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(this.getClass().getSimpleName(), "Can't handle this");
        }
    }

    public void shareThis() {
        String text = mShareEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(text)
                .startChooser();
    }
}
