//package com.team12.android_challenge_w6.adapter;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentStatePagerAdapter;
//
//import com.example.retrofitgetkotlin.Fragment.CallApi;
//import com.example.retrofitgetkotlin.Fragment.PostApi;
//import com.example.retrofitgetkotlin.Fragment.ViewPager;
//import com.team12.android_challenge_w6.fragment.NowPlayingMovieFragment;
//import com.team12.android_challenge_w6.fragment.TopRatedMovieFragment;
//
//public class ViewPagerAdapter extends FragmentStatePagerAdapter {
//
//
//    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
//        super(fm, behavior);
//    }
//
//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//                return new NowPlayingMovieFragment(context);
//            case 1:
//                return new TopRatedMovieFragment();
//            default:
//                return new NowPlayingMovieFragment();
//        }
//    }
//
//    @Override
//    public int getCount() {
//        return 2;
//    }
//}
