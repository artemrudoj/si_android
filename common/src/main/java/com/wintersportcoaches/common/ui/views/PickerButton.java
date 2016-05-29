package com.wintersportcoaches.common.ui.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.artem.common.R;

import java.util.List;

/**
 * Created by artem on 29.05.16.
 */
public class PickerButton extends RelativeLayout {
    TextView pickeredTextView;
    AlertDialog pickerDialog;
    List<String> pickedValues;
    final CharSequence[] items = {" Easy "," Medium "," Hard "," Very Hard "};
    public PickerButton(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.picker_button, this);
    }

    public PickerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public PickerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        pickeredTextView = (TextView)findViewById(R.id.name_tv);
        pickeredTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerDialog = createPickedDialog();
                if(pickerDialog != null)
                    pickerDialog.show();
            }
        });
    }

    void setPickedValues(List<String> values) {
        pickedValues = values;
        pickerDialog = createPickedDialog();
    }

    AlertDialog createPickedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select The Difficulty Level");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {


                switch(item)
                {
                    case 0:
                        // Your code when first option seletced
                        break;
                    case 1:
                        // Your code when 2nd  option seletced

                        break;
                    case 2:
                        // Your code when 3rd option seletced
                        break;
                    case 3:
                        // Your code when 4th  option seletced
                        break;

                }
                pickerDialog.dismiss();
            }
        });
        pickerDialog = builder.create();
        return pickerDialog;
    }
}
