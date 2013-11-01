package com.hornets.kplanner.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.hornets.kplanner.R;

public class HourPickerFragment extends DialogFragment {

	public Dialog onCreateDialog(Bundle savedInstanceState){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		//Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		//Inflate and set layout for the dialog
		//Pass null as the parent view because it's going in the dialog layout
		builder.setView(inflater.inflate(R.layout.hourpicker, null))
		//Add action buttons
		 .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int id) {
             }
         })
         .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int id) {
                 HourPickerFragment.this.getDialog().cancel();
             }
         }).setTitle(R.string.pickHours);      
  return builder.create();
	}
	
}
