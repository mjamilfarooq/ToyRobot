package org.example.position;

public enum EWNSFacingImpl implements IFacing {
    EAST,
    WEST,
    NORTH,
    SOUTH;

    @Override
    public boolean isValid(String direction) {
        try {
            valueOf(direction);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public IFacing change(String to) {
        if (to.compareToIgnoreCase("LEFT") == 0) return left();
        else if (to.compareToIgnoreCase("RIGHT") == 0 ) return right();
        else return null;
    }

    private IFacing left() {
        EWNSFacingImpl retVal = null;
        switch (this) {
            case EAST:
                retVal = NORTH;
                break;
            case WEST:
                retVal = SOUTH;
                break;
            case NORTH:
                retVal = WEST;
                break;
            case SOUTH:
                retVal = EAST;
                break;
        }
        return retVal;
    }

    private IFacing right() {
        EWNSFacingImpl retVal = null;
        switch (this) {
            case EAST:
                retVal = SOUTH;
                break;
            case WEST:
                retVal = NORTH;
                break;
            case NORTH:
                retVal = EAST;
                break;
            case SOUTH:
                retVal = WEST;
                break;
        }
        return retVal;
    }
}
