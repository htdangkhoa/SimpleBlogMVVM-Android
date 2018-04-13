package com.github.htdangkhoa.blogmvvmandroid.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import com.github.htdangkhoa.blogmvvmandroid.R;

/**
 * Created by dangkhoa on 4/10/18.
 */

public class Utils {
    public static class Fragment {
        public enum Anim {
            NONE,
            FROM_RIGHT_TO_LEFT,
            FROM_LEFT_TO_RIGHT,
            FROM_BOTTOM_TO_TOP,
            FROM_TOP_TO_BOTTOM
        }

        public final static void addFragment(@NonNull final Context context,
                                             @NonNull android.support.v4.app.Fragment fragment,
                                             boolean canAddBackStrace,
                                             @NonNull @IdRes int layoutId,
                                             Bundle bundle,
                                             Anim anim) {

            FragmentManager fragmentManager = ((FragmentActivity) context)
                    .getSupportFragmentManager();

            FragmentTransaction transaction = fragmentManager.beginTransaction();

            if (bundle != null) fragment.setArguments(bundle);

            if (canAddBackStrace) transaction.addToBackStack(null);

            switch (anim) {
                case FROM_RIGHT_TO_LEFT: {
                    transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                            R.anim.enter_from_left, R.anim.exit_to_right);
                    break;
                }
                case FROM_LEFT_TO_RIGHT: {
                    transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right,
                            R.anim.enter_from_right, R.anim.exit_to_left);
                    break;
                }
                case FROM_BOTTOM_TO_TOP: {
                    transaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top,
                            R.anim.enter_from_top, R.anim.exit_to_bottom);
                    break;
                }
                case FROM_TOP_TO_BOTTOM: {
                    transaction.setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom,
                            R.anim.enter_from_bottom, R.anim.exit_to_top);
                    break;
                }
            }

            transaction.replace(layoutId, fragment).commit();
        }

        public final static void popBackStack(@NonNull final Context context, int numBackStack) {
            FragmentManager fragmentManager = ((FragmentActivity) context)
                    .getSupportFragmentManager();

            int count = fragmentManager.getBackStackEntryCount();
            if (count == 0) {
                Intent homeIntent= new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(homeIntent);

                return;
            }

            for(int i = 0; i < count - numBackStack; i++){
                fragmentManager.popBackStack();
            }
        }

        public final static void popBackStack(@NonNull final Context context) {
            FragmentManager fragmentManager = ((FragmentActivity) context)
                    .getSupportFragmentManager();

            int count = fragmentManager.getBackStackEntryCount();
            if (count == 0) {
                Intent homeIntent= new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(homeIntent);

                return;
            }

            fragmentManager.popBackStack();
        }
    }

    public static AlertDialog showAlertDialog(@NonNull final Context context,
                                              @NonNull String title,
                                              @NonNull String message,
                                              String negative,
                                              String positive,
                                              DialogInterface.OnClickListener negativeCallback,
                                              DialogInterface.OnClickListener positiveCallback) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);

        if (negative != null && negativeCallback != null) {
            builder.setNegativeButton(negative, negativeCallback);
        }

        if (positive != null && positiveCallback != null) {
            builder.setPositiveButton(positive, positiveCallback);
        }

        AlertDialog dialog = builder.create();
        return dialog;
    }
}
