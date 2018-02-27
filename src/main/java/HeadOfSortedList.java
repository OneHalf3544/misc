class HeadOfSortedList {

    private int value;
    private HeadOfSortedList nextNode;

    HeadOfSortedList(int value, HeadOfSortedList n) {
        // dup ??
        this.value = value;
        nextNode = n;
    }

    HeadOfSortedList setAndReturnNext(HeadOfSortedList n) {
        nextNode = n;
        return this;
    }

    /**
     * Insert value in a proper position of sorted list
     */
    static HeadOfSortedList insertIntoSortedList(HeadOfSortedList listHead, int newValue) {
        if (listHead == null || listHead.value > newValue) {
            // create new node, pointed at the remain part of the list
            return new HeadOfSortedList(newValue, listHead);
        } else {
            return listHead.setAndReturnNext(insertIntoSortedList(listHead.nextNode, newValue));
        }
    }

    /**
     * Merge sorted lists
     */
    static HeadOfSortedList merge(HeadOfSortedList n1, HeadOfSortedList n2) {
        if (n1 == null) {
            return n2;
        }
        if (n2 == null) {
            return n1;
        }
        if (n1.value < n2.value) {
            return new HeadOfSortedList(n1.value, merge(n1.nextNode, n2));
        } else {
            return new HeadOfSortedList(n2.value, merge(n1, n2.nextNode));
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        HeadOfSortedList node = this;
        do {
            sb.append(node.value).append(", ");
            node = node.nextNode;
        } while (node != null);

        sb.setLength(sb.length() - 2);
        return sb.toString();
    }
}
