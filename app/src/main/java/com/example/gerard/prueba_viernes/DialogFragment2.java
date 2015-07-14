package com.example.gerard.prueba_viernes;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragment2 extends android.app.DialogFragment implements View.OnClickListener{

    static Bundle arguments;

    Button confirmButton;

    TextView textView5;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog2, container, false);


        /*Inicializamos las vistas, al estar dentro de un Fragment, necesitamos de la vista inflada
        para buscar en sus elementos*/

        textView5 = (TextView) v.findViewById(R.id.textView5);
        textView5.setText(arguments.getString("dialog"," "));

        confirmButton = (Button) v.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(this);
        confirmButton.setText(arguments.getString("button"," "));

        return  v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //Cogemos el numero si hay, si no, mostramos un toast pidiendo que lo introduzcan
    @Override
    public void onClick(View view) {

     dismiss();
    }
    //Interfaz por la que nos comunicamos con la Activity
    public static interface OnCompleteListener {
        public abstract void onComplete(String res);
    }

    private OnCompleteListener mListener;

    // Hacemos que la Activity haga de listener
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnCompleteListener)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }




    static DialogFragment2 newInstance(String dialog, String button) {

        DialogFragment2 f = new DialogFragment2();

        arguments = new Bundle();
        arguments.putString("dialog",dialog);
        arguments.putString("button",button);
        f.setArguments(arguments);
        return f;
    }

}
