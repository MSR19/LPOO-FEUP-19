public class AreaXMLOutputter {
    private SumProvider sumProvider;

    AreaXMLOutputter(SumProvider sumProvider) {
        this.sumProvider = sumProvider;
    }

    public String output () {
        return "<area>" + sumProvider.sum() +"</area>";
    }
}
