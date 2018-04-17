package fragmentactivity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.android.gms.vision.text.Line;
import com.google.firebase.auth.FirebaseAuth;
import com.upkeep.upkeep.MainFragment;
import com.upkeep.upkeep.MainPage;
import com.upkeep.upkeep.R;

import activities.About_settings;
import activities.Add_currency;
import activities.Add_maintenancecategory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Activity_settingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Activity_settingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Activity_settingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Activity_settingsFragment() {
        // Required empty public constructor
    }

    LinearLayout log_out;
    ProgressDialog pd;
    private PopupWindow mPopupWindow;
    View customView,customviewform,customviewrequestitem,customviewpublicreq,customviewstk,customviewboxaccnt;
    LinearLayout seemore,closed,seemoreform,closeform,files,main_linear,forms,abt_upkeep,hlp_upkeep,request_form,seemorerequest,closerequest,public_req,
    seemorepublicreq,closepublicreq,stacks,seemorestk,closestk,boxaccount,seemoreboxaccount,closeboxaccount,maintenance_ctgry,change_currency,select_cmpany;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Activity_settingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Activity_settingsFragment newInstance(String param1, String param2) {
        Activity_settingsFragment fragment = new Activity_settingsFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_activity_settings, container, false);

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        LayoutInflater inflater2 = (LayoutInflater)getActivity(). getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        customView = inflater2.inflate(R.layout.popup_file, null);
        customviewform=inflater2.inflate(R.layout.popup_templates,null);
        customviewrequestitem=inflater2.inflate(R.layout.popup_requestformitem,null);
        customviewpublicreq=inflater2.inflate(R.layout.popup_publicreq,null);
        customviewstk=inflater2.inflate(R.layout.popup_stack,null);
        customviewboxaccnt=inflater2.inflate(R.layout.popup_boxaccnt,null);

        seemore=(LinearLayout)customView.findViewById(R.id.seevideo1);
        closed=(LinearLayout)customView.findViewById(R.id.clsed);

        seemoreform=(LinearLayout)customviewform.findViewById(R.id.seevideo1);
        closeform=(LinearLayout)customviewform.findViewById(R.id.clsed);

        seemorerequest=(LinearLayout)customviewrequestitem.findViewById(R.id.seevideo1);
        closerequest=(LinearLayout)customviewrequestitem.findViewById(R.id.clsed);

        seemorepublicreq=(LinearLayout)customviewpublicreq.findViewById(R.id.seevideo1);
        closepublicreq=(LinearLayout)customviewpublicreq.findViewById(R.id.clsed);

        seemoreboxaccount=(LinearLayout)customviewboxaccnt.findViewById(R.id.seevideo1);
        closeboxaccount=(LinearLayout)customviewboxaccnt.findViewById(R.id.clsed);

        seemorestk=(LinearLayout)customviewstk.findViewById(R.id.seevideo1);
        closestk=(LinearLayout)customviewstk.findViewById(R.id.clsed);

       // seemoreboxaccount=(LinearLayout)customviewboxaccnt.findViewById(R.id.seevideo1);
        closeboxaccount=(LinearLayout)customviewboxaccnt.findViewById(R.id.clsed);

        main_linear=(LinearLayout)rootView.findViewById(R.id.frame_workers);

        files=(LinearLayout)rootView.findViewById(R.id.file_layout);
        forms=(LinearLayout)rootView.findViewById(R.id.form_templates);

        abt_upkeep=(LinearLayout)rootView.findViewById(R.id.layoutabt);
        hlp_upkeep=(LinearLayout) rootView.findViewById(R.id.layouthlp);
        request_form=(LinearLayout)rootView.findViewById(R.id.request_formitems) ;
        public_req=(LinearLayout)rootView.findViewById(R.id.public_req);
        stacks=(LinearLayout)rootView.findViewById(R.id.stack_activity);
        boxaccount=(LinearLayout)rootView.findViewById(R.id.box_accnt);
        maintenance_ctgry=(LinearLayout)rootView.findViewById(R.id.maintenance_category);
        change_currency=(LinearLayout)rootView.findViewById(R.id.changingcurrency);
        select_cmpany=(LinearLayout)rootView.findViewById(R.id.select_company);

        MainFragment.title_new1.setText("Settings");
        MainFragment.menuitem1.setVisibility(View.GONE);
        MainFragment.spinnerselection.setVisibility(View.GONE);
        MainFragment.filters.setVisibility(View.GONE);
        // MainFragment.tab_barnew.setVisibility(View.GONE);
        MainFragment.sp1.setVisibility(View.VISIBLE);
        MainFragment.linear_one.setVisibility(View.GONE);
        MainFragment.linear_two.setVisibility(View.GONE);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.menuarrow);
        MainFragment.filters.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        MainFragment.filters.setImageBitmap(bitmap);

        MainFragment.menuoption.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.menutoolbar);
        MainFragment.menuoption.setImageBitmap(bitmap1);

        log_out=(LinearLayout)rootView.findViewById(R.id.layoutlogout);

        pd=new ProgressDialog(getActivity());
        pd.setMessage("Sending...");

        hlp_upkeep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.youtube.com/watch?index=1&v=ybK5bcTQ25g&list=PLHcXdz8mnGzaYZjsHMsOAhUjkw_Dv78ua")));
            }
        });

        abt_upkeep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getActivity(),About_settings.class);
                startActivity(in);
            }
        });

        change_currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), Add_currency.class);
                startActivity(in);

            }
        });


        select_cmpany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        maintenance_ctgry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getActivity(), Add_maintenancecategory.class);
                startActivity(in);

            }
        });



        stacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupstk();
            }
        });

        boxaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupboxaccnt();
            }
        });


        public_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopuppublicrequest();
            }
        });

        request_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopuprequest();
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopup();
            }
        });

        forms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayPopupforms();
            }
        });
        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://help.onupkeep.com/getting-started-with-upkeep/getting-your-account-set-up/how-do-i-create-preventative-maintenance-pms-work-orders";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });
        seemorerequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://help.onupkeep.com/getting-started-with-upkeep/how-to-use-upkeep-features/what-are-request-form-items-public-request-portal-and-form-templates";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        closerequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });

        seemoreform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "http://help.onupkeep.com/getting-started-with-upkeep/how-to-use-upkeep-features/how-do-i-use-form-templates-to-create-inspections-and-checklists";
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });

        closeform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });


        seemorepublicreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "http://help.onupkeep.com/getting-started-with-upkeep/how-to-use-upkeep-features/what-are-request-form-items-public-request-portal-and-form-templates";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        closepublicreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });



        seemorestk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "http://help.onupkeep.com/getting-started-with-upkeep/how-to-use-upkeep-features/what-are-request-form-items-public-request-portal-and-form-templates";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });

        closestk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });



       /* seemoreboxaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "http://help.onupkeep.com/getting-started-with-upkeep/how-to-use-upkeep-features/what-are-request-form-items-public-request-portal-and-form-templates";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });*/

        closeboxaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Logout")
                        .setMessage("Are you sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FirebaseAuth.getInstance().signOut();
                                pd.show();
                                Runnable progressRunnable = new Runnable() {

                                    @Override
                                    public void run() {
                                        pd.cancel();
                                        Intent i=new Intent(getActivity(),MainPage.class);
                                        startActivity(i);
                                    }
                                };
                                Handler pdCanceller = new Handler();
                                pdCanceller.postDelayed(progressRunnable, 3000);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

               AlertDialog alert=builder.create();
               alert.show();

                //Toast.makeText(getActivity(),"Logout success",Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }
    public void displayPopuppublicrequest() {

        try {
            System.out.println("inside display popup");
            mPopupWindow = new PopupWindow(customviewpublicreq, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }
            mPopupWindow.setFocusable(true);
            mPopupWindow.setAnimationStyle(R.style.popupAnimation);


            mPopupWindow.showAtLocation(main_linear, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void displayPopupboxaccnt() {

        try {
            System.out.println("inside display popup");
            mPopupWindow = new PopupWindow(customviewboxaccnt, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }
            mPopupWindow.setFocusable(true);
            mPopupWindow.setAnimationStyle(R.style.popupAnimation);


            mPopupWindow.showAtLocation(main_linear, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }











    public void displayPopupstk() {

        try {
            System.out.println("inside display popup");
            mPopupWindow = new PopupWindow(customviewstk, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }
            mPopupWindow.setFocusable(true);
            mPopupWindow.setAnimationStyle(R.style.popupAnimation);


            mPopupWindow.showAtLocation(main_linear, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void displayPopup() {

        try {
            System.out.println("inside display popup");
            mPopupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }
            mPopupWindow.setFocusable(true);
            mPopupWindow.setAnimationStyle(R.style.popupAnimation);


            mPopupWindow.showAtLocation(main_linear, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void displayPopuprequest() {

        try {
            System.out.println("inside display popup");
            mPopupWindow = new PopupWindow(customviewrequestitem, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }
            mPopupWindow.setFocusable(true);
            mPopupWindow.setAnimationStyle(R.style.popupAnimation);


            mPopupWindow.showAtLocation(main_linear, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void displayPopupforms() {

        try {
            System.out.println("inside display popup");
            mPopupWindow = new PopupWindow(customviewform, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }
            mPopupWindow.setFocusable(true);
            mPopupWindow.setAnimationStyle(R.style.popupAnimation);


            mPopupWindow.showAtLocation(main_linear, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


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
