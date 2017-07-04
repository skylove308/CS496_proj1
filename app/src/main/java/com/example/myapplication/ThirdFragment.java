package com.example.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {

    List Korean = new ArrayList();
    List English = new ArrayList();
    ArrayAdapter<String> Adapter;



    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        final ArrayAdapter Adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, Korean);
        final ArrayAdapter Adapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, English);

        final ListView listView = (ListView) view.findViewById(R.id.list1);
        listView.setAdapter(Adapter);
        Korean.add("사과");
        Korean.add("당근");
        Korean.add("기차");
        English.add("apple");
        English.add("carrot");
        English.add("train");

                // Create a ListView-specific touch listener. ListViews are given special treatment because
                // by default they handle touches for their list items... i.e. they're in charge of drawing
                // the pressed state (the list selector), handling list item clicks, etc.
                SwipeDismissListViewTouchListener touchListener =
                        new SwipeDismissListViewTouchListener(
                                listView,
                                new SwipeDismissListViewTouchListener.DismissCallbacks() {
                                    @Override
                                    public boolean canDismiss(int position) {
                                        return true;
                                    }

                                    @Override
                                    public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                        for (int position : reverseSortedPositions) {
                                            Adapter.remove(Adapter.getItem(position));
                                        }
                                        Adapter.notifyDataSetChanged();
                                    }
                                });
                listView.setOnTouchListener(touchListener);
                // Setting this scroll listener is required to ensure that during ListView scrolling,
                // we don't look for swipes.
                listView.setOnScrollListener(touchListener.makeScrollListener());
                return view;
            }
            }

