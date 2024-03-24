package com.example.ocranalytics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.AmazonTextractClient;
import com.amazonaws.services.textract.model.Block;
import com.amazonaws.services.textract.model.DetectDocumentTextRequest;
import com.amazonaws.services.textract.model.DetectDocumentTextResult;
import com.amazonaws.services.textract.model.Document;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView selectedImageView;
    TextView ocrResultTextView;
    private static final int PICK_IMAGE_REQUEST = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedImageView = findViewById(R.id.selectedImageView);
        ocrResultTextView = findViewById(R.id.ocrResultTextView);
        Button selectImageButton = findViewById(R.id.selectImageButton);

        selectImageButton.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            try {
                // Get bitmap from selected image
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap selectedImageBitmap = BitmapFactory.decodeStream(inputStream);
                selectedImageView.setImageBitmap(selectedImageBitmap);

                Toast.makeText(this, "Processing..", Toast.LENGTH_SHORT).show();
                new PerformOCRAsyncTask().execute(selectedImageBitmap);

            } catch (Exception e) {
                Log.e("EXCEPTION",e.toString());
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class PerformOCRAsyncTask extends AsyncTask<Bitmap, Void, String> {

        @Override
        protected String doInBackground(Bitmap... bitmaps) {
            Bitmap imageBitmap = bitmaps[0];
            try {
                // Convert bitmap to byte array
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                byte[] imageBytes = outputStream.toByteArray();

                // Set up AWS credentials
                String accessKeyId = "";
                String secretAccessKey = "";
                AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);

                // Create an AmazonTextractClient
                AmazonTextractClient textractClient = new AmazonTextractClient(awsCredentials);

                // Create Document object for AWS Textract
                Document document = new Document().withBytes(ByteBuffer.wrap(imageBytes));

                // Perform text detection
                DetectDocumentTextRequest request = new DetectDocumentTextRequest().withDocument(document);
                DetectDocumentTextResult result = textractClient.detectDocumentText(request);

                // Process the result
                StringBuilder stringBuilder = new StringBuilder();
                List<Block> blocks = result.getBlocks();
                for (Block block : blocks) {
                    if ("LINE".equals(block.getBlockType())) {
                        stringBuilder.append(block.getText()).append("\n");
                    }
                }
                return stringBuilder.toString();

            } catch (Exception e) {
                Log.e("EXCEPTION",e.toString());
                return "Error detecting text";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Update UI with OCR result
            ocrResultTextView.setText(result);
        }
    }

}