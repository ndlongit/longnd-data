package com.structis.vip.client.navigation;

import static com.structis.vip.client.navigation.Action.ACTION_ACCEUIL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class FilArianeHelper {

    private static Map<Action, List<Action>> navigation = new HashMap<Action, List<Action>>();

    static {
        // Fixed
        navigation.put(Action.ACTION_ACCEUIL, Arrays.asList(Action.ACTION_ACCEUIL));
        navigation.put(ACTION_ACCEUIL, Arrays.asList(ACTION_ACCEUIL));

    }

    public static List<ItemFilAriane> createfilArieane(Action action) {
        List<ItemFilAriane> lists = new ArrayList<ItemFilAriane>();
        if (navigation.get(action) != null) {
            // Fil Ariane
            List<Action> listsNav = navigation.get(action);
            for (Action action2 : listsNav) {
                ItemFilAriane ifa = new ItemFilAriane(action2, null);
                lists.add(ifa);
            }
            HistoryHelper.clearItems();
        } else {
            // Tester histoire
            if (HistoryHelper.isEmpty()) {
                HistoryHelper.pushItem(Action.ACTION_ACCEUIL, null);
            }

            // le cas ou Modification produit, visu produit, visu fcg
            Stack<ItemFilAriane> items = HistoryHelper.getItems();
            Stack<ItemFilAriane> itemsTemp = new Stack<ItemFilAriane>();

            while (testContain(action)) {
                items.pop();
            }

            ItemFilAriane item = null;
            do {
                item = items.pop();
                if (navigation.get(item.getAction()) == null) {
                    itemsTemp.push(item);
                } else {
                    // Recharger histoire si on n'est pas dans la configuration
                    items.push(item);
                    List<Action> listsNav = navigation.get(item.getAction());
                    for (Action action2 : listsNav) {
                        ItemFilAriane ifa = null;
                        if (action2 == item.getAction()) {
                            ifa = item;
                        } else {
                            ifa = new ItemFilAriane(action2, null);
                        }
                        lists.add(ifa);
                    }
                }
            } while (!items.isEmpty() && navigation.get(item.getAction()) == null);

            while (!itemsTemp.isEmpty()) {
                ItemFilAriane itemFilAriane = itemsTemp.pop();
                lists.add(itemFilAriane);

                items.push(itemFilAriane);
            }

            lists.add(new ItemFilAriane(action, null));
        }

        return lists;
    }

    private static boolean testContain(Action action) {
        for (ItemFilAriane item : HistoryHelper.getItems()) {
            if (item.getAction() == action) {
                return true;
            }
        }
        return false;
    }
}
