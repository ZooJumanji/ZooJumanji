package com.example.hb.zoojumanji.ticket;

import com.example.hb.zoojumanji.R;

/**
 * Created by hb on 08/06/2016.
 */
public enum TicketType {
    NORMAL,
    VIP,
    STUDENT,
    CHILD,
    GROUP;

    public int getStringResources() {

        int resource = 0;
        switch (this) {
            case NORMAL :
                resource = R.string.ticket_type_normal;
                break;
            case VIP :
                resource = R.string.ticket_type_vip;
                break;
            case STUDENT :
                resource = R.string.ticket_type_student;
                break;
            case CHILD :
                resource = R.string.ticket_type_child;
                break;
            case GROUP :
                resource = R.string.ticket_type_group;
                break;
        }

        return resource;
    }
}
