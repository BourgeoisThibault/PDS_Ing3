package com.esipe.esibankm.esibankm;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by usman on 10/02/2018.
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class AccountsInstrumentedTests {

    @Rule
    public ActivityTestRule<AccountsActivity> rule  = new  ActivityTestRule<AccountsActivity>(AccountsActivity.class)
    {
        @Override
        protected Intent getActivityIntent() {
            InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.putExtra("MYKEY", "Hello");
            return intent;
        }
    };


    @Test
    public void ensureAccountsActivityDataIsDisplayed() throws Exception {
        AccountsActivity activity = rule.getActivity();

        View viewById = activity.findViewById(R.id.textView);

        assertThat(viewById,notNullValue());
        assertThat(viewById, instanceOf(TextView.class));
        TextView textView = (TextView) viewById;
        assertThat(textView.getText().toString(),is("Mes comptes"));
        }



}
