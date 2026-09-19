package lw01.prelab;

public class ColourPrint extends PrintJob  {
    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int firstPages = Math.min(pages, 10);
        int extraPages = Math.max(pages - 10, 0);
        return firstPages * 1500 + extraPages * 1000 + 2000;
    }

    @Override
    public String label() {
        return "Colour";
    }
}
