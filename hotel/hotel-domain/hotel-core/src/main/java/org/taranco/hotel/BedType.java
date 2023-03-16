package org.taranco.hotel;

enum BedType {
    SINGLE(1), DOUBLE(2), TRIPLE(3);

    private final int personAllowed;

    BedType(int personAllowed) {
        this.personAllowed = personAllowed;
    }

    public int getPersonAllowed() {
        return personAllowed;
    }
}
