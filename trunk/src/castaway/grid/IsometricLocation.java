package castaway.grid;

/**
 *
 * @author Josh
 */
public class IsometricLocation extends Location{

    private int isoTopTop,isoTopLeft,isoLeftTop,isoLeftLeft;

    public IsometricLocation(int isoTopTop, int isoTopLeft, int isoLeftTop, int isoLeftLeft) {
        super (isoTopTop, isoLeftLeft);
        this.isoTopTop = isoTopTop;
        this.isoTopLeft = isoTopLeft;
        this.isoLeftTop = isoLeftTop;
        this.isoLeftLeft = isoLeftLeft;
    }

    public int getIsoLeftLeft() {
        return isoLeftLeft;
    }

    public int getIsoLeftTop() {
        return isoLeftTop;
    }

    public int getIsoTopLeft() {
        return isoTopLeft;
    }

    public int getIsoTopTop() {
        return isoTopTop;
    }

    public void setIsoLeftLeft(int isoLeftLeft) {
        this.isoLeftLeft = isoLeftLeft;
    }

    public void setIsoLeftTop(int isoLeftTop) {
        this.isoLeftTop = isoLeftTop;
    }

    public void setIsoTopLeft(int isoTopLeft) {
        this.isoTopLeft = isoTopLeft;
    }

    public void setIsoTopTop(int isoTopTop) {
        this.isoTopTop = isoTopTop;
    }

}
