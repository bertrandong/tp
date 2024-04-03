package seedu.address.testutil;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);

    public static final Index INDEX_FIRST_ORDER = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_ORDER = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_ORDER = Index.fromOneBased(3);

    public static final Index INDEX_FIRST_PRODUCT = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PRODUCT = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PRODUCT = Index.fromOneBased(3);

    public static final ArrayList<Index> INDEX_LIST_FIRST_ORDER = listOfFirstOrderIndex();
    public static final ArrayList<Index> INDEX_LIST_SECOND_ORDER = listOfSecondOrderIndex();
    public static final ArrayList<Index> INDEX_LIST_FIRST_SECOND_ORDER = listOfFirstSecondOrderIndex();

    /**
     * Returns an ArrayList containing an {@code Index} for the first Order.
     * @return ArrayList containing an {@code Index} for the first Order.
     */
    public static ArrayList<Index> listOfFirstOrderIndex() {
        ArrayList<Index> arrayList = new ArrayList<>();
        arrayList.add(INDEX_FIRST_ORDER);
        return arrayList;
    }

    /**
     * Returns an ArrayList containing an {@code Index} for the second Order.
     * @return ArrayList containing an {@code Index} for the second Order.
     */
    public static ArrayList<Index> listOfSecondOrderIndex() {
        ArrayList<Index> arrayList = new ArrayList<>();
        arrayList.add(INDEX_SECOND_ORDER);
        return arrayList;
    }

    /**
     * Returns an ArrayList containing an {@code Index} for the first and second Orders.
     * @return ArrayList containing an {@code Index} for the first and second Orders.
     */
    public static ArrayList<Index> listOfFirstSecondOrderIndex() {
        ArrayList<Index> arrayList = new ArrayList<>();
        arrayList.add(INDEX_FIRST_ORDER);
        arrayList.add(INDEX_SECOND_ORDER);
        return arrayList;
    }

}
