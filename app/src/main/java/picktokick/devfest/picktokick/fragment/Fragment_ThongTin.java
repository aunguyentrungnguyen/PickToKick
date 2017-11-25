package picktokick.devfest.picktokick.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.adapter.ProfileAdapter;
import picktokick.devfest.picktokick.objects.Constanttt;
import picktokick.devfest.picktokick.objects.Friend;
import picktokick.devfest.picktokick.objects.User;

/**
 * Created by quocb14005xx on 11/24/2017.
 */

public class Fragment_ThongTin extends Fragment implements AdapterView.OnItemClickListener {
    DatabaseReference database;
    User user;
    String mname, linkImg;
    private ListView lvProfile;
    private ProfileAdapter adapter_profile;
    private ArrayList<String> listProfile;
    private EditText edtInputAddFr;
    private boolean boolCLickXemTHu = true;
    private TextView name;
    private CircleImageView anhDaiDien;
    private Button btnLogout;

   /* private String[] getUserByID(final String id) {
        database.child(Constanttt.USERS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                if (user.getIdUser().equals(id)) {
                    mname = user.getTenUser();
                    linkImg = user.getLinkAvataUser();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return new String[]{mname, linkImg};
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongtin, container, false);
        database = FirebaseDatabase.getInstance().getReference();
        lvProfile = (ListView) view.findViewById(R.id.listviewProfile);
        name = (TextView) view.findViewById(R.id.txtName);
        anhDaiDien = (CircleImageView) view.findViewById(R.id.imgAnhDaiDien);
        btnLogout = (Button) view.findViewById(R.id.btnLogOut);

        listProfile = new ArrayList<>();
        listProfile.add("Thêm bạn bè");
        listProfile.add("Lời mời kết bạn");
        listProfile.add("Danh sách bạn bè của tôi");
        listProfile.add("Phản hồi và Đánh giá");
        listProfile.add("Hướng dẫn sử dụng");
        adapter_profile = new ProfileAdapter(getContext(), R.layout.item_profile, listProfile);
        lvProfile.setAdapter(adapter_profile);


        TextView txtId = (TextView) view.findViewById(R.id.txtIdUser);
        txtId.setText("ID facebook :" + getContext().getSharedPreferences(Constanttt.SHARE_REF_LOGIN,
                Context.MODE_PRIVATE).getString(Constanttt.LOGIN_ID, null));
        name.setText(getContext().getSharedPreferences(Constanttt.SHARE_REF_LOGIN,
                Context.MODE_PRIVATE).getString(Constanttt.LOGIN_NAME, null));
        Glide.with(this)
                .load(getContext().getSharedPreferences(Constanttt.SHARE_REF_LOGIN,
                        Context.MODE_PRIVATE).getString(Constanttt.LOGIN_LINK_IMG, null))
                .into(anhDaiDien);

        lvProfile.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                final Dialog dialog0 = new Dialog(getContext());
                dialog0.setTitle("Thêm bạn bè");
                dialog0.setContentView(R.layout.dialog_add_friend);
                dialog0.show();
                //anh xa trong dialog add friend
                edtInputAddFr = (EditText) dialog0.findViewById(R.id.edtInputAddFr);
                Button btnXemThu, btnok, btncancel;
                btncancel = (Button) dialog0.findViewById(R.id.btnCancelAddFr);
                btnok = (Button) dialog0.findViewById(R.id.btnOkAddFr);
                final TextView txttenXemThu = (TextView) dialog0.findViewById(R.id.txtXemThuName);
                final ImageView imgADD = (ImageView) dialog0.findViewById(R.id.imgXemThu);
                btnXemThu = (Button) dialog0.findViewById(R.id.btnXemAvata);


                //btn xem thu avt nguoi ban
                btnXemThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (edtInputAddFr.getText().toString().equals("")) {
                            Toast.makeText(getContext(), "Vui lòng nhập thông tin cần thiết để tìm người bạn!", Toast.LENGTH_SHORT).show();
                        } else {
                           /* String a[] = getUserByID(edtInputAddFr.getText().toString());
                            // Log.e(Constanttt.TAG_APP,"xxxx "+a[1]);
                            txttenXemThu.setText(a[0]);
                            Glide.with(getContext())
                                    .load(a[1])
                                    .into(imgADD);*/
                            //load anh bang glide
                            if (boolCLickXemTHu) {
                                imgADD.setVisibility(View.INVISIBLE);

                                boolCLickXemTHu = false;
                            } else {
                                imgADD.setVisibility(View.GONE);
                                boolCLickXemTHu = true;
                            }
                        }
                    }
                });
                //button cancel ko ket ban
                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog0.dismiss();
                    }
                });
                //button click dong y ket ban
                btnok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String myID = getContext()
                                .getSharedPreferences(Constanttt.SHARE_REF_LOGIN, Context.MODE_PRIVATE).getString(Constanttt.LOGIN_ID, null);
                        String link = getContext()
                                .getSharedPreferences(Constanttt.SHARE_REF_LOGIN, Context.MODE_PRIVATE).getString(Constanttt.LOGIN_LINK_IMG, null);
                        String name = getContext()
                                .getSharedPreferences(Constanttt.SHARE_REF_LOGIN, Context.MODE_PRIVATE).getString(Constanttt.LOGIN_NAME, null);
                        String x = getContext()
                                .getSharedPreferences(Constanttt.SHARE_REF_LOGIN, Context.MODE_PRIVATE).getString(Constanttt.LOGIN_LATITUDE, null);
                        String y = getContext()
                                .getSharedPreferences(Constanttt.SHARE_REF_LOGIN, Context.MODE_PRIVATE).getString(Constanttt.LOGIN_LONGITUDE, null);
                        //A goi B id cua A,,,id A nam trong requestlist cua B

                        database.child(Constanttt.USERS)
                                .child(edtInputAddFr.getText().toString())
                                .child(Constanttt.USERS_listWait)
                                .child("FRIENDS").push().setValue(new Friend(myID, link, name, Double.parseDouble(x), Double.parseDouble(y)));

                    }
                });

                break;
            case 1:
                //danh sách lời mời
                break;
            case 2:
                //danh sách bạn bè
                break;
            case 3:
                Dialog dialog2 = new Dialog(getContext());
                dialog2.setTitle("Thông tin và phản hồi APP");
                dialog2.setContentView(R.layout.dialog_info_app);
                dialog2.show();
                break;
            case 4:
                Dialog dialog3 = new Dialog(getContext());
                dialog3.setTitle("Hướng dẫn sử dụng");
                dialog3.setContentView(R.layout.dialog_tuts);
                dialog3.show();
                break;
        }
    }

}
