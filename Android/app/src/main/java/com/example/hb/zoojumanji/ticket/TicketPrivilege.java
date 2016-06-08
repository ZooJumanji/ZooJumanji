package com.example.hb.zoojumanji.ticket;

import com.example.hb.zoojumanji.R;

/**
 * Created by hb on 08/06/2016.
 */
public enum TicketPrivilege {
    FEED_TIMON,
    CARE_ENTRANCE,
    ENCLOSURE_ENTRANCE,
    NURSERY_ENTRANCE;

    public int getStringResource() {

        int resource = 0;
        switch (this) {
            case FEED_TIMON :
                resource = R.string.ticket_privilege_feed_timon;
                break;
            case CARE_ENTRANCE :
                resource = R.string.ticket_privilege_care_entrance;
                break;
            case ENCLOSURE_ENTRANCE :
                resource = R.string.ticket_privilege_enclosure_entrance;
                break;
            case NURSERY_ENTRANCE :
                resource = R.string.ticket_privilege_nursery_entrance;
                break;
        }

        return resource;
    }
}
