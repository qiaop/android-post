package com.qiaop.opensource.post;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageDetailFragment extends Fragment {
	
	private String mImageUrl;
	private ImageView mImageView;
	private ProgressBar progressBar;
	private PhotoViewAttacher mAttacher;
	private TextView textTip;
	
	public static ImageDetailFragment newInstance(String imageUrl) {
		final ImageDetailFragment f = new ImageDetailFragment();

		final Bundle args = new Bundle();
		args.putString("url", imageUrl);
		f.setArguments(args);
		return f;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageUrl = getArguments() != null ? getArguments().getString("url") : null;  
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 View v = inflater.inflate(R.layout.fragment_image_detail, container, false);
		mImageView = (ImageView) v.findViewById(R.id.image_browse_image);
		textTip = (TextView) v.findViewById(R.id.image_browse_tip);
		progressBar = (ProgressBar) v.findViewById(R.id.image_browse_loading);
		
		mAttacher = new PhotoViewAttacher(mImageView);
		mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
			
			@Override
			public void onPhotoTap(View arg0, float arg1, float arg2) {
				getActivity().finish();
			}

			@Override
			public void onOutsidePhotoTap() {
				
			}
		});
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
//		Glide.with(getActivity()).load(mImageUrl).listener(new LoadListener<String,GlideDrawable>()).into(mImageView);
		Glide.with(getActivity()).load(mImageUrl).asBitmap().into(new SimpleTarget<Bitmap>() {

			@Override
			public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> arg1) {
				mImageView.setImageBitmap(bitmap);
				mAttacher.update();
				progressBar.setVisibility(View.INVISIBLE);
				
			}
			
			@Override
			public void onLoadFailed(Exception e, Drawable errorDrawable) {
				super.onLoadFailed(e, errorDrawable);
				progressBar.setVisibility(View.INVISIBLE);
				textTip.setText("加载失败");
				textTip.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onLoadStarted(Drawable placeholder) {
				super.onLoadStarted(placeholder);
				progressBar.setVisibility(View.VISIBLE);
			}
			
			
		});
		
	}
	
//	private class LoadListener<T,R> implements RequestListener<T, R>{
//
//		@Override
//		public boolean onException(Exception e, T arg1, Target<R> arg2, boolean arg3) {
//			progressBar.setVisibility(View.INVISIBLE);
//			return false;
//		}
//
//		@Override
//		public boolean onResourceReady(R arg0, T arg1, Target<R> arg2, boolean arg3, boolean arg4) {
//			progressBar.setVisibility(View.INVISIBLE);
//			return false;
//		}
//		
//	}
	
}
