package com.hornets.kplanner.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.hornets.kplanner.R;

public class RatePickerFragment extends DialogFragment {

	public Dialog onCreateDialog(Bundle savedInstanceBundle){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		builder.setView(inflater.inflate(R.layout.rate_dialog, null))
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
			}
		})
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				RatePickerFragment.this.getDialog().cancel();
			}
		}).setTitle(R.string.enterRate);      
		return builder.create();
	}
}
