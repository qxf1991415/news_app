package com.qxf.newsapp.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qxf.newsapp.R;
import com.qxf.newsapp.base.AppConstant;
import com.qxf.newsapp.base.BaseActivity;
import com.qxf.newsapp.base.SettingActivity;
import com.qxf.newsapp.find.FindFrgment;
import com.qxf.newsapp.frends.FrendsFrgment;
import com.qxf.newsapp.home.HomeFrgment;
import com.qxf.newsapp.news.NewsFrgment;
import com.qxf.newsapp.talk.TalkFrgment;
import com.qxf.newsapp.talk.TalkListFrgment;
import com.qxf.newsapp.utils.GlideCircleTransform;
import com.qxf.newsapp.utils.SPUtils;
import com.qxf.newsapp.widget.MAlertDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final int FRAGMENT_FLAG_A = 0X01;
    private static final int FRAGMENT_FLAG_B = 0X02;
    private static final int FRAGMENT_FLAG_C = 0X03;
    private static final int FRAGMENT_FLAG_D = 0X04;
    private static final int FRAGMENT_FLAG_E = 0X05;
    private static final int TAKE_PHOTO = 10011;
    private static final int CHOOSE_PICTURE = 10012;
    private static final int CROP_SMALL_PICTURE = 10013;

    @BindView(R.id.index_tab_a)
    RadioButton indexTabA;
    @BindView(R.id.index_tab_b)
    RadioButton indexTabB;
    @BindView(R.id.index_tab_c)
    RadioButton indexTabC;
    @BindView(R.id.index_tab_d)
    RadioButton indexTabD;
    @BindView(R.id.index_tab_e)
    RadioButton indexTabE;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    private NewsFrgment fragmentNews;
    private TalkListFrgment fragmentTalkList;
    private FrendsFrgment fragmentFrends;
    private FindFrgment fragmentFind;
    private HomeFrgment fragmentHome;

    private Context context = MainActivity.this;
    private ActionBarDrawerToggle mDrawerToggle;
    private View headerLayout;
    private ImageView userPhoto;
    private TextView userSign;
    private TextView userInfo;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);
        setToolBarMenuOnclick(new mainToolBarMenuClick());
        ButterKnife.bind(this);
        initView1();
        //创建返回键，并实现打开关/闭监听
        drawerToggleListen();
        //mNavigationView点击监听
        navigationViewListen();
    }

    private void drawerToggleListen() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, commonTitleTb, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity.this, "打开了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(MainActivity.this, "关闭了", Toast.LENGTH_SHORT).show();
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initView1() {
        headerLayout = mNavigationView.inflateHeaderView(R.layout.nav_header_layout);
        userPhoto = headerLayout.findViewById(R.id.user_photo);
        userInfo = headerLayout.findViewById(R.id.user_info);
        userSign = headerLayout.findViewById(R.id.user_sign);
        indexTabA.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_A));
        indexTabB.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_B));
        indexTabC.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_C));
        indexTabD.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_D));
        indexTabE.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_E));
        indexTabA.setChecked(true);
        mNavigationView.setCheckedItem(R.id.nav_news);
        userPhoto.setOnClickListener(this);
        initUserPhoto();
    }

    private void initUserPhoto() {
        String path = getExternalCacheDir() + "/CachePhoto";
        String fileName = SPUtils.getInstance().getString(AppConstant.USER_PHOTO_GET, "");
        File file = new File(path, fileName);
        Glide.with(this)
                .load(file)
                .thumbnail(0.1f)
                .placeholder(R.mipmap.ic_launcher)
                .bitmapTransform(new GlideCircleTransform(this))
                .into(userPhoto);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_photo:
                choiceGetPhotoMethod();
                break;
            case R.id.user_info:
                break;
            case R.id.user_sign:
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private void choiceGetPhotoMethod() {
        final MAlertDialog dialog = new MAlertDialog(MainActivity.this, MAlertDialog.NO_BUTTON);
        dialog.setDialogClickListener(new MAlertDialog.DialogClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.alert_dialog_title:
                        takePhotoGet();
                        dialog.dismiss();
                        break;
                    case R.id.alert_dialog_msg:
                        fromPictureGet();
                        dialog.dismiss();
                        break;
                    case R.id.cancle:
                        dialog.dismiss();
                        break;
                    default:
                        break;
                }
            }
        });
        dialog.show();
        dialog.getTvCancle().setText("取消");
        dialog.getMsg().setText("从相册选择图片");
        dialog.getTitle().setText("拍照");
    }

    private void navigationViewListen() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_news:
                        changeTab(FRAGMENT_FLAG_A);
                        setRadioChecked(R.id.nav_news);
                        break;
                    case R.id.nav_talk:
                        changeTab(FRAGMENT_FLAG_B);
                        setRadioChecked(R.id.nav_talk);
                        break;
                    case R.id.nav_frends:
                        changeTab(FRAGMENT_FLAG_C);
                        setRadioChecked(R.id.nav_frends);
                        break;
                    case R.id.nav_find:
                        changeTab(FRAGMENT_FLAG_D);
                        setRadioChecked(R.id.nav_find);
                        break;
                    case R.id.nav_home:
                        changeTab(FRAGMENT_FLAG_E);
                        setRadioChecked(R.id.nav_home);
                        break;
                    case R.id.nav_set:
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void setRadioChecked(@IdRes int ids) {
        indexTabA.setChecked(ids == R.id.nav_news);
        indexTabB.setChecked(ids == R.id.nav_talk);
        indexTabC.setChecked(ids == R.id.nav_frends);
        indexTabD.setChecked(ids == R.id.nav_find);
        indexTabE.setChecked(ids == R.id.nav_home);
    }

    /**
     * 定义RadioButton的选中改变监听
     */
    class OnNaviCheckChangeListener implements CompoundButton.OnCheckedChangeListener {

        private int posttion;

        public OnNaviCheckChangeListener(int posttion) {
            this.posttion = posttion;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                changeTab(posttion);
            }
        }
    }

    /**
     * 切换tab
     */
    private void changeTab(int posttion) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (posttion) {
            case FRAGMENT_FLAG_A:
                mNavigationView.setCheckedItem(R.id.nav_news);
                changeTabToFragmentA(transaction);
                break;
            case FRAGMENT_FLAG_B:
                mNavigationView.setCheckedItem(R.id.nav_talk);
                changeTabToFragmentB(transaction);
                break;
            case FRAGMENT_FLAG_C:
                mNavigationView.setCheckedItem(R.id.nav_frends);
                changeTabToFragmentC(transaction);
                break;
            case FRAGMENT_FLAG_D:
                mNavigationView.setCheckedItem(R.id.nav_find);
                changeTabToFragmentD(transaction);
                break;
            case FRAGMENT_FLAG_E:
                mNavigationView.setCheckedItem(R.id.nav_home);
                changeTabToFragmentE(transaction);
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 跳转到fragmentA
     */
    private void changeTabToFragmentA(FragmentTransaction transaction) {
        setTitle(R.string.tab_a);
        if (fragmentNews == null) {
            fragmentNews = new NewsFrgment();
            transaction.add(R.id.index_content_fl, fragmentNews);
        } else {
            transaction.show(fragmentNews);
        }
    }

    /**
     * 跳转到fragmentB
     */
    private void changeTabToFragmentB(FragmentTransaction transaction) {
        setTitle(R.string.tab_b);
        if (fragmentTalkList == null) {
            fragmentTalkList = new TalkListFrgment();
            transaction.add(R.id.index_content_fl, fragmentTalkList);
        } else {
            transaction.show(fragmentTalkList);
        }

    }

    /**
     * 跳转到fragmentC
     */
    private void changeTabToFragmentC(FragmentTransaction transaction) {
        setTitle(R.string.tab_c);
        if (fragmentFrends == null) {
            fragmentFrends = new FrendsFrgment();
            transaction.add(R.id.index_content_fl, fragmentFrends);
        } else {
            transaction.show(fragmentFrends);
        }
    }

    /**
     * 跳转到fragmentD
     */
    private void changeTabToFragmentD(FragmentTransaction transaction) {
        setTitle(R.string.tab_d);
        if (fragmentFind == null) {
            fragmentFind = new FindFrgment();
            transaction.add(R.id.index_content_fl, fragmentFind);
        } else {
            transaction.show(fragmentFind);
        }
    }

    private void changeTabToFragmentE(FragmentTransaction transaction) {
        setTitle(R.string.tab_e);
        if (fragmentHome == null) {
            fragmentHome = new HomeFrgment();
            transaction.add(R.id.index_content_fl, fragmentHome);
        } else {
            transaction.show(fragmentHome);
        }
    }

    /**
     * 先隐藏掉所有的fragment
     */
    private void hideAllFragment(FragmentTransaction transaction) {
        if (fragmentNews != null && !fragmentNews.isHidden()) {
            transaction.hide(fragmentNews);
        }
        if (fragmentTalkList != null && !fragmentTalkList.isHidden()) {
            transaction.hide(fragmentTalkList);
        }
        if (fragmentFrends != null && !fragmentFrends.isHidden()) {
            transaction.hide(fragmentFrends);
        }
        if (fragmentFind != null && !fragmentFind.isHidden()) {
            transaction.hide(fragmentFind);
        }
        if (fragmentHome != null && !fragmentHome.isHidden()) {
            transaction.hide(fragmentHome);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * 显示menu的icon,通过反射,设置Menu的icon显示.
     */
    @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    class mainToolBarMenuClick implements Toolbar.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_refresh:
                    Toast.makeText(context, "加好友", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_message:
                    Toast.makeText(context, "扫一扫", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void fromPictureGet() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS) != PackageManager.PERMISSION_GRANTED) {
            requestWritePermission();
        } else {
            getPhoto();
        }
    }

    private void requestWritePermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)
                .subscribe(granted -> {
                    if (granted) {
                        getPhoto();
                    } else {
                    }
                });
    }

    private void getPhoto() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PICTURE);
    }

    private void takePhotoGet() {
        String oldName = SPUtils.getInstance().getString(AppConstant.USER_PHOTO);
        if (!"".equals(oldName)) {
            new File(getExternalCacheDir(), oldName).delete();
        }
        String photoName = System.currentTimeMillis() + ".jpg";
        SPUtils.getInstance().put(AppConstant.USER_PHOTO, photoName);
        File userImage = new File(getExternalCacheDir(), photoName);
        try {
            userImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(getApplicationContext(), "com.qxf.newsapp.fileprovider", userImage);
        } else {
            imageUri = Uri.fromFile(userImage);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS) != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission();
        } else {
            startCarma();
        }
    }

    private void startCarma() {
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    private void requestCameraPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)
                .subscribe(granted -> {
                    if (granted) {
                        startCarma();
                    } else {

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PHOTO:
                    startPhotoZoom(imageUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            //Glide要加载bitmap，需要先把bitmap转化为byte，不能直接加载
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] bytes = baos.toByteArray();
            writeToFile(photo);
            Glide.with(MainActivity.this)
                    .load(bytes)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .thumbnail(0.1f)
                    .placeholder(R.mipmap.ic_launcher)
                    .bitmapTransform(new GlideCircleTransform(this))
                    .into(userPhoto);
        }
    }

    private void writeToFile(Bitmap photo) {
        FileOutputStream fileOutputStream = null;
        try {
            String path = getExternalCacheDir() + "/CachePhoto";
            String oldFileName = SPUtils.getInstance().getString(AppConstant.USER_PHOTO_GET);
            if (!"".equals(oldFileName)) {
                new File(path, oldFileName).delete();
            }
            String fileNmae = System.currentTimeMillis() + ".png";
            SPUtils.getInstance().put(AppConstant.USER_PHOTO_GET, fileNmae);
            File file = new File(path, fileNmae);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            photo.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
    }
}
