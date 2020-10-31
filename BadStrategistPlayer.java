package ai.rock_paper_markov_chain;

import java.util.ArrayList;
import java.util.Random;

class BadStrategistPlayer {

    Item prv = Item.SCISSORS;

    String choose() {
        return Item.values()[new Random().nextInt(Item.values().length)].name(); //almost winner in 75%
        //return Item.values()[0].name(); //almost lose 99% or 100%
        //return itemRingNameGen(); //almost lose ~98.5% or 99%
        //return itemRandomLoserNameGen(); //almost wins ~13%
        //return itemVerifyRandomLoserNameGen(); //almost wins ~40%
        //return itemVerifyRandomParadoxNameGen(); //almost wins ~38%
        //return UUU(); //almost wins ~35.5%
        //return sss(); //almost wins ~45.5%
        //return ssd(); //almost wins ~29-30%
        //return itemVerifyRandomParadoxNameGen(); //almost wins ~38%
    }

    private String itemRingNameGen() {
        Item now = prv;
        prv = prv.losesTo.get(1);
        return now.name();
    }

    private String itemRandomParadoxNameGen() {
        Item now = prv;
        int i = new Random().nextInt(Item.values().length);
        if (!prv.losesTo(Item.values()[i]))
            prv = Item.values()[i];
        return now.name();
    }

    private String itemVerifyRandomParadoxNameGen() {
        Item now = prv;
        int i = new Random().nextInt(Item.values().length);
        if (!prv.losesTo(Item.values()[i]))
            prv = Item.values()[i];
        i = new Random().nextInt(Item.values().length);
        if (!prv.losesTo(Item.values()[i]))
            prv = Item.values()[i];
        i = new Random().nextInt(Item.values().length);
        if (!prv.losesTo(Item.values()[i]))
            prv = Item.values()[i];
        return now.name();

    }

    private String itemVerifyRandomLoserNameGen() {
        Item now = prv;
        int i = new Random().nextInt(Item.values().length);
        if (prv.losesTo(Item.values()[i]))
            prv = Item.values()[i];
        i = new Random().nextInt(Item.values().length);
        if (prv.losesTo(Item.values()[i]))
            prv = Item.values()[i];
        i = new Random().nextInt(Item.values().length);
        if (prv.losesTo(Item.values()[i]))
            prv = Item.values()[i];
        return now.name();
    }

    private String itemRandomLoserNameGen() {
        Item now = prv;
        int i = new Random().nextInt(Item.values().length);
        if (prv.losesTo(Item.values()[i]))
            prv = Item.values()[i];
        i = new Random().nextInt(Item.values().length);
        if (prv.losesTo(Item.values()[i]))
            prv = Item.values()[i];
        i = new Random().nextInt(Item.values().length);
        if (prv.losesTo(Item.values()[i]))
            prv = Item.values()[i];
        return now.name();
    }

    private String UUU() {
        Item now = prv;
        System.out.println("asdf");
        ArrayList<Item> itemList = new ArrayList<>();
        for (Item value : Item.values()) {
            if (value != now)
                itemList.add(value);
        }

        if (new Random().nextBoolean()) {
            int i = new Random().nextInt(itemList.size());
            if (!prv.losesTo(itemList.get(i)))
                prv = Item.values()[i];
            i = new Random().nextInt(itemList.size());
            if (!prv.losesTo(itemList.get(i)))
                prv = Item.values()[i];
            i = new Random().nextInt(itemList.size());
            if (!prv.losesTo(itemList.get(i)))
                prv = Item.values()[i];
        } else {
            int i = new Random().nextInt(itemList.size());
            if (prv.losesTo(itemList.get(i)))
                prv = Item.values()[i];
            i = new Random().nextInt(itemList.size());
            if (prv.losesTo(itemList.get(i)))
                prv = Item.values()[i];
            i = new Random().nextInt(itemList.size());
            if (prv.losesTo(itemList.get(i)))
                prv = Item.values()[i];
        }
        return now.name();
    }

    private String sss() {
        Item now = prv;
        System.out.println("asdfa");
        ArrayList<Item> itemList = new ArrayList<>();
        for (Item value : Item.values()) {
            if (value != now && now.losesTo(value))
                itemList.add(value);
        }

        int i = new Random().nextInt(itemList.size());
        prv = Item.values()[i];
        return now.name();
    }

    private String ssd() {
        Item now = prv;
        System.out.println("");
        ArrayList<Item> itemList = new ArrayList<>();
        Item value1 = Item.values()[new Random().nextInt(Item.values().length)];
        if (new Random().nextBoolean()) {
            int i = prv.ordinal() + 2;
            if (i < Item.values().length - 1) {
                Item value = Item.values()[i];
                itemList.add(value);
                prv = value;
            } else {
                itemList.add(value1);
                prv = value1;
            }
        } else {
            int i = prv.ordinal() - 2;
            if (i > 1) {
                Item value = Item.values()[i];
                itemList.add(value);
                prv = value;
            } else {
                itemList.add(value1);
                prv = value1;
            }
        }
        return now.name();
    }

}
