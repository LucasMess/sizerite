package com.sizerite.cs465.sizerite.HomePage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sizerite.cs465.sizerite.MainActivity;
import com.sizerite.cs465.sizerite.R;
import com.sizerite.cs465.sizerite.WardrobeItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WardrobeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WardrobeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WardrobeFragment extends Fragment {


    String[] brands = {
            "Nike",
            "Reebok",
            "RalphLauren",
            "OldNavy",
            "Gap",
            "Gap"
    } ;
    String[] categories = {
            "Shoes",
            "Shoes",
            "Jackets",
            "Jeans",
            "Sweatshirt",
            "TShirts"
    } ;

    String[] sizes = {
            "11",
            "10.5",
            "L",
            "32",
            "M",
            "M"
    };

    private OnFragmentInteractionListener mListener;
    static WardrobeList adapter;
    ListView listView;

    public WardrobeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WardrobeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WardrobeFragment newInstance() {
        WardrobeFragment fragment = new WardrobeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ArrayList<WardrobeItem> items = new ArrayList<>();
        for (int i = 0; i < categories.length; i++){
            WardrobeItem item = new WardrobeItem();
            item.brand = brands[i];
            item.category = categories[i];
            item.size = sizes[i];
            items.add(item);
        }

        if (adapter == null){
            adapter = new WardrobeList(getActivity(), items);
        }
        listView = view.findViewById(R.id.wardrobe_view);
        listView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wardrobe, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void AddItem(WardrobeItem item){
        adapter.add(item);
        adapter.notifyDataSetInvalidated();
        listView.invalidateViews();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
