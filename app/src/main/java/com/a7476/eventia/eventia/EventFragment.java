package com.a7476.eventia.eventia;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class EventFragment extends Fragment {


   public EventFragment(){

    }

    String eventId , name= "Tanishq" , category , date , time , description ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      //  LayoutInflater lf= getActivity().getLayoutInflater();
        //View view =  lf.inflate(R.layout.fragment_event, container, false);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        TextView text = (TextView) view.findViewById(R.id.textView);
        text.setText(name);
        return view;

    }
}