package com.davidparry.widgets.showcase;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.davidparry.widgets.AnimationListener;
import com.davidparry.widgets.SimonAnimation;
import com.davidparry.widgets.SimonCircle;


/**
 * Copyright 2015 David Parry
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class SimonCircleFragment extends Fragment implements SimonCircle.OnSectionClickListener{

    private OnFragmentInteractionListener mListener;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ThreadLoadingImageFragment.
     */
    public static SimonCircleFragment newInstance() {
        SimonCircleFragment fragment = new SimonCircleFragment();
        return fragment;
    }

    public SimonCircleFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_simon_circle, container, false);
        final SimonAnimation circle = (SimonAnimation)rootView.findViewById(R.id.Circle);
        circle.setOnSectionClickListener(this);

        Button button = (Button)rootView.findViewById(R.id.animate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] sec = {0,2,0,3,0,1,2,3,0,1,1,3,3,2,0,3,3};
                circle.animate(sec, 500);
            }
        });

        circle.setAnimationListener(new AnimationListener() {
            @Override
            public void started() {
                Toast.makeText(getContext(),"Here we GO!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void stopped() {
                Toast.makeText(getContext(),"All Done!",Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
            ((ExampleActivity) activity).onSectionAttached(3);
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent, int i) {
        Toast.makeText(getActivity(),"Clicked Section::"+i,Toast.LENGTH_SHORT).show();
        return true;
    }
}
