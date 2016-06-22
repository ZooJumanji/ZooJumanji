package com.example.hb.zoojumanji.ticket.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.hb.zoojumanji.ticket.Ticket;
import com.example.hb.zoojumanji.ticket.manager.TicketManager;

import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class TicketService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_CREATE = "com.example.hb.zoojumanji.ticket.service.action.FOO";
    private static final String ACTION_GET_LIST = "com.example.hb.zoojumanji.ticket.service.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.hb.zoojumanji.ticket.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.hb.zoojumanji.ticket.service.extra.PARAM2";

    public TicketService() {
        super("TicketService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionGetTicketList(Context context, String param1, String param2) {
        Intent intent = new Intent(context, TicketService.class);
        intent.setAction(ACTION_GET_LIST);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionCreateTicket(Context context, int ticketId) {
        Intent intent = new Intent(context, TicketService.class);
        intent.setAction(ACTION_CREATE);
        intent.putExtra(EXTRA_PARAM1, ticketId);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("tag", "ServiceStarted");
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_CREATE.equals(action)) {
                final int param1 = intent.getIntExtra(EXTRA_PARAM1, -1);
                //intent.getStringExtra(EXTRA_PARAM1);
                //final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionCreate(param1);
            } else if (ACTION_GET_LIST.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionGetList(param1, param2);
            }
        }
        else {
            //handleActionToast();
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionCreate(int param1) {
        Ticket ticket = TicketManager.getTicket(param1);
        Log.e("tag", "Created " + ticket.getQuantity() + " ticket(s) ID:" + ticket.getId() + " for " + ticket.getPrice()+ "€");
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGetList(String param1, String param2) {
        //Log.e("tag", "Ticket " + param1 + " ticket(s) ID:" + param2);
        List<Repository> repos = GithubRepositoryListSyncService.startSyncLocalRepositoryAction("fpoyer"/*, textView*/);
        StringBuilder builder = new StringBuilder("List of repos online : \n");
        for (Repository repo :
                repos) {
            builder.append(repo.getName()).append('\n');
        }
        Log.e("tag", builder.toString());
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
