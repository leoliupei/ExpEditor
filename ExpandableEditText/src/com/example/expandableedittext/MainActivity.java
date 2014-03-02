package com.example.expandableedittext;

import java.net.URI;

import com.example.expandableedittext.R;

import afzkl.development.colorpickerview.dialog.ColorPickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Selection;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	public int color = 0xFF000000;
	public DroidWriterEditText dwEdit;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ToggleButton boldToggle = (ToggleButton) findViewById(R.id.BoldButton);
		ToggleButton italicsToggle = (ToggleButton) findViewById(R.id.ItalicsButton);
		ToggleButton underlinedToggle = (ToggleButton) findViewById(R.id.UnderlineButton);
		ToggleButton leftButton = (ToggleButton) findViewById(R.id.leftButton);
		ToggleButton rigButton = (ToggleButton) findViewById(R.id.rightButton);
		ToggleButton centerButton = (ToggleButton) findViewById(R.id.centerButton);
		final Button colorBtn = (Button) findViewById(R.id.colorBtn);
		Button imageBtn = (Button) findViewById(R.id.ImageButton);

		// View coolButton = findViewById(R.id.CoolButton);
		// View cryButton = findViewById(R.id.CryButton);

		Button clearButton = (Button) findViewById(R.id.ClearButton);

		dwEdit = (DroidWriterEditText) findViewById(R.id.DwEdit);
//		dwEdit.setImageGetter(new Html.ImageGetter() {
//			@Override
//			public Drawable getDrawable(String source) {
//				Drawable drawable = null;
//				
//				Uri dataUri = new Uri
//				
//				try {
//					if (source.equals("smiley_cool.gif")) {
//						drawable = getResources().getDrawable(R.drawable.smiley_cool);
//					} else if (source.equals("smiley_cry.gif")) {
//						drawable = getResources().getDrawable(R.drawable.smiley_cry);
//					} else {
//						drawable = null;
//					}
//					
//					// Important
//					if (drawable != null) {
//						drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
//								drawable.getIntrinsicHeight());
//					}
//				} catch (Exception e) {
//					Log.e("DroidWriterTestActivity", "Failed to load inline image!");
//				}
//				return drawable;
//			}
//		});
		dwEdit.setSingleLine(false);
		dwEdit.setMinLines(10);
		dwEdit.setBoldToggleButton(boldToggle);
		dwEdit.setItalicsToggleButton(italicsToggle);
		dwEdit.setUnderlineToggleButton(underlinedToggle);
		dwEdit.setLeftToggleButton(leftButton);
		dwEdit.setCenterToggleButton(centerButton);
		dwEdit.setRightToggleButton(rigButton);
		dwEdit.setImageInsertButton(imageBtn);
		// dwEdit.setImageInsertButton(coolButton, "smiley_cool.gif");
		// dwEdit.setImageInsertButton(cryButton, "smiley_cry.gif");

		// dwEdit.setClearButton(clearButton);
		clearButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, dwEdit.getTextHTML(),
						Toast.LENGTH_LONG).show();
			}
		});

		colorBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onClickColorPickerDialog(v);
			}
		});
	}

	public void onClickColorPickerDialog(final View v) {

		final ColorPickerDialog colorDialog = new ColorPickerDialog(this, color);

		colorDialog.setAlphaSliderVisible(true);
		colorDialog.setTitle("Pick a Color!");

		colorDialog.setButton(DialogInterface.BUTTON_POSITIVE,
				getString(android.R.string.ok),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(
								MainActivity.this,
								"Selected Color: "
										+ colorToHexString(colorDialog
												.getColor()), Toast.LENGTH_LONG)
								.show();

						// Save the value in our preferences.
						color = colorDialog.getColor();
						v.setBackgroundColor(color);
						dwEdit.colorChange(color);
					}
				});

		colorDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
				getString(android.R.string.cancel),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Nothing to do here.
					}
				});

		colorDialog.show();
	}

	private String colorToHexString(int color) {
		return String.format("#%06X", 0xFFFFFFFF & color);
	}

	private ImageGetter customImageGetter = new Html.ImageGetter() {
		@Override
		public Drawable getDrawable(String source) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	/**
	 * int position = Selection
						.getSelectionStart(DroidWriterEditText.this.getText());

				Spanned e = Html.fromHtml(
						"<img src=\"" + imageResource + "\">", imageGetter,
						null);

				DroidWriterEditText.this.getText().insert(position, e);
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		dwEdit.onResultForResolveRichMedia(requestCode, resultCode, data);
	}

}