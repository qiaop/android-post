package com.qiaop.opensource.post;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.qiaop.opensource.post.utils.BitmapUtils;
import com.qiaop.opensource.post.utils.FileUtils;
import com.qiaop.opensource.post.view.RichEditor;

import java.io.File;

/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class NewPostActivity extends Activity implements View.OnClickListener {

    private static final String PHOTO_FILE_NAME = "take_photo.jpg";
    private EditText textTitle;
    private RichEditor mEditor;

    private LinearLayout btnCamera;
    private LinearLayout btnGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        initView();
    }

    private void initView() {

        textTitle = (EditText) findViewById(R.id.post_text_title);
        mEditor = (RichEditor) findViewById(R.id.post_richEditor);

        btnCamera = (LinearLayout) findViewById(R.id.post_layout_camera);
        btnGallery = (LinearLayout) findViewById(R.id.post_layout_gallery);

        btnCamera.setOnClickListener(this);
        btnGallery.setOnClickListener(this);


        mEditor.setEditorFontSize(20);
        textTitle.setTextSize(20);
        //mEditor.setEditorFontColor(getResources().getColor(R. .font_black));
        // mEditor.setEditorBackgroundColor(Color.BLUE);
        // mEditor.setBackgroundColor(Color.BLUE);
        // mEditor.setBackgroundResource(R.drawable.bg);
        //mEditor.setPadding(12, 10, 10, 10);
        // mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor.setPlaceholder("内容");
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                Log.d("html", text);
            }
        });
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.post_layout_camera:
                camera();
                break;
            case R.id.post_layout_gallery:
                gallery();
                break;
            default:
                break;
        }
    }

    /**
     * 从相册获取
     */
    public void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 9998);
    }

    /**
     * 从相机获取
     */
    public void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/post/",
                            PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, 9999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9998 && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                Log.d("uri", data.getData().toString());
//				String path = saveAndNoUpload(FileUtils.getRealFilePath(this, uri));
//				mEditor.insertImage(path, "");
               String path = saveAndNoUpload(FileUtils.getRealFilePath(this, uri));
                mEditor.insertImage(path, "");//第二个参数为图片描述
            }
        }
        if (requestCode == 9999 && resultCode == RESULT_OK) {
            File file = new File(Environment.getExternalStorageDirectory() + "/post/",
                    PHOTO_FILE_NAME);
            String path = saveAndNoUpload(file.getAbsolutePath());
            mEditor.insertImage(path, "");//第二个参数为图片描述
        }
    }

    /**
     * 压缩图片
     */
    private String saveAndNoUpload(String imageUrl) {
        Bitmap bitmap = BitmapUtils.getimage(imageUrl);
        File file = BitmapUtils.saveBitmap(bitmap, "post", System.currentTimeMillis() + ".jpg");
        bitmap.recycle();
        return file.getAbsolutePath();
    }
}
