package com.anzen.android.examenandroid.view;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.anzen.android.examenandroid.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Icom_JP on 2020-03-13.
 * Description: Pruebas sobre los componentes del login
 */
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    private LoginActivity activity = null;

    @Test
    public void launch() {
        View view = activity.findViewById(R.id.usuarioEt);
        assertNotNull(view);
        view = activity.findViewById(R.id.passwordPv);
        assertNotNull(view);
    }

    @Before
    public void setUp() {
        activity = mActivityRule.getActivity();
    }

    @After
    public void tearDown() {
        activity = null;
    }
}