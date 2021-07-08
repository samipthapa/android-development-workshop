package com.example.firstapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.firstapplication.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserDetails extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static UserDetails newInstance(String param1, String param2) {
        UserDetails fragment = new UserDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_details, container, false);

        ImageView back = view.findViewById(R.id.back);
        TextView firstName = view.findViewById(R.id.firstNameDetails);
        TextView lastName = view.findViewById(R.id.lastNameDetails);
        TextView email = view.findViewById(R.id.emailDetails);
        TextView phone = view.findViewById(R.id.phoneDetails);
        TextView gender = view.findViewById(R.id.genderDetails);

        firstName.setText("First Name: " + User.getInstance().getFirstName());
        lastName.setText("Last Name: " + User.getInstance().getLastName());
        email.setText("Email: " + User.getInstance().getEmail());
        phone.setText("Phone Number: " + User.getInstance().getPhoneNumber());
        gender.setText("Gender: " + User.getInstance().getGender());


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_userDetails2_to_userForm);
            }
        });

        return view;
    }
}