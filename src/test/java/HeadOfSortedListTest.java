import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by onehalf on 11.11.17.
 */
public class HeadOfSortedListTest {

    @Test
    public void should_addValueToEmptyList() {
        HeadOfSortedList headOfSortedList = HeadOfSortedList.insertIntoSortedList(null, 3);
        assertEquals("3", headOfSortedList.toString());
    }

    @Test
    public void should_addValueToListWithOneElement() {
        HeadOfSortedList headOfSortedList = new HeadOfSortedList(3, null);
        headOfSortedList = HeadOfSortedList.insertIntoSortedList(headOfSortedList, 1);
        assertEquals("1, 3", headOfSortedList.toString());
    }

    @Test
    public void should_addValuesToList() {
        HeadOfSortedList sortedList = new HeadOfSortedList(10, null);

        sortedList = HeadOfSortedList.insertIntoSortedList(sortedList, 3);
        sortedList = HeadOfSortedList.insertIntoSortedList(sortedList, 4);
        sortedList = HeadOfSortedList.insertIntoSortedList(sortedList, 1);
        sortedList = HeadOfSortedList.insertIntoSortedList(sortedList, 16);

        assertEquals("1, 3, 4, 10, 16", sortedList.toString());
    }

    @Test
    public void should_mergeLists() {

        HeadOfSortedList sortedList1 = HeadOfSortedList.insertIntoSortedList(null, 3);
        sortedList1 = HeadOfSortedList.insertIntoSortedList(sortedList1, 4);
        sortedList1 = HeadOfSortedList.insertIntoSortedList(sortedList1, 9);
        sortedList1 = HeadOfSortedList.insertIntoSortedList(sortedList1, 16);

        HeadOfSortedList sortedList2 = HeadOfSortedList.insertIntoSortedList(null, 1);
        sortedList2 = HeadOfSortedList.insertIntoSortedList(sortedList2, 4);
        sortedList2 = HeadOfSortedList.insertIntoSortedList(sortedList2, 8);
        sortedList2 = HeadOfSortedList.insertIntoSortedList(sortedList2, 18);

        assertEquals("1, 3, 4, 4, 8, 9, 16, 18", HeadOfSortedList.merge(sortedList1, sortedList2).toString());
    }
}
