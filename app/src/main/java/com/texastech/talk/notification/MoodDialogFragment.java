package com.texastech.talk.notification;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.texastech.talk.MainActivity;
import com.texastech.talk.R;

public class MoodDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable final Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DarkAlertDialog);
        builder.setTitle("How are you feeling?")
                .setSingleChoiceItems(new CharSequence[]{
                        "Happy", "Sad", "Scared", "Digusted", "Angry", "Depressed"
                }, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Contains index position of chosen item
                    }
                }).setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Save the chosen mood
                dialog.cancel();
            }
        });
        return builder.create();
    }
}