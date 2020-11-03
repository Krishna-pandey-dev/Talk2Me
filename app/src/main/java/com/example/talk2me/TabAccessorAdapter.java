package com.example.talk2me;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAccessorAdapter extends FragmentPagerAdapter
{
    public TabAccessorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch(i)
        {
            case 0:
                ChatsFragment chatFragment = new ChatsFragment();
                return chatFragment;
            case 1:
                GroupFragment groupFragment = new GroupFragment();
                return groupFragment;
            case 2:
                ContactsFragment contactFragment = new ContactsFragment();
                return contactFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {

        switch(position)
        {
            case 0:
                return "Chat";

            case 1:
                return "Group";

            case 2:
                return "Contact";
            case 3:
                return "Requests";

            default:
                return null;
        }

    }
}
